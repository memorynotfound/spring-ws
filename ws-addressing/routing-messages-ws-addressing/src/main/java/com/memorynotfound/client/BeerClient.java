package com.memorynotfound.client;

import com.memorynotfound.beer.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.addressing.client.ActionCallback;
import org.springframework.ws.soap.addressing.core.EndpointReference;

import java.net.URI;
import java.net.URISyntaxException;

public class BeerClient extends WebServiceGatewaySupport {

    public GetBeerResponse getBeer(int id) throws URISyntaxException {
        GetBeerRequest request = new GetBeerRequest();
        request.setId(id);

        ActionCallback callback = new ActionCallback(
                new URI("http://memorynotfound.com/getBeerRequest"));

        return (GetBeerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request, callback);
    }
}