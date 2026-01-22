package org.erhc.orderflow.core.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class AuditEntry {

    private final String entityName;
    private final String entityId;
    private final AuditAction action;
    private final String performedBy;
    private final LocalDateTime timestamp;
    private final List<AuditChange> changes;


    public AuditEntry
    (
            String entityName,
            String entityId,
            AuditAction action,
            String performedBy,
            List<AuditChange> changes
    )
    {
        this.entityName = entityName;
        this.entityId = entityId;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = LocalDateTime.now();
        this.changes = changes;
    }

    public String entityName() { return entityName;}
    public String entityId() { return entityId;}
    public AuditAction action() { return action;}
    public String performedBy() { return performedBy;}
    public LocalDateTime timestamp() { return timestamp;}
    public List<AuditChange> changes() { return changes;}

}
