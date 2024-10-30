package org.javaacademy;

import org.javaacademy.employee.Manager;
import org.javaacademy.employee.Programmer;
import org.javaacademy.employee.Task;
import org.javaacademy.human.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class CompanyIntegrationTest {


    @Test
    @DisplayName("Проверка на обнуление времени после выдачи зарплаты")
    public void clearWorkTimeSuccess() {
        Programmer programmer = new Programmer("Иван", "Иванов", "Иванович", Gender.MALE);
        Manager manager = new Manager("Иван", "Петров", "Петрович", Gender.MALE);
        Company company = new Company("Oracle", manager, BigDecimal.valueOf(1600), programmer);
        Task task1 = new Task("Задание №1",10.0);
        company.doWork(task1);
        company.paySalaries();
        Assertions.assertEquals(0.0, company.getTimeTracking().get(programmer));
    }

    @Test
    @DisplayName("Проверка на правильный расчет ЗП")
    public void paymentSalarySuccess() {
        Programmer programmer = new Programmer("Иван", "Иванов", "Иванович", Gender.MALE);
        Programmer programmer2 = new Programmer("Петр", "Петров", "Петрович", Gender.FEMALE);
        Manager manager = new Manager("Олег", "Олежкин", "Олегович", Gender.MALE);
        Company company = new Company("Oracle", manager, BigDecimal.valueOf(1600), programmer, programmer2);
        Task task1 = new Task("Задание №1",2.0);
        Task task2 = new Task("Задание №2", 1.0);
        company.doWork(task1, task2);
        company.paySalaries();
        Assertions.assertEquals(BigDecimal.valueOf(3200.0), programmer.getMoneyEarned());
        Assertions.assertEquals(BigDecimal.valueOf(1600.0), programmer2.getMoneyEarned());
    }

    @Test
    @DisplayName("Проверка правильности печати инфо о компании")
    public void companyInfoPrintSucces() {
    }

    @Test
    @DisplayName("Проверка, что счетчик времени после выплат обнуляется")
    public void timeCounterAfterSalaryClearSuccess() {
    }

    @Test
    @DisplayName("Проверка печатания информации")
    public void companyPrintInfoSuccess() {
        Programmer programmer = new Programmer("Иван", "Иванов", "Иванович", Gender.MALE);
        Manager manager = new Manager("Олег", "Олежкин", "Олегович", Gender.MALE);
        Company company = new Company("Oracle", manager, BigDecimal.valueOf(1600), programmer);
        Task task1 = new Task("Задание №1",1.0);
        company.doWork(task1);
        company.paySalaries();
        BigDecimal expenses = company.getExpenses();

        company.printCompanyInfo();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        company.printCompanyInfo();

        String resultPrint = outputStream.toString();
        Assertions.assertTrue(resultPrint.contains("Oracle"));
        Assertions.assertTrue(resultPrint.contains("Затраты: " + expenses));
        Assertions.assertTrue(resultPrint.contains("Список выполненных задач у компании:"));
        Assertions.assertTrue(resultPrint.contains(programmer.getFullName()));
        Assertions.assertTrue(resultPrint.contains(task1.toString()));
        System.setOut(System.out);
    }

}
