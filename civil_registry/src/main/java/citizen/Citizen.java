package citizen;

import Human.Human;
import Human.Gender;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class Citizen extends Human {

    private MaritalStatus maritalStatus;
    private Human spouse;

    public Citizen(@NonNull String firstName, @NonNull String lastName,
                   @NonNull String middleName, @NonNull Gender gender, @NonNull MaritalStatus maritalStatus,
                   Human spouse) {
        super(firstName, lastName, middleName, gender);
        this.maritalStatus = maritalStatus;
        if (maritalStatus == MaritalStatus.MARRIED) {
            this.spouse = spouse;
        }
    }
}
