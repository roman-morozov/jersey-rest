package com.epam.grow.jerseyrest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "\"order\"")
public class Order extends AbstractEntity {
}
