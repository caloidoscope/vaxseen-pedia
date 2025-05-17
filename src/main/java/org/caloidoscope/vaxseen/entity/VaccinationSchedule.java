package org.caloidoscope.vaxseen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_VACCINATION_SCHEDULES;

@Data
@NoArgsConstructor
@Entity
@Table(name = VXSN_PEDIA_VACCINATION_SCHEDULES)
public class VaccinationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id")
    private Vaccine vaccine;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    private LocalDate reminderDate;

    private String message;

    private boolean sent;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        if (this.reminderDate == null) {
            this.reminderDate = LocalDate.now().plusWeeks(2); // Default to two weeks before the due date
        }
    }
}
