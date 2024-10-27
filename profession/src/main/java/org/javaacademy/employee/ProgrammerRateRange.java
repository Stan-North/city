package org.javaacademy.employee;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProgrammerRateRange {

    MIN_RATE(new BigDecimal(1500)),
    MAX_RATE(new BigDecimal(2000));

    BigDecimal rate;
}
