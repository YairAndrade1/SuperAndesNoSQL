package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.SuperAndesNoSQL.entities.RecepcionDeProductos;

import java.util.Date;
import java.util.List;

@Repository
public interface RecepcionDeProductosRepository extends MongoRepository<RecepcionDeProductos, String> {

    // Buscar recepciones por orden de compra
    @Query("{ 'orden_compra_id': ?0 }")
    List<RecepcionDeProductos> buscarPorOrdenCompra(String ordenCompraId);

    // Buscar recepciones por fecha
    @Query("{ 'fecha_recepcion': { $gte: ?0, $lte: ?1 } }")
    List<RecepcionDeProductos> buscarPorRangoDeFechas(Date fechaInicio, Date fechaFin);
}
