import Human.*;


public class RunnerTest {
    public static void main(String[] args) {
        Human man = new Human.HumanBuilder(
                "Иван", "Иванов", "Иванович", Gender.MALE).build();

        Human woman = new Human.HumanBuilder(
                "Екатерина", "Петрова", "Ивановна", Gender.FEMALE).build();

        Human child = man.makeChild(
                "Петр", "Иванов", "Иванович", Gender.MALE, woman);

        System.out.println(child.getFullName());
    }
}
