package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Setter
    private static long serialVersionUID = 1L;

    private long id;
    private String name;
    private String email;
    private Integer age;
    private List<Account> accounts = new ArrayList<>();

    @ConstructorProperties({"name", "email", "age"})
    public Customer(String name, String email, Integer age) {
        this.id = serialVersionUID++;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
