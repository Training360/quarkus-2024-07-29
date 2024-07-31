package employees;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import training.dto.EmployeeDto;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class EmployeesResourceTest {

    @Test
    void createEmployee() {
        var result = given()
                .contentType(ContentType.JSON)
                .body(new EmployeeDto().name("Jack Doe"))
                .when().post("/api/employees")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().body().as(EmployeeDto.class);

        assertThat(result.getName())
                .startsWith("Jack");
    }
}
