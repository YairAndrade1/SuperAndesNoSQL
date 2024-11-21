package uniandes.edu.co.SuperAndesNoSQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "productos")
public class Producto {
    @Id
    private String id;
    
    @Field("nombre")
    private String nombre;

    @Field("precio_unitario")
    private double precioUnitario;

    @Field("presentacion")
    private String presentacion;
    
    @Field("categoria_id")
    private String categoriaId;
    
    @Field("codigo_barras")
    private String codigoBarras;
    
    @Field("fecha_expiracion")
    private Date fechaExpiracion;

    // Constructor
    public Producto(String id, String nombre, double precioUnitario, String presentacion, String categoriaId,
            String codigoBarras, Date fechaExpiracion) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.presentacion = presentacion;
        this.categoriaId = categoriaId;
        this.codigoBarras = codigoBarras;
        this.fechaExpiracion = fechaExpiracion;
    }
    // Constructor vacio
    public Producto() {
    }

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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

}
