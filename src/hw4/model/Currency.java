package hw4.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Currency
 *
 */
@NamedQueries({
	@NamedQuery(name = "findAllCurrencies", query = "SELECT Name FROM Currency Name"),
	@NamedQuery(name = "findCurrencyByName", query = "DELETE FROM Currency Name where Name.currencyName = :currencyName")})

@Entity
public class Currency implements Serializable, CurrencyDTO {

	
	@Column(nullable=false)
	private String countryName;
	
	@Column(nullable=false)
	private String currencyName;
	
	@Id
	@Column(nullable=false)
	private String currencyCode;
	
	@Column(nullable=false)
	private double baseCurrencyRate;
	
	private static final long serialVersionUID = 1L;

	public Currency() {
		super();
	}
	
	public Currency(String countryName, String currencyName,String currencyCode, double exchangeRate) {
		this.countryName = countryName;
		this.currencyName = currencyName;
		this.currencyCode = currencyCode;
		this.baseCurrencyRate = exchangeRate;
	}
	
	public String getCountryName(){
		return countryName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public double getBaseCurrencyRate() {
		return baseCurrencyRate;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public void setBaseCurrencyRate(double rate) {
		this.baseCurrencyRate = rate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return currencyName;
	}
   
}
