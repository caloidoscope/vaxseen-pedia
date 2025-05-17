package org.caloidoscope.vaxseen.controller;

import lombok.RequiredArgsConstructor;
import org.caloidoscope.vaxseen.dto.request.PatientRequest;
import org.caloidoscope.vaxseen.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/new-patient")
    public ResponseEntity<String> saveNewPatient(PatientRequest patientRequest) {
        patientService.savePatient(patientRequest);
        return new ResponseEntity<>("Patient saved successfully", HttpStatus.CREATED);
    }

}
