package org.practice.ruleEngine;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class InactiveMonthsRule implements DeletionRule {
    private final int months;

    public InactiveMonthsRule(int months) {
        this.months = months;
    }

    @Override
    public boolean shouldDelete(User user, EvaluationContext context) {
        /*Instant cutoff = context.getClock()
                .instant().minus(Duration.)
                .minus(months, ChronoUnit.MONTHS);
        ZonedDateTime now = ZonedDateTime.now(context.getClock());
        ZonedDateTime cutoff = now.minusMonths(months);

        return user.getLastLoginAt().isBefore(cutoff);*/

        LocalDate now = LocalDate.now(context.getClock());
        LocalDate cutoff = now.minusMonths(months);

        return user.getLastLoginAt()
                .atZone(ZoneOffset.UTC)
                .toLocalDate()
                .isBefore(cutoff);
    }
}
