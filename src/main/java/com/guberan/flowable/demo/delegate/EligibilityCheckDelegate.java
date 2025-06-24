package com.guberan.flowable.demo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;


@Slf4j
public class EligibilityCheckDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String demandeId = (String) execution.getVariable("demandeId");

        // Logique métier ici (ex: vérifier les documents)
        boolean isEligible = true;

        log.info("[EligibilityCheckDelegate] ça {} pour {}", (isEligible ? "roule" : "ne marche pas"), demandeId);

        // Définir la variable 'eligible' dans le contexte du processus
        execution.setVariable("eligible", isEligible);
    }
}