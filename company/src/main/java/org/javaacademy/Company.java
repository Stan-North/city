package org.javaacademy;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.javaacademy.employee.Employee;
import org.javaacademy.employee.Manager;
import org.javaacademy.employee.Programmer;
import org.javaacademy.employee.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
    private static final Double INITIAL_TIME = 0.0;
    private static final String TASK_DONE_MESSAGE = " - сделана.";
    private static final String EXPENSES_MESSAGE = "Затраты: ";
    private static final Double MANAGER_TASK_TIME_MULTIPLIER = 0.1;
    private static final int NUMBER_OF_DECIMAL_SPACES = 2;
    private static final String LIST_OF_TASKS_MESSAGE = "Список выполненных задач у компании:";
    String companyName;
    Manager manager;
    List<Programmer> programmersList;
    HashMap<Programmer, List<Task>> completedTasks;
    HashMap<Employee, Double> timeTracking;
    BigDecimal expenses = BigDecimal.ZERO;
    LinkedList<Task> weekTaskList;

    public Company(@NonNull String companyName, @NonNull Manager manager,
                   @NonNull BigDecimal programmersRate, Programmer... programmers) {
        this.companyName = companyName;
        this.manager = manager;
        programmersList = new ArrayList<>();
        completedTasks = new HashMap<>();
        timeTracking = new HashMap<>();
        Arrays.stream(programmers).forEach(programmer -> programmer.setRate(programmersRate));
        programmersList.addAll(Arrays.asList(programmers));
        programmersList.forEach(programmer -> completedTasks.put(programmer, new ArrayList<>()));
        programmersList.forEach(programmer -> timeTracking.put(programmer, INITIAL_TIME));
        timeTracking.put(manager, INITIAL_TIME);
        weekTaskList = new LinkedList<>();
    }

    /**
     * Выполнение программистами задач на неделю
     */
    public void doWork(Task... tasks) {
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
                programmer.doneTask(task);
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
        if (completedTasks.containsKey(programmer)) {
            completedTasks.get(programmer).add(task);
        } else {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(task);
            completedTasks.put(programmer, tasks);
        }
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
        timeTracking.forEach((employee, aDouble) -> payForWeek(employee));
        timeTracking.clear();
    }

    private void payForWeek(Employee employee) {
        Double weekHours = timeTracking.get(employee);
        BigDecimal moneyForWeek = employee.getRate().multiply(BigDecimal.valueOf(weekHours));
        if (employee.getMoneyEarned().equals(null)) {
            employee.setMoneyEarned(moneyForWeek);
        } else {
            employee.setMoneyEarned(employee.getMoneyEarned().add(moneyForWeek));
            expenses = expenses.add(moneyForWeek);
        }
    }

    public void companyInfo() {
        BigDecimal roundedValue = expenses.setScale(NUMBER_OF_DECIMAL_SPACES, RoundingMode.HALF_UP);
        System.out.println(
                companyName + "\n"
                    + EXPENSES_MESSAGE + roundedValue.toEngineeringString() + "\n"
                    + LIST_OF_TASKS_MESSAGE);
        completedTasks.forEach((programmer, tasks) -> {
            System.out.println(programmer.getFullName());
            System.out.println(tasks);
        });
    }
}
