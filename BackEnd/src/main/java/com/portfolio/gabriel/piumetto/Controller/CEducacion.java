package com.portfolio.gabriel.piumetto.Controller;

import com.portfolio.gabriel.piumetto.Dto.dtoEducacion;
import com.portfolio.gabriel.piumetto.Entity.Educacion;
import com.portfolio.gabriel.piumetto.Security.Controller.Mensaje;
import com.portfolio.gabriel.piumetto.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://gpfront-9680f.web.app")
public class CEducacion {
    
 @Autowired
 SEducacion sEducacion;
  
   @GetMapping("/lista")
   public ResponseEntity<List<Educacion>>list(){
       List<Educacion> list = sEducacion.list();
       return new ResponseEntity(list, HttpStatus.OK);
               
    }
   
   @GetMapping("/detail/{id}")
   public ResponseEntity<Educacion>getByID(@PathVariable("id")int id){
       if(!sEducacion.existsById(id)){
           return new ResponseEntity(new Mensaje("Esa ID no existe"),HttpStatus.NOT_FOUND);
       }
       Educacion educacion = sEducacion.getOne(id).get();
       return new ResponseEntity(educacion,HttpStatus.OK);
   }
   
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> delete(@PathVariable("id")int id){
       if(!sEducacion.existsById(id)){
           return new ResponseEntity(new Mensaje ("Esa ID no existe"), HttpStatus.NOT_FOUND);
        }
       sEducacion.delete(id);
       return new ResponseEntity(new Mensaje("Formación académica eliminada"),HttpStatus.OK);
   }   
    
   @PostMapping("/create")
   public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion){
       if(StringUtils.isBlank(dtoeducacion.getNombreE())){
         return new ResponseEntity(new Mensaje ("El campo Nombre no puede estar en blanco"), HttpStatus.BAD_REQUEST);
       }
       if(sEducacion.existsByNombreE(dtoeducacion.getNombreE())){
          return new ResponseEntity(new Mensaje("Una formación académica con ese nombre ya existe"),HttpStatus.BAD_REQUEST);
       }
       
       if(StringUtils.isBlank(dtoeducacion.getDescripcionE())){
         return new ResponseEntity(new Mensaje ("El campo Descripción no puede estar en blanco"), HttpStatus.BAD_REQUEST);
       }
       if(sEducacion.existsByDescripcionE(dtoeducacion.getDescripcionE())){
          return new ResponseEntity(new Mensaje("Una formación académica con esa descripción ya existe"),HttpStatus.BAD_REQUEST);
       }
       
       Educacion educacion = new Educacion(dtoeducacion.getNombreE(),
               dtoeducacion.getDescripcionE());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Formación académica agregada"),HttpStatus.OK);
   }
   @PutMapping("/update/{id}")
   public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion){
       if(!sEducacion.existsById(id)){
       return new ResponseEntity(new Mensaje("Esa ID no existe"),HttpStatus.NOT_FOUND);
       }
       if(sEducacion.existsByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() !=id){
           return new ResponseEntity(new Mensaje("Una formación académica con ese nombre ya existe"),HttpStatus.BAD_REQUEST);
       }
       if(StringUtils.isBlank(dtoeducacion.getNombreE())){
           return new ResponseEntity(new Mensaje("El campo Nombre no puede estar en blanco"),HttpStatus.BAD_REQUEST);
       }
       if(sEducacion.existsByDescripcionE(dtoeducacion.getDescripcionE()) && sEducacion.getByDescripcionE(dtoeducacion.getDescripcionE()).get().getId() !=id){
           return new ResponseEntity(new Mensaje("Una formación académica con esa descripción ya existe"),HttpStatus.BAD_REQUEST);
       }
       if(StringUtils.isBlank(dtoeducacion.getDescripcionE())){
           return new ResponseEntity(new Mensaje("El campo Descripción no puede estar en blanco"),HttpStatus.BAD_REQUEST);
       }
       
       
       Educacion educacion = sEducacion.getOne(id).get();
       educacion.setNombreE(dtoeducacion.getNombreE());
       educacion.setDescripcionE(dtoeducacion.getDescripcionE());
       sEducacion.save(educacion);
       return new ResponseEntity(new Mensaje("Formación académica actualizada"),HttpStatus.OK);
   }
    
}
