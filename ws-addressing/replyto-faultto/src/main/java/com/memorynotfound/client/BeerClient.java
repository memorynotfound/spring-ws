package com.memorynotfound.client;

import com.memorynotfound.beer.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.addressing.client.ActionCallback;
import org.springframework.ws.soap.addressing.core.EndpointReference;

import java.net.URI;
import java.net.URISyntaxException;

public class BeerClient extends WebServiceGatewaySupport {

    public void sendGetBeerRequest(Integer id) throws URISyntaxException {
        GetBeerRequest request = new GetBeerRequest();
        request.setId(id);

        ActionCallback callback = new ActionCallback(
                new URI("http://memorynotfound.com/getBeerRequest"));
        callback.setReplyTo(new EndpointReference(
                new URI("http://localhost:8080/response")));
        callback.setFaultTo(new EndpointReference(
                new URI("http://localhost:8080/fault")));

        getWebServiceTemplate().marshalSendAndReceive(request, callback);
    }
}