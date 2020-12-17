package com.info.ActividadFinal.repository;

import java.time.LocalDate;
import java.util.List;

import com.info.ActividadFinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query(value = "SELECT * FROM Usuario WHERE ciudad = 'Resistencia'", nativeQuery = true)
    List<Usuario> findResistencia();

    @Query(value = "SELECT * FROM Usuario WHERE creacion >= :fecha", nativeQuery = true)
    List<Usuario> findByCreationDateAfter(LocalDate fecha);

}