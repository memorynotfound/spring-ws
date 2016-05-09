package com.memorynotfound.server;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + BeerNotFoundException.NAMESPACE_URI + "}custom_fault",
        faultStringOrReason = "@faultString")
public class BeerNotFoundException extends Exception {

    public static final String NAMESPACE_URI = "http://memorynotfound.com/exception";

    public BeerNotFoundException(String message) {
        super(message);
    }
}
