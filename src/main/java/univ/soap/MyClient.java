package univ.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Service;

import univ.model.City;
import univ.soap.CityNotFound;
import univ.soap.CityManagerService;
import univ.model.Position;

public class MyClient {
	
	private static final QName SERVICE_NAME = new QName("http://soap.univ/", "CityManagerService");
	private static final QName PORT_NAME = new QName("http://soap.univ/", "CityManagerPort");
	
	public static void main(String[] args) throws MalformedURLException {
		/**
		 * URL passée en argument avec le wsdl
		 */
		if (args.length != 1) {
			System.out.println("Argument : [url?wsdl]");
			System.exit(0);
		}
		//URL wsdlURL = new URL("http://localhost:8080/WebApp-1.0-SNAPSHOT/cityManager?wsdl");
		URL wsdlURL = new URL(args[0]);
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		CityManagerService cityManager = service.getPort(PORT_NAME, CityManagerService.class);
		
		System.out.println("\n Affichage de l'ensemble des villes");
		System.out.println(cityManager.getCities());
		
		System.out.println("\n Suppression de l'ensemble des villes");
		cityManager.removeAllCities();
		
		System.out.println("\n Affichage de l'ensemble des villes");
		System.out.println(cityManager.getCities());
		
		System.out.println("\n Ajout de Rouen en France");
		System.out.println(cityManager.addCity(new City("Rouen",49.443889,1.103333,"France")));
		
		System.out.println("\n Ajout de Mogadiscio en Somalie");
		System.out.println(cityManager.addCity(new City("Mogadiscio",2.333333,48.85,"Somalie")));
		
		System.out.println("\n Ajout de Rouen en France (mêmes coordonnées que précédemment)");
		System.out.println(cityManager.addCity(new City("Rouen",49.443889,1.103333,"France")));
		
		System.out.println("\n Ajout de Bihorel en France");
		System.out.println(cityManager.addCity(new City("Bihorel",49.455278,1.116944,"France")));
		
		System.out.println("\n Ajout de Londres en Angleterre");
		City londres = new City("Londres",51.504872,-0.07857,"Angleterre");
		System.out.println(cityManager.addCity(londres));
		
		System.out.println("\n Ajout de Paris en France");
		System.out.println(cityManager.addCity(new City("Paris",48.856578,2.351828,"France")));
		
		System.out.println("\n Ajout de Paris au Canada");
		System.out.println(cityManager.addCity(new City("Paris",43.2,-80.38333,"Canada")));
		
		System.out.println("\n Affichage de l'ensemble des villes");
		System.out.println(cityManager.getCities());
		
		System.out.println("\n Ajout de Villiers-Bocage en France");
		System.out.println(cityManager.addCity(new City("Villiers-Bocage",49.083333,-0.65,"France")));
		
		System.out.println("\n Ajout de Villiers-Bocage en France");
		City villiersBocage = new City("Villiers-Bocage",50.021858,2.326126,"France");
		System.out.println(cityManager.addCity(villiersBocage));
		
		System.out.println("\n Affichage de l'ensemble des villes");
		System.out.println(cityManager.getCities());
		
		System.out.println("\n Suppression de la ville Villiers-Bocage");
		System.out.println(cityManager.removeCity(villiersBocage));
		
		System.out.println("\n Affichage de l'ensemble des villes");
		System.out.println(cityManager.getCities());
		
		System.out.println("\n Suppression de la ville de Londres");
		System.out.println(cityManager.removeCity(londres));
		
		System.out.println("\n Suppression de la ville de Londres");
		System.out.println(cityManager.removeCity(londres));

		System.out.println("\n Affichage de la ville située à la position exacte 49.443889,1.103333");
		try {
			System.out.println(cityManager.searchExactPosition(new Position(49.443889,1.103333)));
		} catch (CityNotFound e) {
			System.out.println(e);
		}
		
		System.out.println("\n Affichage de la ville située à la position exacte 49.083333,-0.65");
		try {
			System.out.println(cityManager.searchExactPosition(new Position(49.083333,-0.65)));
		} catch (CityNotFound e) {
			System.out.println(e);
		}
		
		System.out.println("\n Affichage de la ville située à la position exacte 43.2,-80.38");
		try {
			System.out.println(cityManager.searchExactPosition(new Position(43.2,-80.38)));
		} catch (CityNotFound e) {
			System.out.println(e);
		}
		
		System.out.println("\n Affichage de la ville située autour de 48.85,2.34");
		System.out.println(cityManager.searchNear(new Position(48.85,2.34)));
		
		System.out.println("\n Affichage de la ville située autour de 42,64");
		System.out.println(cityManager.searchNear(new Position(42,64)));
		
		System.out.println("\n Affichage de la ville située autour de 49.45,1.11");
		System.out.println(cityManager.searchNear(new Position(49.45,1.11)));
		
		System.out.println("\n Affichage de(s) ville(s) Mogadiscio");
		System.out.println(cityManager.searchFor("Mogadiscio"));
		
		System.out.println("\n Affichage de(s) ville(s) Paris");
		System.out.println(cityManager.searchFor("Paris"));
		
		System.out.println("\n Affichage de(s) ville(s) Hyrule");
		System.out.println(cityManager.searchFor("Hyrule"));
		
		/*System.out.println("\n Suppression de l'ensemble des villes");
		cityManager.removeAllCities();*/
		
		System.out.println("\n Affichage de l'ensemble des villes");
		System.out.println(cityManager.getCities());
		
	}

}
