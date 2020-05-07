package edu.miu.waa.onlineshopping.data.jpa.repository;

import edu.miu.waa.onlineshopping.data.jpa.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Integer> {
}
