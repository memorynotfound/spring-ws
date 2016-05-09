package com.memorynotfound.server;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.KeyStoreCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;
import java.util.Properties;

@EnableWs
@Configuration
public class SoapServerConfig extends WsConfigurerAdapter {

    @Bean
    public KeyStoreCallbackHandler securityCallbackHandler(){
        KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
        callbackHandler.setKeyStore(getKeyStoreFactoryBean().getObject());
        callbackHandler.setTrustStore(getKeyStoreFactoryBean().getObject());
        callbackHandler.setPrivateKeyPassword("changeit");
        return callbackHandler;
    }

    @Bean
    public KeyStoreFactoryBean getKeyStoreFactoryBean(){
        KeyStoreFactoryBean keyStoreFactoryBean = new KeyStoreFactoryBean();
        keyStoreFactoryBean.setLocation(new ClassPathResource("server.jks"));
        keyStoreFactoryBean.setPassword("changeit");
        return keyStoreFactoryBean;
    }

    @Bean
    public XwsSecurityInterceptor xwsSecurityInterceptor(){
        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
        securityInterceptor.setPolicyConfiguration(new ClassPathResource("security/security-server.xml"));
        securityInterceptor.setCallbackHandler(securityCallbackHandler());
        return securityInterceptor;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(xwsSecurityInterceptor());
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext appContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(appContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "beers")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BeersPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(BeerEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchema(schema);

        // fix for adding soap action to the dynamic generated wsdl
        Properties soapActions = new Properties();
        soapActions.setProperty("getBeer", "http://memorynotfound.com/getBeerRequest");

        wsdl11Definition.setSoapActions(soapActions);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema beersSchema(){
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource("xsd/beers.xsd"));
    }

}
