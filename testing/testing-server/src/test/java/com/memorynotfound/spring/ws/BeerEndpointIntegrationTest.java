package com.memorynotfound.spring.ws;

import com.memorynotfound.server.SoapServerConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;
import javax.xml.transform.Source;
import java.io.IOException;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoapServerConfig.class)
public class BeerEndpointIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;
    private Resource xsdSchema = new ClassPathResource("xsd/beers.xsd");

    @Before
    public void init(){
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void valid_xsd_request_response_test() throws IOException {
        Source requestPayload = new StringSource(
                "<ns2:getBeerRequest xmlns:ns2=\"http://memorynotfound.com/beer\">" +
                        "<ns2:id>1</ns2:id>" +
                "</ns2:getBeerRequest>");

        Source responsePayload = new StringSource(
                "<ns2:getBeerResponse xmlns:ns2=\"http://memorynotfound.com/beer\"></ns2:getBeerResponse>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }

    @Test
    public void id_cannot_be_0_test() throws IOException {
        Source requestPayload = new StringSource(
                "<ns2:getBeerRequest xmlns:ns2=\"http://memorynotfound.com/beer\">" +
                        "<ns2:id>0</ns2:id>" +
                        "</ns2:getBeerRequest>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(serverOrReceiverFault());
    }
}
