package com.javaacademy.unit.civil_registry;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.civil_registry.CivilActionRecord;
import org.javaacademy.civil_registry.CivilRegistry;
import org.javaacademy.human.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

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
    void setUp() {
        civilRegistryName = "ЗАГС № 15";
        civilRegistry = new CivilRegistry(civilRegistryName);
        LocalDate date = LocalDate.now();
        Citizen man = new Citizen("Иван", "Иванов1", "Иванович",
                Gender.MALE, MaritalStatus.NOT_MARRIED, null);
        Citizen woman = new Citizen("Екатерина", "Петрова", "Ивановна",
                Gender.FEMALE, MaritalStatus.NOT_MARRIED, null);
        Citizen child = man.makeChild("Петр", "Иванов",
                "Иванович", Gender.MALE, woman);
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

//    marriageRegistration

//    divorceRegistration

}
