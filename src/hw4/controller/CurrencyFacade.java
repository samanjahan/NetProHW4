package hw4.controller;

import java.util.List;

import hw4.model.*;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

	/**
	 * A controller. All calls to the model that are executed because of an action taken by
	 * the cashier pass through here.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Stateless
	public class CurrencyFacade {
	    @PersistenceContext(unitName = "convertPU")
	    private EntityManager em;

	    public CurrencyDTO createCurrency(String countryName, String currencyName, String currencyCode, double exchangeRate) {
	        Currency newCurrency = new Currency(countryName, currencyName, currencyCode, exchangeRate);
	        em.persist(newCurrency);
	        return newCurrency;
	    }

	    public CurrencyDTO findCurrency(String currencyCode) {
	    	CurrencyDTO found =  em.find(Currency.class, currencyCode);
	        if (found == null) {
	            throw new EntityNotFoundException("No currency with code " + currencyCode);
	        }
	        return found;
	    }

	    public void changeRate(String currencyCode, double newExchangeRate){
	        Currency currency = em.find(Currency.class, currencyCode);
	        currency.setBaseCurrencyRate(newExchangeRate);
	    }
	    
	    public List<CurrencyDTO> getAllCurrencys(){
	    	return em.createNamedQuery("findAllCurrencies", CurrencyDTO.class).getResultList();
	    }

		public void deleteCurrencyByName(String chosenCurrency) {
			em.createNamedQuery("deleteCurrencyByName").setParameter("currencyName", chosenCurrency).executeUpdate();
		}
		
		public void deleteCurrency(CurrencyDTO chosenCurrency) {
			Currency currency = em.find(Currency.class, chosenCurrency.getCurrencyCode());
			em.remove(currency);
		}
		
		public void deleteCurrencyFromString(String chosenCurrency) {
			Currency currency = em.find(Currency.class, findCurrency(chosenCurrency).getCurrencyCode());
			em.remove(currency);
		}
		
		public CurrencyDTO findCurrencyByName(String chosenCurrency) {
			CurrencyDTO currency = em.createNamedQuery("findCurrencyByName", CurrencyDTO.class).setParameter("currencyName", chosenCurrency).getSingleResult();
			return currency;
		}

		public void updateCurrency(CurrencyDTO chosenCurrencyForUpdate, double newRate) {
			System.out.println("Inside cf Update " + chosenCurrencyForUpdate + " to " +  newRate);
			em.createNamedQuery("updateCurrency").setParameter("currencyName", chosenCurrencyForUpdate.getCurrencyCode()).setParameter("newRate", newRate).executeUpdate();
		}

		public double convert(double amountToConvert, CurrencyDTO fromCurrency, CurrencyDTO toCurrency) {			
			double rate1 = findCurrency(fromCurrency.getCurrencyCode()).getBaseCurrencyRate();
			double rate2 = findCurrency(toCurrency.getCurrencyCode()).getBaseCurrencyRate();
			System.out.println("Amount = " + amountToConvert + " rate1 =  " + rate1 + " rate 2 = " + rate2);
			System.out.println(amountToConvert * rate1 / rate2);
			return (amountToConvert * rate1 / rate2);			
		}
		

	}
