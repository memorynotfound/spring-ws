package com.memorynotfound.client;

import com.memorynotfound.beer.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        System.out.println(context.getBeanDefinitionCount());
        BeerClient client = context.getBean(BeerClient.class);

        GetBeerRequest request = new GetBeerRequest();
        request.setId(2);
        GetBeerResponse resp = client.getBeer(request);
        System.out.println("response: " + resp);
    }

}
