package com.guberan.flowable.demo.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EligibilityCheckDelegateTest {

    private EligibilityCheckDelegate delegate;
    private DelegateExecution delegateExecution;

    @BeforeEach
    void setUp() {
        delegate = new EligibilityCheckDelegate();
        delegateExecution = mock(DelegateExecution.class);
    }

    @Test
    void execute() {
        when(delegateExecution.getVariable("demandeId")).thenReturn("CDCG-456");

        // When
        delegate.execute(delegateExecution);

        verify(delegateExecution).getVariable("demandeId");
        verify(delegateExecution).setVariable("eligible", true);
    }

}