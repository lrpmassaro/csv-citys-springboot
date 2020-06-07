package br.com.csv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.csv.model.City;
import br.com.csv.repository.CityRepository;
import br.com.csv.repository.ColumnsRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service

public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	ColumnsRepository columnsRepository;

	String line = null;

	public String saveCityData() {
		try {
			BufferedReader bfrd = new BufferedReader(new FileReader("src/main/resources/citys.csv"));
			bfrd.readLine();
			while ((line = bfrd.readLine()) != null) {
				String[] data = line.split(",");
				City c = new City();
				c.setIbge_id(Integer.parseInt(data[0]));
				c.setUf(data[1]);
				c.setName(data[2]);
				c.setCapital((data[3]));
				c.setLon((data[4]));
				c.setLat((data[5]));
				c.setNo_accents(data[6]);
				c.setAlternative_names(data[7]);
				c.setMicroregion(data[8]);
				c.setMesoregion(data[9]);
				cityRepository.save(c);
			}
			bfrd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Arquivo CSV importado com sucesso!";
	}

	public List<City> findCapital() {
		return cityRepository.findCapital();
	}

	public Integer numberOfCitiesByState(String uf) {
		return cityRepository.numberOfCitiesByState(uf);
	}

	public City findByidIbge(Integer idIbge) {
		return cityRepository.findByIdIbge(idIbge);
	}

	public List<City> findCitiesByState(String uf) {
		return cityRepository.findCitiesByState(uf);
	}

	public Integer totalNumberOfCities() {
		return cityRepository.totalNumberOfCities();
	}

	public List<City> findByFilter(String column, String filter) {
		return cityRepository.findByFilter(column, filter);
	}
	
	public Integer findByColumn(String column) {
		return cityRepository.findByColumn(column);
	}

	public City addCity(City city) {
		if (!isPersist(city)) {
			buildCity(city);
			cityRepository.save(city);
		}
		return city;
	}

	private void buildCity(City city) {
		city.setIbge_id(city.getIbge_id());
		city.setUf(city.getUf());
		city.setName(city.getName());
		city.setCapital(city.getCapital());
		city.setLon(city.getLon());
		city.setLat(city.getLat());
		city.setNo_accents(city.getNo_accents());
		city.setAlternative_names(city.getAlternative_names());
		city.setMicroregion(city.getMicroregion());
		city.setMesoregion(city.getMesoregion());
	}

	private boolean isPersist(City city) {
		if (city.getIbge_id() != null) {
			return true;
		}
		return false;
	}

	public void deleteCity(Integer idCity) {
		cityRepository.deleteById(idCity);
	}
}
