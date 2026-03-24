package org.hibernate.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.hibernate.dto.FlightDto;
import org.hibernate.enums.JobTitle;
import org.hibernate.model.Airline;
import org.hibernate.model.Employee;
import org.hibernate.model.Flight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.CriteriaUpdate;
import java.util.List;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager em;
    private final AirlineService airlineService;

    public EmployeeService(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @Transactional
    public void addEmployee(Employee employee, String jobTitle, int airlineId) {
        //check for job title validity
        JobTitle title=JobTitle.valueOf(jobTitle);
        employee.setJobTitle(title);

        //check for airline
        Airline airline=airlineService.getAirLineById(airlineId);
        employee.setAirline(airline);
        //save employee
        em.persist(employee);
    }

    public List<?> getAllEmplloyees() {
        String jpql="select e from Employee e";
        Query query=em.createQuery(jpql,Employee.class);
        return query.getResultList();
    }

    public FlightDto getAllAirlineEmployeeDetailsByFlightId(int flightId) {

        // check for flight
        Flight flight=em.find(Flight.class,flightId);

        // get all employees
        String jpql="select e from Employee e where e.airline.id=:airlineId";
        Query query=em.createQuery(jpql,Employee.class);
        query.setParameter("airlineId",flight.getAirline().getId());
        List<Employee>list=query.getResultList();

        // returning new flight dto wih required fields
        return new FlightDto(
                flight.getFlightNumber(),
                flight.getSource(),
                flight.getDestination(),
                flight.getAirline().getName(),
                list
        );
    }

    public List<Employee> getAllEmployeesByJobTitle(String title) {
        // check job title
        JobTitle.valueOf(title);

        // create Criteria Builder object
        CriteriaBuilder cb=em.getCriteriaBuilder();

        // create criteria Query
        CriteriaQuery<Employee> ctq=cb.createQuery(Employee.class);

        // create from clause
        Root<Employee>employee=ctq.from(Employee.class);

        //create where clause

        jakarta.persistence.criteria.Predicate predicate =cb.equal(employee.get("jobTitle"),title);
        ctq.where(predicate);

        return em.createQuery(ctq).getResultList();

    }

    @Transactional
    public void deleteById(int employeeId) {
        String jpql="delete from Employee e where e.id = :employeeId";
        Query query=em.createQuery(jpql);
        query.setParameter("employeeId",employeeId);
        query.executeUpdate();

    }

    @Transactional
    public void updateEmployee(Employee update1,int id) {
        // create criteria update
        CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
        // create criteria update
        CriteriaUpdate<Employee> criteriaUpdate=criteriaBuilder.createCriteriaUpdate(Employee.class);
        //create from clause
        Root<Employee>employee=criteriaUpdate.from(Employee.class);
        //create conditional update
        if(update1.getName()!=null && !update1.getName().isEmpty())
            criteriaUpdate.set(employee.get("name"),update1.getName());
        if(update1.getEmail()!=null && !update1.getEmail().isEmpty())
            criteriaUpdate.set(employee.get("email"),update1.getEmail());
        if(update1.getJobTitle()!=null)
            criteriaUpdate.set(employee.get("jobTitle"),update1.getJobTitle());

        Predicate predicate=criteriaBuilder.equal(employee.get("id"),id);
        criteriaUpdate.where(predicate);
        em.createQuery(criteriaUpdate).executeUpdate();


    }
}
