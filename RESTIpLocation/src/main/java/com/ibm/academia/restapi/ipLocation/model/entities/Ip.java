package com.ibm.academia.restapi.ipLocation.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="ip")
public class Ip implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Not be empty")
	@NotNull(message="Not be null")
	@Column(name = "ip", nullable = false)
	private String ip;
	
	@Column(name = "creation_date")
	private Date creationDate;

	public Ip(@NotEmpty(message = "Not be empty") @NotNull(message = "Not be null") String ip) {
		this.ip = ip;
	}
	
	@PrePersist
	private void beforePersist()
	{
		this.creationDate = new Date();
	}
	
	private static final long serialVersionUID = 4587187564556369254L;

}
