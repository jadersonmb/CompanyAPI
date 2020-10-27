package com.zuka.company.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "postalCode")
	private String postalCode;
	
	@Column(name = "address", length = 150)
	private String address;
	
	@Column(name = "district", length = 45)
	private String district;
	
	@Column(name = "city", length = 100)
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "number")
	private Integer number;
	
	@Column(name = "complement")
	private String complement;
	
}
