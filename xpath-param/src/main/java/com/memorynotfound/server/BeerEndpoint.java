package com.memorynotfound.server;

import com.memorynotfound.beer.*;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class BeerEndpoint {

    public static final String NAMESPACE_URI = "http://memorynotfound.com/beer";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBeerRequest")
    @Namespace(uri = NAMESPACE_URI, prefix = "ns2")
    public @ResponsePayload GetBeerResponse getBeer(@XPathParam("//ns2:id") Integer id,
                                                    @XPathParam("/ns2:getBeerRequest/ns2:id") String name) {

        System.out.println("id: " + id);
        System.out.println("name: " + name);

        return new GetBeerResponse();
    }

}
