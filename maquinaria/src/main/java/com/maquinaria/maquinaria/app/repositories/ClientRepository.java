/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.repositories;
import com.maquinaria.maquinaria.app.entities.Client;
import com.maquinaria.maquinaria.app.repositories.crud.ClientCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ClientRepository {
    @Autowired
    private ClientCrudRepository clientCrudRepository;
    
    /**
     * Select
     * @return 
     */
    public List<Client> getAll(){
        return (List<Client>) clientCrudRepository.findAll();
    }
    /**
     * Buscar registro
     * @param idClient
     * @return 
     */
    public Optional<Client> getClient(int idClient){
        return clientCrudRepository.findById(idClient);
    }

    /**
     * Insert
     * @param client
     * @return 
     */
    public Client save(Client client){
        return clientCrudRepository.save(client);
    }
    
    
    /**
     * Delete
     * @param client 
     */
    public void delete(Client client){
        clientCrudRepository.delete(client);
    }
    
    
}
