package hw4.view;

import hw4.controller.CurrencyFacade;
import hw4.model.CurrencyDTO;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

 
@FacesConverter( value="cConverter" )
public class cConverter implements Converter {
	@EJB
	CurrencyFacade cf;
	

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CurrencyDTO currency;
		currency = cf.findCurrency(value);
		return currency;
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		CurrencyDTO currency = (CurrencyDTO)object;
		return currency.toString();
	}
 
}
