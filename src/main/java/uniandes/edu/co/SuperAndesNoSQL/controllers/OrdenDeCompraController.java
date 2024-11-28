package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.OrdenDeCompra;
import uniandes.edu.co.SuperAndesNoSQL.entities.OrdenDeCompra.DetalleProducto;
import uniandes.edu.co.SuperAndesNoSQL.repositories.OrdenDeCompraRepository;
import uniandes.edu.co.SuperAndesNoSQL.repositories.ProductoRepository;
import uniandes.edu.co.SuperAndesNoSQL.repositories.ProveedorRepository;
import uniandes.edu.co.SuperAndesNoSQL.repositories.SucursalRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired 
    private ProductoRepository productoRepository;
    
    // Crear una nueva orden de compra
    @PostMapping("/new")
    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            // Validar que la sucursal exista
            if (!sucursalRepository.existsById(ordenDeCompra.getSucursalId())) {
                return new ResponseEntity<>("La sucursal no existe", HttpStatus.BAD_REQUEST);
            }
    
            // Validar que el proveedor exista
            if (!proveedorRepository.existsById(ordenDeCompra.getProveedorId())) {
                return new ResponseEntity<>("El proveedor no existe", HttpStatus.BAD_REQUEST);
            }
    
            // Validar que todos los productos en el detalle existan
            for (DetalleProducto detalle : ordenDeCompra.getDetalles()) {
                if (!productoRepository.existsById(detalle.getProductoId())) {
                    return new ResponseEntity<>("El producto con ID " + detalle.getProductoId() + " no existe", HttpStatus.BAD_REQUEST);
                }
            }
    
            // Establecer la fecha de creación a la fecha actual del sistema
            ordenDeCompra.setFechaCreacion(new Date());
    
            // Establecer el estado inicial a "vigente"
            ordenDeCompra.setEstado("Vigente");
    
            // Guardar la orden de compra
            ordenDeCompraRepository.save(ordenDeCompra);
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las órdenes de compra
    @GetMapping
    public ResponseEntity<List<OrdenDeCompra>> obtenerTodasLasOrdenesDeCompra() {
        try {
            List<OrdenDeCompra> ordenes = ordenDeCompraRepository.findAll();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener una orden de compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDeCompra> obtenerOrdenDeCompraPorId(@PathVariable String id) {
        try {
            Optional<OrdenDeCompra> orden = ordenDeCompraRepository.findById(id);
            return orden.map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una orden de compra
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarOrdenDeCompra(@PathVariable String id, @RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            Optional<OrdenDeCompra> ordenExistente = ordenDeCompraRepository.findById(id);
            if (ordenExistente.isPresent()) {
                ordenDeCompra.setId(id);
                ordenDeCompraRepository.save(ordenDeCompra);
                return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una orden de compra por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOrdenDeCompra(@PathVariable String id) {
        try {
            if (ordenDeCompraRepository.existsById(id)) {
                ordenDeCompraRepository.deleteById(id);
                return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
