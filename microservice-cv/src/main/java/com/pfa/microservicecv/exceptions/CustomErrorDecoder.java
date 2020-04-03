package com.pfa.microservicecv.exceptions;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder  implements ErrorDecoder {

    private final ErrorDecoder errorDecoder=new Default();

    @Override
    public Exception decode(String invoqueur, Response response) {

        Gson gson= new Gson();
        JsonObject jsonObject=gson.fromJson(response.body().toString(),JsonObject.class);
        String message=jsonObject.get("message").toString().substring(1,jsonObject.get("message").toString().length()-1);

        if(response.status()==400)
        {
            return new BadRequestException(message);
        }
        else if (response.status() == 404 ) {
            return new ResourceNotFoundException(message);
        }

        return errorDecoder.decode(invoqueur,response);
    }
}
