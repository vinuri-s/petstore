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

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {
	
	List<Pet> pets = new ArrayList<Pet>();
	
	public PetResource()
	{
		Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(3);
		pet1.setPetName("Boola");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Sudda");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(3);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
	}
	
	
//	---------------------------- Get All ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {

		return Response.ok(pets).build();
	}

//	---------------------------- Get By Id ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		
		if (petId > 0) {
			for (Pet t : pets) {
				   if (t.getPetId().equals(petId)) {
						return Response.ok(t).build();
				  }
				
			}
		
		}

		return Response.status(Status.NOT_FOUND).build();
		
	}
	
//	---------------------------- Add ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Add new pet",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "500",description = "Failed to create a pet") })
	@POST
    @Path("/add")
    public Response addPet(Pet newPet ) {

	    pets.add(newPet);
		
	    return Response.ok(pets).build();
    }
	
//	---------------------------- Delete By Id ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Delete pet",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "500",description = "Failed to delete pet") })
	@DELETE
	@Path("{petId}")
	public Response removePet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		pets.remove(petId-1);
		
		String res =  "Deleted pet with id "+petId;

		return Response.ok(res).build();

	}
	
//	---------------------------- Update By Id ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200",description = "Update pet for id",content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404",description = "No Pet found for the id.") })
	@PUT
	@Path("{petId}")
	public Response updatePet(@PathParam("petId") int petId,Pet pet) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
			for (Pet t : pets) {
				   if (t.getPetId().equals(petId)) {
					   t.setPetAge(pet.getPetAge());
						t.setPetName(pet.getPetName());
						t.setPetType(pet.getPetType());
						
				  }
				
			}
		


		return Response.ok(pets).build();

	}
	
//	---------------------------- Search By Name ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the name.") })
	@GET
	@Path("/name/{petName}")
	public Response searchByName(@PathParam("petName") String petName) {
		
		List<Pet> filteredList = new ArrayList<Pet>();
		
		for (Pet t : pets) {
			   if (t.getPetName().equals(petName)) {
				   filteredList.add(t);
					
			  }
			
		}


		return Response.ok(filteredList).build();
		
	}	
//	---------------------------- Search By Age ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for age", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the age.") })
	@GET
	@Path("/age/{petAge}")
	public Response searchByAge(@PathParam("petAge") int petAge) {
		
		List<Pet> filteredList = new ArrayList<Pet>();
		
		for (Pet t : pets) {
			   if (t.getPetAge().equals(petAge)) {
				   filteredList.add(t);
					
			  }
			
		}


		return Response.ok(filteredList).build();
		
	}
//	---------------------------- Search By Type ----------------------------------------------------------
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the type.") })
	@GET
	@Path("/type/{petType}")
	public Response searchByType(@PathParam("petType") String petType) {
		
		List<Pet> filteredList = new ArrayList<Pet>();
		
		for (Pet t : pets) {
			   if (t.getPetType().equals(petType)) {
				   filteredList.add(t);
					
			  }
			
		}


		return Response.ok(filteredList).build();
		
	}
	
}
