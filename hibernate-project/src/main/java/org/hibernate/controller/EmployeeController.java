package org.hibernate.controller;

import org.hibernate.config.ProjConfig;
import org.hibernate.dto.FlightDto;
import org.hibernate.enums.JobTitle;
import org.hibernate.exception.ResourceNotFoundException;
import org.hibernate.model.Employee;
import org.hibernate.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(ProjConfig.class);
        EmployeeService employeeService=context.getBean(EmployeeService.class);
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1 .Add Employee");
            System.out.println("2. List all Employees");
            System.out.println("3. ListAirlineEmployee Details");
            System.out.println("4. Get Employees by JobTitle using Criteria Query");
            System.out.println("5. Delete Employee by Id");
            System.out.println("6. Update Employee");
            System.out.println("0. exit");
            int choice=sc.nextInt();
            if(choice==0){
                break;
            }
            switch(choice){
                case 1:
                    Employee employee=new Employee();
                    System.out.println("Enter employee name: ");
                    employee.setName(sc.next());
                    System.out.println("enter employee email: ");
                    employee.setEmail(sc.next());
                    System.out.println("enter employee jobtitle: ");
                    String jobTitle=sc.next();
                    System.out.println("enter airline id: ");
                    int airlineId=sc.nextInt();
                    try {
                        employeeService.addEmployee(employee, jobTitle, airlineId);
                        System.out.println("employee added successfully");
                    }catch(IllegalArgumentException | ResourceNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                   List<?> list= employeeService.getAllEmplloyees();
                   list.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter flight id: ");
                    int flightId=sc.nextInt();
                    FlightDto flightDto=employeeService.getAllAirlineEmployeeDetailsByFlightId(flightId);
                    System.out.println(flightDto.flightNumber()+"   "+flightDto.flightSource());
                    System.out.println(flightDto.airlineName()+"   "+flightDto.flightDestination());
                    flightDto.list().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("-------------Job Titles--------------");
                    Arrays.stream(JobTitle.values()).forEach(System.out::println);
                    System.out.println("Enter job title: ");
                    String title=sc.next();
                    try {
                        List<Employee> employees = employeeService.getAllEmployeesByJobTitle(title);
                        employees.forEach(System.out::println);
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter id of employee to delete");
                    int employeeId=sc.nextInt();
                    employeeService.deleteById(employeeId);
                    System.out.println("deleted successfully");
                    break;
                case 6:
                    int id=2;
                    Employee update1=new Employee();
                    update1.setName("Ajith D");

                    Employee update2=new Employee();
                    update2.setName("Ajith kumar D");
                    update2.setEmail("ajithkumar@gmail.com");
                    Employee update3=new Employee();
                    update3.setName("Ajith kumar dhulipalla");
                    update3.setEmail("ajithkumardhulipalla@gmail.com");
                    update3.setJobTitle(JobTitle.CAPTAIN);
                    employeeService.updateEmployee(update3,id);
                    System.out.println("update successful!!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
