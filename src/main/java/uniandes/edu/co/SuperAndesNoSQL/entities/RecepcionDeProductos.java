package uniandes.edu.co.SuperAndesNoSQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "recepciones_productos")
public class RecepcionDeProductos {
    @Id
    private String id;

    @Field("orden_compra_id")
    private String ordenCompraId; 

    @Field("fecha_recepcion")
    private Date fechaRecepcion;

    @Field("productos")
    private List<DetalleRecepcion> productos; 

    public static class DetalleRecepcion {
        
        @Field("producto_id")
        private String productoId; 

        @Field("cantidad")
        private int cantidad;

        @Field("nuevo_costo_promedio")
        private double nuevoCostoPromedio;

        // Getters y Setters
        public String getProductoId() {
            return productoId;
        }

        public void setProductoId(String productoId) {
            this.productoId = productoId;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getNuevoCostoPromedio() {
            return nuevoCostoPromedio;
        }

        public void setNuevoCostoPromedio(double nuevoCostoPromedio) {
            this.nuevoCostoPromedio = nuevoCostoPromedio;
        }

        // Constructor
        public DetalleRecepcion(String productoId, int cantidad, double nuevoCostoPromedio) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.nuevoCostoPromedio = nuevoCostoPromedio;
        }

        // Constructor vacio
        public DetalleRecepcion() {

        }
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(String ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public List<DetalleRecepcion> getProductos() {
        return productos;
    }

    public void setProductos(List<DetalleRecepcion> productos) {
        this.productos = productos;
    }

    // Constructor
    public RecepcionDeProductos(String id, String ordenCompraId, Date fechaRecepcion,
            List<DetalleRecepcion> productos) {
        this.id = id;
        this.ordenCompraId = ordenCompraId;
        this.fechaRecepcion = fechaRecepcion;
        this.productos = productos;
    }

    // Constructor vacio
    public RecepcionDeProductos() {

    }
}
