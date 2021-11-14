package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets-types")
@Produces("application/json")
public class PetTypeResource {
	
	List<PetType> types = new ArrayList<PetType>();
	
	public PetTypeResource() {
		PetType type1 = new PetType();
		type1.setPetTypeId(1);
		type1.setPetTypeType("Dog");

		PetType type2 = new PetType();
		type2.setPetTypeId(2);
		type2.setPetTypeType("Cat");

		PetType type3 = new PetType();
		type3.setPetTypeId(3);
		type3.setPetTypeType("Bird");
		
		types.add(type1);
		types.add(type2); 
		types.add(type3);
	  }

//	---------------------------- Get All ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@GET
	public Response getPetTypes() {  
		return Response.ok(types).build();
	}

//	---------------------------- Get By Id ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet Type by Id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
			@APIResponse(responseCode = "404", description = "No Pet Type found for the id.") })
	@GET
	@Path("{petTypeId}")
	public Response getPet(@PathParam("petTypeId") int petTypeId) {
		if (petTypeId > 0) {
			for (PetType t : types) {
				   if (t.getPetTypeId().equals(petTypeId)) {
						return Response.ok(t).build();
				  }
				
			}
		
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
//	---------------------------- Add ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Add new pet type",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "PetType"))),
			@APIResponse(responseCode = "500",description = "Failed to create a pet type") })
	@POST
    @Path("/add")
    public Response addType(PetType newType ) {

	    types.add(newType);
		
	    return Response.ok(types).build();
    }
	
//	---------------------------- Delete By Id ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Delete pet type",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "PetType"))),
			@APIResponse(responseCode = "500",description = "Failed to delete pet type") })
	@DELETE
	@Path("{petTypeId}")
	public Response removePetType(@PathParam("petTypeId") int petTypeId) {
		if (petTypeId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		types.remove(petTypeId-1);
		
		String res =  "Deleted pet with id "+petTypeId;

		return Response.ok(res).build();

	}
	
//	---------------------------- Update By Id ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Update pet type for id",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "PetType"))),
			@APIResponse(responseCode = "404",description = "No Pet Type found for the id.") })
	@PUT
	@Path("{petTypeId}")
	public Response updatePet(@PathParam("petTypeId") int petTypeId,PetType type) {
		if (petTypeId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
			for (PetType t : types) {
				   if (t.getPetTypeId().equals(petTypeId)) {
						t.setPetTypeType(type.getPetTypeType());
						
				  }
				
			}
		

		return Response.ok(types).build();

	}	
		
}
