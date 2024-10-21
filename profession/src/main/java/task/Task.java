package task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Setter(value = AccessLevel.PROTECTED)
@Getter(value = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    private String description;
    private TaskStatusType status;
    private Integer laborHours;

    public Task(String description) {
        this.description = description;
    }
}
