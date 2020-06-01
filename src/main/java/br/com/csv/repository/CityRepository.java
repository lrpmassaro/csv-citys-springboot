package br.com.csv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.csv.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM city WHERE capital = 'TRUE' ORDER BY name")
	public List<City> findCapital();

	@Query(nativeQuery = true, value = "SELECT COUNT(uf) FROM city WHERE uf = ?1")
	public String numberOfCitiesByState(String uf);

	@Query(nativeQuery = true, value = "SELECT * FROM city WHERE ibge_id = ?1")
	public City findByIdIbge(Integer idIbge);

	@Query(nativeQuery = true, value = "SELECT * FROM city WHERE uf = ?1")
	public List<City> findCitiesByState(String uf);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM city")
	public Integer totalNumberOfCities();

	@Query(nativeQuery = true, value = "SELECT COUNT(DISTINCT ?1) FROM city")
	public List<City> findByColumn(String column);

}
