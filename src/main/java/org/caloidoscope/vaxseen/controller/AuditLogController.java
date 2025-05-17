package org.caloidoscope.vaxseen.controller;

import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.audit.AuditLogService;
import org.caloidoscope.vaxseen.dto.response.AuditLogResponse;
import org.caloidoscope.vaxseen.util.CurrentUserHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditLogController {
    private final AuditLogService auditLogService;

    @GetMapping("/current-user")
    public ResponseEntity<AuditLogResponse> getCurrentUser() {
        return ResponseEntity.ok(auditLogService.getUserAuditLog(CurrentUserHolder.get()));
    }
}
