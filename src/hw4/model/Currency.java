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

public class Currency implements Serializable {

	   
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	private String country;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private double baseCurrencyRate;
	
	private static final long serialVersionUID = 1L;

	public Currency() {
		super();
	}
   
}
