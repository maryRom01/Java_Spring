package app.entities;

import app.entities.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Account implements Serializable {

    private long id;
    private String number = UUID.randomUUID().toString();
    private Currency currency;
    private double balance = 0;
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.currency = currency;
        this.customer = customer;
    }
}
