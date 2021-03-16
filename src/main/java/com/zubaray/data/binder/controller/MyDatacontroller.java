package com.zubaray.data.binder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zubaray.data.binder.models.MyData;

@RestController
@RequestMapping("/v1")
public class MyDatacontroller {

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

    @GetMapping("/greeting/form_data")
    public String greetingFormdata(@ModelAttribute MyData data) {
        return "Hola " + data.getName() + ", you are " + data.getAge() + " years old";
    }

    @GetMapping("/greeting/json")
    public String greetingJson(@RequestBody MyData data) {
        return "Hola " + data.getName() + ", how's everything";
    }

    @GetMapping("/greeting/json_snake_case")
    public String greetingJsonSnakeCase(@RequestBody MyData data) {
        return "Hola " + data.getFirstName() + " " + data.getLastName();
    }

    @GetMapping("/greeting/header")
    public String greetingHeader(@RequestHeader String name) {
        return "Hola " + name + ", by header";
    }
}
