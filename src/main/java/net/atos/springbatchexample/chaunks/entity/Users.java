package net.atos.springbatchexample.chaunks.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Users {
	
	@Id
	private Long userId;
	
	private String name;
	
	private String address;
	
	private BigDecimal account;
}
