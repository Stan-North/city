package com.javaacademy.unit.civil_registry;

import com.javaacademy.unit.util.TestUtil;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.civil_registry.CitizenIsMarriedException;
import org.javaacademy.civil_registry.CivilActionRecord;
import org.javaacademy.civil_registry.CivilActionType;
import org.javaacademy.civil_registry.CivilRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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

        Assertions.assertEquals(expected, man.getMaritalStatus(), "Не состоит в браке");
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
        Assertions.assertNull(woman.getSpouse(), "Не удален супруг после развода");
    }

    @SneakyThrows(Exception.class)
    private CivilActionRecord getCivilActionRecordByIndexZero() {
        Field civilActionRecordsField = CivilRegistry.class.getDeclaredField("civilActionRecords");
        civilActionRecordsField.setAccessible(true);
        TreeMap<LocalDate, ArrayList<CivilActionRecord>> civilActionRecords =
                (TreeMap) civilActionRecordsField.get(civilRegistry);
        ArrayList<CivilActionRecord> civilActionRecordsList = civilActionRecords.get(date);

        return civilActionRecordsList.get(0);
    }

    @Test
    @DisplayName("Добавление действия в журнал регистрации")
    public void childRegistrationAddActionInRecordSuccess() {
        CivilActionRecord expected =
                new CivilActionRecord(date, CivilActionType.BIRTH_REGISTRATION, child, man, woman);

        civilRegistry.childRegistration(child, man, woman, date);
        CivilActionRecord actual = getCivilActionRecordByIndexZero();

        Assertions.assertEquals(expected, actual, "Запись в акте гражданского состояния не равна");
    }

    @Test
    @SneakyThrows(CitizenIsMarriedException.class)
    @DisplayName("Добавление действия в журнал регистрации")
    public void marriageRegistrationAddActionInRecordSuccess() {
        CivilActionRecord expected =
                new CivilActionRecord(date, CivilActionType.MARRIAGE_REGISTRATION, man, woman);

        civilRegistry.marriageRegistration(man, woman, date);
        CivilActionRecord actual = getCivilActionRecordByIndexZero();

        Assertions.assertEquals(expected, actual, "Запись в акте гражданского состояния не равна");
    }

    @Test
    @SneakyThrows(CitizenIsMarriedException.class)
    @DisplayName("Добавление действия в журнал регистрации")
    public void divorceRegistrationAddActionInRecordSuccess() {
        man.setMaritalStatus(MaritalStatus.MARRIED);
        woman.setMaritalStatus(MaritalStatus.MARRIED);
        man.setSpouse(woman);
        woman.setSpouse(man);
        CivilActionRecord expected =
                new CivilActionRecord(date, CivilActionType.DIVORCE_REGISTRATION, man, woman);

        civilRegistry.divorceRegistration(man, woman, date);
        CivilActionRecord actual = getCivilActionRecordByIndexZero();

        Assertions.assertEquals(expected, actual, "Запись в акте гражданского состояния не равна");
    }
}