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
@Table(name = "part_tab")
public class Part {

	@Id
	@GeneratedValue
	@Column(name="part_id_col")
	private Integer id;
	@Column(name="part_code_col")
	private String partCode;
	@Column(name="part_length_col")
	private Double partLength;
	@Column(name="part_width_col")
	private Double partWidth;
	@Column(name="part_height_col")
	private Double partHeight;
	@Column(name="part_cost_col")
	private Double partCost;
	@Column(name="part_currency_col")
	private String partCurrency;
	
	@Column(name="part_gstslob_col")
	private Double gstSlob;
	
	@ManyToOne
	@JoinColumn(name="uom_id_fk")
	private Uom uom;
	
	@ManyToOne
	@JoinColumn(name="ord_id_sale_fk")
	private OrderMethod orderMethodSale;
	
	@ManyToOne
	@JoinColumn(name="ord_id_purchase_fk")
	private OrderMethod orderMethodPurchase;
	
}
