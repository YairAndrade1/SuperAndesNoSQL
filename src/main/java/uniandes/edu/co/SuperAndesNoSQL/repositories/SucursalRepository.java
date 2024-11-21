package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.SuperAndesNoSQL.entities.Sucursal;

import java.util.List;

@Repository
public interface SucursalRepository extends MongoRepository<Sucursal, String> {

    // Buscar sucursales por ciudad
    @Query("{ 'ciudad': ?0 }")
    List<Sucursal> buscarPorCiudad(String ciudad);

    // Buscar sucursal por nombre
    @Query("{ 'nombre': ?0 }")
    Sucursal buscarPorNombre(String nombre);
}
