package org.caloidoscope.vaxseen.dto.response;

import java.util.List;

public record AuditLogResponse (
        List<AuditLogEntryResponse> logs
){
}
