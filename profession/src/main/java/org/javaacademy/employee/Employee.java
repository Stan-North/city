package org.javaacademy.employee;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.javaacademy.human.Gender;
import org.javaacademy.human.Human;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends Human {
    BigDecimal rate;
    BigDecimal moneyEarned = BigDecimal.ZERO;

    public Employee(@NonNull String firstName, @NonNull String lastName,
                    @NonNull String middleName, @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
    }
}
