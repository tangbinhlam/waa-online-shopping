package edu.miu.waa.onlineshopping.data.jpa.adapter;

import edu.miu.waa.onlineshopping.data.jpa.mapper.PaymentEntityDomainMapper;
import edu.miu.waa.onlineshopping.data.jpa.repository.PaymentRepository;
import edu.miu.waa.onlineshopping.domain.model.Payment;
import edu.miu.waa.onlineshopping.domain.repository.PaymentDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryAdapter implements PaymentDomainRepository {

    private final PaymentRepository paymentRepository;
    private final PaymentEntityDomainMapper paymentEntityDomainMapper;

    @Autowired
    public PaymentRepositoryAdapter(PaymentRepository paymentRepository, PaymentEntityDomainMapper paymentEntityDomainMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentEntityDomainMapper = paymentEntityDomainMapper;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentEntityDomainMapper.mapToDomain(paymentRepository.save(paymentEntityDomainMapper.mapToEntity(payment)));
    }
}
