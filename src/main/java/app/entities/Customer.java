package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Customer implements Serializable {

    private long id;
    private String name;
    private String email;
    private Integer age;
    private List<Account> accounts = new ArrayList<>();

    @ConstructorProperties({"name", "email", "age"})
    public Customer(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
