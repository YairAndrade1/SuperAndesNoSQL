package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.OrdenDeCompra;
import uniandes.edu.co.SuperAndesNoSQL.repositories.OrdenDeCompraRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    // Crear una nueva orden de compra
    @PostMapping("/new")
    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
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
