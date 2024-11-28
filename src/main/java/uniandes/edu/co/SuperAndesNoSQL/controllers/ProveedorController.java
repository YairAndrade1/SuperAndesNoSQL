package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.Proveedor;
import uniandes.edu.co.SuperAndesNoSQL.repositories.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Crear un nuevo proveedor
    @PostMapping("/new")
    public ResponseEntity<String> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            if (proveedorRepository.existsById(proveedor.getId())) {
                return new ResponseEntity<>("El ID del proveedor ya existe", HttpStatus.BAD_REQUEST);
            }
            if (proveedorRepository.buscarPorNit(proveedor.getNit()) != null) {
                return new ResponseEntity<>("El NIT del proveedor ya existe", HttpStatus.BAD_REQUEST);
            }
            proveedorRepository.save(proveedor);
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodosLosProveedores() {
        try {
            List<Proveedor> proveedores = proveedorRepository.findAll();
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable String id) {
        try {
            Optional<Proveedor> proveedor = proveedorRepository.findById(id);
            return proveedor.map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar un proveedor
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable String id, @RequestBody Proveedor proveedor) {
        try {
            Optional<Proveedor> proveedorExistente = proveedorRepository.findById(id);
            if (proveedorExistente.isPresent()) {
                Proveedor proveedorActual = proveedorExistente.get();
                if (!proveedor.getNit().equals(proveedorActual.getNit())) {
                    return new ResponseEntity<>("El NIT no puede ser cambiado", HttpStatus.BAD_REQUEST);
                }
                proveedor.setId(id); // Asegurar que el ID no cambie
                proveedorRepository.save(proveedor);
                return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el proveedor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable String id) {
        try {
            if (proveedorRepository.existsById(id)) {
                proveedorRepository.deleteById(id);
                return new ResponseEntity<>("Proveedor eliminado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el proveedor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar proveedor por NIT
    @GetMapping("/nit/{nit}")
    public ResponseEntity<Proveedor> buscarPorNit(@PathVariable String nit) {
        try {
            Proveedor proveedor = proveedorRepository.buscarPorNit(nit);
            return proveedor != null ? ResponseEntity.ok(proveedor) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
