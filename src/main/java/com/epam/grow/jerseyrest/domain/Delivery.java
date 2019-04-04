package com.epam.grow.jerseyrest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Delivery extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;
}
