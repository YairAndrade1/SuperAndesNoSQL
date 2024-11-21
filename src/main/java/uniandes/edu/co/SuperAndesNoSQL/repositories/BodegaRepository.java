package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.SuperAndesNoSQL.entities.Bodega;

import java.util.List;

@Repository
public interface BodegaRepository extends MongoRepository<Bodega, String> {

    // Buscar bodegas por sucursal
    @Query("{ 'sucursalId': ?0 }")
    List<Bodega> buscarPorSucursal(String sucursalId);

    // Buscar bodega por nombre
    @Query("{ 'nombre': ?0 }")
    Bodega buscarPorNombre(String nombre);

    // Obtener inventario de productos en una sucursal
    @Query("{ 'sucursal_id': ?0 }")
    List<Bodega> obtenerBodegasPorSucursal(String sucursalId);

}
