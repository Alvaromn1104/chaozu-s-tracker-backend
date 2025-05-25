package com.chaozusTracker.dto;

import com.chaozusTracker.models.characterRelated.DatosPersonajes;

import java.util.List;

public class DatosPersonajesResponse {

    private long id;
    private String nombre;
    private String imagen;
    private List<TransformacionResponse> transformaciones;
    private List<String> habilidades;


    public DatosPersonajesResponse(long id, String nombre, String imagen, List<DatosPersonajes> transformaciones, String habilidades) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.transformaciones = transformaciones.stream()
                .map(t -> new TransformacionResponse(t.getId(), t.getNombre(), t.getImagen()))
                .toList();
        this.habilidades = this.convertHabilidades(habilidades);
    }

    public DatosPersonajesResponse() {}

    private List<String> convertTransformaciones(String transformaciones) {
        return List.of(transformaciones.split(","));
    }


    private List<String> convertHabilidades(String habilidades) {
        return List.of(habilidades.split(","));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<TransformacionResponse> getTransformaciones() {
        return transformaciones;
    }

    public void setTransformaciones(List<TransformacionResponse> transformaciones) {
        this.transformaciones = transformaciones;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }
}
