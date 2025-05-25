package com.chaozusTracker.models.characterRelated;

import com.chaozusTracker.models.Categorias;
import com.chaozusTracker.models.userRelated.UserProfileRelated.Favorito;
import jakarta.persistence.*;

@Entity
@Table(name = "personajes")
public class Personajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "datospersonaje_id", referencedColumnName = "id")
    private DatosPersonajes datosPersonajes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categorias categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public DatosPersonajes getDatosPersonajes() {
        return datosPersonajes;
    }

    public void setDatosPersonajes(DatosPersonajes datosPersonajes) {
        this.datosPersonajes = datosPersonajes;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
}
