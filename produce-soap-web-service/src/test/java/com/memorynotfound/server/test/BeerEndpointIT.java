package com.memorynotfound.server.test;

import com.memorynotfound.server.BeerEndpoint;
import com.memorynotfound.server.GetBeerRequest;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

//TODO: only for jax-ws endpoints, this app is spring based
public class BeerEndpointIT {

    @Test
    public void shouldReturnLaChouffe() throws MalformedURLException {
        // Publishes the SOAP web service
        Endpoint endpoint = Endpoint.publish("http://localhost:8123/beer", new BeerEndpoint());
        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        // Data to access the web service
        URL wsdlDocumentLocation = new URL("http://localhost:8123/beer?wsdl");
        String servicePart = "BeersService";
        String portName = "BeersPort";
        QName serviceQN = new QName(BeerEndpoint.NAMESPACE_URI, servicePart);
        QName portQN = new QName(BeerEndpoint.NAMESPACE_URI, portName);

        // Creates a service instance
        Service service = Service.create(wsdlDocumentLocation, serviceQN);
        BeerEndpoint beerService = service.getPort(portQN, BeerEndpoint.class);

        // execute the beer service
        GetBeerRequest request = new GetBeerRequest();
        request.setId(1);
        assertEquals(3, beerService.getBeer(request).getBeer().getId());

        // Unpublishes the SOAP Web Service
        endpoint.stop();
        assertFalse(endpoint.isPublished());
    }

}
