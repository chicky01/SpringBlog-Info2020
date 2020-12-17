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

import com.info.ActividadFinal.repository.ComentarioRepository;
import com.info.ActividadFinal.entity.Comentario;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController{

    @Autowired
    private ComentarioRepository comentarioRepository;

    // GET Todos los Comentarios
    @GetMapping
    public ResponseEntity<?> getComentario(){
        return new ResponseEntity<>(comentarioRepository.findAll(), HttpStatus.OK);
    }

    // POST Crear un Comentario
    @PostMapping
    public ResponseEntity<?> createComentario(@RequestBody Comentario comentario){
        return new ResponseEntity<>(comentarioRepository.save(comentario), HttpStatus.CREATED);
    }

    // PUT Modificar Comentario segun ID
    @PutMapping("/{comentarioId}")
    public ResponseEntity<?> editComentario(@PathVariable Long comentarioId, @RequestBody Comentario comentario){
        Comentario comentarioEdit = comentarioRepository.getOne(comentarioId);
        comentarioEdit.setComentario(comentario.getComentario());
        return new ResponseEntity<>(comentarioRepository.save(comentarioEdit), HttpStatus.OK);
    }

    // DELETE Eliminar Comentario segun ID
    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<?> deleteComentario(@PathVariable Long comentarioId){
        Comentario comentarioDelete = comentarioRepository.getOne(comentarioId);
        comentarioRepository.delete(comentarioDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<?> getCommentsByPost(@RequestParam Long postId, @RequestParam(required = false) Integer limite) {
        return new ResponseEntity<>(comentarioRepository.getCommentsByPostId(postId, limite), HttpStatus.OK); }
}