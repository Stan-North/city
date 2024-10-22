package civil_registry;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
public class CivilRegistry {

    private String civilRegistryName;
    private static HashMap<LocalDate, ArrayList<CivilActionRecord>> civilActionRecords;

    public CivilRegistry(String civilRegistryName) {
        this.civilRegistryName = civilRegistryName;
        civilActionRecords = new HashMap<>();
    }

    public static void addActionInRecord(CivilActionRecord record) {
        if (civilActionRecords.containsKey(record.getActionDate())) {
            civilActionRecords.get(record.getActionDate()).add(record);
        } else {
            civilActionRecords.put(record.getActionDate(), new ArrayList<>());
        }

    }
}
