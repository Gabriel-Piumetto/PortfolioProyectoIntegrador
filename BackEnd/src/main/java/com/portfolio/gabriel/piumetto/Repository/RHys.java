package com.portfolio.gabriel.piumetto.Repository;

import com.portfolio.gabriel.piumetto.Entity.Hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHys extends JpaRepository<Hys, Integer> {
    Optional<Hys> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
