package task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Setter(value = AccessLevel.PROTECTED)
@Getter()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    String description;
    TaskStatusType status;
    Integer laborHours;

    public Task(String description) {
        this.description = description;
    }
}
