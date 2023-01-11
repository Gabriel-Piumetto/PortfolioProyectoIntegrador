package com.portfolio.gabriel.piumetto.Controller;

import com.portfolio.gabriel.piumetto.Dto.dtoExperiencia;
import com.portfolio.gabriel.piumetto.Entity.Experiencia;
import com.portfolio.gabriel.piumetto.Security.Controller.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.portfolio.gabriel.piumetto.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("explab")
@CrossOrigin(origins = "https://gpfront-9680f.web.app")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list =sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }    

@PostMapping("/create")    
public ResponseEntity<?> create (@RequestBody dtoExperiencia dtoexp){
    if(StringUtils.isBlank(dtoexp.getNombreE())){
        return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);}
    
    if(sExperiencia.existsByNombreE(dtoexp.getNombreE())){
        return new ResponseEntity (new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);}
    
    Experiencia experiencia = new Experiencia(dtoexp.getNombreE(),dtoexp.getDescripcionE());
    sExperiencia.save(experiencia);
    
    return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
        
}

@PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoExperiencia dtoexp){
    if(!sExperiencia.existsById(id)){
        return new ResponseEntity(new Mensaje ("Esa ID no existe"), HttpStatus.BAD_REQUEST);
    }
    if(sExperiencia.existsByNombreE(dtoexp.getNombreE())&& sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId()!=id){
       return new ResponseEntity(new Mensaje("Esa experiencia ya existe"),HttpStatus.BAD_REQUEST);    
    }
    if(StringUtils.isBlank(dtoexp.getNombreE())){
        return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    }
    
  Experiencia experiencia = sExperiencia.getOne(id).get();
  experiencia.setNombreE(dtoexp.getNombreE());
  experiencia.setDescripcionE(dtoexp.getDescripcionE());

  sExperiencia.save(experiencia);

  
  return new ResponseEntity(new Mensaje("Experiencia actualizada"),HttpStatus.OK);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<?> delete(@PathVariable("id")int id){
    if(!sExperiencia.existsById(id)){
        return new ResponseEntity(new Mensaje ("Esa ID no existe"), HttpStatus.BAD_REQUEST);
    }
    sExperiencia.delete(id);
    
     return new ResponseEntity (new Mensaje("La experiencia ha sido eliminada"), HttpStatus.OK);
}

@GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("Esa ID no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

}

   