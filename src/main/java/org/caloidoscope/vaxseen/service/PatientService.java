package org.caloidoscope.vaxseen.service;

import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.dto.request.PatientRequest;
import org.caloidoscope.vaxseen.entity.Patient;
import org.caloidoscope.vaxseen.entity.PatientParentLink;
import org.caloidoscope.vaxseen.entity.User;
import org.caloidoscope.vaxseen.mapper.PatientMapper;
import org.caloidoscope.vaxseen.repository.PatientRepository;
import org.caloidoscope.vaxseen.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public void savePatient(PatientRequest patientRequest) {
        List<User> parents = userRepository.findByUsernameIn(patientRequest.parentUsernames());
        Patient patient = patientMapper.toPatient(patientRequest);
        List<PatientParentLink> parentLinks = patientMapper.toPatientParentLinks(patientRequest.parents(), parents, patient);
        patient.setParents(parentLinks);
        patientRepository.save(patient);
    }


}
