/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.services;

import com.maquinaria.maquinaria.app.entities.Client;
import com.maquinaria.maquinaria.app.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rocio Dorado
 */
@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    /**
     * GET Consultar Todos los registros.
     * @return 
     */
    public List<Client> getClients(){
        return repository.findAll();
    }
    
    /**
     * POST Crear o Registrar.
     * @param client
     * @return 
     */
    public Client saveClient(Client client){
        return repository.save(client);
    }
    
    /**
     * PUT Actualizar o Editar
     * @param client
     * @return 
     */
    public Client updateClient(Client client){
        Client existingClient = repository.findById(client.getId()).orElse(null);
        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setAge(client.getAge());
        return repository.save(existingClient);
    }
    
    /**
     * DELETE Eliminar
     * @param id
     * @return 
     */
    public String deleteClient(int id){
        repository.deleteById(id);
        return "Maquina eliminada "+ id;
    }
}
