package org.practice.ruleEngine;

import java.util.HashMap;
import java.util.Map;

public class RuleFactory {

    static Map<RuleType, DeletionRule> map = new HashMap<>();

    static {
        map.put(RuleType.INVALID_DAYS, new InactiveDaysRule(90));
        map.put(RuleType.INVALID_MONTH, new InactiveMonthsRule(1));
    }

    static DeletionRule getDeletionRule(RuleType ruleType) throws Exception {
        if (!map.containsKey(ruleType))
            throw new Exception();
        return map.get(ruleType);
    }
}

