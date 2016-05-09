package com.memorynotfound.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.memorynotfound.beer");
        return marshaller;
    }

    @Bean
    public BeerClient weatherClient(Jaxb2Marshaller marshaller) {
        BeerClient client = new BeerClient();
        client.setDefaultUri("http://localhost:8080/ws/beer");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
