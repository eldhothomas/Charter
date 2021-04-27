package com.eldho.charter.repo01.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CUSTOMER_ID")
    private String customerId;
    @Column(name = "PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

}