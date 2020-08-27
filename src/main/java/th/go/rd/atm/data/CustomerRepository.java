package th.go.rd.atm.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.go.rd.atm.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}
