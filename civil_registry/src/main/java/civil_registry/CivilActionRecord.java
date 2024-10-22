package civil_registry;

import citizen.Citizen;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CivilActionRecord {

    LocalDate actionDate;
    CivilActionType actionType;
    List<Citizen> citizensList;

    public CivilActionRecord(LocalDate actionDate, CivilActionType actionType, Citizen... humans) {
        this.actionDate = actionDate;
        this.actionType = actionType;
        this.citizensList = Arrays.asList(humans);
    }
}
