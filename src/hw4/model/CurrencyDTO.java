package hw4.model;

public interface CurrencyDTO {
	public String getCountryName();
	public String getCurrencyName();
	public String getCurrencyCode();
	public double getBaseCurrencyRate();
	public void setCountryName(String countryName);
	public void setCurrencyName(String currencyName);
	public void setBaseCurrencyRate(double rate);
	
}
