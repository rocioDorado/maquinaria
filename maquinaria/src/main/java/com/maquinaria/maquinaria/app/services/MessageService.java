/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.services;

import com.maquinaria.maquinaria.app.entities.Message;
import com.maquinaria.maquinaria.app.repositories.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Rocio Dorado
 */
@Service
public class MessageService {
    
       @Autowired
    private MessageRepository repository;
    
    /**
     * GET
     * @return 
     */
    public List<Message> getAll(){
        return repository.getAll();
    }
    
    /**
     * Buscar por ID
     * @param idMessage
     * @return 
     */
    public Optional<Message> getMessage(int idMessage){
        return repository.getMessage(idMessage);
    }
    
    /**
     * POST
     * @param message
     * @return 
     */
    public Message save(Message message){
        if(message.getMessageText() != null && message.getMachine() != null && message.getClient() != null){
            return repository.save(message);
        }else{
            Optional<Message> resultado = repository.getMessage(message.getIdMessage());
            if(resultado.isPresent()){
                return message;
            }else{
                return repository.save(message);
            }
        }
    }
    
    /**
     * UPDATE
     * @param message
     * @return 
     */
    public Message update(Message message){
        System.out.println(message.getIdMessage());
        System.out.println(message.getMessageText());
        System.out.println(message.getMachine().getName());
        System.out.println(message.getClient().getName());
        
        if(message.getIdMessage()!=null){
            Optional<Message> resultado = repository.getMessage(message.getIdMessage());
            if(resultado.isPresent()){
                if(message.getMessageText()!= null && message.getMachine() != null && message.getClient() != null){
                    resultado.get().setMessageText(message.getMessageText());
                    resultado.get().setMachine(message.getMachine());
                    resultado.get().setClient(message.getClient());
                }
                
                repository.save(resultado.get());
                return resultado.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }
    
    /**
     * DELETE
     * @param idMessage
     * @return 
     */

    public boolean deleteMessage(int idMessage) {
        Boolean aBoolean = getMessage(idMessage).map(message -> {
            repository.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    
}
