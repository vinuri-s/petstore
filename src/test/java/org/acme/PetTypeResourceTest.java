package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;

@QuarkusTest
public class PetTypeResourceTest {

	@Test
    public void testPetTypeEndpoint() {
        given()
          .when().get("/v1/pets-types")
          .then()
             .statusCode(200);
    }
	
	@Test
	public void getPetTypeById() {
	  given()
	    .when().get("/v1/pets-types/1")
	    .then()
	      .statusCode(200)
	      .body(
	        "petTypeId", is(1),
	        "petTypeType", is("Dog")
	      );
	}
	
	@Test
	public void getOneNotFound() {
	  given()
	    .when().get("/v1/pets-types/50")
	    .then().statusCode(404);
	}

}