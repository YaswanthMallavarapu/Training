package com.service;

import com.exception.ValidatePerson;
import com.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest {

    PersonService personService=null;
    @BeforeEach
    public void init(){
        personService=new PersonService();
    }
    @AfterEach
    public void destroy(){
        personService=null;

    }
    @Test
    public void getContactNumberCountTest(){
//        list with 0 perosns
        List<Person>list=new ArrayList<>();
        Assertions.assertEquals(0,personService.getContactNumberCount(list));
        Person p1=new Person(1,"yash",22,"ongole");
        Person p2=new Person(2,"ajith",23,"addanki");
//        list with 1 person
        List<Person>list1=new ArrayList<>();
        list1.add(p1);
//        list with 2 person
        List<Person>list2=new ArrayList<>();
        list2.add(p1);
        list2.add(p2);
//        list with reference null
        List<Person>list3=null;
        Assertions.assertEquals(0,personService.getContactNumberCount(list));
        Assertions.assertEquals(1,personService.getContactNumberCount(list1));
        Assertions.assertEquals(2,personService.getContactNumberCount(list2));

//        try{
//            Assertions.assertEquals(0,personService.getContactNumberCount(list3));
//        } catch (RuntimeException e) {
//            Assertions.assertEquals("list cannot be null",e.getMessage());
//        }
          RuntimeException e=Assertions.assertThrows(RuntimeException.class,()->personService.getContactNumberCount(list3));

    }

    @Test
    public void validatePersonTest(){
        Person p1=new Person(22,"ongole",1,"yash");
        // validating null pointer exception for name
        NullPointerException ne=Assertions.assertThrows(NullPointerException.class,()->personService.validatePerson(null));
        Assertions.assertEquals("Person ref cannot be null", ne.getMessage());

        //validating person name length > 1
        Person p2=new Person(22,"ongole",2,"y");
        ValidatePerson ve=Assertions.assertThrows(ValidatePerson.class,()->personService.validatePerson(p2));
        Assertions.assertEquals("Name atleast of should be of 2 char", ve.getMessage());

        // validating person age > 17
        Person p3=new Person(12,"vizag",3,"yamudu");
        ValidatePerson ve2=Assertions.assertThrows(ValidatePerson.class,()->personService.validatePerson(p3));
        Assertions.assertEquals("your are not eligible for the operation",ve2.getMessage());

        //validating valid person
        Assertions.assertTrue(personService.validatePerson(p1));

    }

    @Test
    public void getAdultPersonsTest(){
        //checking count of adults manullay
        List<Person>list=personService.getAdultPersons();
        Assertions.assertEquals(2,list.size(),"There should be only 2 adults.");

//        checking if any minor present using anyMatch stream method

        List<Person>adultlist=personService.getList();
        boolean status=adultlist.stream().anyMatch(person->person.getAge()<18);
        Assertions.assertTrue(status,"there should be no minors in list");

        List<Person>adultlist2=personService.getAdultPersons();
        boolean status2=adultlist2.stream().anyMatch(person->person.getAge()<18);
        Assertions.assertFalse(status2,"there should be no minors in list");

//        checking anyone have mumbai as thier city

        boolean status1=personService.getList().stream().anyMatch(person->person.getCity().equalsIgnoreCase("mumbai"));
        Assertions.assertFalse(status1,"There should be no person with mumbai as city");
    }
}
