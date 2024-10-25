package org.javaacademy.civil_registry;

import lombok.SneakyThrows;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CivilRegistry {
    private static final String PATTERN_FROM_DATE = "dd/MM/yyyy";
    private static final String PATTERN_FROM_STATISTICS = """
            Статистика по ЗАГС: %s
            Дата %s: количество свадеб - %d, количество разводов - %d, количество рождений - %d.
            """;
    private String civilRegistryName;
    private HashMap<LocalDate, ArrayList<CivilActionRecord>> civilActionRecords;

    /**
     * Конструктор для ЗАГС.
     */
    public CivilRegistry(String civilRegistryName) {
        this.civilRegistryName = civilRegistryName;
        this.civilActionRecords = new HashMap<>();
    }

    /**
     * Добавление записи в список
     */
    public void addActionInRecord(CivilActionRecord record) {
        if (!this.civilActionRecords.containsKey(record.getActionDate())) {
            this.civilActionRecords.put(record.getActionDate(), new ArrayList<>());
        }
        this.civilActionRecords.get(record.getActionDate()).add(record);
    }

    /**
     * Внутренний метод получения количества записей на переданную дату по типу гражданского действия
     */
    private Long getCountTypeActionFromRecords(LocalDate statisticsDate, CivilActionType actionType) {
        return this.civilActionRecords.get(statisticsDate).stream()
                .filter(record -> record.getActionType().equals(actionType))
                .count();
    }

    /**
     * Метод Регистрация ребенка
     */
    public void childRegistration(Citizen child, Citizen firstParent, Citizen secondParent, LocalDate dateRecord) {
        CivilActionRecord record = new CivilActionRecord(dateRecord, CivilActionType.BIRTH_REGISTRATION,
                child, firstParent, secondParent);
        addActionInRecord(record);
    }

    /**
     * Метод Регистрация брака
     */
    public void marriageRegistration(Citizen male, Citizen female, LocalDate dateRecord) {
        CivilActionRecord record = new CivilActionRecord(dateRecord, CivilActionType.MARRIAGE_REGISTRATION,
                male, female);
        checkMarriedStatus(male, female);
        addActionInRecord(record);
        setMaritalStatus(MaritalStatus.MARRIED, male, female);
        setSpouse(male, female);
    }

    /**
     * Метод расторжение брака
     */
    public void divorceRegistration(Citizen male, Citizen female, LocalDate dateRecord) {
        CivilActionRecord record = new CivilActionRecord(dateRecord, CivilActionType.DIVORCE_REGISTRATION,
                male, female);
        checkDivorceStatus(male, female);
        addActionInRecord(record);
        removeSpouse(male, female);
        setMaritalStatus(MaritalStatus.DIVORCED, male, female);
    }

    /**
     * Метод Получения статистики на указанную дату
     */
    public void statisticsOfDate(LocalDate statisticsDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FROM_DATE);
        System.out.printf(PATTERN_FROM_STATISTICS, civilRegistryName,
                statisticsDate.format(formatter),
                getCountTypeActionFromRecords(statisticsDate, CivilActionType.MARRIAGE_REGISTRATION),
                getCountTypeActionFromRecords(statisticsDate, CivilActionType.DIVORCE_REGISTRATION),
                getCountTypeActionFromRecords(statisticsDate, CivilActionType.BIRTH_REGISTRATION));
    }

    /**
     * Внутренний метод проверки состоит кто-то кандидатов в браке или нет
     */
    @SneakyThrows
    private void checkMarriedStatus(Citizen firstCandidate, Citizen secondCandidate) {
        if (firstCandidate.getMaritalStatus() == MaritalStatus.MARRIED) {
            throw new CitizenIsMarriedException("%s состоит в браке"
                    .formatted(firstCandidate.getFullName()));
        }
        if (secondCandidate.getMaritalStatus() == MaritalStatus.MARRIED) {
            throw new CitizenIsMarriedException("%s состоит в браке"
                    .formatted(firstCandidate.getFullName()));
        }
    }

    /**
     * Внутренний метод проверки разведен или не в браке, кто то из кандидатов
     */
    @SneakyThrows
    private void checkDivorceStatus(Citizen firstCandidate, Citizen secondCandidate) {
        if (firstCandidate.getMaritalStatus() != MaritalStatus.MARRIED) {
            throw new CitizenIsMarriedException("%s не состоит в браке"
                    .formatted(firstCandidate.getFullName()));
        }
        if (secondCandidate.getMaritalStatus() != MaritalStatus.MARRIED) {
            throw new CitizenIsMarriedException("%s не состоит в браке"
                    .formatted(firstCandidate.getFullName()));
        }
    }

    /**
     * Внутренний метод обнуляет супругов(spouse)
     */
    private void removeSpouse(Citizen... citizens) {
        Arrays.stream(citizens)
                .forEach(citizen -> citizen.setSpouse(null));
    }

    /**
     * Внутренний метод изменяет семейное положение(MaritalStatus)
     */
    private void setMaritalStatus(MaritalStatus status, Citizen... citizens) {
        Arrays.stream(citizens)
                .forEach(citizen -> citizen.setMaritalStatus(status));
    }

    /**
     * Внутренний метод устанавливает супругов(spouse)
     */
    private void setSpouse(Citizen firstCandidate, Citizen secondCandidate) {
        firstCandidate.setSpouse(secondCandidate);
        secondCandidate.setSpouse(firstCandidate);
    }
}
