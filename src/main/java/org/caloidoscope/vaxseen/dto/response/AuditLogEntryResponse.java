package org.caloidoscope.vaxseen.dto.response;

import java.time.LocalDateTime;

public record AuditLogEntryResponse(
        String description,
        String author,
        LocalDateTime timestamp
) {
}
