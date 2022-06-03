package acme.entities.sisit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.items.Item;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sisit extends AbstractEntity{
	
	protected static final long serialVersionUID = 1L;
	
	@NotNull
	@Pattern(regexp = "^[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{1,3}$")
	protected String code;

	@NotNull
	@Past
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationMoment;
	
	@NotBlank
	@Length(min=1, max = 100)
	protected String name;
	
	@NotBlank
	@Length(min=1, max = 255)
	protected String summary;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	protected Date startDate;
	 
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date endDate;
	
	@NotNull
	@Valid
	protected Money share;

	@URL
	protected String moreInfo;
	
	@NotNull
	@Valid
	@OneToOne(optional = false)
	@JoinColumn(unique = true)
	protected Item	item;
	
}
