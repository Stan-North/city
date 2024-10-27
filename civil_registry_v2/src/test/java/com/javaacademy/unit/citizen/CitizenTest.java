package com.javaacademy.unit.citizen;

import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.citizen.SpouseNotFoundException;
import org.javaacademy.human.Gender;
import org.javaacademy.human.GenderEqualsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование класса гражданин(Citizen)")
public class CitizenTest {

    private Citizen getMan() {
        return new Citizen(
                "Отец",
                "Отцов",
                "Отцович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    private Citizen getWoman() {
        return new Citizen(
                "Мать",
                "Родящая",
                "Живо",
                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    private Citizen getMan2() {
        return new Citizen(
                "Мужик",
                "Это",
                "Левый",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    private Citizen getWoman2() {
        return new Citizen(
                "Нет",
                "Нет",
                "Нет",
                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    @Test
    @DisplayName("Успешная проверка полей ребенка-гражданина")
    public void makeChildCitizenSuccess() {
        Citizen man = getMan();
        Citizen woman = getWoman();
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
    @DisplayName("Негативный тест проверки полей ребенка-гражданина FirstName")
    public void makeChildCitizenFirstNameNegative() {
        Citizen man = getMan();
        Citizen woman = getWoman();
        Citizen woman2 = getWoman2();
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

    @Test
    @DisplayName("Негативный тест проверки полей ребенка-гражданина lastName")
    public void makeChildCitizenLastNameNegative() {
        Citizen man = getMan();
        Citizen woman = getWoman();
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
                "Иное",
                "Сынович",
                Gender.MALE,
                woman);

        Assertions.assertNotEquals(expected, actual, "Атрибуты созданного ребенка-гражданина равны");
    }

    @Test
    @DisplayName("Негативный тест проверки полей ребенка-гражданина middleName")
    public void makeChildCitizenMiddleNameNegative() {
        Citizen man = getMan();
        Citizen woman = getWoman();
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
                "Иное",
                Gender.MALE,
                woman);

        Assertions.assertNotEquals(expected, actual, "Атрибуты созданного ребенка-гражданина равны");
    }

    @Test
    @DisplayName("Негативный тест проверки полей ребенка-гражданина gender")
    public void makeChildCitizenGenderNegative() {
        Citizen man = getMan();
        Citizen woman = getWoman();
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
                "Иное",
                Gender.FEMALE,
                woman);

        Assertions.assertNotEquals(expected, actual, "Атрибуты созданного ребенка-гражданина равны");
    }

    @Test
    @DisplayName("Негативный тест проверки полей ребенка-гражданина otherParent")
    public void makeChildCitizenOtherParentNegative() {
        Citizen man = getMan();
        Citizen woman = getWoman();
        Citizen woman2 = getWoman2();
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
                "Иное",
                Gender.FEMALE,
                woman2);

        Assertions.assertNotEquals(expected, actual, "Атрибуты созданного ребенка-гражданина равны");
    }

    @Test
    @DisplayName("Проверка SpouseNotFoundException, если при создании гражданина есть брак, но нет супруга")
    public void citizenMarriedNoSpouseException() {
        Assertions.assertThrows(SpouseNotFoundException.class, () -> new Citizen(
                "Отец",
                "Отцов",
                "Отцович",
                Gender.MALE,
                MaritalStatus.MARRIED,
                null), "Отсутствует SpouseNotFoundException");
    }

    @Test
    @DisplayName("Проверка добавления супруга, если при создании гражданина есть брак")
    public void citizenMarriedSetSpouseSuccess() {
        Citizen woman = new Citizen(
                "Мать",
                "Родящая",
                "Живо",
                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);

        Citizen man = new Citizen(
                "Отец",
                "Отцов",
                "Отцович",
                Gender.MALE,
                MaritalStatus.MARRIED,
                woman);

        Assertions.assertEquals(woman, man.getSpouse(), "Супруги не совпадают");
    }

    @Test
    @DisplayName("Проверка формата полного ФИО у гражданина")
    public void citizenGetFullNameSuccess() {
        String expected = "Юрьев Юрий Юрьевич";
        Citizen man = new Citizen(
                "юрий",
                "юрьев",
                "юрьевич",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);

        String actual = man.getFullName();

        Assertions.assertEquals(expected, actual, "Формат полного ФИО не совпадает");
    }

    @Test
    @DisplayName("Проверка пола родителей при рождении ребенка-гражданина")
    public void makeChildGenderExceptionThrow() {
        Citizen man = getMan();
        Citizen man2 = getMan2();

        Assertions.assertThrows(GenderEqualsException.class, () -> man.makeChild(
                        "Сын",
                        "Сынов",
                        "Сынович",
                        Gender.MALE,
                        man2),
                "Атрибуты созданного ребенка-гражданина неравны");
    }

    @Test
    @DisplayName("Проверка добавления родителей, если ребенка-гражданина родила мать")
    public void makeChildCitizenMotherFatherReplace() {
        Citizen man = getMan();
        Citizen woman = getWoman();
        Citizen expected = new Citizen(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
        expected.setFather(man);
        expected.setMother(woman);

        Citizen actual = woman.makeChild(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                man);

        Assertions.assertEquals(expected.getFather(), actual.getFather(), "Отец не отец");
        Assertions.assertEquals(expected.getMother(), actual.getMother(), "Мать не мать");
    }

    @Test
    @DisplayName("Проверка NullPointerException, если при создании гражданина есть null в NonNull firstName")
    public void citizenNoNNullFirstNameException() {
        String firstName = null;
        String lastName = "Петров";
        String middleName = "Петрович";
        Gender gender = Gender.MALE;
        MaritalStatus maritalStatus = MaritalStatus.NOT_MARRIED;
        Citizen spouse = null;

        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(firstName, lastName, middleName, gender, maritalStatus, spouse),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, если при создании гражданина есть null в NonNull lastName")
    public void citizenNoNNullLastNameException() {
        String firstName = "Петр";
        String lastName = null;
        String middleName = "Петрович";
        Gender gender = Gender.MALE;
        MaritalStatus maritalStatus = MaritalStatus.NOT_MARRIED;
        Citizen spouse = null;

        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(firstName, lastName, middleName, gender, maritalStatus, spouse),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, если при создании гражданина есть null в NonNull middleName")
    public void citizenNoNNullMiddleNameException() {
        String firstName = "Петр";
        String lastName = "Петров";
        String middleName = null;
        Gender gender = Gender.MALE;
        MaritalStatus maritalStatus = MaritalStatus.NOT_MARRIED;
        Citizen spouse = null;

        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(firstName, lastName, middleName, gender, maritalStatus, spouse),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, если при создании гражданина есть null в NonNull gender")
    public void citizenNoNNullGenderException() {
        String firstName = "Петр";
        String lastName = "Петров";
        String middleName = "Петрович";
        Gender gender = null;
        MaritalStatus maritalStatus = MaritalStatus.NOT_MARRIED;
        Citizen spouse = null;

        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(firstName, lastName, middleName, gender, maritalStatus, spouse),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, если при создании гражданина есть null в NonNull maritalStatus")
    public void citizenNoNNullMaritalStatusException() {
        String firstName = "Петр";
        String lastName = "Петров";
        String middleName = "Петрович";
        Gender gender = Gender.MALE;
        MaritalStatus maritalStatus = null;
        Citizen spouse = null;

        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(firstName, lastName, middleName, gender, maritalStatus, spouse),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, " +
            "если при создании ребенка-гражданина есть null в NonNull firstName")
    public void makeChildCitizenNoNNullFirstNameException() {
        Citizen man = getMan();
        Citizen woman = getWoman();

        Assertions.assertThrows(NullPointerException.class, () -> woman.makeChild(
                        null,
                        "Сынов",
                        "Сынович",
                        Gender.MALE,
                        man),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, " +
            "если при создании ребенка-гражданина есть null в NonNull lastName")
    public void makeChildCitizenNoNNullLastNameException() {
        Citizen man = getMan();
        Citizen woman = getWoman();

        Assertions.assertThrows(NullPointerException.class, () -> woman.makeChild(
                        "Сын",
                        null,
                        "Сынович",
                        Gender.MALE,
                        man),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, " +
            "если при создании ребенка-гражданина есть null в NonNull middleName")
    public void makeChildCitizenNoNNullMiddleNameException() {
        Citizen man = getMan();
        Citizen woman = getWoman();

        Assertions.assertThrows(NullPointerException.class, () -> woman.makeChild(
                "Сын",
                "Сынов",
                null,
                Gender.MALE,
                man), "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, " +
            "если при создании ребенка-гражданина есть null в NonNull gender")
    public void makeChildCitizenNoNNullGenderException() {
        Citizen man = getMan();
        Citizen woman = getWoman();

        Assertions.assertThrows(NullPointerException.class, () -> woman.makeChild(
                        "Сын",
                        "Сынов",
                        "Сынович",
                        null,
                        man),
                "Отсутствует NullPointerException");
    }

    @Test
    @DisplayName("Проверка NullPointerException, " +
            "если при создании ребенка-гражданина есть null в NonNull otherParent")
    public void makeChildCitizenNoNNullOtherParentException() {
        Citizen woman = getWoman();

        Assertions.assertThrows(NullPointerException.class, () -> woman.makeChild(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                null), "Отсутствует NullPointerException");
    }
}
