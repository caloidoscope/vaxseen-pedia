package org.caloidoscope.vaxseen.audit;

import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.entity.User;
import org.caloidoscope.vaxseen.util.CurrentUserHolder;
import org.hibernate.envers.RevisionListener;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditLogListener implements RevisionListener {

    @Override
    public void newRevision(Object auditLogEntity) {
        AuditLog auditLog = (AuditLog) auditLogEntity;
        auditLog.setAuthor(Optional.ofNullable(CurrentUserHolder.get()).map(User::getUsername).orElse("SYSTEM"));
    }
}
