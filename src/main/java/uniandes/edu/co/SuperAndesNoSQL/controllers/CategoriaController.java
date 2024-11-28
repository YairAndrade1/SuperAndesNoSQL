package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.Categoria;
import uniandes.edu.co.SuperAndesNoSQL.repositories.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear una nueva categoría
    @PostMapping("/new")
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria) {
        try {
            if (categoriaRepository.existsById(categoria.getId())) {
                return new ResponseEntity<>("El ID de la categoría ya existe", HttpStatus.BAD_REQUEST);
            }
            categoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoría creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la categoría: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        try {
            List<Categoria> categorias = categoriaRepository.findAll();
            return ResponseEntity.ok(categorias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable String id) {
        try {
            Optional<Categoria> categoria = categoriaRepository.findById(id);
            return categoria.map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una categoría
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCategoria(@PathVariable String id, @RequestBody Categoria categoria) {
        try {
            Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
            if (categoriaExistente.isPresent()) {
                Categoria categoriaActual = categoriaExistente.get();
                if (!categoria.getId().equals(categoriaActual.getId())) {
                    return new ResponseEntity<>("El ID no puede ser cambiado", HttpStatus.BAD_REQUEST);
                }
                categoria.setId(id); // Asegurar que el ID no cambie
                categoriaRepository.save(categoria);
                return new ResponseEntity<>("Categoría actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categoría no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la categoría: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable String id) {
        try {
            if (categoriaRepository.existsById(id)) {
                categoriaRepository.deleteById(id);
                return new ResponseEntity<>("Categoría eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categoría no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la categoría: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar categoría por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Categoria> buscarPorNombre(@PathVariable String nombre) {
        try {
            Categoria categoria = categoriaRepository.buscarPorNombre(nombre);
            return categoria != null ? ResponseEntity.ok(categoria) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
