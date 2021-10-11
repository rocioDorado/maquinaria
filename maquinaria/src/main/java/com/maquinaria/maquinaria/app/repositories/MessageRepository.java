/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.repositories;

import com.maquinaria.maquinaria.app.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fdomoreno
 */
public interface MessageRepository extends JpaRepository<Message,Integer> {
    
}

