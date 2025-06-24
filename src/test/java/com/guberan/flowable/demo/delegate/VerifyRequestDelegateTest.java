package com.guberan.flowable.demo.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class VerifyRequestDelegateTest {

    @Test
    void execute() {
        var execution = mock(DelegateExecution.class);
        when(execution.getVariable("demandeId")).thenReturn("CDCG-123");

        var delegate = new VerifyRequestDelegate();
        delegate.execute(execution);

        verify(execution).setVariable(eq("verified"), eq(true));
    }
}