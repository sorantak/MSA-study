package egovframework.msa.sample.controller;

import egovframework.msa.sample.entity.Customer;
import egovframework.msa.sample.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;

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
        long id = Long.parseLong(customerId);
        Customer customer = customerRepository.findById(id);
        if (ObjectUtils.isEmpty(customer)) {
            logger.error("no customer");
            throw new RuntimeException("I/O Exception");
        } else {
            logger.info(customer.toString());
            return "[Customer id = " + customerId + " at " + System.currentTimeMillis() + "]";
        }
    }

    @GetMapping("/db-info")
    public String getDBInfo() {
        String url = "No URL";
        String userName = "No USERNAME";
        try(Connection connection = dataSource.getConnection()){
            url = connection.getMetaData().getURL();
            userName = connection.getMetaData().getUserName();
            logger.info("url : " + url + " & username : " + userName);
        } catch (Exception e){
            logger.error("url : " + url + " & username : " + userName);
            logger.error(e.getMessage());
        }

        return url + " & " + userName;
    }
}
