package com.theatomicity.qonto.client.config;

import com.theatomicity.qonto.client.batch.QontoBatchReader;
import com.theatomicity.qonto.client.feign.QontoAuthInterceptor;
import com.theatomicity.qonto.client.feign.QontoClient;
import com.theatomicity.qonto.client.rest.QontoRestClient;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableFeignClients(clients = {QontoClient.class})
public class QontoClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RequestInterceptor qontoAuthInterceptor() {
        return new QontoAuthInterceptor();
    }

    @Bean
    public QontoBatchReader qontoBatchReader(final QontoClient qontoClient) {
        return new QontoBatchReader(qontoClient);
    }

    @Bean
    public QontoRestClient qontoRestClient(final QontoClient qontoClient) {
        return new QontoRestClient(qontoClient);
    }
    
}
