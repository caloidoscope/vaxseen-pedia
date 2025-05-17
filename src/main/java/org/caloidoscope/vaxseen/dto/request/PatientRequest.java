package org.caloidoscope.vaxseen.dto.request;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record PatientRequest (
        String firstName,
        String lastName,
        String gender,
        String suffix,
        LocalDate birthdate,
        List<ParentLinkRequest> parents
) {
    public List<String> parentUsernames() {
        return parents.stream()
                .map(ParentLinkRequest::parentUsername)
                .collect(Collectors.toList());
    }
}