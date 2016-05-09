package com.memorynotfound.client;

import com.memorynotfound.beer.GetBeerResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        BeerClient client = context.getBean(BeerClient.class);
        GetBeerResponse response = client.getBeer(1);
    }

}
