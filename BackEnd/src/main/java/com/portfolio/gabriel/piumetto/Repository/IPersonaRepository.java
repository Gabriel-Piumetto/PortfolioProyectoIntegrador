package com.portfolio.gabriel.piumetto.Repository;

import com.portfolio.gabriel.piumetto.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPersonaRepository extends JpaRepository<Persona, Integer>  {
    
    public Optional<Persona> findByNombre(String nombre);
    public Optional<Persona> findByDescripcion(String descripcion);
    public boolean existsByNombre(String nombre);
    public boolean existsByDescripcion(String descripcion);
}
