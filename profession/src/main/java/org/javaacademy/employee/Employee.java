package org.javaacademy.employee;

import org.javaacademy.human.Gender;
import org.javaacademy.human.Human;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class Employee extends Human {
    private BigDecimal rate;
    private BigDecimal moneyEarned;

    public Employee(@NonNull String firstName, @NonNull String lastName,
                    @NonNull String middleName, @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
    }
}
