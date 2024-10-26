package com.javaacademy.unit.citizen;

import human.Gender;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование класса гражданин(Citizen)")
public class CitizenTest {

    @Test
    @DisplayName("Успешное рождение гражданина")
    public void makeChildCitizenClassSuccess() {
        Citizen man = new Citizen(
                "Отец",
                "Отцов",
                "Отцович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
        Citizen woman = new Citizen(
                "Мать",
                "Родящая",
                "Живо",

                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);

        Citizen actual = man.makeChild(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                woman);

        Assertions.assertInstanceOf(Citizen.class, actual);
    }

    @Test
    @DisplayName("Успешная проверка полей ребенка-гражданина")
    public void makeChildCitizenSuccess() {
        Citizen man = new Citizen(
                "Отец",
                "Отцов",
                "Отцович",

                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
        Citizen woman = new Citizen(
                "Мать",
                "Родящая",
                "Живо",

                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);
        Citizen expected = new Citizen(
                "Сын",
                "Сынов",

                "Сынович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
        expected.setFather(man);
        expected.setMother(woman);

        Citizen actual = man.makeChild(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                woman);

        Assertions.assertEquals(expected, actual, "Атрибуты созданного ребенка-гражданина неравны");
    }

    @Test
    @DisplayName("Негативный тест проверки полей ребенка-гражданина")
    public void makeChildCitizenNegative() {
        Citizen man = new Citizen(
                "Отец",
                "Отцов",
                "Отцович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
        Citizen woman = new Citizen(
                "Мать",
                "Родящая",
                "Живо",
                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);
        Citizen woman2 = new Citizen(
                "Мать",
                "Родящая",
                "Живо",
                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);
        Citizen expected = new Citizen(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
        expected.setFather(man);
        expected.setMother(woman);

        Citizen actual = man.makeChild(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                woman2);

        Assertions.assertNotEquals(expected, actual, "Атрибуты созданного ребенка-гражданина равны");
    }
}
