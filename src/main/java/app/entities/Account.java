package app.entities;

import app.entities.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Account implements Serializable {

    @Setter
    private static long serialVersionUID = 1L;

    private long id;
    private String number;
    private Currency currency;
    private double balance = 0;
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.id = serialVersionUID++;
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.customer = customer;
    }
}
