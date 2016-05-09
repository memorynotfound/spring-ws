package com.memorynotfound.client;

import com.memorynotfound.beer.GetBeerResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.net.URISyntaxException;

public class RunClient {

    public static void main(String[] args) throws URISyntaxException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        BeerClient client = context.getBean(BeerClient.class);
        GetBeerResponse response = client.getBeer(1);
        System.out.println(response);
    }

}
