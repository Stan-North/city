package org.javaacademy.civil_registry;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.javaacademy.citizen.Citizen;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
