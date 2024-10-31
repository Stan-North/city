package org.javaacademy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.javaacademy.employee.Employee;
import org.javaacademy.manager.Manager;
import org.javaacademy.programmer.Programmer;
import org.javaacademy.programmer.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Company {
    static final Double INITIAL_TIME = 0.0;
    static final String TASK_DONE_MESSAGE = " - сделана.";
    static final String EXPENSES_MESSAGE = "Затраты: ";
    static final Double MANAGER_TASK_TIME_MULTIPLIER = 0.1;
    static final int NUMBER_OF_DECIMAL_SPACES = 2;
    static final String LIST_OF_TASKS_MESSAGE = "Список выполненных задач у компании:";
    static final String REPORT_DIVIDER = " - ";
    final String companyName;
    final Manager manager;
    final List<Programmer> programmersList = new ArrayList<>();
    final MultiValuedMap<Programmer, Task> completedTasks = new ArrayListValuedHashMap<>();
    final HashMap<Employee, Double> timeTracking = new HashMap<>();
    BigDecimal expenses = BigDecimal.ZERO;
    LinkedList<Task> weekTaskList = new LinkedList<>();

    public Company(@NonNull String companyName, @NonNull Manager manager,
                   @NonNull BigDecimal programmersRate, @NonNull Programmer... programmers) {
        this.companyName = companyName;
        this.manager = manager;
        initProgrammersList(programmersRate, programmers);
        timeTracking.put(manager, INITIAL_TIME);
        weekTaskList = new LinkedList<>();
    }

    /**
     * Инициализация списков с программистами
     */
    private void initProgrammersList(BigDecimal programmersRate, Programmer... programmers) {
        programmersList.addAll(Arrays.asList(programmers));
        programmersList.forEach(programmer -> programmer.setRate(programmersRate));
        programmersList.forEach(programmer -> timeTracking.put(programmer, INITIAL_TIME));
    }

    /**
     * Выполнение программистами задач на неделю
     */
    public void doWork(@NonNull Task... tasks) {
        weekTaskList.addAll(List.of(tasks));
        while (!weekTaskList.isEmpty()) {
            doTasks();
        }
    }

    private void doTasks() {
        for (Programmer programmer : programmersList) {
            Task task = weekTaskList.pollFirst();
            if (task != null) {
                programmer.takeTask(task);
                System.out.println(task.getDescription() + TASK_DONE_MESSAGE);
                addTimeToEmployee(programmer, task);
                addTaskToList(programmer, task);
            }
        }
    }

    /**
     * Прибавление времени задачи к общему отработанному времени
     */
    private Double timeSum(Employee employee, Double workTime) {
        Double currentWorkingTime = timeTracking.get(employee);
        return currentWorkingTime + workTime;
    }

    /**
     * Добавление задачи в список выполненных задач
     */
    private void addTaskToList(Programmer programmer, Task task) {
        completedTasks.put(programmer, task);
    }

    /**
     * Добавление рабочего времени менеджеру и программисту
     */
    private void addTimeToEmployee(Programmer programmer, Task task) {
        addTimeToProgrammer(programmer, task);
        addTimeToManager(task);
    }

    /**
     * Добавление рабочего времени программисту
     */
    private void addTimeToProgrammer(Programmer programmer, Task task) {
        timeTracking.put(programmer, timeSum(programmer, task.getLaborHours()));
    }

    /**
     * Добавление рабочего времени менеджеру
     */
    private void addTimeToManager(Task task) {
        Double managerTimeOnTask = task.getLaborHours() * MANAGER_TASK_TIME_MULTIPLIER;
        Double currentWorkTime = timeTracking.get(manager);
        Double result = currentWorkTime + managerTimeOnTask;
        timeTracking.put(manager, result);
    }

    public void paySalaries() {
        timeTracking.forEach(this::payForWeek);
        timeTracking.replaceAll((employee, workingTime) -> INITIAL_TIME);
        weekTaskList.clear();
    }

    private void payForWeek(Employee employee, double workedHours) {
        BigDecimal moneyForWeek = employee.getRate().multiply(BigDecimal.valueOf(workedHours));
        employee.setMoneyEarned(employee.getMoneyEarned().add(moneyForWeek));
        expenses = expenses.add(moneyForWeek);
    }

    public void printCompanyInfo() {
        BigDecimal roundedValue = expenses.setScale(NUMBER_OF_DECIMAL_SPACES, RoundingMode.HALF_UP);
        System.out.println(
                companyName + "\n"
                    + EXPENSES_MESSAGE + roundedValue.toEngineeringString() + "\n"
                    + LIST_OF_TASKS_MESSAGE);

        completedTasks.keySet()
                .forEach(programmer -> {
                    System.out.print(programmer.getFullName() + REPORT_DIVIDER);
                    System.out.print(completedTasks.get(programmer) + "\n");
                });
    }
}
