package com.memorynotfound.spring.ws;

import com.memorynotfound.beer.GetBeerResponse;
import com.memorynotfound.client.BeerClient;
import com.memorynotfound.client.SoapClientConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;
import javax.xml.transform.Source;
import java.io.IOException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoapClientConfig.class)
public class BeerClientIntegrationTest {

    @Autowired
    private BeerClient client;

    private MockWebServiceServer mockServer;

    @Before
    public void init(){
        mockServer = MockWebServiceServer.createServer(client);
    }

    @Test
    public void valid_xsd_request_response_test() throws IOException {
        Source requestPayload = new StringSource(
                "<ns2:getBeerRequest xmlns:ns2=\"http://memorynotfound.com/beer\">" +
                        "<ns2:id>1</ns2:id>" +
                "</ns2:getBeerRequest>");

        Source responsePayload = new StringSource(
                "<ns2:getBeerResponse xmlns:ns2=\"http://memorynotfound.com/beer\"></ns2:getBeerResponse>");

        mockServer
                .expect(payload(requestPayload))
                .andRespond(withPayload(responsePayload));

        GetBeerResponse response = client.getBeer(1);
        assertNotNull(response);

        mockServer.verify();
    }


}
