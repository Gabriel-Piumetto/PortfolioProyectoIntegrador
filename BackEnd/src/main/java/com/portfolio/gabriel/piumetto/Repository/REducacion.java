package com.portfolio.gabriel.piumetto.Repository;

import com.portfolio.gabriel.piumetto.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REducacion extends JpaRepository<Educacion, Integer> {
    public Optional<Educacion> findByNombreE(String nombreE);
    public Optional<Educacion> findByDescripcionE(String descripcionE);
    public boolean existsByNombreE(String nombreE);
    public boolean existsByDescripcionE(String descripcionE);
}
