package org.javaacademy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.javaacademy.employee.Manager;
import org.javaacademy.employee.Programmer;
import org.javaacademy.employee.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
    private static final Double INITIAL_TIME = 0.0;
    @Setter
    String name;
    @Setter
    Manager manager;
    final List<Programmer> programmersList;
    final HashMap<Programmer, List<Task>> completedTasks;
    final HashMap<Programmer, Double> timeTracking;
    @Setter
    BigDecimal expenses;

    public Company(BigDecimal programmersRate, Programmer... programmers) {
        programmersList = new ArrayList<>();
        completedTasks = new HashMap<>();
        timeTracking = new HashMap<>();
        Arrays.stream(programmers).forEach(programmer -> programmer.setRate(programmersRate));
        programmersList.addAll(Arrays.asList(programmers));
        programmersList.forEach(programmer -> completedTasks.put(programmer, new ArrayList<>()));
        programmersList.forEach(programmer -> timeTracking.put(programmer, INITIAL_TIME));
    }
}
