package uniandes.edu.co.SuperAndesNoSQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "bodegas")
public class Bodega {
    @Id
    private String id;
    private String nombre;
    private int tamaño;
    private String sucursalId; // Relación con la sucursal
    private List<ProductoBodega> productos; // Lista de productos en la bodega

    public static class ProductoBodega {
        private String productoId; // Referencia al producto
        private int cantidad;
        private double costoPromedio;

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

        public double getCostoPromedio() {
            return costoPromedio;
        }

        public void setCostoPromedio(double costoPromedio) {
            this.costoPromedio = costoPromedio;
        }

        // Constructor
        public ProductoBodega(String productoId, int cantidad, double costoPromedio) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.costoPromedio = costoPromedio;
        }

        // Constructor vacio
        public ProductoBodega() {

        }

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

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(String sucursalId) {
        this.sucursalId = sucursalId;
    }

    public List<ProductoBodega> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoBodega> productos) {
        this.productos = productos;
    }

    // Constructor
    public Bodega(String id, String nombre, int tamaño, String sucursalId, List<ProductoBodega> productos) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.sucursalId = sucursalId;
        this.productos = productos;
    }

    // Constructor vacio
    public Bodega() {

    }

}
