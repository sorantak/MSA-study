package egovframework.msa.sample;

import egovframework.msa.sample.entity.Customer;
import egovframework.msa.sample.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

@ComponentScan("egovframework.*")
@EnableEurekaClient
@SpringBootApplication
public class CustomersApplication {

    Logger logger = LoggerFactory.getLogger(CustomersApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack"));
            repository.save(new Customer("Chloe"));
            repository.save(new Customer("Kim"));
            repository.save(new Customer("David"));
            repository.save(new Customer("Michelle"));

            // fetch all customers
            logger.info("Customers found with findAll():");
            logger.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                logger.info(customer.toString());
            }
            logger.info("");

            // fetch an individual customer by ID
            Optional<Customer> customer = repository.findById(1);
            logger.info("Customer found with findById(1L):");
            logger.info("--------------------------------");
            logger.info(customer.toString());
            logger.info("");

            // fetch customers by last name
            logger.info("Customer found with findByLastName('Bauer'):");
            logger.info("--------------------------------------------");
            repository.findByName("Chloe").forEach(bauer -> {
                logger.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  logger.info(bauer.toString());
            // }
            logger.info("");
        };
    }

}
