package Human;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Human {
    private static final String SPACE = " ";
    private static final String GENDER_EXCEPTION_MESSAGE = "У родителей одинаковый пол";
    @NonNull
    String firstName; //имя
    @NonNull
    String lastName;
    @NonNull// фамилия
    String middleName;
    @NonNull// отчество
    Gender gender;
    Human father;
    Human mother;
    List<Human> children;

    /**
     * Приводит строку к правильному виду
     */
    private static String transformText(String text) {
        String result = text.toLowerCase();
        return StringUtils.capitalize(result);
    }

    private Human(HumanBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.gender = builder.gender;
        this.father = builder.father;
        this.mother = builder.father;
        this.children = builder.children;
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
    public Human makeChild(String name, String secondName, String thirdName, Gender gender, Human otherParent) {
        if (isParentsHasDifferentGender(this, otherParent)) {
            Human child = new Human.HumanBuilder(name, secondName, thirdName, gender).build();
            setParentsAndChild(this, otherParent, child);
            return child;
        } else {
            throw new GenderEqualsException(GENDER_EXCEPTION_MESSAGE);
        }
    }

    //2.4 У человека есть функция "Получить полное имя": возвращается строка ФИО.
    /**
     * Получение полного имени
     */
    public String getFullName() {
        return lastName + SPACE + firstName + SPACE + middleName;
    }

    /**
     * билдер для человека
     */
    @SuppressWarnings("checkstyle:LineLength")
    public static class HumanBuilder {
        @NonNull
        String firstName;
        @NonNull
        String lastName;
        @NonNull
        String middleName;
        @NonNull// отчество
        Gender gender;
        Human father;
        Human mother;
        List<Human> children;

        public HumanBuilder(
                @NonNull String firstName, @NonNull String lastName, @NonNull String middleName, Gender gender) {
            this.firstName = transformText(firstName);
            this.lastName = transformText(lastName);
            this.middleName = transformText(middleName);
            this.gender = gender;
        }

        public HumanBuilder father(Human father) {
            this.father = father;
            return this;
        }

        public HumanBuilder mother(Human mother) {
            this.mother = mother;
            return this;
        }

        public HumanBuilder children(Human... children) {
            List<Human> childrenList = List.of(children);
            this.children.addAll(childrenList);
            return this;
        }

        public Human build() {
            this.children = new ArrayList<>();
            return new Human(this);
        }
    }

}
