/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.repositories;

import com.maquinaria.maquinaria.app.entities.Client;
import com.maquinaria.maquinaria.app.entities.Reservation;
import com.maquinaria.maquinaria.app.repositories.crud.ReservationCrudRepository;
import com.maquinaria.maquinaria.reports.CountClient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  Rocio Dorado
 */
@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    /**
     * Select
     * @return 
     */
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    /**
     * Buscar registro
     * @param reservationId
     * @return 
     */
    public Optional<Reservation> getReservation(int reservationId){
        return reservationCrudRepository.findById(reservationId);
    }

    /**
     * Insert
     * @param reservation
     * @return 
     */
    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }
    
    /**
     * Delete
     * @param reservation 
     */
    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }
    public List<Reservation> ReservationStatus (String status){
         return reservationCrudRepository.findAllByStatus(status);
     }
     
     public List<Reservation> ReservationTime (Date a, Date b){
         return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a, b);
     }
   
     public List<CountClient> getTopClients(){
         List<CountClient> res=new ArrayList<>();
         List<Object[]>report = reservationCrudRepository.countTotalReservationsByClient();
         for(int i=0; i<report.size();i++){
             res.add(new CountClient((Long)report.get(i)[1],(Client) report.get(i)[0]));
         
         }
         return res;
     }
}
