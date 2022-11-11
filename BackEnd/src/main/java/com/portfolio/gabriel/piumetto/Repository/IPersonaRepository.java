package com.portfolio.gabriel.piumetto.Repository;

import com.portfolio.gabriel.piumetto.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPersonaRepository extends JpaRepository<Persona,Long>  {
    
    
}
