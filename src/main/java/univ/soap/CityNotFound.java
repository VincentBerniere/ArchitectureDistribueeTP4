package univ.soap;

import javax.xml.ws.WebFault;

@WebFault(name="CityNotFound")
public class CityNotFound extends Exception {
	private CityNotFoundBean faultBean;
	 
    public CityNotFound(String message, CityNotFoundBean faultInfo){
        super(message);
        faultBean = faultInfo;
    }
 
    public CityNotFound(String message, CityNotFoundBean faultInfo, Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }
 
    public CityNotFoundBean getFaultInfo(){
        return faultBean;
    }
}
