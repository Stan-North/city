package task;

public interface ProgrammerTask {
    String LABOR_HOURS_NEGATIVE_EXCEPTION = "Часы работы не могут быть отрицательными";
    String REDUCED_LABOR_HOURS_EXCEPTION = "Часы работы нельзя уменьшить";

    default void takeTask(Task task) {
        task.setStatus(TaskStatusType.IN_PROGRESS);
        task.setLaborHours(0);
    }

    default void doneTask(Task task, Integer laborHours) throws LaborHoursException {
        if (laborHours < 0) {
            throw new LaborHoursException(LABOR_HOURS_NEGATIVE_EXCEPTION);

        }
        if (laborHours < task.getLaborHours()) {
            throw new LaborHoursException(REDUCED_LABOR_HOURS_EXCEPTION);
        }
        task.setStatus(TaskStatusType.COMPLETED);
        task.setLaborHours(laborHours);
    }
}
