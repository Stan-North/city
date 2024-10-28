package com.javaacademy.unit.util;

import lombok.experimental.UtilityClass;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.human.Gender;

@UtilityClass
public class TestUtil {
    public Citizen getMan() {
        return new Citizen(
                "Отец",
                "Отцов",
                "Отцович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    public Citizen getWoman() {
        return new Citizen(
                "Мать",
                "Родящая",
                "Живо",
                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    public Citizen getMan2() {
        return new Citizen(
                "Иван",
                "Иванов2",
                "Иванович",
                Gender.MALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    public Citizen getWoman2() {
        return new Citizen(
                "Екатерина",
                "Петрова",
                "Ивановна",
                Gender.FEMALE,
                MaritalStatus.NOT_MARRIED,
                null);
    }

    public Citizen getChild() {
        Citizen man = getMan();
        Citizen woman = getWoman();
        return man.makeChild(
                "Сын",
                "Сынов",
                "Сынович",
                Gender.MALE,
                woman);
    }
}
