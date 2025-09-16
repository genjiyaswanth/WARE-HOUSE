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
@Table(name="grn_details_tab")
public class GrnDetails {

	@Id
	@GeneratedValue
	@Column(name="grn_detail_id_col")
	private Integer id;
	
	@Column(name="grn_detail_item_code_col")
	private String itemCode;
	
	@Column(name="grn_detail_cost_col")
	private Double cost;
	
	@Column(name="grn_detail_quantity_col")
	private Integer quantity;
	
	@Column(name="grn_detail_item_value_col")
	private Double itemValue;
	
	@Column(name="grn_detail_status_col")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="grn_id_fk_col")
	private Grn grn;
	
}
