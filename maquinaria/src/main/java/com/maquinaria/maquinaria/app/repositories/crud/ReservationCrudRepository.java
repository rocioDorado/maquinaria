/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.repositories.crud;

import com.maquinaria.maquinaria.app.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Famiria-SAN
 */
public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer>{
    
}
