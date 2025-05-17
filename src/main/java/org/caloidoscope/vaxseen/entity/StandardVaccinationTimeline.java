package org.caloidoscope.vaxseen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_STANDARD_VACCINATION_TIMELINE;

@Entity
@Table(name = VXSN_PEDIA_STANDARD_VACCINATION_TIMELINE)
@Data
@NoArgsConstructor
public class StandardVaccinationTimeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    private int doseNumber; // 1 = first dose, 2 = second, etc.

    private int dueInDays; // Number of days to wait

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DoseDueType dueType;

    @ManyToOne
    @JoinColumn(name = "depends_on_vaccine_id")
    private Vaccine dependsOnVaccine;
}

enum DoseDueType {
    AFTER_BIRTHDATE,
    AFTER_DEPENDENT_VACCINE
}
