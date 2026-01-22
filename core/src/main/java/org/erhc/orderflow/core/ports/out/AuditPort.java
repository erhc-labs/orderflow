package org.erhc.orderflow.core.ports.out;

import org.erhc.orderflow.core.domain.model.AuditEntry;

public interface AuditPort {
    void register(AuditEntry auditEntry);
}
