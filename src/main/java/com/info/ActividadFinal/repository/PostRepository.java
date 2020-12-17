package com.info.ActividadFinal.repository;

import java.util.List;

import com.info.ActividadFinal.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    @Query(value = "SELECT * FROM Post WHERE publicado = false", nativeQuery = true)
    List<Post> findNoPublicado();

    @Query(value = "SELECT * FROM Post WHERE titulo LIKE %:titulo%", nativeQuery = true)
    List<Post> findTitulo(String titulo);
}