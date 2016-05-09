package com.memorynotfound.client;

import com.memorynotfound.Authentication;
import com.memorynotfound.beer.GetBeerRequest;
import com.memorynotfound.beer.GetBeerResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class BeerClient extends WebServiceGatewaySupport {

    public GetBeerResponse getBeer(int id) {
        GetBeerRequest request = new GetBeerRequest();
        request.setId(id);
        return (GetBeerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request,
                        new SecurityHeader(
                                new Authentication("username", "password")));
    }
}