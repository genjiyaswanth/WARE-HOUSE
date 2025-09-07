package com.genji.yaswanth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="docs_tab")
public class Document {

	@Id
	@Column(name="doc_id_col")
	private Integer id;
	@Column(name="doc_name_col")
	private String docName;
	@Lob
	@Column(name = "docs_data_col", columnDefinition = "LONGBLOB")
	private byte[] docData;
	
}
