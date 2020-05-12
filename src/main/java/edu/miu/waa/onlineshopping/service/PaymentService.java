package edu.miu.waa.onlineshopping.service;

import edu.miu.waa.onlineshopping.domain.model.Payment;
import edu.miu.waa.onlineshopping.domain.repository.PaymentDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentDomainRepository paymentDomainRepository;

    @Autowired
    public PaymentService(PaymentDomainRepository paymentDomainRepository) {
        this.paymentDomainRepository = paymentDomainRepository;
    }

    public Payment getPaymentOfOrder(Integer orderId) {
        return paymentDomainRepository.getPaymentByOrder(orderId);
    }
}
