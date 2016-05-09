package com.memorynotfound.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler;

@Configuration
public class SoapClientConfig {

    @Bean
    public KeyStoreCallbackHandler securityCallbackHandler(){
        KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
        callbackHandler.setKeyStore(getKeyStoreFactoryBean().getObject());
        callbackHandler.setPrivateKeyPassword("changeit");
        callbackHandler.setTrustStore(getKeyStoreFactoryBean().getObject());
        return callbackHandler;
    }

    @Bean
    public KeyStoreFactoryBean getKeyStoreFactoryBean(){
        KeyStoreFactoryBean keyStoreFactoryBean = new KeyStoreFactoryBean();
        keyStoreFactoryBean.setLocation(new ClassPathResource("client.jks"));
        keyStoreFactoryBean.setPassword("changeit");
        return keyStoreFactoryBean;
    }

    @Bean
    public XwsSecurityInterceptor securityInterceptor(){
        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
        securityInterceptor.setPolicyConfiguration(new ClassPathResource("security/security-client.xml"));
        securityInterceptor.setCallbackHandler(securityCallbackHandler());
        return securityInterceptor;
    }

    @Bean
    public Jaxb2Marshaller getMarshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.memorynotfound.beer");
        return marshaller;
    }

    @Bean
    public BeerClient getBeerClient(){
        BeerClient beerClient = new BeerClient();
        beerClient.setMarshaller(getMarshaller());
        beerClient.setUnmarshaller(getMarshaller());
        beerClient.setDefaultUri("http://localhost:8080/ws/beers");
        ClientInterceptor[] interceptors = new ClientInterceptor[]{securityInterceptor()};
        beerClient.setInterceptors(interceptors);
        return beerClient;
    }

}
