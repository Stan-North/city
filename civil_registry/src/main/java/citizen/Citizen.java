package citizen;

import Human.Human;
import Human.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Citizen extends Human {
    static final String SPOUSE_NOT_FOUND = "Не указан супруг(а)";
    MaritalStatus maritalStatus;
    Human spouse;

    @SneakyThrows
    public Citizen(@NonNull String firstName, @NonNull String lastName,
                   @NonNull String middleName, @NonNull Gender gender, @NonNull MaritalStatus maritalStatus,
                   Human spouse) {
        super(firstName, lastName, middleName, gender);
        this.maritalStatus = maritalStatus;

        if (maritalStatus == MaritalStatus.MARRIED) {
            checkSpouse(spouse);
            this.spouse = spouse;
        }
    }

    @SneakyThrows
    private void checkSpouse(Human spouse) {
        if (spouse == null) {
            throw new SponceNotFoundException(SPOUSE_NOT_FOUND);
        }
    }
}
