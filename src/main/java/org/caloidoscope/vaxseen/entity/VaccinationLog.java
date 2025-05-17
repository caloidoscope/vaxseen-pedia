package org.caloidoscope.vaxseen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_VACCINATION_RECORDS;

@Data
@NoArgsConstructor
@Entity
@Table(name = VXSN_PEDIA_VACCINATION_RECORDS)
public class VaccinationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id")
    private Vaccine vaccine;

    private LocalDate vaccinationDate;

    private String lotNumber;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "health_center_id", referencedColumnName = "id")
    private HealthCenter healthCenter;

    @ManyToOne
    @JoinColumn(name = "administered_by", referencedColumnName = "id")
    private User administeredBy;
}