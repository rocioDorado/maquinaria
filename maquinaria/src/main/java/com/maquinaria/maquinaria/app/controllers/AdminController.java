/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.controllers;

import com.maquinaria.maquinaria.app.entities.Admin;
import com.maquinaria.maquinaria.app.services.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author  Rocio Dorado
 */
@RestController
@RequestMapping("Admin")
public class AdminController {
    @Autowired
    private AdminService service;
    
    @GetMapping("/all")
    public List<Admin> findAllAdmin(){
        return service.getAll();
    }
    
    @PostMapping("/save")
    public ResponseEntity addAdmin(@RequestBody Admin admin){
        service.save(admin);
        return ResponseEntity.status(201).build();
    }
    
    @PutMapping("/update")
    public ResponseEntity updateAdmin(@RequestBody Admin admin){
        service.update(admin);
        return ResponseEntity.status(201).build();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity deleteAdmin(@RequestBody Admin admin){
        service.deleteAdmin(admin.getIdAdmin());
        return ResponseEntity.status(204).build();
    }
}
