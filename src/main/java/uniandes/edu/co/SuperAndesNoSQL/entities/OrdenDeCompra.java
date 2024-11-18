package uniandes.edu.co.SuperAndesNoSQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "ordenes_compra")
public class OrdenDeCompra {
    @Id
    private String id;
    private Date fechaCreacion;
    private String sucursalId; // Relación con la sucursal
    private String proveedorId; // Relación con el proveedor
    private String estado; // "Vigente", "Entregada", "Anulada"
    private List<DetalleOrden> detalle; // Detalle de los productos en la orden

    public static class DetalleOrden {
        private String productoId; // Relación con el producto
        private int cantidad;
        private double precio;

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

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        // Constructor
        public DetalleOrden(String productoId, int cantidad, double precio) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        // Constructor vacio
        public DetalleOrden() {

        }
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(String sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }

    // Constructor
    public OrdenDeCompra(String id, Date fechaCreacion, String sucursalId, String proveedorId, String estado,
            List<DetalleOrden> detalle) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.sucursalId = sucursalId;
        this.proveedorId = proveedorId;
        this.estado = estado;
        this.detalle = detalle;
    }

    // Constructor vacio
    public OrdenDeCompra() {

    }

}
