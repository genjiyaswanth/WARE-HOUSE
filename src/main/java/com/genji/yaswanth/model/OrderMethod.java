package com.genji.yaswanth.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_method_tab")
public class OrderMethod {

	@Id
	@GeneratedValue(generator = "ordermethod")
	@SequenceGenerator(name = "ordermethod", sequenceName = "ordermethod_seq")
	@Column(name = "ord_id_col")
	private Integer id;
	@Column(name = "ord_mode_col")
	private String orderMode;
	@Column(name = "ord_code_col")
	private String orderCode;
	@Column(name = "ord_type_col")
	private String orderType;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "order_accepted_tab", joinColumns = @JoinColumn(name = "ord_id_col"))
	@Column(name = "ord_accepted_col")
	private List<String> orderAccepted;
	@Column(name = "ord_description_col")
	private String description;

}
