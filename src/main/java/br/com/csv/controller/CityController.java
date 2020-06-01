package br.com.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.csv.model.City;
import br.com.csv.service.CityService;

@RestController
@RequestMapping(path = "/api/citys")
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping(path = "csvImport")
	public void setDataInDB() {
		cityService.saveCityData();
	}

	@RequestMapping(path = "/findCapital", method = RequestMethod.GET)
	public ResponseEntity<?> findCapital() {
		try {
			List<City> capital = cityService.findCapital();
			return ResponseEntity.ok(capital);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/numberOfCitiesByState/{uf}", method = RequestMethod.GET)
	public ResponseEntity<?> numberOfCitiesByState(@PathVariable(value = "uf") String uf) {
		try {
			String total = cityService.numberOfCitiesByState(uf);
			return ResponseEntity.ok(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/findByIdIbge/{idIbge}", method = RequestMethod.GET)
	public ResponseEntity<?> findByIdIbge(@PathVariable(value = "idIbge") Integer idIbge) {
		try {
			City city = cityService.findByidIbge(idIbge);
			return ResponseEntity.ok(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/citiesByState/{uf}", method = RequestMethod.GET)
	public ResponseEntity<?> citiesByState(@PathVariable(value = "uf") String uf) {
		try {
			List<City> cities = cityService.findCitiesByState(uf);
			return ResponseEntity.ok(cities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/findByFilter/{column}/{filter}", method = RequestMethod.GET)

	public ResponseEntity<?> countRecordsByColumn(@PathVariable(value = "column") String column,
			@PathVariable(value = "filter") String filter) {
		try {
			List<City> cities = cityService.findByFilter(column, filter);
			return ResponseEntity.ok(cities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/findByColumn/{column}", method = RequestMethod.GET)

	public ResponseEntity<?> countRecordsByColumn(@PathVariable(value = "column") String column) {
		try {
			List<City> cities = cityService.findByColumn(column);
			return ResponseEntity.ok(cities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/totalNumberOfCities", method = RequestMethod.GET)
	public ResponseEntity<?> totalNumberOfCities() {
		try {
			Integer total = cityService.totalNumberOfCities();
			return ResponseEntity.ok(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/addCity", method = RequestMethod.POST)
	public ResponseEntity<?> importCsv(@RequestBody City city) {
		try {
			cityService.addCity(city);
			return ResponseEntity.ok(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value = "/deleteCity/{idCity}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable(value = "idCity") Integer idcity) {
		try {
			cityService.deleteCity(idcity);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	// metodo para transformar o objeto em json
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getModel() {
		City cityToJson = new City();
		return ResponseEntity.ok(cityToJson);
	}

}
