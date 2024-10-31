package org.javaacademy.employee.programmer;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static org.javaacademy.employee.programmer.Task.TaskStatusType.IN_PROGRESS;

@Setter(value = AccessLevel.PROTECTED)
@Getter()
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Task {
    String description;
    TaskStatusType status = IN_PROGRESS;
    Double laborHours;

    public Task(@NonNull String description, @NonNull Double laborHours) {
        this.description = description;
        this.laborHours = laborHours;
    }

    public enum TaskStatusType {
        IN_PROGRESS,
        COMPLETED;
    }
}
