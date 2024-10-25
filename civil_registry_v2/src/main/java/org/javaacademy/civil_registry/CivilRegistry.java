package org.javaacademy.civil_registry;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CivilRegistry {
    static final String MESSAGE_IS_MARRIED = "Кто то из кандидатов состоит в браке";
    static final String MESSAGE_NOT_MARRIED = "Кто то из кандидатов не состоит в браке";
    static final String PATTERN_FROM_DATE = "dd/MM/yyyy";
    static final String PATTERN_FROM_STATISTICS = """
            Статистика по ЗАГС: %s
            Дата %s: количество свадеб - %d, количество разводов - %d, количество рождений - %d.
            """;
    String civilRegistryName;
    HashMap<LocalDate, ArrayList<CivilActionRecord>> civilActionRecords;

    /**
     * Конструктор для ЗАГС.
     */
    public CivilRegistry(String civilRegistryName) {
        this.civilRegistryName = civilRegistryName;
        this.civilActionRecords = new HashMap<>();
    }

    /**
     * Внутренний метод добавление записи в список.
     */
    private void addActionInRecord(CivilActionRecord record) {
        if (!this.civilActionRecords.containsKey(record.getActionDate())) {
            this.civilActionRecords.put(record.getActionDate(), new ArrayList<>());
        }
        this.civilActionRecords.get(record.getActionDate()).add(record);
    }

    /**
     * Внутренний метод получения количества записей на переданную дату по типу гражданского действия.
     */
    private Long getCountTypeActionFromRecords(LocalDate statisticsDate, CivilActionType actionType) {
        return this.civilActionRecords.get(statisticsDate).stream()
                .filter(record -> record.getActionType().equals(actionType))
                .count();
    }

    /**
     * Метод Регистрация ребенка.
     */
    public void childRegistration(Citizen child, Citizen father, Citizen mother, LocalDate dateRecord) {
        CivilActionRecord record = new CivilActionRecord(dateRecord, CivilActionType.BIRTH_REGISTRATION,
                child, father, mother);
        addActionInRecord(record);
    }

    /**
     * Метод Регистрация брака.
     */
    public void marriageRegistration(Citizen male, Citizen female, LocalDate dateRecord)
            throws CitizenIsMarriedException {
        CivilActionRecord record = new CivilActionRecord(dateRecord, CivilActionType.MARRIAGE_REGISTRATION,
                male, female);
        if (isAnyoneMarried(male, female)) {
            throw new CitizenIsMarriedException(MESSAGE_IS_MARRIED);
        }
        addActionInRecord(record);
        setMaritalStatus(MaritalStatus.MARRIED, male, female);
        setSpouse(male, female);
    }

    /**
     * Метод расторжение брака.
     */
    public void divorceRegistration(Citizen male, Citizen female, LocalDate dateRecord)
            throws CitizenIsMarriedException {
        CivilActionRecord record = new CivilActionRecord(dateRecord, CivilActionType.DIVORCE_REGISTRATION,
                male, female);
        if (isAnyoneNotMarried(male, female)) {
            throw new CitizenIsMarriedException(MESSAGE_NOT_MARRIED);
        }
        addActionInRecord(record);
        removeSpouse(male, female);
        setMaritalStatus(MaritalStatus.DIVORCED, male, female);
    }

    /**
     * Метод Получения статистики на указанную дату.
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
     * Внутренний метод проверки состоит кто-то кандидатов в браке или нет.
     */
    private boolean isAnyoneMarried(Citizen... citizens) {
        return Arrays.stream(citizens)
                .anyMatch(citizen -> citizen.getMaritalStatus() == MaritalStatus.MARRIED);
    }

    /**
     * Внутренний метод проверки разведен или не в браке, кто-то из кандидатов.
     */
    private boolean isAnyoneNotMarried(Citizen... citizens) {
        return Arrays.stream(citizens)
                .anyMatch(citizen -> citizen.getMaritalStatus() != MaritalStatus.MARRIED);
    }

    /**
     * Внутренний метод обнуляет супругов(spouse).
     */
    private void removeSpouse(Citizen... citizens) {
        Arrays.stream(citizens)
                .forEach(citizen -> citizen.setSpouse(null));
    }

    /**
     * Внутренний метод изменяет семейное положение(MaritalStatus).
     */
    private void setMaritalStatus(MaritalStatus status, Citizen... citizens) {
        Arrays.stream(citizens)
                .forEach(citizen -> citizen.setMaritalStatus(status));
    }

    /**
     * Внутренний метод устанавливает супругов(spouse).
     */
    private void setSpouse(Citizen firstCandidate, Citizen secondCandidate) {
        firstCandidate.setSpouse(secondCandidate);
        secondCandidate.setSpouse(firstCandidate);
    }
}
