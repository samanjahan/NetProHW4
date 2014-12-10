package hw4.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: Currency
 *
 */
@NamedQueries({
	@NamedQuery(name = "findAllCurrencies", query = "SELECT Name FROM Currency Name"),
	@NamedQuery(name = "deleteCurrencyByName", query = "DELETE FROM Currency Name WHERE Name.currencyName = :currencyName"),
	@NamedQuery(name = "updateCurrency", query = "UPDATE Currency c SET c.baseCurrencyRate = :newRate WHERE c.currencyCode = :currencyName"),
	@NamedQuery(name = "findCurrencyByName", query = "SELECT Name FROM Currency Name WHERE Name.currencyName = :currencyName")})

@Entity
public class Currency implements Serializable, CurrencyDTO {

	
	@Column(nullable=false)
	private String countryName;
	
	@Column(nullable=false, unique= true)
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
		return currencyCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
   
}
