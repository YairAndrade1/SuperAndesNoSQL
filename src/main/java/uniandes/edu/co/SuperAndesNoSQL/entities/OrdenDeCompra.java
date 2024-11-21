package uniandes.edu.co.SuperAndesNoSQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "ordenes_compra")
public class OrdenDeCompra {

    @Id
    private String id;

    @Field("fecha_creacion")
    private Date fechaCreacion;

    @Field("fecha_entrega_esperada")
    private Date fechaEntregaEsperada;

    @Field("estado")
    private String estado;

    @Field("sucursal_id")
    private String sucursalId;

    @Field("proveedor_id")
    private String proveedorId;

    @Field("detalles")
    private List<DetalleProducto> detalles;

    public static class DetalleProducto {
        @Field("producto_id")
        private String productoId;

        @Field("cantidad")
        private int cantidad;

        @Field("precio_unitario")
        private double precioUnitario;

        // Constructor vacío
        public DetalleProducto() {}

        // Constructor completo
        public DetalleProducto(String productoId, int cantidad, double precioUnitario) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }

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

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }
    }

    // Constructor vacío
    public OrdenDeCompra() {}

    // Constructor completo
    public OrdenDeCompra(String id, Date fechaCreacion, Date fechaEntregaEsperada, String estado, String sucursalId, String proveedorId, List<DetalleProducto> detalles) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntregaEsperada = fechaEntregaEsperada;
        this.estado = estado;
        this.sucursalId = sucursalId;
        this.proveedorId = proveedorId;
        this.detalles = detalles;
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

    public Date getFechaEntregaEsperada() {
        return fechaEntregaEsperada;
    }

    public void setFechaEntregaEsperada(Date fechaEntregaEsperada) {
        this.fechaEntregaEsperada = fechaEntregaEsperada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public List<DetalleProducto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleProducto> detalles) {
        this.detalles = detalles;
    }
}
