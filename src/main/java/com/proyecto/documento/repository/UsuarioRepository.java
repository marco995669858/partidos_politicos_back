package com.proyecto.documento.repository;

import com.proyecto.documento.security.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios,Integer> {
    public List<Usuarios> findByDni(String dni);
    boolean existsByNombreusuario (String nombreUsuario);
    boolean existsByCorreo (String correo);

    @Query(value = "SELECT * FROM db_usuario u WHERE u.correo = ?1 and u.id <> ?2", nativeQuery = true)
    String buscarCorreoExisteDistintoalRegistrado(String correo, int id);


}