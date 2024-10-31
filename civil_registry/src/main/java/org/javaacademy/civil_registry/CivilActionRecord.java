package org.javaacademy.civil_registry;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CivilActionRecord {
    LocalDate actionDate;
    CivilActionType actionType;
    List<Citizen> citizenList;

    public CivilActionRecord(@NonNull LocalDate actionDate, @NonNull CivilActionType actionType,
                             @NonNull Citizen... citizens) {
        this.actionDate = actionDate;
        this.actionType = actionType;
        this.citizenList = Arrays.asList(citizens);
    }
}
