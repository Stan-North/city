package employee;

import human.Gender;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static employee.ProgrammerRateRange.MAX_RATE;
import static employee.ProgrammerRateRange.MIN_RATE;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Programmer extends Employee {
    static int COMPARE_SEPARATOR = 0;
    static String MIN_RATE_EXCEPTION = "Часовая ставка ниже минимальной < ";
    static String MAX_RATE_EXCEPTION = "Часовая ставка выше максимальной > ";
    static Double INITIAL_PROGRAMMER_TASK_LABOR_HOURS = 0.0;
    static String REDUCED_LABOR_HOURS_EXCEPTION = "Часы работы нельзя уменьшить";

    public Programmer(@NonNull String firstName, @NonNull String lastName,
                      @NonNull String middleName, @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
    }

    /**
     * Устанавливает ставку в заданных границах минимальной и максимальнйо ставки
     */
    @Override
    public void setRate(BigDecimal rate) throws EmployeeInvalidRateException  {
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
     * Задача взята в работу, переводится в статус "в работе", устанавливаются часы в ноль.
     */
    public void takeTask(@NonNull Task task) {
        task.setStatus(TaskStatusType.IN_PROGRESS);
        task.setLaborHours(INITIAL_PROGRAMMER_TASK_LABOR_HOURS);
    }

    /**
     * Задача сделана, переводится в статус "завершена", устанавливаются часы, но не меньше имеющихся по задаче.
     */
    public void doneTask(@NonNull Task task, @NonNull Double laborHours) throws LaborHoursException {
        if (laborHours < task.getLaborHours()) {
            throw new LaborHoursException(REDUCED_LABOR_HOURS_EXCEPTION);
        }
        task.setStatus(TaskStatusType.COMPLETED);
        task.setLaborHours(laborHours);
    }
}
