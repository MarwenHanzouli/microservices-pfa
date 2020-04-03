package com.pfa.microservicecv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pfa.microservicecv.beans.CandidatBean;
import com.pfa.microservicecv.exceptions.BadRequestException;
import com.pfa.microservicecv.exceptions.ResourceNotFoundException;
import com.pfa.microservicecv.models.Cv;
import com.pfa.microservicecv.models.Fichier;
import com.pfa.microservicecv.proxy.CandidatProxy;
import com.pfa.microservicecv.service.CvService;
import com.pfa.microservicecv.service.FileUploadService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cvs")
public class CvController {

    @Autowired
    private CvService cvService;

    @Autowired
    private CandidatProxy candidatProxy;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/add/{idCandidat}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Cv create(@PathVariable(value = "idCandidat") Long id,
                     @RequestParam("cv") String cv,
                     @RequestParam("file") MultipartFile file) throws IOException
    {
        CandidatBean candidatBean=this.candidatProxy.findCandidatById(id);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String type=file.getContentType();
        byte[] fileContent = new byte[0];
        try {
            fileContent = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        Fichier fichier=new Fichier(fileName,type,encodedString);
        ObjectMapper mapper = new ObjectMapper();
        Cv cvObject = mapper.readValue(cv, Cv.class);
        cvObject.setFichier(fichier);
        cvObject.setIdCandidat(id);
        //parser
        ResponseEntity entity= this.fileUploadService.postFile(fileName,fileContent);
        String parse=entity.getBody().toString();
        int status=entity.getStatusCodeValue();
        if(status==200)
        {
            //System.out.println(parse);
            Gson gson= new Gson();
            JsonObject jsonObject=gson.fromJson(parse,JsonObject.class);
            String detailsCv=jsonObject.get("ParsedResults").getAsJsonArray().get(0).getAsJsonObject().get("ParsedText").toString();
            //System.out.println(detailsCv.split("\\n").length);
            cvObject.setCvParser(detailsCv.substring(1,detailsCv.length()-1));
            Date date=new Date();
            Long idd= date.getTime();
            System.out.println(idd);
            //System.out.println(uuid.timestamp());
            cvObject.setId(idd);
            return this.cvService.create(cvObject);
        }
        else
        {
            throw new ResourceNotFoundException("Le parsing de fichier est échoué");
        }
    }
    @GetMapping("/getOne/{id}")
    public Cv getOne(@PathVariable(value = "id") Long id)
    {
        Optional<Cv> cv=this.cvService.findById(id);
        if(!cv.isPresent())
        {
            throw new BadRequestException("Ce CV : "+id+" n'existe pas");
        }
        return cv.get();
    }
    @DeleteMapping("/delete/{idCandidat}/{id}")
    public ResponseEntity<?> deleteCvByCandidat(@PathVariable(value = "idCandidat") Long idCandidat,
                                      @PathVariable(value = "id") Long id)
    {
        CandidatBean candidatBean=this.candidatProxy.findCandidatById(idCandidat);
        return this.cvService.findById(id).map(cv -> {
            this.cvService.delete(cv);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new BadRequestException("Ce CV : "+id+" n'existe pas!!"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCv(@PathVariable(value = "id") Long id)
    {
        return this.cvService.findById(id).map(cv -> {
            this.cvService.delete(cv);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new BadRequestException("Ce CV : "+id+" n'existe pas!!"));
    }
    @GetMapping("/getAll")
    public List<Cv> getAll()
    {
        return this.cvService.findAll();
    }

    @GetMapping("/getAllPaginate")
    public Page<Cv> getAll(Pageable pageable)
    {
        return this.cvService.findAll(pageable);
    }

    @GetMapping("/getByCandidat/{idCandidat}")
    public List<Cv> getAllByCandidat(@PathVariable(value = "idCandidat") Long id)
    {
        return this.cvService.findByIdCandidat(id);
    }
    @GetMapping("/getByCandidatPaginate/{idCandidat}")
    public Page<Cv> getAllByCandidat(@PathVariable(value = "idCandidat") Long id, Pageable pageable)
    {
        return this.cvService.findByIdCandidat(id,pageable);
    }
    @GetMapping("/getByTag/{tag}")
    public List<Cv> getAllByTag(@PathVariable(value = "tag") String tag)
    {
        return this.cvService.findByCvParserUsingCustomQuery(tag);
    }
    @GetMapping("/getByTagPaginate/{tag}")
    public Page<Cv> getAllByTag(@PathVariable(value = "tag") String tag, Pageable pageable)
    {
        return this.cvService.findByCvParserUsingCustomQuery(tag,pageable);
    }
}
