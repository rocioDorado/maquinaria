/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	


/**
 *
 * @author  Rocio Dorado
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="machine")
public class Machine implements Serializable {
    /**
    *
    * @Id llave primaria de la tabla Machine  
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    *
    * name
    */
    private String name;
    /**
    *
    * @Column brand 
    */
    @Column (name="brand", length=45)
    private String brand;
    
    /**
    *
    * year
    */
    private Integer year;
    /**
    *
    * description
    */
    private String description;
    
    /**
    *
    * @ManyToOne Relación con la tabla Category
    */
    
    @ManyToOne
    @JoinColumn(name="categoryId")
    @JsonIgnoreProperties("machines")
    private Category category;

    /**
    *
    * @OneToMany Relación con la tabla Client
    */
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "machine")
    @JsonIgnoreProperties({"machine","client"})
    private List<Message> messages;
    
    /**
    *
    * @OneToMany Relación con la tabla Message
    */
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "machine")
    @JsonIgnoreProperties({"machine","messages"})
    public List<Reservation> reservations;
    
}
