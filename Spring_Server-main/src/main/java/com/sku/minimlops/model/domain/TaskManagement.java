package com.sku.minimlops.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
public class TaskManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_mangement_id")
	private Long id;

	private boolean train;

	private boolean deploy;

	private LocalDateTime deployTime;

	@OneToOne
	@JoinColumn(name = "model_id")
	private Model currentModel;

	@Enumerated(EnumType.STRING)
	private TableName currentTable;

	public void trainOn() {
		this.train = true;
	}

	public void trainOff() {
		this.train = false;
	}

	public void deployOn() {
		this.deploy = true;
	}

	public void deployOff() {
		this.deploy = false;
		this.deployTime = LocalDateTime.now();
	}

	public void changeCurrentModel(Model model) {
		this.currentModel = model;
	}

	public void switchCurrentTable() {
		if (this.currentTable == TableName.word2vec_emb_01) {
			this.currentTable = TableName.word2vec_emb_02;
		} else {
			this.currentTable = TableName.word2vec_emb_01;
		}
	}

	public TableName nextTable() {
		if (this.currentTable == TableName.word2vec_emb_01) {
			return TableName.word2vec_emb_02;
		} else {
			return TableName.word2vec_emb_01;
		}
	}
}
