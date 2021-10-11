/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.services;

import com.maquinaria.maquinaria.app.entities.Message;
import com.maquinaria.maquinaria.app.repositories.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rocio Dorado
 */
@Service
public class MessageService {
    
    @Autowired
    private MessageRepository repository;
    
    /**
     * GET Consultar Todos los registros.
     * @return 
     */
    public List<Message> getMessages(){
        return repository.findAll();
    }
    
    /**
     * POST Crear o Registrar.
     * @param message
     * @return 
     */
    public Message saveMessage(Message message){
        return repository.save(message);
    }
    
    /**
     * PUT Actualizar o Editar
     * @param message
     * @return 
     */
    public Message updateMessage(Message message){
        Message existingMessage = repository.findById(message.getId()).orElse(null);
        existingMessage.setMessagetext(message.getMessagetext());
        return repository.save(existingMessage);
    }
    
    /**
     * DELETE Eliminar
     * @param id
     * @return 
     */
    public String deleteMessage(int id){
        repository.deleteById(id);
        return "Mensaje eliminado "+ id;
    }
}
