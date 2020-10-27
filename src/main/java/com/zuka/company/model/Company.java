package com.zuka.company.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator",
		parameters = {
			@Parameter(
				name = "uuid_gen_strategy_class",
				value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
			)
		}
	)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "cnpj", nullable = false)
	private String cnpj;
	
	@Embedded
	private Address address;

}
