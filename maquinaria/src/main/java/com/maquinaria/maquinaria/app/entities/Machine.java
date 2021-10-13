/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	


/**
 *
 * @author Famiria-SAN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="machine")
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private int year;
  //  @ManyToOne
 //   @JoinColumn(name = "categoryId")
 //   @JsonIgnoreProperties("machines")
    
    private int category_id;
    private String name;
    private String description;
 //   @oneToMany(cascade = {cascadeType.PERSIST}, mappedBy)
 //   private String message

}

