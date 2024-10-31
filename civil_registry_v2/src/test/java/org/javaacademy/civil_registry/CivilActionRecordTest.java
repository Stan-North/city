package org.javaacademy.civil_registry;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.human.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
class CivilActionRecordTest {
    LocalDate actionDate;
    CivilActionType actionType;
    Citizen firstCitizen;
    Citizen secondCitizen;
    CivilActionRecord civilActionRecord1;
    CivilActionRecord civilActionRecord2;
    CivilActionRecord civilActionRecord3;
    CivilActionRecord civilActionRecordNotEqual;

    @BeforeEach
    void setUp() {
        actionDate = LocalDate.now();
        actionType = CivilActionType.MARRIAGE_REGISTRATION;
        firstCitizen = new Citizen("Гарри", "Картошкин", "Вашингтонович", Gender.MALE,
                MaritalStatus.NOT_MARRIED, null);
        secondCitizen = new Citizen("Валентина", "Картошкина", "Семеновна", Gender.FEMALE,
                MaritalStatus.NOT_MARRIED, null);
        civilActionRecord1 = new CivilActionRecord(actionDate, actionType, firstCitizen, secondCitizen);
        civilActionRecord2 = new CivilActionRecord(actionDate, actionType, firstCitizen, secondCitizen);
        civilActionRecord3 = new CivilActionRecord(actionDate, actionType, firstCitizen, secondCitizen);
        civilActionRecordNotEqual = new CivilActionRecord(actionDate.minusDays(1), actionType, firstCitizen, secondCitizen);
    }

    @Test
    @DisplayName("Проверка даты на null")
    void localDateNotNull() {
        Assertions.assertThrowsExactly(NullPointerException.class,
                () -> new CivilActionRecord(null, actionType, firstCitizen, secondCitizen));
    }

    @Test
    @DisplayName("Проверка типа действия на null")
    void actionTypeNotNull() {
        Assertions.assertThrowsExactly(NullPointerException.class,
                () -> new CivilActionRecord(actionDate, null, firstCitizen, secondCitizen));
        }

    @Test
    @DisplayName("Проверка гражданина на null")
    void citizenNotNull() {
        Assertions.assertThrowsExactly(NullPointerException.class,
                () -> new CivilActionRecord(actionDate, actionType, null));
    }

    @Test
    @DisplayName("Проверка на пустой список")
    void emptyCitizenListSuccess() {
        CivilActionRecord emptyListInRecord = new CivilActionRecord(actionDate, actionType);

        Assertions.assertNotNull(emptyListInRecord.getCitizenList(),
                "Список граждан не должен быть null");
        Assertions.assertTrue(emptyListInRecord.getCitizenList().isEmpty(),
                "Список граждан не должен быть пустым");
    }

    @Test
    @DisplayName("Соответствие установленной даты и даты из записи")
    void actionDateInRecordSuccess() {
        Assertions.assertEquals(actionDate, civilActionRecord1.getActionDate(),
                "Дата должна совпадать с установленной датой");
        Assertions.assertEquals(actionDate.hashCode(), civilActionRecord1.getActionDate().hashCode(),
                "hash дат должен совпадать");
    }

    @Test
    @DisplayName("Соответствие типа и типа из записи")
    void actionTypeInRecordSuccess() {
        Assertions.assertEquals(actionType, civilActionRecord1.getActionType(),
                "Тип действия должен совпадать с установленным типом действия");
        Assertions.assertEquals(actionType.hashCode(), civilActionRecord1.getActionType().hashCode(),
                "hash типа действия должен совпадать");
    }

    @Test
    @DisplayName("Проверка списка граждан")
    void checkListInRecordSuccess() {
        List<Citizen> citizenList = civilActionRecord1.getCitizenList();
        Assertions.assertEquals(2, citizenList.size(), "Несоответствие длины списка");
        Assertions.assertEquals(firstCitizen, citizenList.get(0), "Несоответствие 1 гражданина в списке");
        Assertions.assertEquals(secondCitizen, citizenList.get(1), "Несоответствие 2 гражданина в списке");
        Assertions.assertEquals(citizenList.hashCode(), civilActionRecord1.getCitizenList().hashCode(),
                "hash списков должен совпадать");
    }

    @Test
    @DisplayName("Равенство на Equals и hash")
    void equalsRecord() {
        Assertions.assertEquals(civilActionRecord1, civilActionRecord1, "Записи должны быть равны");
        Assertions.assertEquals(civilActionRecord1.hashCode(), civilActionRecord1.hashCode());
    }

    @Test
    @DisplayName("Equals должен быть симметричным")
    void equalsSymmetric() {
        Assertions.assertEquals(civilActionRecord1, civilActionRecord2, "Equals должен быть симметричным");
        Assertions.assertEquals(civilActionRecord2, civilActionRecord1, "Equals должен быть симметричным");
    }

    @Test
    @DisplayName("Equals должен быть транзитивным")
    void equalsTrans() {
        Assertions.assertEquals(civilActionRecord1, civilActionRecord2, "Equals должен быть транзитивным");
        Assertions.assertEquals(civilActionRecord2, civilActionRecord3, "Equals должен быть транзитивным");
        Assertions.assertEquals(civilActionRecord3, civilActionRecord1, "Equals должен быть транзитивным");
    }

    @Test
    @DisplayName("hash у равных объектов, должен совпадать")
    void hashCodeEquality() {
        Assertions.assertEquals(civilActionRecord1.hashCode(), civilActionRecord2.hashCode(),
                "hash записей должен совпадать");
    }

    @Test
    @DisplayName("hash у не равных объектов должен быть разным")
    void hashCodeDifferent() {
        Assertions.assertNotEquals(civilActionRecord1.hashCode(), civilActionRecordNotEqual.hashCode(),
                "hash должен быть разным");
    }
}