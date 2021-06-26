
package acme.entities.kales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Kale extends DomainEntity {

	// Serialisation identifier 

	protected static final long serialVersionUID = 1L;

	// Attributes
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^\\d{6}#\\d{1,3}$")
	protected String 		tiplet;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date			deadline;
	
	@NotNull
	@Valid
	protected Money		budget;
	
	protected boolean		important;

}
