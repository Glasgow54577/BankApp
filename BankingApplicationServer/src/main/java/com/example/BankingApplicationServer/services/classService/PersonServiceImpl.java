package com.example.BankingApplicationServer.services.classService;

import com.example.BankingApplicationServer.models.Person;
import com.example.BankingApplicationServer.repositories.PersonRepository;
import com.example.BankingApplicationServer.services.interfaceServices.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true )
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findByPersonLogin(String login){
        Optional<Person> foundPerson = personRepository.findByLogin(login);
        return foundPerson.orElse(null);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }
}
