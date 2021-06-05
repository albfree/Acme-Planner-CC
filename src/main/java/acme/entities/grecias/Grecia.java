
package acme.entities.grecias;

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
public class Grecia extends DomainEntity {

	// Serialisation identifier 

	protected static final long serialVersionUID = 1L;

	// Attributes
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^(SHOUT)\\d{2}-\\d{4}\\/(0[1-9]|1[012])\\/(0[1-9]|[12][0-9]|3[01])$")
	protected String 		zeus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date			poseidon;
	
	@NotNull
	@Valid
	protected Money		hades;
	
	protected boolean		afrodita;

}
