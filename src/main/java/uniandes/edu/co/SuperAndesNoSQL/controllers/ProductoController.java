package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.SuperAndesNoSQL.entities.Producto;
import uniandes.edu.co.SuperAndesNoSQL.repositories.CategoriaRepository;
import uniandes.edu.co.SuperAndesNoSQL.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear un nuevo producto
    @PostMapping("/new")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
        try {
            if (productoRepository.existsById(producto.getId())) {
                return new ResponseEntity<>("El ID del producto ya existe", HttpStatus.BAD_REQUEST);
            }
            if (productoRepository.buscarPorCodigoBarras(producto.getCodigoBarras()) != null) {
                return new ResponseEntity<>("El código de barras del producto ya existe", HttpStatus.BAD_REQUEST);
            }
            if (!categoriaRepository.existsById(producto.getCategoriaId())) {
                return new ResponseEntity<>("La categoría asociada no existe", HttpStatus.BAD_REQUEST);
            }
            productoRepository.save(producto);
            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        try {
            List<Producto> productos = productoRepository.findAll();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable String id) {
        try {
            Optional<Producto> producto = productoRepository.findById(id);
            return producto.map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable String id, @RequestBody Producto producto) {
        try {
            Optional<Producto> productoExistente = productoRepository.findById(id);
            if (productoExistente.isPresent()) {
                Producto productoActual = productoExistente.get();
                if (!producto.getId().equals(productoActual.getId())) {
                    return new ResponseEntity<>("El ID no puede ser cambiado", HttpStatus.BAD_REQUEST);
                }
                if (!producto.getCodigoBarras().equals(productoActual.getCodigoBarras()) &&
                        productoRepository.buscarPorCodigoBarras(producto.getCodigoBarras()) != null) {
                    return new ResponseEntity<>("El código de barras del producto ya existe", HttpStatus.BAD_REQUEST);
                }
                if (!categoriaRepository.existsById(producto.getCategoriaId())) {
                    return new ResponseEntity<>("La categoría asociada no existe", HttpStatus.BAD_REQUEST);
                }
                producto.setId(id); // Asegurar que el ID no cambie
                productoRepository.save(producto);
                return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable String id) {
        try {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id);
                return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Consultar productos por categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable String categoriaId) {
        try {
            List<Producto> productos = productoRepository.buscarPorCategoria(categoriaId);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Consultar producto por código de barras
    @GetMapping("/codigo-barras/{codigoBarras}")
    public ResponseEntity<Producto> obtenerProductoPorCodigoBarras(@PathVariable String codigoBarras) {
        try {
            Producto producto = productoRepository.buscarPorCodigoBarras(codigoBarras);
            if (producto != null) {
                return ResponseEntity.ok(producto);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
