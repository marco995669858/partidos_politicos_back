package com.proyecto.documento.controller;

import com.proyecto.documento.security.entity.Archivo;
import com.proyecto.documento.services.ArchivosServices;
import com.proyecto.documento.util.Constantes;
import com.proyecto.documento.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/archivos/")
@CrossOrigin("*")
public class ArchivosController {
    @Autowired
    ArchivosServices archivosServices;

    @PostMapping("/upload")
    public Mensaje uploadFiles(@RequestParam("codusuario") int codigo,@RequestParam("files")MultipartFile files){

        try{
            return archivosServices.save(codigo,files);
        }catch (Exception e){
            return new Mensaje(Constantes.ERROR, e.getMessage());
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<Archivo>> getFiles() {
            return archivosServices.listarTodosLosArchivos();
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = archivosServices.load(filename);
        if(file == null){
            return new ResponseEntity("No existe el archivo", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete/{codigo}/{filename:.+}")
    public Mensaje deleteFile(@PathVariable int codigo, @PathVariable String filename) {
        try {
            return archivosServices.deleteFile(codigo,filename);
        } catch (Exception e) {
            return new Mensaje(Constantes.ERROR, e.getMessage());
        }
    }
}
