package com.memorynotfound.server;

import com.memorynotfound.server.Beer;
import com.memorynotfound.server.GetBeerRequest;
import com.memorynotfound.server.GetBeerResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

@Endpoint
public class BeerEndpoint {

    public static final String NAMESPACE_URI = "http://memorynotfound.com/beer";

    @SoapAction("http://memorynotfound.com/getBeerRequest")
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBeerRequest")
    @ResponsePayload
    public GetBeerResponse getBeer(@RequestPayload GetBeerRequest request)  {
        GetBeerResponse beerResponse = new GetBeerResponse();
        Beer beer = new Beer();
        beer.setId(request.getId());
        beer.setName("Beer name");
        beerResponse.setBeer(beer);
        return beerResponse;
    }

}
