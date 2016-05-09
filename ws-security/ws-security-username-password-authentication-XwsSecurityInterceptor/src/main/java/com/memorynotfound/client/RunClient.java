package com.memorynotfound.client;

import com.memorynotfound.client.BeerClient;
import com.memorynotfound.beer.GetBeerRequest;
import com.memorynotfound.beer.GetBeerResponse;
import com.memorynotfound.client.SoapClientConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        System.out.println(context.getBeanDefinitionCount());
        BeerClient wsclient = context.getBean(BeerClient.class);
        GetBeerRequest request = new GetBeerRequest();
        request.setId(2);
        GetBeerResponse resp = wsclient.getBeer(request);
        System.out.println("response: " + resp);
    }

}
