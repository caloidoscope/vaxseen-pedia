package org.caloidoscope.vaxseen.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_GROWTH_LOGS;

@Entity
@Table(name = VXSN_PEDIA_GROWTH_LOGS)
@Data
@NoArgsConstructor
public class GrowthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private LocalDate logDate; // date of the check-up or growth recording

    private Double weightKg; // in kilograms
    private Double heightCm; // in centimeters
    private Double headCircumferenceCm; // optional, in cm

    private String notes; // optional notes from health worker or parent
}
