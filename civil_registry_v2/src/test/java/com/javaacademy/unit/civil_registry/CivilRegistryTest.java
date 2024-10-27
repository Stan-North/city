package com.javaacademy.unit.civil_registry;

import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.human.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("Тестирование класса ЗАГС(CivilRegistry)")
public class CivilRegistryTest {

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
