package org.erhc.orderflow.core.domain.model;

public record AuditChange(
        String field,
        String oldValue,
        String newValue
) {}
