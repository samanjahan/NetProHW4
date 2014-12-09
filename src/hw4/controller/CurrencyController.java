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
	public class CurrencyController {
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

	}
