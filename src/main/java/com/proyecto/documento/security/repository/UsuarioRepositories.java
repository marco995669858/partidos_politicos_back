package com.proyecto.documento.security.repository;

import com.proyecto.documento.security.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositories extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByNombreusuarioOrCorreo(String nombreUsuario, String correo);
    boolean existsByNombreusuario (String nombreUsuario);
    boolean existsByCorreo (String correo);

    public List<Usuarios> findByDni(String dni);
}
