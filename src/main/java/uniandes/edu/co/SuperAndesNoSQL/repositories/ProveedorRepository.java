package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.SuperAndesNoSQL.entities.Proveedor;

@Repository
public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

    // Buscar proveedor por NIT
    @Query("{ 'nit': ?0 }")
    Proveedor buscarPorNit(String nit);

    // Buscar proveedor por nombre
    @Query("{ 'nombre': ?0 }")
    Proveedor buscarPorNombre(String nombre);
}
