package org.caloidoscope.vaxseen.mapper;

import javax.annotation.processing.Generated;
import org.caloidoscope.vaxseen.dto.request.ParentLinkRequest;
import org.caloidoscope.vaxseen.dto.request.PatientRequest;
import org.caloidoscope.vaxseen.entity.Patient;
import org.caloidoscope.vaxseen.entity.PatientParentLink;
import org.caloidoscope.vaxseen.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T22:19:07+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public Patient toPatient(PatientRequest patientRequest) {
        if ( patientRequest == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setFirstName( patientRequest.firstName() );
        patient.setLastName( patientRequest.lastName() );
        patient.setSuffix( patientRequest.suffix() );
        patient.setGender( patientRequest.gender() );
        patient.setBirthdate( patientRequest.birthdate() );

        return patient;
    }

    @Override
    public PatientParentLink toPatientParentLink(ParentLinkRequest parentLinkRequest, User parent) {
        if ( parentLinkRequest == null && parent == null ) {
            return null;
        }

        PatientParentLink.PatientParentLinkBuilder patientParentLink = PatientParentLink.builder();

        if ( parentLinkRequest != null ) {
            patientParentLink.relationship( parentLinkRequest.relationship() );
        }
        if ( parent != null ) {
            patientParentLink.parent( parent );
            patientParentLink.id( parent.getId() );
        }

        return patientParentLink.build();
    }
}
