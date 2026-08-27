package org.practice.ruleEngine;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.*;

/*
Design an extensible system to manage user data deletion in compliance with GDPR regulations.
The system should identify and purge users who have been inactive for a configurable duration.

Requirements:
Rule Extensibility: The system must support various inactivity rules, such as deleting users who haven't logged in for 'N' days or 'M' months.
Compliance Triggers: The logic should be flexible enough to add future compliance rules without modifying the core deletion engine.
Class Structure: Define the necessary interfaces and concrete classes to model these rules and the evaluator that processes user records.
Data Integrity: Ensure the system can handle different date formats and rule parameters effectively.

 */
public class UserDeletionRuleEngine {

    public static void main(String[] args) throws Exception {

        // Fixed clock for deterministic behavior
        Clock clock = Clock.fixed(Instant.parse("2026-08-25T00:00:00Z"),
                ZoneOffset.UTC);

        EvaluationContext context =
                new EvaluationContext(clock, new HashMap<>());

        // Sample users
        User activeUser = new User("U1",
                Instant.parse("2026-08-01T00:00:00Z"), // recent login
                Instant.parse("2025-01-01T00:00:00Z"), false);

        User inactiveUser = new User("U2",
                Instant.parse("2025-01-01T00:00:00Z"), // very old login
                Instant.parse("2024-01-01T00:00:00Z"), false);

        User borderlineUser = new User("U3",
                Instant.parse("2026-05-27T00:00:00Z"), // ~90 days
                Instant.parse("2025-06-01T00:00:00Z"), false);

        // Rules
        List<RuleType> rules =
                List.of(RuleType.INVALID_MONTH, RuleType.INVALID_DAYS);

        DeletionRuleEngine engine = new DeletionRuleEngine(rules);

        // Execute tests
        evaluate(engine, activeUser, context);
        evaluate(engine, inactiveUser, context);
        evaluate(engine, borderlineUser, context);
    }

    private static void evaluate(DeletionRuleEngine engine, User user,
            EvaluationContext context) throws Exception {
        boolean shouldDelete = engine.shouldDelete(user, context);

        System.out.println(
                "User: " + user.getUserId() + " | LastLogin: " + user.getLastLoginAt() + " | ShouldDelete: " + shouldDelete);
    }

}
