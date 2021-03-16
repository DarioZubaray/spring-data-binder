# spring-data-binder

springboot demo for data binder in controller

- Query param

```
/v1/greeting/query_param?name=dario

@RequestParam String name
```

- Path variable
```
/v1/greeting/path_variable/dario

@GetMapping("/greeting/path_variable/{name}")

@PathVariable String name
```

- Form data
```
<form action="/v1/greeting/form_data">
  <iput name="name" type="text"/>
  <input type="submit" value="Submit">
</form>

/v1/greeting/form_data

@ModelAttribute MyData data
```

- Request body json
```
/v1/greeting/json
{
    "name": "Jason",
    "age": 5
}

@RequestBody MyData data
```

- Header param
```
/v1/greeting/header


@RequestHeader String name
```
