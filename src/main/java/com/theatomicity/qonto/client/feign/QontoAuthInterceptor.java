package com.theatomicity.qonto.client.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class QontoAuthInterceptor implements RequestInterceptor {

    @Autowired
    private Environment environment;

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        final String qontoId = environment.getProperty("QONTO_ID");
        final String qontoApiKey = environment.getProperty("QONTO_API_KEY");
        if (qontoId == null || qontoApiKey == null) {
            throw new RuntimeException("QONTO_ID or QONTO_API_KEY is not set");
        }
        final String auth = String.format("%s:%s", qontoId, qontoApiKey);
        requestTemplate.header("Authorization", auth);
    }
}
