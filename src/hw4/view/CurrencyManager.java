package hw4.view;

import hw4.controller.CurrencyController;
import hw4.model.CurrencyDTO;

import java.awt.event.ActionEvent;
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
    private CurrencyController currencyController;
    private CurrencyDTO currentCurrency;
    private String newCountryName;
    private String newCurrencyName;
    private String newCurrencyCode;
    private Double newExchangeRate;
    private String searchedCurrency;
    private Exception transactionFailure;
    private List<CurrencyDTO> currencies;
    private String fromCurrency;
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
            currentCurrency = currencyController.findCurrency(searchedCurrency);
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
            currentCurrency = currencyController.createCurrency(newCountryName,newCurrencyName,newCurrencyCode,newExchangeRate);
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    public void remove(){
    	System.out.println("hahahahaha" + fromCurrency);
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
		currencies = currencyController.getAllCurrencys();
		return currencies;
	}
}