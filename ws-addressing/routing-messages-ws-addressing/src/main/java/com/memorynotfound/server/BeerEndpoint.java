package com.memorynotfound.server;

import com.memorynotfound.beer.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.addressing.server.annotation.Action;

@Endpoint
public class BeerEndpoint {

    @Action("http://memorynotfound.com/getBeerRequest")
    public @ResponsePayload GetBeerResponse getBeer(@RequestPayload GetBeerRequest request) {
        GetBeerResponse response = new GetBeerResponse();
        Beer beer = new Beer();
        beer.setId(request.getId());
        beer.setName("La Chouffe");
        response.setBeer(beer);
        return response;
    }
}
