package com.portfolio.gabriel.piumetto.Controller;

import com.portfolio.gabriel.piumetto.Dto.dtoPersona;
import com.portfolio.gabriel.piumetto.Entity.Persona;
import com.portfolio.gabriel.piumetto.Security.Controller.Mensaje;
import com.portfolio.gabriel.piumetto.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/personas")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PersonaController {
/*  
    @Autowired
  IPersonaService ipersonaService;
  
  @GetMapping({"personas/traer"})
  public List<Persona> getPersona() {
    return this.ipersonaService.getPersona();
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping({"/personas/crear"})
  public String createPersona(@RequestBody Persona persona) {
    this.ipersonaService.savePersona(persona);
    return "La persona fue creada correctamente";
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping({"/personas/borrar/{id}"})
  public String deletePersona(@PathVariable Long id) {
    this.ipersonaService.deletePersona(id);
    return "La persona fue eliminada correctamente";
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping({"/personas/editar/{id}"})
  public Persona editPersona(@PathVariable Long id, @RequestParam("nombre") String nuevoNombre, @RequestParam("apellido") String nuevoApellido, @RequestParam("img") String nuevoImg) {
    Persona persona = this.ipersonaService.findPersona(id);
    persona.setNombre(nuevoNombre);
    persona.setApellido(nuevoApellido);
    persona.setImg(nuevoImg);
    this.ipersonaService.savePersona(persona);
    return persona;
  }
  
  @GetMapping({"personas/traer/perfil"})
  public Persona findPersona() {
    return this.ipersonaService.findPersona(Long.valueOf(1L));
  }
*/
    @Autowired
    ImpPersonaService impPersonaService;
  
   @GetMapping("/lista")
   public ResponseEntity<List<Persona>>list(){
       List<Persona> list = impPersonaService.list();
       return new ResponseEntity(list, HttpStatus.OK);
               
    }
   
   @GetMapping("/detail/{id}")
   public ResponseEntity<Persona>getByID(@PathVariable("id")int id){
       if(!impPersonaService.existsById(id)){
           return new ResponseEntity(new Mensaje("Esa ID no existe"),HttpStatus.NOT_FOUND);
       }
       Persona persona = impPersonaService.getOne(id).get();
       return new ResponseEntity(persona,HttpStatus.OK);
   }
   
   @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> delete(@PathVariable("id")int id){
       if(!impPersonaService.existsById(id)){
           return new ResponseEntity(new Mensaje ("Esa ID no existe"), HttpStatus.NOT_FOUND);
        }
       impPersonaService.delete(id);
       return new ResponseEntity(new Mensaje("Formación académica eliminada"),HttpStatus.OK);
   }   
   
   @PreAuthorize("hasRole('ADMIN')") 
   @PostMapping("/create")
   public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
       if(StringUtils.isBlank(dtopersona.getNombre())){
         return new ResponseEntity(new Mensaje ("El campo Nombre no puede estar en blanco"), HttpStatus.BAD_REQUEST);
       }
       
       if(StringUtils.isBlank(dtopersona.getApellido())){
         return new ResponseEntity(new Mensaje ("El campo Apellido no puede estar en blanco"), HttpStatus.BAD_REQUEST);
       }
       
       if(StringUtils.isBlank(dtopersona.getDescripcion())){
         return new ResponseEntity(new Mensaje ("El campo Descripción no puede estar en blanco"), HttpStatus.BAD_REQUEST);
       }
       
       
       Persona persona = new Persona(dtopersona.getNombre(),
               dtopersona.getApellido(),
               dtopersona.getDescripcion(),dtopersona.getImg());
        impPersonaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona agregada"),HttpStatus.OK);
   }
   
   @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("/update/{id}")
   public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
       if(!impPersonaService.existsById(id)){
       return new ResponseEntity(new Mensaje("Esa ID no existe"),HttpStatus.NOT_FOUND);
       }
       
       if(StringUtils.isBlank(dtopersona.getNombre())){
           return new ResponseEntity(new Mensaje("El campo Nombre no puede estar en blanco"),HttpStatus.BAD_REQUEST);
       }
       if(StringUtils.isBlank(dtopersona.getApellido())){
           return new ResponseEntity(new Mensaje("El campo Nombre no puede estar en blanco"),HttpStatus.BAD_REQUEST);
       }
       
       if(StringUtils.isBlank(dtopersona.getDescripcion())){
           return new ResponseEntity(new Mensaje("El campo Descripción no puede estar en blanco"),HttpStatus.BAD_REQUEST);
       }
       
       
       Persona persona = impPersonaService.getOne(id).get();
       persona.setNombre(dtopersona.getNombre());
       persona.setApellido(dtopersona.getApellido());
       persona.setDescripcion(dtopersona.getDescripcion());
       persona.setImg(dtopersona.getImg());
       impPersonaService.save(persona);
       return new ResponseEntity(new Mensaje("Persona actualizada"),HttpStatus.OK);
   }
    
}
