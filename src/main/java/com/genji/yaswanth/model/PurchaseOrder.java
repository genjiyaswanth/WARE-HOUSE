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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase_order_tab")
public class PurchaseOrder {

	@Id
	@GeneratedValue
	@Column(name = "po_id_col")
	private Integer id;
	
	@Column(name = "po_code_col")
	private String purchaseOrderCode;
	
	@Column(name = "po_ref_num_col")
	private String referenceNumber;
	
	@Column(name = "po_qty_chk_col")
	private String qualityCheck;
	
	@Column(name = "po_status_col")
	private String status;
	
	@Column(name = "po_desc_col")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="shipment_id_fk")
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name="wh_user_vendor_id_fk")
	private WhUserType vendor;

}
