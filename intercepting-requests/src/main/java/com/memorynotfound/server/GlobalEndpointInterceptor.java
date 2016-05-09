package com.memorynotfound.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

@Component
public class GlobalEndpointInterceptor implements EndpointInterceptor {

    private static final Log LOG = LogFactory.getLog(GlobalEndpointInterceptor.class);

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Global Request Handling");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Global Response Handling");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Global Exception Handling");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        LOG.info("Execute Code After Completion");
    }
}
