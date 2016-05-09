package com.memorynotfound.client;

import com.memorynotfound.beer.GetBeerRequest;
import com.memorynotfound.beer.GetBeerResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class BeerClient extends WebServiceGatewaySupport {

    public GetBeerResponse getBeer(GetBeerRequest request){
        return (GetBeerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request,
                        new SoapActionCallback("http://memorynotfound.com/GetBeer"));

    }
}
