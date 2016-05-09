package com.memorynotfound.client;

import com.memorynotfound.server.GetBeerRequest;
import com.memorynotfound.server.GetBeerResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        BeerClient wsclient = context.getBean(BeerClient.class);
        GetBeerRequest request = new GetBeerRequest();
        request.setId(2);
        GetBeerResponse resp = wsclient.getBeer(request);
        System.out.println("response: " + resp);
    }

}
