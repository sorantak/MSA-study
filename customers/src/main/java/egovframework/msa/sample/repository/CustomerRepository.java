package egovframework.msa.sample.repository;

import egovframework.msa.sample.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
