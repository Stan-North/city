package org.javaacademy;

import org.javaacademy.employee.manager.Manager;
import org.javaacademy.employee.programmer.Programmer;
import org.javaacademy.employee.programmer.Task;
import org.javaacademy.human.Gender;

import java.math.BigDecimal;

public class Runner {
    private static final Double FIRST_TASK_HOURS = 50.0;
    private static final Double SECOND_TASK_HOURS = 35.0;
    private static final Double THIRD_TASK_HOURS = 10.0;
    public static void main(String[] args) {

        Programmer firstProgrammer = new Programmer(
                "Иван", "Иванов", "Иванович", Gender.MALE);
        Programmer secondProgrammer = new Programmer(
                "Екатерина", "Петрова", "Ивановна", Gender.FEMALE);
        Manager manager = new Manager("Петр", "Петрович", "Петренко", Gender.MALE);

        Task firsTask = new Task("Написать интеграцию", FIRST_TASK_HOURS);
        Task secondTask = new Task("Написать чат-бота", SECOND_TASK_HOURS);
        Task thirdTask = new Task("Написать unit-тесты", THIRD_TASK_HOURS);

        Double hourRate = Double.valueOf(args[1]);
        Company company = new Company(
                args[0], manager, BigDecimal.valueOf(hourRate), firstProgrammer, secondProgrammer);
        company.doWork(firsTask, secondTask, thirdTask);
        company.paySalaries();
        company.printCompanyInfo();
    }
}
