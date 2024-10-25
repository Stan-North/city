package org.javaacademy;

import human.Gender;
import org.javaacademy.citizen.Citizen;
import org.javaacademy.citizen.MaritalStatus;
import org.javaacademy.civil_registry.CitizenIsMarriedException;
import org.javaacademy.civil_registry.CivilRegistry;

import java.time.LocalDate;

public class RunnerProm {
    public static void main(String[] args) throws CitizenIsMarriedException {
        Citizen man = new Citizen("Иван", "Иванов1", "Иванович",
                Gender.MALE, MaritalStatus.NOT_MARRIED, null);
        Citizen woman = new Citizen("Екатерина", "Петрова", "Ивановна",
                Gender.FEMALE, MaritalStatus.NOT_MARRIED, null);
        Citizen child = man.makeChild("Петр", "Иванов",
                "Иванович", Gender.MALE, woman);

        Citizen man2 = new Citizen("Иван", "Иванов2", "Иванович",
                Gender.MALE, MaritalStatus.NOT_MARRIED, null);
        Citizen woman2 = new Citizen("Екатерина", "Петрова", "Ивановна",
                Gender.FEMALE, MaritalStatus.NOT_MARRIED, null);


        CivilRegistry civilRegistry = new CivilRegistry(args[0]);

        civilRegistry.marriageRegistration(man, woman, LocalDate.now());
        civilRegistry.marriageRegistration(man2, woman2, LocalDate.now());


        civilRegistry.divorceRegistration(man2, woman2, LocalDate.now());

        civilRegistry.childRegistration(child, man, woman, LocalDate.now());
        civilRegistry.childRegistration(child, man, woman, LocalDate.now());
        civilRegistry.childRegistration(child, man, woman, LocalDate.now());

        civilRegistry.statisticsOfDate(LocalDate.now());
    }
}
