package org.javaacademy.employee.manager;

import lombok.NonNull;
import org.javaacademy.employee.employee.Employee;
import org.javaacademy.human.Gender;

import java.math.BigDecimal;

public class Manager extends Employee {
    public static final BigDecimal MANAGER_RATE = BigDecimal.valueOf(10_000);

    public Manager(@NonNull String firstName, @NonNull String lastName, @NonNull String middleName,
                   @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
        this.setRate(MANAGER_RATE);
    }
}
