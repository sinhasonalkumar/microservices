package com.demo.perf.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.demo.perf.aggregates.capability.CapabilityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapabilityQueryEntity {

    @Id
    private String id;
    
    private String name;

    private String description;
    
    private CapabilityType capabilityType;
    
    public CapabilityQueryEntity(String id) {
    	this.id = id;
    }
}
