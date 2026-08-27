package org.practice.ruleEngine;

import java.time.Duration;
import java.time.Instant;

public class InactiveDaysRule implements DeletionRule {
    private final int days;

    public InactiveDaysRule(int days) {
        this.days = days;
    }

    @Override
    public boolean shouldDelete(User user, EvaluationContext context) {
        Instant cutoff =
                context.getClock().instant().minus(Duration.ofDays(days));

        return user.getLastLoginAt().isBefore(cutoff);
    }
}
