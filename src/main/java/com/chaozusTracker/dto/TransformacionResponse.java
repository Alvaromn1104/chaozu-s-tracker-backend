package com.chaozusTracker.dto;

public class TransformacionResponse {
    private Long id;
    private String nombre;
    private String imagen;

    public TransformacionResponse(Long id, String nombre, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }
}
