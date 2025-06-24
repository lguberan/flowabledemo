package com.guberan.flowable.demo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

@Slf4j
public class VerifyRequestDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String demandeId = (String) execution.getVariable("demandeId");
        log.info("[VerifyRequestDelegate] Vérification de la demande : {}", demandeId);
        execution.setVariable("verified", true);
    }
}