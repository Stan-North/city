package task;

import lombok.NonNull;

public interface ProgrammerTask {
    Double INITIAL_PROGRAMMER_TASK_LABOR_HOURS = 0.0;
    String REDUCED_LABOR_HOURS_EXCEPTION = "Часы работы нельзя уменьшить";

    /**
     * Задача взята в работу, переводится в статус "в работе", устанавливаются часы в ноль.
     */
    default void takeTask(@NonNull Task task) {
        task.setStatus(TaskStatusType.IN_PROGRESS);
        task.setLaborHours(INITIAL_PROGRAMMER_TASK_LABOR_HOURS);
    }

    /**
     * Задача сделана, переводится в статус "завершена", устанавливаются часы, но не меньше имеющихся по задаче.
     */
    default void doneTask(@NonNull Task task, @NonNull Double laborHours) throws LaborHoursException {
        if (laborHours < task.getLaborHours()) {
            throw new LaborHoursException(REDUCED_LABOR_HOURS_EXCEPTION);
        }
        task.setStatus(TaskStatusType.COMPLETED);
        task.setLaborHours(laborHours);
    }
}
