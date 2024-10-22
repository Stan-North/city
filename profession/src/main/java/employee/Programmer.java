package employee;

import human.Gender;
import lombok.NonNull;
import task.ProgrammerTask;

import java.math.BigDecimal;

public class Programmer extends Employee implements ProgrammerTask {
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
}
