package com.genji.yaswanth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="purchase_details_tab")
public class PurchaseDetails {

	@Id
	@GeneratedValue
	@Column(name="purchase_dtls_id_col")
	private Integer id;
	@Column(name="purchase_dtls_quantity_col")
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name="part_id_fk_col")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name="purchase_order_id_fk")
	private PurchaseOrder purchaseOrder;
}
