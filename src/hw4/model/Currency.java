package hw4.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Currency
 *
 */
@Entity
public class Currency implements Serializable, CurrencyDTO {

	   
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	private String countryName;
	
	@Column(nullable=false)
	private String currencyName;

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
	
	@Override
	public String getCountryName() {
		return countryName;
	}

	@Override
	public String getCurrencyName() {
		return currencyName;
	}

	@Override
	public double getBaseCurrencyRate() {
		return baseCurrencyRate;
	}

	@Override
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	@Override
	public void setBaseCurrencyRate(double rate) {
		this.baseCurrencyRate = rate;
	}

	@Override
	public String getCurrencyCode() {
		return currencyCode;
	}
   
}
