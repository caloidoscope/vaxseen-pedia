package org.caloidoscope.vaxseen.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_PATIENTS;

@Data
@NoArgsConstructor
@Entity
@Audited
@Table(name = VXSN_PEDIA_PATIENTS)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    private String firstName;
    private String lastName;
    private String suffix;
    private String gender;

    private LocalDate birthdate;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientParentLink> parents;

}
