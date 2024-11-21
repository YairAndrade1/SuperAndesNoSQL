package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.Bodega;
import uniandes.edu.co.SuperAndesNoSQL.entities.Bodega.ProductoBodega;
import uniandes.edu.co.SuperAndesNoSQL.repositories.BodegaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Crear una nueva bodega
    @PostMapping("/new")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        try {
            bodegaRepository.save(bodega);
            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la bodega: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las bodegas
    @GetMapping
    public ResponseEntity<List<Bodega>> obtenerTodasLasBodegas() {
        try {
            List<Bodega> bodegas = bodegaRepository.findAll();
            return ResponseEntity.ok(bodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener una bodega por ID
    @GetMapping("/{id}")
    public ResponseEntity<Bodega> obtenerBodegaPorId(@PathVariable String id) {
        try {
            Optional<Bodega> bodega = bodegaRepository.findById(id);
            return bodega.map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una bodega
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarBodega(@PathVariable String id, @RequestBody Bodega bodega) {
        try {
            Optional<Bodega> bodegaExistente = bodegaRepository.findById(id);
            if (bodegaExistente.isPresent()) {
                bodega.setId(id); // Asegurar que el ID no cambie
                bodegaRepository.save(bodega);
                return new ResponseEntity<>("Bodega actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bodega no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la bodega: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una bodega por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarBodega(@PathVariable String id) {
        try {
            if (bodegaRepository.existsById(id)) {
                bodegaRepository.deleteById(id);
                return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bodega no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Consultar bodegas por sucursal
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Bodega>> obtenerBodegasPorSucursal(@PathVariable String sucursalId) {
        try {
            List<Bodega> bodegas = bodegaRepository.buscarPorSucursal(sucursalId);
            return ResponseEntity.ok(bodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/sucursal/{sucursalId}/inventario")
    public ResponseEntity<Map<String, Object>> obtenerInventarioPorSucursal(@PathVariable String sucursalId) {
        try {
            List<Bodega> bodegas = bodegaRepository.obtenerBodegasPorSucursal(sucursalId);
            Map<String, Object> reporte = new HashMap<>();

            for (Bodega bodega : bodegas) {
                List<Map<String, Object>> productosReporte = new ArrayList<>();

                for (ProductoBodega producto : bodega.getProductos()) {
                    Map<String, Object> detalleProducto = new HashMap<>();
                    detalleProducto.put("productoId", producto.getProductoId());
                    detalleProducto.put("cantidadActual", producto.getCantidad());
                    detalleProducto.put("costoPromedio", producto.getCostoPromedio());
                    productosReporte.add(detalleProducto);
                }

                reporte.put(bodega.getNombre(), productosReporte);
            }

            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
