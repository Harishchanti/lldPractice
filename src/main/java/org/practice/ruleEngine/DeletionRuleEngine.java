package org.practice.ruleEngine;

import java.util.List;

public class DeletionRuleEngine {
    private final List<RuleType> rules;

    public DeletionRuleEngine(List<RuleType> rules) {
        this.rules = rules;
    }

    public boolean shouldDelete(User user, EvaluationContext context)
            throws Exception {
        for (RuleType rule : rules) {
            if (!RuleFactory.getDeletionRule(rule).shouldDelete(user, context)) {
                return false; // AND logic
            }
        }
        return true;
    }
}
