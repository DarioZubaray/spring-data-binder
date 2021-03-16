package com.zubaray.data.binder.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zubaray.data.binder.models.MyDataDto;

@RestController
@RequestMapping("/v1")
public class MyDataController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Hola Juli";
    }

    @GetMapping("/greeting/query_param")
    public String greetingQueryParam(@RequestParam String name) {
        return "Hola " + name;
    }

    @GetMapping("/greeting/path_variable/{name}")
    public String greetingPathVariable(@PathVariable String name) {
        return "Hola " + name;
    }

    @PostMapping(path = "/greeting/form_data",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String greetingFormdata(@ModelAttribute MyDataDto data) {
        return "Hola " + data.getName() + ", you are " + data.getAge() + " years old";
    }

    @PostMapping(path = "/greeting/form_data/snake_case",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String greetingFormdataSnakeCase(@ModelAttribute MyDataDto data) {
        return "Hola " + data.getFirstName() + " " + data.getLastName() + " formdata";
    }

    @PostMapping("/greeting/json")
    public String greetingJson(@RequestBody MyDataDto data) {
        return "Hola " + data.getName() + ", how's everything";
    }

    @PostMapping("/greeting/json_snake_case")
    public String greetingJsonSnakeCase(@RequestBody MyDataDto data) {
        return "Hola " + data.getFirstName() + " " + data.getLastName();
    }

    @GetMapping("/greeting/header")
    public String greetingHeader(@RequestHeader String name) {
        return "Hola " + name + ", by header";
    }
}
