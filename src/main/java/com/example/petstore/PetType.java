package com.example.petstore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "PetType")

public class PetType {
		
	@Schema(required = true, description = "PetType id")
	@JsonProperty("petType_id")
	private Integer petTypeId;

	@Schema(required = true, description = "PetType type")
	@JsonProperty("petType_type")
	private String petTypeType;

	

	public Integer getPetTypeId() {
		return petTypeId;
	}

	public void setPetTypeId(Integer petTypeId) {
		this.petTypeId = petTypeId;
	}

	public String getPetTypeType() {
		return petTypeType;
	}

	public void setPetTypeType(String petTypeType) {
		this.petTypeType = petTypeType;
	}

}
