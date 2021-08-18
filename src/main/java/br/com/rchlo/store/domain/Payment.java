package br.com.rchlo.store.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    @AttributeOverride(name = "clientName", column = @Column(name = "card_client_name"))
    @AttributeOverride(name = "number", column = @Column(name = "card_number"))
    @AttributeOverride(name = "expiration", column = @Column(name = "card_expiration"))
    @AttributeOverride(name = "verificationCode", column = @Column(name = "card_verification_code"))
    private Card card;

    public Payment() {}

    public Payment(BigDecimal value, Card card) {
        this.value = value;
        this.card = card;
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

    public Card getCard() {
        return card;
    }

    public Payment confirmPayment(){
        if(PaymentStatus.CANCELED.equals(this.status)){
            throw new IllegalArgumentException("Operação inválida para pagamento cancelado.");
        }
        this.status = PaymentStatus.CONFIRMED;
        return this;
    }

    public Payment cancelPayment(){
        if(PaymentStatus.CONFIRMED.equals(this.status)){
            throw new IllegalArgumentException("Operação inválida para pagamento confirmado.");
        }
        this.status = PaymentStatus.CANCELED;
        return this;
    }
}
