package civil_registry;

import citizen.Citizen;
import citizen.MaritalStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Getter
public class CivilRegistry {

    private static final String PATTERN_FROM_DATE = "dd/MM/yyyy";
    private static final String PATTERN_FROM_STATISTICS = """
            Статистика по ЗАГС: %s
            Дата %s: количество свадеб - %d, количество рвзводов - %d, количество рождений - %d.
            """;
    private String civilRegistryName;
    private HashMap<LocalDate, ArrayList<CivilActionRecord>> civilActionRecords;

    /**
     * Конструктор для ЗАГС
     */
    public CivilRegistry(String civilRegistryName) {
        this.civilRegistryName = civilRegistryName;
        this.civilActionRecords = new HashMap<>();
    }

    /**
     * Добавление довой записи в список
     */
    public void addActionInRecord(CivilActionRecord record) {
        if (!this.civilActionRecords.containsKey(record.getActionDate())) {
            this.civilActionRecords.put(record.getActionDate(), new ArrayList<>());
        }

        this.civilActionRecords.get(record.getActionDate()).add(record);
    }

    /**
     * Метод получения количества записей на переданную дату по типу гражданского действия
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
        male.setMaritalStatus(MaritalStatus.MARRIED);
        female.setMaritalStatus(MaritalStatus.MARRIED);
        addActionInRecord(record);
    }

    /**
     * Метод расторжение брака
     */
    public void divorceRegistration(Citizen male, Citizen female, LocalDate dateRecord) {
        CivilActionRecord record = new CivilActionRecord(dateRecord, CivilActionType.DIVORCE_REGISTRATION,
                male, female);
        male.setMaritalStatus(MaritalStatus.DIVORCED);
        female.setMaritalStatus(MaritalStatus.DIVORCED);
        addActionInRecord(record);
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
}
