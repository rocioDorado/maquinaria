/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.repositories;

import com.maquinaria.maquinaria.app.entities.Admin;
import com.maquinaria.maquinaria.app.repositories.crud.AdminCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rocio Dorado
 */

@Repository
public class AdminRepository {
    @Autowired
    private AdminCrudRepository adminCrudRepository;
    
    /**
     * Select
     * @return 
     */
    public List<Admin> getAll(){
        return (List<Admin>) adminCrudRepository.findAll();
    }
    /**
     * Buscar registro
     * @param adminId
     * @return 
     */
    public Optional<Admin> getAdmin(int adminId){
        return adminCrudRepository.findById(adminId);
    }

    /**
     * Insert
     * @param admin
     * @return 
     */
    public Admin save(Admin admin){
        return adminCrudRepository.save(admin);
    }
    
    /**
     * Delete
     * @param admin 
     */
    public void delete(Admin admin){
        adminCrudRepository.delete(admin);
    }
    
    
}


