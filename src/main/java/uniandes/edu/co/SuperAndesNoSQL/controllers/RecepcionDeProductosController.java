package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.RecepcionDeProductos;
import uniandes.edu.co.SuperAndesNoSQL.repositories.RecepcionDeProductosRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recepciones")
public class RecepcionDeProductosController {

    @Autowired
    private RecepcionDeProductosRepository recepcionDeProductosRepository;

    // Crear una nueva recepción
    @PostMapping("/new")
    public ResponseEntity<String> crearRecepcion(@RequestBody RecepcionDeProductos recepcion) {
        try {
            recepcionDeProductosRepository.save(recepcion);
            return new ResponseEntity<>("Recepción creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la recepción: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las recepciones
    @GetMapping
    public ResponseEntity<List<RecepcionDeProductos>> obtenerTodasLasRecepciones() {
        try {
            List<RecepcionDeProductos> recepciones = recepcionDeProductosRepository.findAll();
            return ResponseEntity.ok(recepciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener una recepción por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecepcionDeProductos> obtenerRecepcionPorId(@PathVariable String id) {
        try {
            Optional<RecepcionDeProductos> recepcion = recepcionDeProductosRepository.findById(id);
            return recepcion.map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una recepción
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarRecepcion(@PathVariable String id, @RequestBody RecepcionDeProductos recepcion) {
        try {
            Optional<RecepcionDeProductos> recepcionExistente = recepcionDeProductosRepository.findById(id);
            if (recepcionExistente.isPresent()) {
                recepcion.setId(id); // Asegurar que el ID no cambie
                recepcionDeProductosRepository.save(recepcion);
                return new ResponseEntity<>("Recepción actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Recepción no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la recepción: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una recepción por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRecepcion(@PathVariable String id) {
        try {
            if (recepcionDeProductosRepository.existsById(id)) {
                recepcionDeProductosRepository.deleteById(id);
                return new ResponseEntity<>("Recepción eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Recepción no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la recepción: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar recepciones por orden de compra
    @GetMapping("/orden-compra/{ordenCompraId}")
    public ResponseEntity<List<RecepcionDeProductos>> buscarPorOrdenCompra(@PathVariable String ordenCompraId) {
        try {
            List<RecepcionDeProductos> recepciones = recepcionDeProductosRepository.buscarPorOrdenCompra(ordenCompraId);
            return ResponseEntity.ok(recepciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Buscar recepciones por rango de fechas
    @GetMapping("/fechas")
    public ResponseEntity<List<RecepcionDeProductos>> buscarPorRangoDeFechas(
            @RequestParam String fechaInicio, 
            @RequestParam String fechaFin) {
        try {
            Date fechaInicioDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
            Date fechaFinDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
            List<RecepcionDeProductos> recepciones = recepcionDeProductosRepository.buscarPorRangoDeFechas(fechaInicioDate, fechaFinDate);
            return ResponseEntity.ok(recepciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
}

}
