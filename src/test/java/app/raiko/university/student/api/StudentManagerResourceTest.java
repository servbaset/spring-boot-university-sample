package app.raiko.university.student.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.raiko.university.integration.BaseIntegrationTest;
import app.raiko.university.student.dto.StudentCreationsRequest;
import app.raiko.university.student.dto.StudentInformationResponse;
import app.raiko.university.student.entity.Student;
import app.raiko.university.student.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class StudentManagerResourceTest extends BaseIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private StudentRepository studentRepository;

  @Autowired private ObjectMapper objectMapper;

  @Test
  @Order(1)
  void itShouldCreate() throws Exception {
    // given
    var studentCreationsRequest =
        new StudentCreationsRequest("firstName", "lastName", "address", "94249801", "3040483781");

    // when
    var result =
        mockMvc
            .perform(
                post("/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        Objects.requireNonNull(
                            objectMapper.writeValueAsString(studentCreationsRequest))))
            // then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.firstName").value("firstName"))
            .andExpect(jsonPath("$.lastName").value("lastName"))
            .andExpect(jsonPath("$.address").value("address"))
            .andExpect(jsonPath("$.number").value("94249801"))
            .andExpect(jsonPath("$.nationalCode").value("3040483781"))
            .andReturn()
            .getResponse()
            .getContentAsString();
    var id = objectMapper.readValue(result, StudentInformationResponse.class).id();

    var foundStudent = studentRepository.findById(id);

    assertThat(foundStudent)
        .isPresent()
        .usingRecursiveComparison(
            RecursiveComparisonConfiguration.builder().withComparedFields("id").build())
        .isEqualTo(
            Student.builder()
                .firstName("firstName")
                .lastName("lastName")
                .number("94249801")
                .nationalCode("3040483781")
                .address("address")
                .build());
  }
}
