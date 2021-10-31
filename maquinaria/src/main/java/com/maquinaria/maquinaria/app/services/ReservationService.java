/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.services;

import com.maquinaria.maquinaria.app.entities.Reservation;
import com.maquinaria.maquinaria.app.repositories.ReservationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maquinaria.maquinaria.reports.StatusReservations;
import com.maquinaria.maquinaria.reports.CountClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author  Rocio Dorado
 */
@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    
    /**
     * GET
     * @return 
     */
    public List<Reservation> getAllReservations(){
        return repository.getAll();
    }
    
    /**
     * Buscar por ID
     * @param reservationId
     * @return 
     */
    public Optional<Reservation> getReservation(int reservationId){
        return repository.getReservation(reservationId);
    }
    
    /**
     * POST
     * @param reservation
     * @return 
     */
    public Reservation save(Reservation reservation){
        if(reservation.getStartDate() != null 
            && reservation.getDevolutionDate() != null 
            && reservation.getClient() != null 
            && reservation.getMachine() != null){
            return repository.save(reservation);
        }else{
            Optional<Reservation> resultado = repository.getReservation(reservation.getIdReservation());
            if(resultado.isPresent()){
                return reservation;
            }else{
                return repository.save(reservation);
            }
        }
    }
    
    /**
     * UPDATE
     * @param reservation
     * @return 
     */
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> resultado = repository.getReservation(reservation.getIdReservation());
            if(resultado.isPresent()){
                if(reservation.getStartDate()!=null){
                    resultado.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    resultado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                
                repository.save(resultado.get());
                return resultado.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
    
    /**
     * DELETE
     * @param clientId
     * @return 
     */
    public boolean deleteReservation(int clientId) {
        return getReservation(clientId).map(client -> {
            repository.delete(client);
            return true;
        }).orElse(false);
    }

    /**
     * StatusReservations
     * @return new StatusReservations
     */
    public StatusReservations getReportStatusReservations(){
        List<Reservation>completed= repository.ReservationStatus("completed");
        List<Reservation>cancelled= repository.ReservationStatus("cancelled");
        return new StatusReservations(completed.size(), cancelled.size());
    }
    
    /**
     * getReportsTimeReservations
     * @param datoA
     * @param datoB
     * @return ArrayList
     */
    public List<Reservation> getReportsTimeReservations(String datoA, String datoB){
        SimpleDateFormat parser=new SimpleDateFormat ("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }
        
        if(datoUno.before(datoDos)){
            return repository.ReservationTime(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        }
    }  
    
    /**
     * serviceTopClients
     * @return List
     */
    public List<CountClient> serviceTopClients(){
        return repository.getTopClients();
    }
}
