package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.SuperAndesNoSQL.entities.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    // Consulta personalizada: Buscar producto por código de barras
    @Query("{ 'codigo_barras': ?0 }")
    Producto buscarPorCodigoBarras(String codigoBarras);

    // Consulta personalizada: Buscar productos por categoría
    @Query("{ 'categoria_id': ?0 }")
    List<Producto> buscarPorCategoria(String categoriaId);

    @Query("{ '_id': { $in: ?0 } }")
    List<Producto> findAllByIds(List<String> ids);
}
