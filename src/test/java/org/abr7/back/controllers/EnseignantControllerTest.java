package org.abr7.back.controllers;

import org.abr7.back.dao.EnseignantDao;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@Tag("TestEnseignantController")
@ExtendWith(MockitoExtension.class)
@DisplayName("Enseignant")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnseignantControllerTest {
    @Autowired
    private MockMvc mvc;
    private JSONObject json;

    @Autowired
    private EnseignantDao EnseignantDao;

    private JSONObject json_Enseignant;



    @BeforeAll
    @AfterAll
    public void clearDataBaseEnseignant() {
        this.EnseignantDao.deleteAll();
        json = null;
        json_Enseignant = null;
    }



    @Test
    @Order(1)
    @DisplayName("1-CREATE")
    public void Create() throws Exception {
        MvcResult result = this.mvc.perform(
                        MockMvcRequestBuilders
                                .post("/service/enseignant/save")
                                .content("{"
                                        + "\"nom\": \"license\","
                                        + "\"prenom\": \"koko\","
                                        + "\"telephone\": \"license\","
                                        + "\"numeroM\": 1,"
                                        + "\"email\": \"license\""
                                        + "}"
                                ).contentType(MediaType.APPLICATION_JSON)

                ).andDo(print())
                .andExpect(jsonPath("$.nom", is("license")))
                .andExpect(jsonPath("$.prenom", is("koko")))
                .andExpect(jsonPath("$.telephone", is("license")))
                .andExpect(jsonPath("$.numeroM", is(1)))
                .andExpect(jsonPath("$.email", is("license")))
                .andReturn();
        json = new JSONObject(result.getResponse().getContentAsString());
    }

    /*@Test
    @Order(value=4)
    @DisplayName("2-GETBYID")
    public void ById() throws Exception {

        this.mvc.perform(
                        MockMvcRequestBuilders
                                .get("lien api sans id/" + json.getInt("id"))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", is("master")))
                .andExpect(jsonPath("$.code", is("004")));

    }*/

    @Test
    @Order(2)
    @DisplayName("3-READ")
    public void List() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders
                                .get("/service/enseignant/findAll")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("license")))
                .andExpect(jsonPath("$[0].prenom", is("koko")))
                .andExpect(jsonPath("$[0].telephone", is("license")))
                .andExpect(jsonPath("$[0].numeroM", is(1)))
                .andExpect(jsonPath("$[0].email", is("license")));

    }

    @Test
    @Order(3)
    @DisplayName("4-UPDATE")
    public void Update() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders
                                .put("/service/enseignant/update/" + json.getInt("id"))
                                .content("{"
                                        +"\"id\":" + json.getInt("id")+","
                                        + "\"nom\": \"master\","
                                        + "\"prenom\": \"master\","
                                        + "\"telephone\":\"004\","
                                        + "\"numeroM\":4,"
                                        + "\"email\":\"004\""
                                        + "}"
                                ).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(json.getInt("id"))))
                .andExpect(jsonPath("$.nom", is("master")))
                .andExpect(jsonPath("$.prenom", is("master")))
                .andExpect(jsonPath("$.telephone", is("004")))
                .andExpect(jsonPath("$.numeroM", is(4)))
                .andExpect(jsonPath("$.email", is("004")));
    }

    @Test
    @Order(5)
    @DisplayName("5-DELETE")
    public void Delete() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .delete("/service/enseignant/delete/" + json.getInt("id"))
        ).andDo(print());
    }
}