package employee;

import human.Gender;
import human.Human;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

public class Employee extends Human {
    @Setter
    @Getter
    private BigDecimal rate;

    public Employee(@NonNull String firstName, @NonNull String lastName, @NonNull String middleName, @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
    }
}
