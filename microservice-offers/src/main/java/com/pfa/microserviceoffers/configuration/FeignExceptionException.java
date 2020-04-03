package com.pfa.microserviceoffers.configuration;

import com.pfa.microserviceoffers.exceptions.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FeignExceptionException {

    @Bean
    public CustomErrorDecoder customErrorDecoder2(){
        return new CustomErrorDecoder();
    }
}
