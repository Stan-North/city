package com.javaacademy.unit.civil_registry;

import com.javaacademy.unit.util.TestUtil;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.civil_registry.CitizenIsMarriedException;
import org.javaacademy.civil_registry.CivilRegistry;
import org.javaacademy.human.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("Тестирование класса ЗАГС(CivilRegistry)")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CivilRegistryTest {
    String civilRegistryName;
    CivilRegistry civilRegistry;
    LocalDate date;
    Citizen man;
    Citizen woman;
    Citizen child;

    @BeforeEach
    public void setUp() {
        civilRegistryName = "ЗАГС № 15";
        civilRegistry = new CivilRegistry(civilRegistryName);
        date = LocalDate.now();
        man = TestUtil.getMan();
        woman = TestUtil.getWoman();
        child = TestUtil.getChild();
    }

    @Test
    @DisplayName("Проверка конструктора ЗАГСА")
    void checkCivilRegistry() {
        Assertions.assertEquals(civilRegistryName, civilRegistry.getCivilRegistryName(),
                "Имема загса не соответствует");
    }

//    childRegistration

    @Test
    @DisplayName("Успешная проверка полей ребенка-гражданина")
    public void childRegistrationSuccess() {
        LocalDate date = LocalDate.now();
        Citizen man = new Citizen("Иван", "Иванов1", "Иванович",
                Gender.MALE, MaritalStatus.NOT_MARRIED, null);
        Citizen woman = new Citizen("Екатерина", "Петрова", "Ивановна",
                Gender.FEMALE, MaritalStatus.NOT_MARRIED, null);
        Citizen child = man.makeChild("Петр", "Иванов",
                "Иванович", Gender.MALE, woman);


    }

    @Test
    @DisplayName("Проверка исключения, если кто-то из сочетающихся браком уже состоит в браке")
    public void marriageRegistrationAnyoneMarriedException() {
        man.setMaritalStatus(MaritalStatus.MARRIED);

        Assertions.assertThrows(CitizenIsMarriedException.class,
                () -> civilRegistry.marriageRegistration(man, woman, date), "Состоящих в браке нет");
    }

    @Test
    @DisplayName("Проверка статуса в браке")
    @SneakyThrows(CitizenIsMarriedException.class)
    public void marriageRegistrationSetMaritalStatusSuccess() {
        MaritalStatus expected = MaritalStatus.MARRIED;

        civilRegistry.marriageRegistration(man, woman, date);

        Assertions.assertEquals(expected, man.getMaritalStatus(), "По итогу не состоит в браке");
    }

    @Test
    @DisplayName("Проверка статуса в браке")
    @SneakyThrows(CitizenIsMarriedException.class)
    public void marriageRegistrationSetSpouseSuccess() {
        Citizen expected = man;

        civilRegistry.marriageRegistration(man, woman, date);

        Assertions.assertEquals(expected, woman.getSpouse(), "Супруг не совпадает");
    }

    @Test
    @DisplayName("Проверка исключения, если кто-то из расторгающих брак не состоит в браке")
    public void divorceRegistrationAnyoneNotMarriedException() {
        Assertions.assertThrows(CitizenIsMarriedException.class,
                () -> civilRegistry.divorceRegistration(man, woman, date), "Состоит кто-то в браке");
    }

    @Test
    @DisplayName("Проверка семейного статуса после расторжения брака")
    @SneakyThrows(CitizenIsMarriedException.class)
    public void divorceRegistrationSetMaritalStatus() {
        MaritalStatus expected = MaritalStatus.DIVORCED;
        man.setMaritalStatus(MaritalStatus.MARRIED);
        woman.setMaritalStatus(MaritalStatus.MARRIED);

        civilRegistry.divorceRegistration(man, woman, date);

        Assertions.assertEquals(expected, man.getMaritalStatus(), "Не разведен");
    }

    @Test
    @DisplayName("Проверка семейного статуса после расторжения брака")
    @SneakyThrows(CitizenIsMarriedException.class)
    public void divorceRegistrationRemoveSpouse() {
        man.setMaritalStatus(MaritalStatus.MARRIED);
        woman.setMaritalStatus(MaritalStatus.MARRIED);
        man.setSpouse(woman);
        woman.setSpouse(man);

        civilRegistry.divorceRegistration(man, woman, date);

        Assertions.assertNull(man.getSpouse(), "Не удален супруг после развода");
    }
}