package org.erhc.orderflow.core.application.service;

import org.erhc.orderflow.core.domain.model.AuditAction;
import org.erhc.orderflow.core.domain.model.AuditChange;
import org.erhc.orderflow.core.domain.model.AuditEntry;
import org.erhc.orderflow.core.ports.out.AuditPort;

import java.util.List;

public class AuditService {

    private final AuditPort auditPort;
    private final AuditDiffCalculator diffCalculator;



    public AuditService(AuditPort auditPort, AuditDiffCalculator diffCalculator) {

        this.auditPort = auditPort;
        this.diffCalculator = diffCalculator;
    }



    public void auditCreate(
            String entityName,
            String entityId,
            String user
    ){
        AuditEntry entry = new AuditEntry(
                entityName,
                entityId,
                AuditAction.CREATE,
                user,
                List.of()
        );
        auditPort.register(entry);
    }


    public void auditUpdate(
            String entityName,
            String entityId,
            String before,
            String after,
            String user
    ){
        List<AuditChange> changes = diffCalculator.calculate(before,after);

        AuditEntry entry = new AuditEntry(
                entityName,
                entityId,
                AuditAction.UPDATE,
                user,
                changes
        );
        auditPort.register(entry);
    }
}
