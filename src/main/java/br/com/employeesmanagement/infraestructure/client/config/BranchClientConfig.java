package br.com.employeesmanagement.infraestructure.client.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BranchClientConfig {

    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }

    @Bean
    ErrorDecoder clientErrorDecoder() {
        return new ClientErrorDecode();
    }

}
