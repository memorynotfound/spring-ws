package com.memorynotfound.server;

import com.memorynotfound.beer.Beer;
import com.memorynotfound.beer.GetBeerRequest;
import com.memorynotfound.beer.GetBeerResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BeerEndpoint {

    public static final String NAMESPACE_URI = "http://memorynotfound.com/beer";

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
