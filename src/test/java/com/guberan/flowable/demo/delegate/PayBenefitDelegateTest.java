package com.guberan.flowable.demo.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PayBenefitDelegateTest {

    private PayBenefitDelegate delegate;
    private DelegateExecution delegateExecution;

    @BeforeEach
    void setUp() {
        delegate = new PayBenefitDelegate();
        delegateExecution = mock(DelegateExecution.class);
    }

    @Test
    void execute_shouldLogPaymentForDemande() {
        when(delegateExecution.getVariable("demandeId")).thenReturn("CDCG-789");

        // When
        delegate.execute(delegateExecution);

        verify(delegateExecution).getVariable("demandeId");
        verify(delegateExecution).setVariable("paid", true);
    }
}