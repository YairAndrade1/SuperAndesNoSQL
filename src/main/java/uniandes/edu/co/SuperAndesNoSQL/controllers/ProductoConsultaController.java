package uniandes.edu.co.SuperAndesNoSQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.SuperAndesNoSQL.entities.Producto;
import uniandes.edu.co.SuperAndesNoSQL.repositories.ProductoRepositoryCustom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoConsultaController {

    @Autowired
    private ProductoRepositoryCustom productoRepositoryCustom;

    @GetMapping("/filtrar")
    public ResponseEntity<List<Producto>> filtrarProductos(
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) String fechaVencimiento,
            @RequestParam(required = false) String categoriaId,
            @RequestParam(required = false) String sucursalId) {
        try {
            Date fecha = null;
            if (fechaVencimiento != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                fecha = formatter.parse(fechaVencimiento);
            }

            List<Producto> productos = productoRepositoryCustom.buscarPorCaracteristicas(
                    precioMin,
                    precioMax,
                    fecha,
                    categoriaId,
                    sucursalId
            );

            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
