package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.SuperAndesNoSQL.entities.Categoria;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    // Buscar categoría por nombre
    @Query("{ 'nombre': ?0 }")
    Categoria buscarPorNombre(String nombre);
}
