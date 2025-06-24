package com.guberan.flowable.demo.dmn;

import org.flowable.dmn.api.DmnDecisionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EligibilityRuleTest {

    @Autowired
    private DmnDecisionService dmnRuleService;

    @Test
    public void testEligibilityAccepted() {
        Map<String, Object> inputVariables = Map.of(
                "age", 35,
                "revenu", 100000,
                "documents", "complets"
        );

        Map<String, Object> result = dmnRuleService.createExecuteDecisionBuilder()
                .decisionKey("eligibilite-decision")
                .variables(inputVariables)
                .executeWithSingleResult();
        
        assertThat(result)
                .containsEntry("eligible", true);
    }
}