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

import com.info.ActividadFinal.repository.PostRepository;
import com.info.ActividadFinal.entity.Post;

@RestController
@RequestMapping("/api/v1/post")
public class PostController{

    @Autowired
    private PostRepository postRepository;

    // GET Todos los Post
    @GetMapping
    public ResponseEntity<?> getPost(){
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    // POST Crear un Post
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post){
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    // PUT Modificar Post segun ID
    @PutMapping("/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody Post post){
        Post postEdit = postRepository.getOne(postId);
        postEdit.setContenido(post.getContenido());
        postEdit.setDescripcion(post.getDescripcion());
        postEdit.setTitulo(post.getTitulo());
        postEdit.setPublicado(post.getPublicado());
        return new ResponseEntity<>(postRepository.save(postEdit), HttpStatus.OK);
    }

    // DELETE Eliminar Post segun ID
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        Post postDelete = postRepository.getOne(postId);
        postRepository.delete(postDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET Todos los Post no publicados
    @GetMapping("/nopublic")
    public ResponseEntity<?> getPostPublic(){
        return new ResponseEntity<>(postRepository.findNoPublicado(), HttpStatus.OK);
    }

    // GET Post según titulo
    @GetMapping("/titulo")
    public ResponseEntity<?> getPostTitulo(@RequestParam String titulo){
        return new ResponseEntity<>(postRepository.findTitulo(titulo), HttpStatus.OK);
    }
}