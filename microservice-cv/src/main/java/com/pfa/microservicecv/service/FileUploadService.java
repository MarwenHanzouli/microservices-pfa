package com.pfa.microservicecv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class FileUploadService {

    private RestTemplate restTemplate;

    @Autowired
    public FileUploadService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    @Value("${ocr.api-key}")
    private String apiKey;

    public ResponseEntity postFile(String filename, byte[] someByteArray) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("apikey",apiKey);
        // This nested HttpEntiy is important to create the correct
        // Content-Disposition entry with metadata "name" and "filename"
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(filename)
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(someByteArray, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);
        body.add("isOverlayRequired",false);
        body.add("language","fre");
        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);
        ResponseEntity<String> response=null;
        try {
           response = restTemplate.exchange(
                    "https://api.ocr.space/parse/image",
                    HttpMethod.POST,
                    requestEntity,
                    String.class);

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return response;
    }
}