package Human;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Human {
    private static final String SPACE = " ";
    private static final String GENDER_EXCEPTION_MESSAGE = "У родителей одинаковый пол";
    private static final String HUMAN_FIELDS_EXCEPTION_MESSAGE = "Поля: имя, фамилия, " +
            "отчество и пол не могут быть null";
    @NonNull
    final String firstName;
    @NonNull
    final String lastName;
    @NonNull
    final String middleName;
    @NonNull
    final Gender gender;
    @Setter
    Human father;
    @Setter
    Human mother;
    final List<Human> children;

    public Human(String firstName, String lastName, String middleName, Gender gender) {
        if ((firstName == null || lastName == null || middleName == null || gender == null)) {
            throw new HumanFieldsNullException(HUMAN_FIELDS_EXCEPTION_MESSAGE);
        }
        this.firstName = transformText(firstName);
        this.lastName = transformText(lastName);
        this.middleName = transformText(middleName);
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    /**
     * Приводит строку к правильному виду
     */
    private static String transformText(String text) {
        String result = text.toLowerCase();
        return StringUtils.capitalize(result);
    }


    /**
     * Метод, устанавливающий для обоих родителей - ребенка, а для ребенка - родителей
     */
    private void setParentsAndChild(Human firstParent, Human secondParent, Human child) {
        if (isParentsHasDifferentGender(firstParent, secondParent)) {
            addChildToParents(firstParent, secondParent, child);
            setMotherAndFather(firstParent, secondParent, child);
        } else {
            throw new GenderEqualsException(GENDER_EXCEPTION_MESSAGE);
        }
    }

    /**
     * проверка на разные пол у родителей
     */
    private boolean isParentsHasDifferentGender(Human firstParent, Human secondParent) {
        return !firstParent.getGender().equals(secondParent.getGender());
    }

    /**
     * Добавление ребенка родителям
     */
    private void addChildToParents(Human firstParent, Human secondParent, Human child) {
        firstParent.children.add(child);
        secondParent.children.add(child);
    }

    /**
     * добавление человеку родителей
     */
    private void setMotherAndFather(Human firstHuman, Human secondHuman, Human child) {
        if (firstHuman.getGender().equals(Gender.MALE)) {
            child.father = firstHuman;
            child.mother = secondHuman;
        } else {
            child.father = secondHuman;
            child.mother = firstHuman;
        }
    }

    /**
     * создание ребенка
     */
    public Human makeChild(@NonNull String name, @NonNull String secondName, @NonNull String thirdName,
                           @NonNull Gender gender, @NonNull Human otherParent) {
        if (isParentsHasDifferentGender(this, otherParent)) {
            Human child = new Human(name, secondName, thirdName, gender);
            setParentsAndChild(this, otherParent, child);
            return child;
        } else {
            throw new GenderEqualsException(GENDER_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Получение полного имени
     */
    public String getFullName() {
        return lastName + SPACE + firstName + SPACE + middleName;
    }
}
