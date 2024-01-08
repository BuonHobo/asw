package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

	public Collection<Order> findAll();

	public Collection<Order> findByCustomer(String customer);

	public List<Order> findByOrderItems_Product(String product);

}

