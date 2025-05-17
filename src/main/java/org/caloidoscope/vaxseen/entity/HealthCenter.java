package org.caloidoscope.vaxseen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_HEALTH_CENTERS;

@Entity
@Table(name = VXSN_PEDIA_HEALTH_CENTERS)
@Data
@NoArgsConstructor
public class HealthCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private HealthCenterType type;

    private String address;

    private String city;

    private String province;

    private String contactNumber;

    private Double latitude;
    private Double longitude;

    private boolean active = true;
}
