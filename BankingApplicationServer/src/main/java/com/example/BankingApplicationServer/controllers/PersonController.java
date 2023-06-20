package com.example.BankingApplicationServer.controllers;

import com.example.BankingApplicationServer.models.Person;
import com.example.BankingApplicationServer.services.interfaceServices.AccountService;
import com.example.BankingApplicationServer.services.interfaceServices.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {
    private final PersonService personServiceImpl;
    private final AccountService accountServiceImpl;

    @Autowired
    public PersonController(PersonService personServiceImpl, AccountService accountServiceImpl) {
        this.personServiceImpl = personServiceImpl;
        this.accountServiceImpl = accountServiceImpl;
    }

    @PostMapping("/inform")
    public void inf(@RequestBody String notification){
        log.info("Отправляем оповещение: " + notification);
    }

    @PostMapping("/add")
    public void personAdd(@RequestBody @Valid Person person,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ///
        }
        personServiceImpl.save(person);
    }



}
