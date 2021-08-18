package br.com.rchlo.store.form;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
public class PaymentForm {

    @NotNull @Positive
    private BigDecimal value;
    @NotNull @NotBlank @Length(max=100)
    private String clientName;
    @NotNull @NotBlank @Pattern(regexp = "\\d{16}")
    private String cardNumber;
    @NotNull @Future
    private YearMonth cardExpiration;
    @NotNull @Pattern(regexp = "\\d{3}")
    private String cardVerificationCode;

    public Payment convertToPayment() {
        Card card = new Card(clientName, cardNumber, cardExpiration.toString(), cardVerificationCode);
        return new Payment(value, card);
    }
}
