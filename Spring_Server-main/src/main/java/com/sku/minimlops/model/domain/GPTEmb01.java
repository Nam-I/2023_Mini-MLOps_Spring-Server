package com.sku.minimlops.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "gpt_emb_01")
public class GPTEmb01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpt_emb_01_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(columnDefinition = "TEXT")
    private String vector;
}
