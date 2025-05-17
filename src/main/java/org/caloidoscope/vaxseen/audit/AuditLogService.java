package org.caloidoscope.vaxseen.audit;

import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.dto.response.AuditLogEntryResponse;
import org.caloidoscope.vaxseen.dto.response.AuditLogResponse;
import org.caloidoscope.vaxseen.entity.User;
import org.caloidoscope.vaxseen.repository.UserRepository;
import org.caloidoscope.vaxseen.util.AuditDiffUtil;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final UserRepository userRepository;

    public AuditLogResponse getUserAuditLog(User user) {
        return new AuditLogResponse(getAuditLogs(userRepository, user.getId(), User::getUsername));
    }

    public <ID, T> List<AuditLogEntryResponse> getAuditLogs(
            RevisionRepository<T, ID, Integer> repository,
            ID entityId,
            Function<T, String> nameExtractor // e.g., User::getUsername
    ) {
        List<Revision<Integer, T>> revisions = repository.findRevisions(entityId).toList();
        List<AuditLogEntryResponse> auditLogs = new ArrayList<>();

        for (int i = 0; i < revisions.size(); i++) {
            Revision<Integer, T> current = revisions.get(i);
            Revision<Integer, T> previous = i > 0 ? revisions.get(i - 1) : null;

            RevisionMetadata.RevisionType type = current.getMetadata().getRevisionType();
            AuditLog meta = current.getMetadata().getDelegate();
            String author = meta.getAuthor();
            String entityName = nameExtractor.apply(current.getEntity());
            LocalDateTime timestamp = Instant.ofEpochMilli(meta.getTimestamp())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();switch (type) {
                case INSERT -> auditLogs.add(new AuditLogEntryResponse(
                        "User created: "+ entityName, author, timestamp
                ));
                case UPDATE -> {
                    if (previous != null) {
                        List<String> diffs = AuditDiffUtil.getDifferences(
                                previous.getEntity(), current.getEntity());
                        if (!diffs.isEmpty()) {
                            StringBuilder log = new StringBuilder();
                            log.append("User ").append(entityName).append(" modified: ");
                            diffs.forEach(diff -> log.append("\n- ").append(diff));
                            auditLogs.add(new AuditLogEntryResponse(
                                    log.toString(), author, timestamp));
                        }
                    }
                }
            }
        }

        return auditLogs;
    }

}
