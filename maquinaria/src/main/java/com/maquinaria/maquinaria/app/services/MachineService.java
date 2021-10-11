/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.services;

import com.maquinaria.maquinaria.app.entities.Machine;
import com.maquinaria.maquinaria.app.repositories.MachineRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rocio Dorado
 */
@Service
public class MachineService {
    
    @Autowired
    private MachineRepository repository;
    
    /**
     * GET Consultar Todos los registros.
     * @return 
     */
    public List<Machine> getMachines(){
        return repository.findAll();
    }
    
    /**
     * POST Crear o Registrar.
     * @param machine
     * @return 
     */
    public Machine saveMachine(Machine machine){
        return repository.save(machine);
    }
    
    /**
     * PUT Actualizar o Editar
     * @param machine
     * @return 
     */
    public Machine updateMachine(Machine machine){
        Machine existingMachine = repository.findById(machine.getId()).orElse(null);
        existingMachine.setName(machine.getName());
        existingMachine.setBrand(machine.getBrand());
        existingMachine.setModel(machine.getModel());
        existingMachine.setCategory_id(machine.getCategory_id());
        return repository.save(existingMachine);
    }
    
    /**
     * DELETE Eliminar
     * @param id
     * @return 
     */
    public String deleteMachine(int id){
        repository.deleteById(id);
        return "Maquina eliminada "+ id;
    }
}
