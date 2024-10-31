package org.javaacademy.programmer;

import org.javaacademy.employee.Employee;
import org.javaacademy.employee.EmployeeInvalidRateException;
import org.javaacademy.human.Gender;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static org.javaacademy.programmer.ProgrammerRateRange.MAX_RATE;
import static org.javaacademy.programmer.ProgrammerRateRange.MIN_RATE;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Programmer extends Employee {
    static final int COMPARE_SEPARATOR = 0;
    static final String MIN_RATE_EXCEPTION = "Часовая ставка ниже минимальной < ";
    static final String MAX_RATE_EXCEPTION = "Часовая ставка выше максимальной > ";

    public Programmer(@NonNull String firstName, @NonNull String lastName,
                      @NonNull String middleName, @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
    }

    /**
     * Устанавливает ставку в заданных границах минимальной и максимальнйо ставки
     */
    @Override
    public void setRate(BigDecimal rate) throws EmployeeInvalidRateException {
        checkRateInRange(rate);
        super.setRate(rate);
    }

    /**
     * Проверяет границы ставки в заданных границах минимальной и максимальнйо ставки
     */

    private void checkRateInRange(BigDecimal rate) throws EmployeeInvalidRateException {
        if (rate.compareTo(MIN_RATE.getRate()) < COMPARE_SEPARATOR) {
            throw new EmployeeInvalidRateException(MIN_RATE_EXCEPTION + MIN_RATE.getRate());
        }
        if (rate.compareTo(MAX_RATE.getRate()) > COMPARE_SEPARATOR) {
            throw new EmployeeInvalidRateException(MAX_RATE_EXCEPTION + MAX_RATE.getRate());
        }
    }

    /**
     * Задача сделана, переводится в статус "завершена".
     */
    public void takeTask(@NonNull Task task) throws LaborHoursException {
        task.setStatus(TaskStatusType.COMPLETED);
    }
}
