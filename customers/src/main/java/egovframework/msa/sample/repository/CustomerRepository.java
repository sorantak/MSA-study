package egovframework.msa.sample.repository;

import egovframework.msa.sample.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByName(String name);

    Optional<Customer> findById(Integer id);
}
