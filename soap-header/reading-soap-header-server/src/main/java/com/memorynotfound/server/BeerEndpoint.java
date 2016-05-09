package com.memorynotfound.server;

import com.memorynotfound.beer.*;
import com.memorynotfound.Authentication;
import org.springframework.ws.server.endpoint.annotation.*;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Endpoint
public class BeerEndpoint {

    public static final String NAMESPACE_URI = "http://memorynotfound.com/beer";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBeerRequest")
    @ResponsePayload
    public GetBeerResponse getBeer(@RequestPayload GetBeerRequest request,
                                   @SoapHeader("{" + Authentication.AUTH_NS + "}authentication") SoapHeaderElement auth) {

        Authentication authentication = getAuthentication(auth);

        // empty response
        return new GetBeerResponse();
    }

    private Authentication getAuthentication(SoapHeaderElement header){
        Authentication authentication = null;
        try {

            JAXBContext context = JAXBContext.newInstance(Authentication.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            authentication = (Authentication) unmarshaller.unmarshal(header.getSource());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return authentication;
    }

}
