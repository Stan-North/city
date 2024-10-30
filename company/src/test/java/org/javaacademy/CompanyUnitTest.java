package org.javaacademy;

import org.javaacademy.employee.Manager;
import org.javaacademy.employee.Programmer;
import org.javaacademy.employee.Task;
import org.javaacademy.human.Gender;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class CompanyUnitTest {
    Manager manager;
    Company company;
    Programmer firstProgrammer;
    Programmer secondProgrammer;
    Task firstTask;
    Task secondTask;
    private static final String COMPANY_NAME = "Oracle";
    private static final BigDecimal PROGRAMMERS_HOUR_RATE = BigDecimal.valueOf(1500);

    @BeforeEach
    public void FieldsSetUp() {
        manager = Mockito.mock(Manager.class);
        company = Mockito.mock(Company.class);
        firstProgrammer = Mockito.mock(Programmer.class);
        secondProgrammer = Mockito.mock(Programmer.class);
        firstTask = Mockito.mock(Task.class);
        secondTask = Mockito.mock(Task.class);

        company = new Company(COMPANY_NAME, manager, PROGRAMMERS_HOUR_RATE, firstProgrammer, secondProgrammer);
        Mockito.when(firstTask.getDescription()).thenReturn("Первая задача");
        Mockito.when(secondTask.getDescription()).thenReturn("Вторая задача");
    }

    @AfterEach
    public void clear() {
        manager = null;
        company = null;
        firstProgrammer = null;
        secondProgrammer = null;
        firstTask = null;
        secondTask = null;

    }

    @Test
    @DisplayName("Создание экземпляра company c не null объектами")
    public void createCompanySuccess() {
    Assertions.assertDoesNotThrow(() -> new Company(COMPANY_NAME,
            manager, PROGRAMMERS_HOUR_RATE, firstProgrammer));
    }

    @Test
    @DisplayName("Создание класса company, менеджер == null ")
    public void createCompanyWithNullManagerFailure() {
        Assertions.assertThrows(RuntimeException.class, () -> new Company(COMPANY_NAME,
                null, PROGRAMMERS_HOUR_RATE, firstProgrammer));
    }

    @Test
    @DisplayName("Создание класса company, список программистов == null ")
    public void createCompanyWithNullProgrammersFailure() {
        Assertions.assertThrows(RuntimeException.class, () -> new Company(COMPANY_NAME,
                manager, PROGRAMMERS_HOUR_RATE, null));
    }

    @Test
    @DisplayName("Создание класса company, часовая ставка == null ")
    public void createCompanyWithNullHourRateFailure() {
        Assertions.assertThrows(RuntimeException.class, () -> new Company(COMPANY_NAME,
                manager, null, firstProgrammer));
    }

    @Test
    @DisplayName("Создание класса company, название == null ")
    public void createCompanyWithNullNameFailure() {
        Assertions.assertThrows(RuntimeException.class, () -> new Company(null,
                manager, PROGRAMMERS_HOUR_RATE, firstProgrammer));
    }

    @Test
    @DisplayName("Выполнение метода работы компании, список задач == null")
    public void doWorkWithoutTasksFailure() {
        Assertions.assertThrows(RuntimeException.class, () -> company.doWork(null));
    }

    @Test
    @DisplayName("Запуск метода работы компании с одной задачей")
    public void doWorkStartWithOneTaskMethodSuccess() {
        Assertions.assertDoesNotThrow(() -> company.doWork(firstTask));
    }

    @Test
    @DisplayName("Запуск метода работы компании с несколькими задачами")
    public void doWorkWithFewTasksSuccess() {
        Assertions.assertDoesNotThrow(() -> company.doWork(firstTask, secondTask));
    }

    @Test
    @DisplayName("Проверка имени компании")
    public void checkCompanyNameSuccess(){
        Assertions.assertEquals(COMPANY_NAME, company.getCompanyName());
    }

    @Test
    @DisplayName("проверка менеджера компании")
    public void checkCompanyManage() {
        Assertions.assertEquals(manager, company.getManager());
    }

}
