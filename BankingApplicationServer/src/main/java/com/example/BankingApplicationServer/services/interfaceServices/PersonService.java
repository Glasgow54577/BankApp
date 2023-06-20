package com.example.BankingApplicationServer.services.interfaceServices;


import com.example.BankingApplicationServer.models.Person;

import java.util.List;

public interface PersonService {

    public List<Person> findAll();

    public Person findByPersonLogin(String login);

    public void save(Person person);
}
