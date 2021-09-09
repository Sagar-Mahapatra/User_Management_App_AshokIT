package in.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "country")
@Data
public class Country {
	@Id
	@Column(name = "country_id")
	private Integer countryId;
	@Column(name = "country_name")
	private String countryName;

}
