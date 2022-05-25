package egovframework.msa.sample.serviceImpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import egovframework.msa.sample.service.CustomerApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerApiServiceImpl implements CustomerApiService {

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(CustomerApiServiceImpl.class);

    @Override
    @HystrixCommand(fallbackMethod = "getCustomerDetailFallback")
    public String getCustomerDetail(String customerId) {
        logger.info("customerId : ", customerId);
        logger.info("request success");
//        return customerId;
//        return restTemplate.getForObject("http://localhost:8082/customers/" + customerId, String.class);

//        LoadBalanced
        return restTemplate.getForObject("http://customer/customers/" + customerId, String.class);
    }

    public String getCustomerDetailFallback(String customerId, Throwable ex) {
        logger.error("circuitbreaker");
        logger.error("request fail" + ex.getMessage());
        return "고객정보 조회가 지연되고 있습니다.";
    }
}