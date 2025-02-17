package org.abr7.back.controllers;

import org.abr7.back.dao.MatiereDao;
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
@Tag("TestMatiereController")
@ExtendWith(MockitoExtension.class)
@DisplayName("Matiere")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MatiereControllerTest {
    @Autowired
    private MockMvc mvc;
    private JSONObject json;

    @Autowired
    private MatiereDao MatiereDao;

    private JSONObject json_Matiere;



    @BeforeAll
    @AfterAll
    public void clearDataBaseMatiere() {
        this.MatiereDao.deleteAll();
        json = null;
        json_Matiere = null;
    }



    @Test
    @Order(1)
    @DisplayName("1-CREATE")
    public void Create() throws Exception {
        MvcResult result = this.mvc.perform(
                        MockMvcRequestBuilders
                                .post("/service/matiere/save")
                                .content("{"
                                        + "\"libelle\": \"license\","
                                        + "\"code\": \"003\""
                                        + "}"
                                ).contentType(MediaType.APPLICATION_JSON)

                ).andDo(print())
                .andExpect(jsonPath("$.libelle", is("license")))
                .andExpect(jsonPath("$.code", is("003")))
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
                                .get("/service/matiere/findAll")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].libelle", is("license")))
                .andExpect(jsonPath("$[0].code", is("003")));

    }

    @Test
    @Order(3)
    @DisplayName("4-UPDATE")
    public void Update() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders
                                .put("/service/matiere/update/" + json.getInt("id"))
                                .content("{"
                                        +"\"id\":" + json.getInt("id")+","
                                        + "\"libelle\": \"master\","
                                        + "\"code\":\"004\""
                                        + "}"
                                ).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(json.getInt("id"))))
                .andExpect(jsonPath("$.libelle", is("master")))
                .andExpect(jsonPath("$.code", is("004")));
    }

    @Test
    @Order(5)
    @DisplayName("5-DELETE")
    public void Delete() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .delete("/service/matiere/delete/" + json.getInt("id"))
        ).andDo(print());
    }
}