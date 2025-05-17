package org.caloidoscope.vaxseen.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_HEALTH_PROFESSIONAL;

@Entity
@Table(name = VXSN_PEDIA_HEALTH_PROFESSIONAL)
@Data
@NoArgsConstructor
public class HealthProfessional {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String licenseNumber;

    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private HealthCenter center;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}