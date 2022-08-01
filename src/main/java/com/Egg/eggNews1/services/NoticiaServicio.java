/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Egg.eggNews1.services;

import com.Egg.eggNews1.entidades.Noticia;
import com.Egg.eggNews1.excepciones.MiException;
import com.Egg.eggNews1.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional  
    public void crearNoticia(String titulo, String cuerpo) throws MiException{
        
        validar(titulo, cuerpo);
        Noticia not = new Noticia();
        
        not.setTitulo(titulo);
        not.setCuerpo(cuerpo);
        
        noticiaRepositorio.save(not);
        
    }
    
    public void mostrarNoticia(String titulo){
        
    }
    
    public List<Noticia> mostrarTodasLasNoticias(){
        
        List<Noticia> noticias = new ArrayList();
        
        noticias = noticiaRepositorio.findAll();
        
        return noticias;
        
    }
    
    public void modificarNoticia(String titulo, String cuerpo, Long id) throws MiException{
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id); 
        validar(titulo, cuerpo);
        if(respuesta.isPresent()){
            
            Noticia noticia = respuesta.get();
            
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            
            
            noticiaRepositorio.save(noticia);
        }
        
    }
    
    private void validar(String titulo, String cuerpo) throws MiException{
        
           if(titulo.isEmpty() || titulo == null){
            throw new MiException("El titulo no puede ser nulo o estar vacio");
        }
        if(cuerpo.isEmpty() || cuerpo == null){
            throw new MiException("El cuerpo de la noticia no puede ser nulo o estar vacio");
        }
      
    }
    
}
