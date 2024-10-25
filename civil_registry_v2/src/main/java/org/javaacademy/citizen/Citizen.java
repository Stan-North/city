package org.javaacademy.citizen;

import human.Gender;
import human.Human;
import lombok.Setter;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Citizen extends Human {
    static final String SPOUSE_NOT_FOUND = "Не указан супруг(а)";
    MaritalStatus maritalStatus;
    Citizen spouse;

    /**
     * Конструктор
     */
    public Citizen(@NonNull String firstName, @NonNull String lastName,
                   @NonNull String middleName, @NonNull Gender gender, @NonNull MaritalStatus maritalStatus,
                   Citizen spouse) {
        super(firstName, lastName, middleName, gender);
        this.maritalStatus = maritalStatus;

        if (maritalStatus == MaritalStatus.MARRIED) {
            checkSpouse(spouse);
            this.spouse = spouse;
        }
    }

    /**
     * Конструктор для преобразования ребенка(Human) в гражданина(Citizen)
     */
    private Citizen(Human child) {
        super(child.getFirstName(), child.getLastName(), child.getMiddleName(), child.getGender());
        this.setFather(child.getFather());
        this.setMother(child.getMother());
        this.maritalStatus = MaritalStatus.NOT_MARRIED;
        this.spouse = null;
    }

    /**
     * Переопределение метода для получения ребенка гражданином(Citizen)
     */
    @Override
    public Citizen makeChild(@NonNull String name, @NonNull String secondName, @NonNull String thirdName,
                             @NonNull Gender gender, @NonNull Human otherParent) {
        return new Citizen(super.makeChild(name, secondName, thirdName, gender, otherParent));
    }

    /**
     * Внутренний метод, проверяет передан ли супруг, если maritalStatus == MaritalStatus.MARRIED
     */
    @SneakyThrows
    private void checkSpouse(Human spouse) {
        if (spouse == null) {
            throw new SponceNotFoundException(SPOUSE_NOT_FOUND);
        }
    }
}
