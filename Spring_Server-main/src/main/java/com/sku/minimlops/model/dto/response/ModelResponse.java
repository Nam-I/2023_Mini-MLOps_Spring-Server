package com.sku.minimlops.model.dto.response;

import com.sku.minimlops.model.dto.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelResponse {
	private ModelDTO model;
}
