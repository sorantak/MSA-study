package egovframework.msa.sample.controller;

import egovframework.msa.sample.service.CustomerApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs/customerinfo")
public class CatalogsController {

    @Autowired
    private CustomerApiService customerApiService;

    Logger logger = LoggerFactory.getLogger(CatalogsController.class);

    @GetMapping(path = "/{customerId}")
    public String getCustomerInfo(@PathVariable String customerId) {
        String customerInfo = customerApiService.getCustomerDetail(customerId);
        logger.info("Catalog to Customer");
        return String.format("[Customer id = %s at %s %s ]", customerId,
                System.currentTimeMillis(), customerInfo);
    }

}
