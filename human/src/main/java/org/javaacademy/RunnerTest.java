package org.javaacademy;

import org.javaacademy.human.Human;
import org.javaacademy.human.Gender;

public class RunnerTest {
    public static void main(String[] args) {
        Human man = new Human("Иван", "Иванов", "Иванович", Gender.MALE);
        Human woman = new Human("Екатерина", "Петрова", "Ивановна", Gender.FEMALE);
        Human child = man.makeChild("Петр", "Иванов", "Иванович", Gender.MALE, woman);
        System.out.println(child.getFullName());

    }
}
