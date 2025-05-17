package org.caloidoscope.vaxseen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_PATIENT_PARENT_LINK;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Audited
@Table(name = VXSN_PEDIA_PATIENT_PARENT_LINK)
public class PatientParentLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User parent;

    private String relationship; // e.g., "mother", "father", "guardian"
}
