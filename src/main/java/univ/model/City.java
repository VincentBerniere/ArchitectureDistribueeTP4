package univ.model; 

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent a city with its  
 * <ul>
 * 	<li>name</li>
 * 	<li>latitude</li>
 * 	<li>longitude</li>
 * 	<li>country</li>
 * </ul>
 *
 */
@XmlRootElement
public class City {

	private String name;
	private Position location;
	private String country;
	
	/**
	 * Creates a city with its name, its latitude, its longitude and its country
	 * @param name the name of the city
	 * @param latitude the latitude of the city in WGS84
	 * @param longitude the longitude of the city in WGS84
	 * @param country the country of the city
	 */
	public City(String name, double latitude, double longitude, String country) {
		this.name = name;
		this.location = new Position(latitude,longitude);
		this.country = country;
	}
	
	public City() {
	}
	
	public Position getPosition() {
		return location;
	}
	public void setPosition(Position position) {
		this.location = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString(){
		final StringBuffer buffer = new StringBuffer();
		buffer.append(name).append(" in ").append(country).append(" at ").append(location);
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
