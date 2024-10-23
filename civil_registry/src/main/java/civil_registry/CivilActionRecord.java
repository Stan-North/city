package civil_registry;

import Human.Human;
import citizen.Citizen;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Запись гражданского действия
 */

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CivilActionRecord {

    LocalDate actionDate;
    CivilActionType actionType;
    List<Citizen> citizenList;

    public CivilActionRecord(LocalDate actionDate, CivilActionType actionType, Citizen... citizens) {
        this.actionDate = actionDate;
        this.actionType = actionType;
        this.citizenList = Arrays.asList(citizens);
    }
}
