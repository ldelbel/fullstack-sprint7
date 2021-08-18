package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentDto {

    private final Long id;
    private final BigDecimal value;
    private final PaymentStatus status;


    public PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.value = payment.getValue();
        this.status = payment.getStatus();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public static PaymentDto convertOneToDto(Payment payment) {
        return new PaymentDto(payment);
    }

    public static List<PaymentDto> convertToDto(List<Payment> payments) {
        return payments.stream().map(PaymentDto::new).collect(Collectors.toList());
    }

}
