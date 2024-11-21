package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.Sucursal;
import uniandes.edu.co.SuperAndesNoSQL.repositories.SucursalRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    // Crear una nueva sucursal
    @PostMapping("/new")
    public ResponseEntity<String> crearSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursalRepository.save(sucursal);
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las sucursales
    @GetMapping
    public ResponseEntity<List<Sucursal>> obtenerTodasLasSucursales() {
        try {
            List<Sucursal> sucursales = sucursalRepository.findAll();
            return ResponseEntity.ok(sucursales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener una sucursal por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursalPorId(@PathVariable String id) {
        try {
            Optional<Sucursal> sucursal = sucursalRepository.findById(id);
            return sucursal.map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una sucursal
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarSucursal(@PathVariable String id, @RequestBody Sucursal sucursal) {
        try {
            Optional<Sucursal> sucursalExistente = sucursalRepository.findById(id);
            if (sucursalExistente.isPresent()) {
                sucursal.setId(id); // Asegurar que el ID no cambie
                sucursalRepository.save(sucursal);
                return new ResponseEntity<>("Sucursal actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una sucursal por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSucursal(@PathVariable String id) {
        try {
            if (sucursalRepository.existsById(id)) {
                sucursalRepository.deleteById(id);
                return new ResponseEntity<>("Sucursal eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Sucursal no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la sucursal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar sucursales por ciudad
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Sucursal>> buscarPorCiudad(@PathVariable String ciudad) {
        try {
            List<Sucursal> sucursales = sucursalRepository.buscarPorCiudad(ciudad);
            return ResponseEntity.ok(sucursales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
}
