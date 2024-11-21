package uniandes.edu.co.SuperAndesNoSQL.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.SuperAndesNoSQL.entities.Producto;

import java.util.Date;
import java.util.List;

@Repository
public class ProductoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public ProductoRepositoryCustom(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Producto> buscarPorCaracteristicas(Double precioMin, Double precioMax, Date fechaVencimiento, String categoriaId, String sucursalId) {
        Query query = new Query();

        if (precioMin != null || precioMax != null) {
            Criteria precioCriteria = Criteria.where("precio_unitario");
            if (precioMin != null) {
                precioCriteria.gte(precioMin);
            }
            if (precioMax != null) {
                precioCriteria.lte(precioMax);
            }
            query.addCriteria(precioCriteria);
        }

        if (fechaVencimiento != null) {
            query.addCriteria(Criteria.where("fecha_expiracion").gte(fechaVencimiento));
        }

        if (categoriaId != null) {
            query.addCriteria(Criteria.where("categoria_id").is(categoriaId));
        }

        if (sucursalId != null) {
            query.addCriteria(Criteria.where("sucursal_id").is(sucursalId));
        }

        return mongoTemplate.find(query, Producto.class);
    }
}
