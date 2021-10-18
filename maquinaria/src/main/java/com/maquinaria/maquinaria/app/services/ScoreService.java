/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquinaria.maquinaria.app.services;

import com.maquinaria.maquinaria.app.entities.Score;
import com.maquinaria.maquinaria.app.repositories.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  Rocio Dorado
 */
@Service
public class ScoreService {
    @Autowired
    private ScoreRepository repository;
    
    /**
     * GET
     * @return 
     */
    public List<Score> getAll(){
        return repository.getAll();
    }
    
    /**
     * Buscar por ID
     * @param scoreId
     * @return 
     */
    public Optional<Score> getScore(int scoreId){
        return repository.getScore(scoreId);
    }
    
    /**
     * POST
     * @param score
     * @return 
     */
    public Score save(Score score){
        if(score.getIdScore()==null){
            return repository.save(score);
        }else{
            Optional<Score> resultado = repository.getScore(score.getIdScore());
            if(resultado.isPresent()){
                return score;
            }else{
                return repository.save(score);
            }
        }
    }
    
    /**
     * UPDATE
     * @param score
     * @return 
     */
    public Score update(Score score){
        if(score.getIdScore()!=null){
            Optional<Score> resultado = repository.getScore(score.getIdScore());
            if(resultado.isPresent()){
                if(score.getMessageText()!=null){
                    resultado.get().setMessageText(score.getMessageText());
                }
                if(score.getStars()!=null){
                    resultado.get().setStars(score.getStars());
                }
                
                repository.save(resultado.get());
                return resultado.get();
            }else{
                return score;
            }
        }else{
            return score;
        }
    }
    
    /**
     * DELETE
     * @param scoreId
     * @return 
     */
    public boolean delete(int scoreId) {
        Boolean aBoolean = getScore(scoreId).map(score -> {
            repository.delete(score);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
