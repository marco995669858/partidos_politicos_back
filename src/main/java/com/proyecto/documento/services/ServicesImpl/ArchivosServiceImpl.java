package com.proyecto.documento.services.ServicesImpl;


import com.proyecto.documento.controller.ArchivosController;
import com.proyecto.documento.repository.ArchivosRepository;
import com.proyecto.documento.security.entity.Archivo;
import com.proyecto.documento.security.entity.Usuarios;
import com.proyecto.documento.services.ArchivosServices;
import com.proyecto.documento.util.Constantes;
import com.proyecto.documento.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ArchivosServiceImpl implements ArchivosServices {
    @Autowired
    private ArchivosRepository archivosRepository;
    private final Path root = Paths.get("D:\\archivos");

    @Override
    public void init() {
        try {
            File crearCarpeta = new File(root.toUri());
            if(!crearCarpeta.exists()){
                crearCarpeta.mkdirs();
            }

        } catch (Exception e) {
            throw new RuntimeException("No se puede inicializar la carpeta archivos");
        }
    }

    @Override
    public Mensaje save(int codigo, MultipartFile file) {
        try {

            Archivo guardarArchivo = new Archivo();
            //copy (que queremos copiar, a donde queremos copiar)
            Files.copy(file.getInputStream(),
                    this.root.resolve(file.getOriginalFilename()));
            String url = MvcUriComponentsBuilder.fromMethodName(ArchivosController.class, "getFile",
                    file.getOriginalFilename()).build().toString();
            //para guardar la ruta en la base de datos
            guardarArchivo.setId(new Usuarios(codigo));
            guardarArchivo.setRutaarchivo(this.root + file.getOriginalFilename());
            guardarArchivo.setNombrearchivo(file.getOriginalFilename());
            guardarArchivo.setFecharegistro(new Timestamp(System.currentTimeMillis()));
            guardarArchivo.setFechaactualizacion(new Timestamp(System.currentTimeMillis()));
            guardarArchivo.setUrl(url);
            archivosRepository.save(guardarArchivo);
            return new Mensaje(Constantes.SUCCESS, file.getOriginalFilename());
        } catch (IOException e) {
            return new Mensaje(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                return null;
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Mensaje deleteFile(int codigo,String filename){
        try {
            Optional<Archivo> buscarcodigosixiste = archivosRepository.findById(codigo);
            if(buscarcodigosixiste.isPresent()){
                archivosRepository.deleteById(codigo);
                Boolean delete = Files.deleteIfExists(this.root.resolve(filename));
            }else{
                return new Mensaje(Constantes.ERROR, Constantes.MENSAJE_ELIMINACION_CODIGO_NO_ENCONTRADO);
            }

            return new Mensaje(Constantes.SUCCESS, Constantes.MENSAJE_ELIMINACION);
        }catch (IOException e){
            return new Mensaje(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Archivo>> listarTodosLosArchivos() {
        HashMap<String, Object> salida = new HashMap<>();
        List<Archivo> listaarchivos = archivosRepository.findAll();
        salida.put("archivos", listaarchivos);
        return new ResponseEntity(salida, HttpStatus.OK);
    }

    @Override
    public void eliminardata() {
        archivosRepository.deleteAll();
    }
}
