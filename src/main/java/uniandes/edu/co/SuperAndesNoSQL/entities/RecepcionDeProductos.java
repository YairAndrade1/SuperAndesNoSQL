package uniandes.edu.co.SuperAndesNoSQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "recepciones_productos")
public class RecepcionDeProductos {
    @Id
    private String id;
    private String ordenCompraId; // Relación con la orden de compra
    private Date fechaRecepcion;
    private List<DetalleRecepcion> productos; // Detalle de productos recibidos

    public static class DetalleRecepcion {
        private String productoId; // Relación con el producto
        private int cantidad;
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
