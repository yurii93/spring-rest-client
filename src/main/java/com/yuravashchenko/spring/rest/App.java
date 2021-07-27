package com.yuravashchenko.spring.rest;

import com.yuravashchenko.spring.rest.configuration.MyConfig;
import com.yuravashchenko.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        System.out.println("==== Employees list ====");
        List<Employee> allEmployees = communication.showAllEmployees();
        System.out.println(allEmployees);

        System.out.println("==== Employees ====");
        Employee employee = communication.getEmployee(4);
        System.out.println(employee);

        System.out.println("==== Add employee ====");
        Employee emp = new Employee("Sveta", "Sokolova", "HR", 900);
        communication.saveEmployee(emp);

        System.out.println("==== Update employee ====");
        Employee emp2 = new Employee("Sveta", "Sokolova", "HR", 5000);
        emp2.setId(10);
        communication.saveEmployee(emp2);

        System.out.println("==== Delete employee ====");
        communication.deleteEmployee(10);
    }
}
