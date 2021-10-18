/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.repositories;

import com.maquinaria.maquinaria.app.entities.Message;
import com.maquinaria.maquinaria.app.repositories.crud.MessageCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  Rocio Dorado
 */
@Repository
public class MessageRepository  {
    @Autowired
    private MessageCrudRepository messageCrudRepository;
    
    /**
     * Select
     * @return 
     */
    public List<Message> getAll(){
        return (List<Message>) messageCrudRepository.findAll();
    }
    /**
     * Buscar registro
     * @param messageId
     * @return 
     */
    public Optional<Message> getMessage(int messageId){
        return messageCrudRepository.findById(messageId);
    }

    /**
     * Insert
     * @param message
     * @return 
     */
    public Message save(Message message){
        return messageCrudRepository.save(message);
    }
    
    /**
     * Delete
     * @param message 
     */
    public void delete(Message message){
        messageCrudRepository.delete(message);
    }
    
    
}

