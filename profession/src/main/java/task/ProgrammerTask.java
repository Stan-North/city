package task;

import lombok.NonNull;

public interface ProgrammerTask {
    String REDUCED_LABOR_HOURS_EXCEPTION = "Часы работы нельзя уменьшить";

    default void takeTask(@NonNull Task task) {
        task.setStatus(TaskStatusType.IN_PROGRESS);
        task.setLaborHours(0);
    }

    default void doneTask(@NonNull Task task, @NonNull Integer laborHours) throws LaborHoursException {
        if (laborHours < task.getLaborHours()) {
            throw new LaborHoursException(REDUCED_LABOR_HOURS_EXCEPTION);
        }
        task.setStatus(TaskStatusType.COMPLETED);
        task.setLaborHours(laborHours);
    }
}
