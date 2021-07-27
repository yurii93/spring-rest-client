package com.yuravashchenko.spring.rest;

import com.yuravashchenko.spring.rest.entity.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class Communication {

    private final String URL = "http://localhost:8080/spring_course_rest/api/employees";
    private RestTemplate restTemplate;

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> showAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                });

        return responseEntity.getBody();
    }

    public Employee getEmployee(int id) {
        return restTemplate.getForObject(URL + "/" + id, Employee.class);
    }

    public void saveEmployee(Employee employee) {
        int id = employee.getId();

        if (id == 0) {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("New employee was added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println("Employee with id " + id + " was updated");
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with id " + id + " was deleted");
    }
}
