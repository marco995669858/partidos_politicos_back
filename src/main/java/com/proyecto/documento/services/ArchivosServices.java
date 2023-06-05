package com.proyecto.documento.services;

import com.proyecto.documento.security.entity.Archivo;
import com.proyecto.documento.util.Mensaje;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public interface ArchivosServices {

    /*Metodo para crear la carpeta donde vamos a guardar los archivos*/

    public void init();
    /*
    Metodo para guardar los archivos*/

    public Mensaje save(int codigo,MultipartFile file);

    /*
    Metodo para cargar un archivo*/
    public Resource load(String filename);

    /*
    Metodo para borrar todos los archivos cada vez que se inicie el servidor*/

    public void deleteAll();

    /*
    Metodo para Borrar un archivo*/

    public Mensaje deleteFile(int codigo,String filename);

    public ResponseEntity<List<Archivo>> listarTodosLosArchivos();

    /*Eliminar todos los archivos de la tabla db_archivos*/
    public  void eliminardata();

}
