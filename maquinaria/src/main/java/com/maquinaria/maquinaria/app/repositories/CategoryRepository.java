/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.repositories;

import com.maquinaria.maquinaria.app.entities.Category;
import com.maquinaria.maquinaria.app.repositories.crud.CategoryCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  Rocio Dorado
 */
@Repository
public class CategoryRepository {
    @Autowired
    private CategoryCrudRepository categoryCrudRepository;
    
    /**
     * Select
     * @return 
     */
    public List<Category> getAll(){
        return (List<Category>) categoryCrudRepository.findAll();
    }
    /**
     * Buscar registro
     * @param categoryId
     * @return 
     */
    public Optional<Category> getCategory(int categoryId){
        return categoryCrudRepository.findById(categoryId);
    }

    /**
     * Insert
     * @param category
     * @return 
     */
    public Category save(Category category){
        return categoryCrudRepository.save(category);
    }
    
    /**
     * Delete
     * @param category 
     */
    public void delete(Category category){
        categoryCrudRepository.delete(category);
    }
    
    
}
