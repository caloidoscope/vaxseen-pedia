package org.caloidoscope.vaxseen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

import static org.caloidoscope.vaxseen.constant.Constants.TableNames.VXSN_PEDIA_VACCINE_INVENTORY;

@Data
@NoArgsConstructor
@Entity
@Table(name = VXSN_PEDIA_VACCINE_INVENTORY)
public class VaccineInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @ManyToOne
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id")
    private Vaccine vaccine; // Link to the Vaccine entity

    private String lotNumber; // Lot number of the vaccine batch
    private LocalDate expiryDate; // Expiry date of the batch

    private int quantity; // Quantity of the vaccine doses available in inventory

    private String healthCenter; // Health center or location where the vaccine is stored

    private LocalDate lastUpdated; // Date the inventory was last updated

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDate.now(); // Automatically update the last updated date
    }
}
