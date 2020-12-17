package com.info.ActividadFinal.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private LocalDate creacion = LocalDate.now();

    private String ciudad;
    private String provincia;
    private String pais;

    @OneToMany(mappedBy="autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "userPost")
    private List <Post> posts = new ArrayList<>();

    @OneToMany(mappedBy="autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "userComment")
    private List <Comentario> comentario = new ArrayList<>();

    public Usuario(){
        
    }

    public Usuario(Long id){
        this.id = id;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void setCreacion(LocalDate creacion) {
        this.creacion = creacion;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public String getApellido() {
        return apellido;
    }
    public String getCiudad() {
        return ciudad;
    }
    public LocalDate getCreacion() {
        return creacion;
    }
    public String getEmail() {
        return email;
    }
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPais() {
        return pais;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    
    public String getProvincia() {
        return provincia;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
