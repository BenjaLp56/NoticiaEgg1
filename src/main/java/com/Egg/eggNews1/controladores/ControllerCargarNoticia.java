/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Egg.eggNews1.controladores;

import com.Egg.eggNews1.excepciones.MiException;
import com.Egg.eggNews1.services.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")
public class ControllerCargarNoticia {
    
    @Autowired
    private NoticiaServicio noticiaServicio;
    

    @GetMapping("/cargar")
    public String cargar() {
        return "cargarNoticia.html";
    }

    @PostMapping("/registro")
    public String carga(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) {
        
        try{
            
        noticiaServicio.crearNoticia(titulo, cuerpo);
        
        modelo.put("Exito", "La noticia se creo correctamente");
        
        }catch(MiException e){
            
            modelo.put("Error", e.getMessage());
            
            return "cargarNoticia.html";
        }
        return "index.html";
    }

}
