package employee;

import human.Gender;
import lombok.NonNull;

import java.math.BigDecimal;

public class Programmer extends Employee {
    private static final int COMPARE_SEPARATOR = 0;
    private static final BigDecimal MIN_RATE = new BigDecimal(1500);
    private static final BigDecimal MAX_RATE = new BigDecimal(2000);
    private static final String MIN_RATE_EXCEPTION = "Часовая ставка ниже минимальной < ";
    private static final String MAX_RATE_EXCEPTION = "Часовая ставка выше максимальной > ";

    public Programmer(@NonNull String firstName, @NonNull String lastName,
                      @NonNull String middleName, @NonNull Gender gender) {
        super(firstName, lastName, middleName, gender);
    }

    /**
     * Устанавливает ставку в заданных границах минимальной и максимальнйо ставки
     */
    @Override
    public void setRate(BigDecimal rate) throws EmployeeInvalidRateException {
        if (rate.compareTo(MIN_RATE) < COMPARE_SEPARATOR) {
            throw new EmployeeInvalidRateException(MIN_RATE_EXCEPTION + MIN_RATE);
        }
        if (rate.compareTo(MAX_RATE) > COMPARE_SEPARATOR) {
            throw new EmployeeInvalidRateException(MAX_RATE_EXCEPTION + MAX_RATE);
        }
        super.setRate(rate);
    }

    Double INITIAL_PROGRAMMER_TASK_LABOR_HOURS = 0.0;
    String REDUCED_LABOR_HOURS_EXCEPTION = "Часы работы нельзя уменьшить";

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
