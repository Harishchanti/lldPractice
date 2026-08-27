package org.practice.ruleEngine;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

public class EvaluationContext {
    private Clock clock; // for testability
    private Map<String, Object> config;

    public EvaluationContext(Clock clock, Map<String, Object> config) {
        this.clock = clock;
        this.config = config;
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}
