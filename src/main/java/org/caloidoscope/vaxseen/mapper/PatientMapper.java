package org.caloidoscope.vaxseen.mapper;

import org.caloidoscope.vaxseen.dto.request.ParentLinkRequest;
import org.caloidoscope.vaxseen.dto.request.PatientRequest;
import org.caloidoscope.vaxseen.entity.Patient;
import org.caloidoscope.vaxseen.entity.PatientParentLink;
import org.caloidoscope.vaxseen.entity.Role;
import org.caloidoscope.vaxseen.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "parents", ignore = true)
    Patient toPatient(PatientRequest patientRequest);

    @Mapping(target = "parent", source = "parent")
    @Mapping(target = "relationship", source = "parentLinkRequest.relationship")
    @Mapping(target = "patient", ignore = true)
    PatientParentLink toPatientParentLink(ParentLinkRequest parentLinkRequest, User parent);

    default List<PatientParentLink> toPatientParentLinks(List<ParentLinkRequest> parentLinkRequests,
                                                         List<User> parents,
                                                         Patient patient) {

        return parentLinkRequests.stream()
                .map(request -> {
                    User parent = parents.stream()
                            .filter(p -> p.getUsername().equals(request.parentUsername()) && p.getRole().equals(Role.PARENT))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Parent not found: " + request.parentUsername()));
                    PatientParentLink link = toPatientParentLink(request, parent);
                    link.setPatient(patient);
                    return link;
                })
                .collect(Collectors.toList());
    }
}
