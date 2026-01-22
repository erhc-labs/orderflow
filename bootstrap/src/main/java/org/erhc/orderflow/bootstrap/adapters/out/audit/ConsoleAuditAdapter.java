package org.erhc.orderflow.bootstrap.adapters.out.audit;

import org.erhc.orderflow.core.domain.model.AuditEntry;
import org.erhc.orderflow.core.ports.out.AuditPort;
import org.springframework.stereotype.Component;

@Component
public class ConsoleAuditAdapter implements AuditPort {

    @Override
    public void register(AuditEntry auditEntry) {
        System.out.println(
                "AUDIT -> " + auditEntry.action()
                +" | " + auditEntry.entityName()
                +" | " + auditEntry.entityId()
        );
    }
}
