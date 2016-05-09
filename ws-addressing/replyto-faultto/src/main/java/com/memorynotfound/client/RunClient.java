package com.memorynotfound.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.net.URISyntaxException;

public class RunClient {

    public static void main(String[] args) throws URISyntaxException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        BeerClient client = context.getBean(BeerClient.class);
        client.sendGetBeerRequest(1);
        System.out.println("getBeerRequest handled by replyTo");

        client.sendGetBeerRequest(0);
        System.out.println("getBeerRequest handled by faultTo");
    }

}
