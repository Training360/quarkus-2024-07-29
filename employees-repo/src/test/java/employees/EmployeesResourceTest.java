package employees;

import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class EmployeesResourceTest {

    @Inject
    EmployeesRepository employeesRepository;

    @InjectMock
    EmployeesService employeesService;

//    @BeforeEach
//    @Transactional
//    void emptyTables() {
//        employeesRepository.getEntityManager().createNativeQuery("delete from employee").executeUpdate();
//    }

    @Test
    void createEmployee() {
        when(employeesService.create(any())).thenReturn(new EmployeeDto().id(1L).name("Jack Doe"));

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

    @Test
    void listEmployees() {
        var name = "John Doe " + UUID.randomUUID();
        Log.infof("Name: %s", name);

//        createTestEmployee(name);

        when(employeesService.getEmployees(any())).thenReturn(List.of(new EmployeeDto().name(name)));

//        given()
//                .contentType(ContentType.JSON)
//                .body(new EmployeeDto().name("Jack Doe"))
//                .when().post("/api/employees")
//                .then()
//                .statusCode(Response.Status.CREATED.getStatusCode());

        var employees = given()
                .when().get("/api/employees")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(new TypeRef<List<EmployeeDto>>(){});

        assertThat(employees)
                .extracting(EmployeeDto::getName)
                .contains(name);
    }

    @Transactional
    void createTestEmployee(String name) {
        employeesRepository.persist(new Employee(name));
    }
}
