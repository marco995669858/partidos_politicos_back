package com.proyecto.documento.security.repository;

import com.proyecto.documento.security.entity.Rol;
import com.proyecto.documento.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
