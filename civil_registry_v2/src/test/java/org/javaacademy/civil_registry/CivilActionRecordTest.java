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
    CivilActionRecord civilActionRecord;

    @BeforeEach
    void setUp() {
        actionDate = LocalDate.now();
        actionType = CivilActionType.MARRIAGE_REGISTRATION;
        firstCitizen = new Citizen("Гарри", "Картошкин", "Вашингтонович", Gender.MALE,
                MaritalStatus.NOT_MARRIED, null);
        secondCitizen = new Citizen("Валентина", "Картошкина", "Семеновна", Gender.FEMALE,
                MaritalStatus.NOT_MARRIED, null);
        civilActionRecord = new CivilActionRecord(actionDate, actionType, firstCitizen, secondCitizen);
    }

    @Test
    @DisplayName("Соответствие установленной даты и даты из записи")
    void actionDateInRecordSuccess() {
        Assertions.assertEquals(actionDate, civilActionRecord.getActionDate(),
                "Дата действия должна совпадать с установленной");
    }


    @Test
    @DisplayName("Соответствие типа и типа из записи")
    void actionTypeInRecordSuccess() {
        Assertions.assertEquals(actionType, civilActionRecord.getActionType(),
                "Тип действия должен совпадать с установленной");
    }

    @Test
    @DisplayName("Проверка списка граждан")
    void checkListInRecordSuccess() {
        List<Citizen> citizenList = civilActionRecord.getCitizenList();
        Assertions.assertEquals(2, citizenList.size(), "Несоответствие длины списка");
        Assertions.assertEquals(firstCitizen, citizenList.get(0), "Несоответствие 1 гражданина в списке");
        Assertions.assertEquals(secondCitizen, citizenList.get(1), "Несоответствие 2 гражданина в списке");
    }
}