package com.epam.grow.jerseyrest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Purchase extends AbstractEntity {
}
