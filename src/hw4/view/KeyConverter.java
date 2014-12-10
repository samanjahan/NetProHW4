package hw4.view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

 
@FacesConverter( value="keyConverter" )
public class KeyConverter implements Converter {
	
	

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		System.out.println("getObject " + value);	
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		System.out.println("getAsString");
		return null;
	}
 
}
