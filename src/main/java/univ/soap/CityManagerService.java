package univ.soap;


import java.util.List;

import javax.jws.WebService;

import univ.model.City;
import univ.soap.CityNotFound;
import univ.model.Position;

@WebService
public interface CityManagerService {
	boolean addCity(City city);
	boolean removeCity(City city);
	void removeAllCities();
	List<City> getCities();
	City searchExactPosition(Position position) throws CityNotFound;
	List<City> searchNear(Position position);
	List<City> searchFor(String cityName);
}
