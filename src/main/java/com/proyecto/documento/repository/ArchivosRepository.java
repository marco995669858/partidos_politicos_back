package com.proyecto.documento.repository;

import com.proyecto.documento.security.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivosRepository extends JpaRepository<Archivo, Integer> {
}
