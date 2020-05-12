package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.Payment;

public interface PaymentDomainRepository {
    public Payment save(Payment payment);
    public Payment getPaymentByOrder(Integer orderId);
}
