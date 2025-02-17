package org.abr7.back.controllers;

import org.abr7.back.dao.AnneeDao;
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
@Tag("TestAnneeController")
@ExtendWith(MockitoExtension.class)
@DisplayName("Annee")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnneeControllerTest {
    @Autowired
    private MockMvc mvc;
    private JSONObject json;

    @Autowired
    private AnneeDao AnneeDao;

    private JSONObject json_Annee;



    @BeforeAll
    @AfterAll
    public void clearDataBaseAnnee() {
        this.AnneeDao.deleteAll();
        json = null;
        json_Annee = null;
    }



    @Test
    @Order(1)
    @DisplayName("1-CREATE")
    public void Create() throws Exception {
        MvcResult result = this.mvc.perform(
                        MockMvcRequestBuilders
                                .post("/service/annee/save")
                                .content("{"
                                        + "\"debut\": \"01 janvier 2024\","
                                        + "\"fin\": \"31 Décembre 2024\","
                                        + "\"code\": \"003\""
                                        + "}"
                                ).contentType(MediaType.APPLICATION_JSON)

                ).andDo(print())
                .andExpect(jsonPath("$.debut", is("01 janvier 2024")))
                .andExpect(jsonPath("$.fin", is("31 Décembre 2024")))
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
                                .get("/service/annee/findAll")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].debut", is("01 janvier 2024")))
                .andExpect(jsonPath("$[0].fin", is("31 Décembre 2024")))
                .andExpect(jsonPath("$[0].code", is("003")));

    }

    @Test
    @Order(3)
    @DisplayName("4-UPDATE")
    public void Update() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders
                                .put("/service/annee/update/" + json.getInt("id"))
                                .content("{"
                                        +"\"id\":" + json.getInt("id")+","
                                        + "\"debut\": \"master\","
                                        + "\"fin\": \"master\","
                                        + "\"code\":\"004\""
                                        + "}"
                                ).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(json.getInt("id"))))
                .andExpect(jsonPath("$.debut", is("master")))
                .andExpect(jsonPath("$.fin", is("master")))
                .andExpect(jsonPath("$.code", is("004")));
    }

    @Test
    @Order(5)
    @DisplayName("5-DELETE")
    public void Delete() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .delete("/service/annee/delete/" + json.getInt("id"))
        ).andDo(print());
    }
}