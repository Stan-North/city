package org.javaacademy.employee;

import lombok.NonNull;
import org.javaacademy.human.Gender;

import java.math.BigDecimal;

public class Manager extends Employee {
    public static final BigDecimal MANAGER_RATE = BigDecimal.valueOf(10_000);

    public Manager(@NonNull String firstName, @NonNull String lastName, @NonNull String middleName,
                   @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
        this.setRate(MANAGER_RATE);
    }


    //5.2. Так как профессии менеджера нет,
    // то внутри модуля profession создаем менеджера.
    // Ставка 10_000.
    //  Так же у любого СОТРУДНИКА есть сумма заработанных денег.
    //  Поднимаем версию модуля profession.
}
