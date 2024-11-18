package uniandes.edu.co.SuperAndesNoSQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorias")
public class Categoria {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private String caracteristicasDeAlmacenamiento;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicasDeAlmacenamiento() {
        return caracteristicasDeAlmacenamiento;
    }

    public void setCaracteristicasDeAlmacenamiento(String caracteristicasDeAlmacenamiento) {
        this.caracteristicasDeAlmacenamiento = caracteristicasDeAlmacenamiento;
    }

    // Constructor
    public Categoria(String id, String nombre, String descripcion, String caracteristicasDeAlmacenamiento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicasDeAlmacenamiento = caracteristicasDeAlmacenamiento;
    }

    // Constructor vacio
    public Categoria() {

    }
}
