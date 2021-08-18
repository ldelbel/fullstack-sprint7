package br.com.rchlo.store.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Card {

    private String clientName;
    private String number;
    private String expiration;
    private String verificationCode;

    public Card() {}

    public Card(String clientName, String cardNumber, String cardExpiration, String cardVerificationCode) {
        this.clientName = clientName;
        this.number = cardNumber;
        this.expiration = cardExpiration;
        this.verificationCode = cardVerificationCode;
    }

    public String getClientName() {
        return clientName;
    }

    public String getNumber() {
        return number;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
