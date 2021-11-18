package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;

@QuarkusTest
public class PetResourceTest {

	@Test
    public void testPetEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
             .statusCode(200);
    }
	
	@Test
	public void getPetById() {
	  given()
	    .when().get("/v1/pets/1")
	    .then()
	      .statusCode(200)
	      .body(
	        "petId", is(1),
	        "petAge",is(3),
	        "petName",is("Boola"),
	        "petType", is("Dog")
	      );
	}
	
	@Test
	public void getPetNotFound() {
	  given()
	    .when().get("/v1/pets/50")
	    .then().statusCode(404);
	}

}