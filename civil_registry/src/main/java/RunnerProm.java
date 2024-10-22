import Human.Human;
import Human.Gender;
import citizen.Citizen;
import citizen.MaritalStatus;
import civil_registry.CivilRegistry;

import java.time.LocalDate;
import java.util.Date;

public class RunnerProm {
    public static void main(String[] args) {
        Citizen man = new Citizen("Иван", "Иванов1", "Иванович",
                Gender.MALE, MaritalStatus.NOT_MARRIED, null);
        Citizen woman = new Citizen("Екатерина", "Петрова", "Ивановна",
                Gender.FEMALE, MaritalStatus.NOT_MARRIED, null);
        Citizen child = Citizen.makeCitizenFromChild(man.makeChild("Петр", "Иванов",
                "Иванович", Gender.MALE, woman));

        Citizen man2 = new Citizen("Иван", "Иванов2", "Иванович",
                Gender.MALE, MaritalStatus.NOT_MARRIED, null);
        Citizen man3 = new Citizen("Иван", "Иванов3", "Иванович",
                Gender.MALE, MaritalStatus.NOT_MARRIED, null);
        Citizen woman2 = new Citizen("Екатерина", "Петрова", "Ивановна",
                Gender.FEMALE, MaritalStatus.NOT_MARRIED, null);
        Citizen woman3 = new Citizen("Екатерина", "Петрова", "Ивановна",
                Gender.FEMALE, MaritalStatus.NOT_MARRIED, null);

        CivilRegistry civilRegistry = new CivilRegistry("args[0]");
        civilRegistry.marriageRegistration(man, woman, LocalDate.now());
        civilRegistry.marriageRegistration(man2, woman2, LocalDate.now());


        civilRegistry.divorceRegistration(man2, woman2, LocalDate.now());

        civilRegistry.childRegistration(child, man, woman, LocalDate.now());
        civilRegistry.childRegistration(child, man, woman, LocalDate.now());
        civilRegistry.childRegistration(child, man, woman, LocalDate.now());

        civilRegistry.statisticsOfDate(LocalDate.now());
    }
}
