package com.theatomicity.qonto.client.feign;

import com.theatomicity.qonto.client.config.CustomFeignClientConfiguration;
import com.theatomicity.qonto.client.model.OrganizationDto;
import com.theatomicity.qonto.client.model.TransactionsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "transactions", url = "https://thirdparty.qonto.com", configuration = CustomFeignClientConfiguration.class)
public interface QontoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v2/organization", consumes = "application/json")
    OrganizationDto getOrganization();

    @RequestMapping(method = RequestMethod.GET, value = "/v2/transactions", consumes = "application/json")
    TransactionsDto getTransactions(
            @RequestParam("bank_account_id") String bankAccountId,
            @RequestParam("sort_by") String sortBy,
            @RequestParam("settled_at_from") String settledAtFrom,
            @RequestParam("settled_at_to") String settledAtTo,
            @RequestParam("page") int page
    );

}
