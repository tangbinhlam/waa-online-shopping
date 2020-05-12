package edu.miu.waa.onlineshopping.data.jpa.mapper;

import edu.miu.waa.onlineshopping.data.jpa.entity.PaymentEntity;
import edu.miu.waa.onlineshopping.domain.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentEntityDomainMapper {

    private final OrderEntityDomainMapper orderEntityDomainMapper;
    private final AccountEntityDomainMapper accountEntityDomainMapper;

    @Autowired
    public PaymentEntityDomainMapper(OrderEntityDomainMapper orderEntityDomainMapper, AccountEntityDomainMapper accountEntityDomainMapper) {
        this.orderEntityDomainMapper = orderEntityDomainMapper;
        this.accountEntityDomainMapper = accountEntityDomainMapper;
    }

    public Payment mapToDomain(PaymentEntity paymentEntity) {
        return (paymentEntity != null) ?
                Payment.of(paymentEntity.getPaymentId(),
                        paymentEntity.getPaid(),
                        paymentEntity.getTotal(),
                        paymentEntity.getDetails(),
                        orderEntityDomainMapper.mapToDomain(paymentEntity.getOrder()),
                        accountEntityDomainMapper.mapToDomain(paymentEntity.getPayAccount()),
                        accountEntityDomainMapper.mapToDomain(paymentEntity.getReceiveAccount())
                ) : null;
    }

    public PaymentEntity mapToEntity(Payment payment) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPaymentId(payment.getPaymentId());
        paymentEntity.setDetails(payment.getDetails());
        paymentEntity.setTotal(payment.getTotal());
        paymentEntity.setPaid(payment.getPaid());
        paymentEntity.setOrder(orderEntityDomainMapper.mapToEntity(payment.getOrder()));
        paymentEntity.setPayAccount(accountEntityDomainMapper.mapToEntity(payment.getPayAccount()));
        paymentEntity.setReceiveAccount(accountEntityDomainMapper.mapToEntity(payment.getReceiveAccount()));
        return paymentEntity;
    }
}
