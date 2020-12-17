package com.info.ActividadFinal.repository;

import java.util.List;

import com.info.ActividadFinal.entity.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

    @Query(value="SELECT * FROM Comentario WHERE post_id = ?1 LIMIT ?2", nativeQuery = true)
    List<Comentario> getCommentsByPostId(Long post_id, Integer limite);
}