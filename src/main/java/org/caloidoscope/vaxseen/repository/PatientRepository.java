package org.caloidoscope.vaxseen.repository;


import org.caloidoscope.vaxseen.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>,
        RevisionRepository<Patient,Long,Integer> {
}
