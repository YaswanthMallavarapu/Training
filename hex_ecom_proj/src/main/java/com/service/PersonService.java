package com.service;

import com.exception.ValidatePerson;
import com.model.Person;

import java.util.List;

public class PersonService {

    List<Person>list=null;
    {
        list=generatePersons();
    }

    public int getContactNumberCount(List<Person>list){
        if(list==null){
            throw new RuntimeException("list cannot be null");
        }
        return list.size();
    }

    public boolean validatePerson(Person person){
        if(person==null){
            throw new NullPointerException("Person ref cannot be null");
        }
        if(person.getName().length()<2){
            throw new ValidatePerson("Name atleast of should be of 2 char");
        }
        if (person.getAge()<18){
            throw new ValidatePerson("your are not eligible for the operation");
        }
        return true;
    }

    public List<Person> generatePersons(){
        Person p1=new Person(22,"ongole",1,"yash");
        Person p2=new Person(17,"vizag",2,"akshay");
        Person p3=new Person(23,"annagi",3,"sudha");
        return List.of(p1,p2,p3);
    }

    public List<Person> getAdultPersons(){
        return list.stream()
                .filter(person->person.getAge()>17)
                .toList();
    }

    public List<Person> getList() {
        return list;
    }

}
