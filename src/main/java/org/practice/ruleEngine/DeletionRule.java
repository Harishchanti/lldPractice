package org.practice.ruleEngine;

public interface DeletionRule {
    boolean shouldDelete(User user, EvaluationContext context);
}
