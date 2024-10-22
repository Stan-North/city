package civil_registry;

import Human.Human;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CivilActionRecord {

    LocalDate actionDate;
    CivilActionType actionType;
    List<Human> humanList;

    public CivilActionRecord(LocalDate actionDate, CivilActionType actionType, Human... humans) {
        this.actionDate = actionDate;
        this.actionType = actionType;
        this.humanList = Arrays.asList(humans);
    }
}
