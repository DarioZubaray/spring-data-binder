package com.zubaray.data.binder.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.zubaray.data.binder.utils.TestUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MyDataControllerTest {

    @Autowired
    private MyDataController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    @Description("Should retrn the default message")
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/v1/greeting"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hola Juli")));
    }

    @Test
    @Description("should return a greeting with the name given on query param")
    void test_greetingQueryParam() throws Exception {
        String name = "test";
        this.mockMvc.perform(
                get("/v1/greeting/query_param").queryParam("name", name)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola " + name)));
    }

    @Test
    @Description("should return a greeting with the name given on path variable")
    void test_greetingPathVariable() throws Exception {
        String name = "test";
        this.mockMvc.perform(get("/v1/greeting/path_variable/" + name))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hola " + name)));
    }

    @Test
    @Description("should return a greeting with the name and age given into a formdata")
    void test_greetingFormdata() throws Exception {
        String name = "jose";
        String age = "5";
        this.mockMvc.perform(
                post("/v1/greeting/form_data")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(TestUtils.buildUrlEncodedFormEntity("name", name, "age", age))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola " + name + ", you are " + age + " years old")));
    }

    @Test
    @Description("should return a greeting with the first name and last on snake case given into a formdata")
    void test_greetingFormdataSnakeCase() throws Exception {
        String firstName = "snake";
        String lastName = "case";
        this.mockMvc.perform(
                post("/v1/greeting/form_data/snake_case")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(TestUtils.buildUrlEncodedFormEntity("first_name", firstName, "last_name", lastName))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola " + firstName + " " + lastName + " formdata")));
    }

    @Test
    @Description("should return a greeting with the first name and last name of the json given on the request body")
    void test_greetingJsonSnakeCase() throws Exception {
        this.mockMvc.perform(
                post("/v1/greeting/json_snake_case")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getValidSnakeCaseDto()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Hola snake case")));
    }

    @Test
    @Description("should greeting by the name given on the headers")
    void test_greetingHeader() throws Exception {
        String name = "cabezon";
        this.mockMvc.perform(get("/v1/greeting/header").header("name", name))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hola " + name + ", by header")));
    }
}
