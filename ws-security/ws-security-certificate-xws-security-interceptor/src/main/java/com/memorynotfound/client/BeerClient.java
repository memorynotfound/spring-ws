package com.memorynotfound.client;

import com.memorynotfound.server.GetBeerRequest;
import com.memorynotfound.server.GetBeerResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class BeerClient extends WebServiceGatewaySupport {

    public GetBeerResponse getBeer(GetBeerRequest request){
        return (GetBeerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request,
                        new SoapActionCallback("http://memorynotfound.com/getBeerRequest"));

    }
}
