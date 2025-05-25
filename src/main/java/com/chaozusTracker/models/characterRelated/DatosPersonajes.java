package com.chaozusTracker.models.characterRelated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "datos_personajes")
public class DatosPersonajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, length = 3000)
    private String imagen;


    @OneToMany
    @JoinTable(
            name = "personaje_transformaciones",
            joinColumns = @JoinColumn(name = "personaje_id"),
            inverseJoinColumns = @JoinColumn(name = "transformacion_id")
    )
    private List<DatosPersonajes> transformaciones;



    @Column(nullable = false, length = 3000)
    private String habilidades;

    @OneToOne(mappedBy = "datosPersonajes")
    @JsonIgnore
    private Personajes personajes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades != null ? String.join(",", habilidades) : "";
    }

    public List<DatosPersonajes> getTransformaciones() {
        return transformaciones;
    }

    public void setTransformaciones(List<DatosPersonajes> transformaciones) {
        this.transformaciones = transformaciones;
    }

    public Personajes getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Personajes personajes) {
        this.personajes = personajes;
    }


    @Override
    public String toString() {
        return "DatosPersonajes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", transformaciones='" + transformaciones + '\'' +
                ", habilidades='" + habilidades + '\'' +
                ", personajes=" + personajes +
                '}';
    }
}
