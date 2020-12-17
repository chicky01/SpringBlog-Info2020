package com.info.ActividadFinal.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.info.ActividadFinal.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

import com.info.ActividadFinal.entity.Usuario;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController{

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET Todos los Usuarios
    @GetMapping
    public ResponseEntity<?> getUsuarios(){
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    // POST Crear un Usuario
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    // PUT Modificar Usuario segun ID
    @PutMapping("/{usuarioId}")
    public ResponseEntity<?> editUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuario){
        Usuario usuarioEdit = usuarioRepository.getOne(usuarioId);
        usuarioEdit.setNombre(usuario.getNombre());
        usuarioEdit.setApellido(usuario.getApellido());
        usuarioEdit.setEmail(usuario.getEmail());
        usuarioEdit.setPassword(usuario.getPassword());
        usuarioEdit.setCiudad(usuario.getCiudad());
        usuarioEdit.setProvincia(usuario.getProvincia());
        usuarioEdit.setPais(usuario.getPais());
        return new ResponseEntity<>(usuarioRepository.save(usuarioEdit), HttpStatus.OK);
    }

    // DELETE Eliminar Usuario segun ID
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long usuarioId){
        Usuario usuarioDelete = usuarioRepository.getOne(usuarioId);
        usuarioRepository.delete(usuarioDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET Usuarios de Resistencia
    @GetMapping("/rcia")
    public ResponseEntity<?> getUsuariosRcia(){
        return new ResponseEntity<>(usuarioRepository.findResistencia(), HttpStatus.OK);
    }

    // GET Usuarios creados despues de...
    @GetMapping("/fecha")
    public ResponseEntity<?> getUsuariosFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
        List<Usuario> usuarios = usuarioRepository.findByCreationDateAfter(fecha);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

}