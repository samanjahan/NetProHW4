package hw4.view;

import hw4.controller.CurrencyFacade;
import hw4.model.CurrencyDTO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("currencyManager")
@ConversationScoped
public class CurrencyManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CurrencyFacade currencyFacade;
	private CurrencyDTO currentCurrency;
    private String chosenCurrency;
    private CurrencyDTO chosenCurrency2;
	private String newCountryName;
    private String newCurrencyName;
    private String newCurrencyCode;
    private Double newExchangeRate;
    private String searchedCurrency;
    private Exception transactionFailure;
    private List<CurrencyDTO> currencies;
    private String fromCurrency;
    private String toCurrency;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    private void readCurrencyData() {
    	searchedCurrency = currentCurrency.getCurrencyCode();
        findCurrency();
    }

    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }

    /**
     * This return value is needed because of a JSF 2.2 bug. Note 3 on page 7-10
     * of the JSF 2.2 specification states that action handling methods may be
     * void. In JSF 2.2, however, a void action handling method plus an
     * if-element that evaluates to true in the faces-config navigation case
     * causes an exception.
     *
     * @return an empty string.
     */
    private String jsf22Bugfix() {
        return "";
    }


    /**
     * Searches for the account specified by the latest call to
     * <code>setSearchedAcct</code>.
     */
    public String findCurrency() {
        try {
            startConversation();
            transactionFailure = null;
            currentCurrency = currencyFacade.findCurrency(searchedCurrency);
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    /**
     * Creates a new account. The holder's name is specified by the latest calls
     * to
     * <code>setNewAccountHolderFirstName</code> and
     * <code>setNewAccountHolderLastName</code>. The initial balance is
     * specified by the latest call to
     * <code>setNewAccountBalance</code>.
     */
    public String createCurrency() {
        try {
            startConversation();
            transactionFailure = null;
            currentCurrency = currencyFacade.createCurrency(newCountryName,newCurrencyName,newCurrencyCode,newExchangeRate);
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    public String deleteCurrency() {
    	System.out.println("Delete " + chosenCurrency);
    	try {
            startConversation();
            transactionFailure = null;
            currencyFacade.deleteCurrencyFromString(chosenCurrency);
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    public String deleteCurrency2() {
    	System.out.println("Delete " + chosenCurrency2);
    	try {
            startConversation();
            transactionFailure = null;
            currencyFacade.deleteCurrency(chosenCurrency2);
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

	public String getNewCountryName() {
		return newCountryName;
	}
	
	public void SetFromCurrency(String fromCurrency){
		this.fromCurrency = fromCurrency;
	}
	
	public String getFromCurrency(){
		return fromCurrency;
	}
	
	
	public void SetToCurrency(String toCurrency){
		this.toCurrency = toCurrency;
	}
	
	public String getToCurrency(){
		return toCurrency;
	}

	public void setNewCountryName(String newCountryName) {
		this.newCountryName = newCountryName;
	}

	public String getNewCurrencyName() {
		return newCurrencyName;
	}

	public void setNewCurrencyName(String newCurrencyName) {
		this.newCurrencyName = newCurrencyName;
	}

	public String getNewCurrencyCode() {
		return newCurrencyCode;
	}

	public void setNewCurrencyCode(String newCurrencyCode) {
		this.newCurrencyCode = newCurrencyCode;
	}

	public Double getNewExchangeRate() {
		return newExchangeRate;
	}

	public void setNewExchangeRate(Double newExchangeRate) {
		this.newExchangeRate = newExchangeRate;
	}
	
	public List<CurrencyDTO> setCurrencies(){
		return currencies;
		
	}
		
	public List<CurrencyDTO> getCurrencies(){
		currencies = currencyFacade.getAllCurrencys();
		return currencies;
	}
	
    public String getChosenCurrency() {
    	System.out.println("Get Chosen " + chosenCurrency);
		return chosenCurrency;
	}

	public void setChosenCurrency(String chosenCurrency) {
		System.out.println("Set Chosen " + chosenCurrency);
		this.chosenCurrency = chosenCurrency;
	}
	
    public CurrencyDTO getChosenCurrency2() {
    	System.out.println("Get Chosen2 " + chosenCurrency2);
    	return chosenCurrency2;
	}

	public void setChosenCurrency2(CurrencyDTO chosenCurrency2) {
		System.out.println("Set Chosen2 " + chosenCurrency2);
		this.chosenCurrency2 = chosenCurrency2;
	}
	
	public String convert(){
		return "ok!";
	}
}