package org.caloidoscope.vaxseen.audit;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(AuditLogListener.class)
public class AuditLog extends DefaultRevisionEntity {
    @Getter
    @Setter
    private String author;
}
