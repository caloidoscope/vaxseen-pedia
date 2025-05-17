package org.caloidoscope.vaxseen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_VACCINES;

@Data
@NoArgsConstructor
@Entity
@Table(name = VXSN_PEDIA_VACCINES)
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String manufacturer;

}
