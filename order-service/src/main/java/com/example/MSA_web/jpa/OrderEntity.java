package com.example.MSA_web.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=120, unique=true)
	private String productId;
	
	@Column(nullable=false)
	private Integer qty;
	
	@Column(nullable=false)
	private Integer unitPrice;
	
	@Column(nullable=false)
	private Integer TotalPrice;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false, unique=true)
	private String orderId;
	
	@Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;

}
