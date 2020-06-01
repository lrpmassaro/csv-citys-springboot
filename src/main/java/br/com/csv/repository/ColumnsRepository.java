package br.com.csv.repository;

import br.com.csv.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ColumnsRepository {

    @PersistenceContext
    EntityManager entityManager;
  
    public List<City> findByFilters(String column, String filter){

        StringBuilder consultvalue = new StringBuilder();
        consultvalue.append("SELECT COUNT(DISTINCT ");
        consultvalue.append(getNameColumn(column));
        consultvalue.append(") FROM citys");
        TypedQuery<City> query = entityManager.createQuery(consultvalue.toString(), City.class);

        switch (column) {
            case "ibge_id" : query.setParameter("FILTER", (filter));break;
            case "lat" : query.setParameter("FILTER", (filter));break;
            case "lon" : query.setParameter("FILTER", (filter));break;
            default:
                query.setParameter("FILTER", filter);
        }

        return query.getResultList();
    }
    
    private String getNameColumn(String colum) {
        switch (colum) {
            case "idIbge" : return "ibge_id";
            case "uf" : return "state";
            case "name" : return "city";
            case "capital" : return "capital";
            case "lon" : return "longitude";
            case "lat" : return "latitude";
            case "no_accents" : return "noAccents";
            case "alternative_names" : return "alternativeNames";
            case "microregion" : return "microRegiao";
            case "mesoregion" : return "macroRegiao";

            default:
                throw new RuntimeException("A Coluna não foi encontrada.");
        }
    }

}
