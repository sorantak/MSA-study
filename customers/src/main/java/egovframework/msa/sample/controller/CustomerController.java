package egovframework.msa.sample.controller;

import egovframework.msa.sample.entity.Customer;
import egovframework.msa.sample.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomerRepository customerRepository;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/{customerId}")
    public String getCustomerDetail(@PathVariable String customerId) {
        System.out.println("request customerId :" + customerId);
        return "[Customer id = " + customerId + " at " + System.currentTimeMillis() + "]";

//        throw new RuntimeException("I/O Exception");

    }

    @GetMapping("/db-info")
    public String getDBInfo() {
        String url = "No URL";
        String userName = "No USERNAME";
        try (Connection connection = dataSource.getConnection()) {
            url = connection.getMetaData().getURL();
            userName = connection.getMetaData().getUserName();

            logger.info("*************");
            logger.info("url : " + url);
            logger.info("username : " + userName);
            logger.info("*************");
        } catch (Exception e) {
            logger.error("*************");
            logger.error(e.getMessage());
            logger.error("*************");
        }

        return url + " / " + userName;
    }

    @GetMapping("/one")
    public String getCustomer() {
        String name = "etoos";
        List<Customer> customer = customerRepository.findByName(name);
        logger.info("*************");
        logger.info("customer : " + customer.get(0).getId());
        logger.info("*************");
        return customer.get(0).getName();
    }
}
