package univ.soap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

import univ.model.City;
import univ.soap.CityNotFound;
import univ.model.Position;

@WebService(endpointInterface = "univ.soap.CityManagerService", 
serviceName = "CityManagerService")
public class CityManager implements CityManagerService {

	private List<City> cities;
	/**
	 * Constructeur
	 */
	public CityManager() {
		this.cities = new LinkedList<City>();
	}
	/**
	 * retourne l'ensemble des villes
	 */
	public List<City> getCities() {
		return cities;
	}
	/**
	 * change la liste de ville actuelle par la nouvelle
	 * @param cities
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	/**
	 * ajoute la ville donné en paramètre
	 */
	public boolean addCity(City city){
		if(!cities.contains(city)){
			cities.add(city);
			return true;
		}
		
		return false;
	}
	/**
	 * supprime la ville donnée en paramètre
	 */
	public boolean removeCity(City city){
		if(cities.contains(city)){
			cities.remove(city);
			return true;
		}
		
		return cities.remove(city);
	}
	/**
	 * retourne true si la ville donnée en paramètre existe
	 * @param city
	 * @return
	 */
	public boolean isCityExist(City city){
		return cities.contains(city);
	}
	/**
	 * retourne toutes les villes dont le nom est donné en paramètre
	 */
	public List<City> searchFor(String cityName){
		
		List<City> listCities = new ArrayList<City>();
		
		for(City c:cities)
			if(c.getName().equals(cityName))
				listCities.add(c);
		
		return listCities;
	}
	
	/**
	 * recherche une ville à une position donnée
	 */
	public City searchExactPosition(Position position) throws CityNotFound{
		for(City city:cities){
			if (position.equals(city.getPosition())){
				return city;
			}
		}
		throw new CityNotFound("Aucune ville à la position : "+position, new CityNotFoundBean());
	}
	
	/**
	 * fonction qui retourne la liste des villes à dix klomètres d'une position
	 */
	public List<City> searchNear(Position position) {
		List<City> listCities = new ArrayList<City>();
		
		int R = 6371000; // Rayon de la Terre en metres
		double latPos = Math.toRadians(position.getLatitude());
		
		for(City city:cities){
			double latCit = Math.toRadians(city.getPosition().getLatitude());
			double i1 = Math.toRadians(city.getPosition().getLatitude() - position.getLatitude());
			double i2 = Math.toRadians(city.getPosition().getLongitude() - position.getLongitude());
			
			double a = Math.sin(i1/2) * Math.sin(i2/2) + 
					Math.cos(latPos) * Math.cos(latCit) *
					Math.sin(i2/2) * Math.sin(i2/2);
			
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			
			// c*10000 conversion en kilomètre <= 10 kilomètre 
			if(c*10000 <= 10) {
				listCities.add(city);
			}
		}
		
		return listCities;
	}
	
	/**
	 * Supprime toutes les villes
	 */
	public void removeAllCities() {
		setCities(new LinkedList<City>());
	}
	
}
