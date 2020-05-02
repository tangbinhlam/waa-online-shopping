package edu.miu.waa.onlineshopping.domain.repository;

import edu.miu.waa.onlineshopping.domain.model.Payment;

public interface PaymentDomainRepository {
    public Payment save(Payment payment);
    public boolean cancel(Payment payment);
}
