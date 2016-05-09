package com.memorynotfound.server;

import com.memorynotfound.beer.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BeerEndpoint {

    public static final String NAMESPACE = "http://memorynotfound.com/beer";
    public static final String BEER_REQUEST_LOCAL_PART = "getBeerRequest";

    @PayloadRoot(namespace = NAMESPACE, localPart = BEER_REQUEST_LOCAL_PART)
    @ResponsePayload
    public GetBeerResponse getBeer(@RequestPayload GetBeerRequest request) throws BeerNotFoundException {
        throw new BeerNotFoundException("Beer with id: " + request.getId() + " was not found.");
    }

}
