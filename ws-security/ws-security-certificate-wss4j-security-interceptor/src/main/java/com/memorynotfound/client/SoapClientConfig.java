package com.memorynotfound.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j.support.CryptoFactoryBean;

import java.io.IOException;

@Configuration
public class SoapClientConfig {

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();

        // set security actions
        securityInterceptor.setSecurementActions("Timestamp Signature Encrypt");

        // sign the request
        securityInterceptor.setSecurementUsername("client");
        securityInterceptor.setSecurementPassword("changeit");
        securityInterceptor.setSecurementSignatureCrypto(getCryptoFactoryBean().getObject());

        // encrypt the request
        securityInterceptor.setSecurementEncryptionUser("server-public");
        securityInterceptor.setSecurementEncryptionCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setSecurementEncryptionParts("{Content}{http://memorynotfound.com/beer}getBeerRequest");

        return securityInterceptor;
    }

    @Bean
    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("changeit");
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("client.jks"));
        return cryptoFactoryBean;
    }

    @Bean
    public Jaxb2Marshaller getMarshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.memorynotfound.beer");
        return marshaller;
    }

    @Bean
    public BeerClient getBeerClient() throws Exception {
        BeerClient beerClient = new BeerClient();
        beerClient.setMarshaller(getMarshaller());
        beerClient.setUnmarshaller(getMarshaller());
        beerClient.setDefaultUri("http://localhost:8080/ws/beers");
        ClientInterceptor[] interceptors = new ClientInterceptor[]{securityInterceptor()};
        beerClient.setInterceptors(interceptors);
        return beerClient;
    }

}
