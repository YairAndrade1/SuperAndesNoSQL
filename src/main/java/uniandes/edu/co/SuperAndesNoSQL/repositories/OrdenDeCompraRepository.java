package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.SuperAndesNoSQL.entities.OrdenDeCompra;

import java.util.List;

@Repository
public interface OrdenDeCompraRepository extends MongoRepository<OrdenDeCompra, String> {

    // Buscar órdenes por estado
    @Query("{ 'estado': ?0 }")
    List<OrdenDeCompra> buscarPorEstado(String estado);

    // Buscar órdenes por sucursal
    @Query("{ 'sucursal_id': ?0 }")
    List<OrdenDeCompra> buscarPorSucursal(String sucursalId);
}
