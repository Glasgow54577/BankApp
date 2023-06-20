package com.example.BankingApplicationServer.controllers;

import com.example.BankingApplicationServer.DTO.AccountDTO;
import com.example.BankingApplicationServer.DTO.TransferDTO;
import com.example.BankingApplicationServer.models.Account;
import com.example.BankingApplicationServer.services.interfaceServices.AccountService;
import com.example.BankingApplicationServer.services.interfaceServices.PersonService;
import com.example.BankingApplicationServer.util.AccountErrorResponse;
import com.example.BankingApplicationServer.util.AccountNotCreatedException;
import com.example.BankingApplicationServer.util.AccountValidator;
import com.example.BankingApplicationServer.util.TransferValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final PersonService personServiceImpl;
    private final AccountService accountServiceImpl;
    private final AccountValidator accountValidator;
    private final TransferValidator transferValidator;

    @Autowired
    public AccountController(PersonService personServiceImpl, AccountService accountServiceImpl, AccountValidator accountValidator, TransferValidator transferValidator) {
        this.personServiceImpl = personServiceImpl;
        this.accountServiceImpl = accountServiceImpl;
        this.accountValidator = accountValidator;
        this.transferValidator = transferValidator;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<HttpStatus> accountAdd(@RequestBody @Valid Account account,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ExceptionAccount(bindingResult);
        }
        account.setPerson(personServiceImpl.findByPersonLogin(account.getPerson().getLogin()));
        accountServiceImpl.save(account);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/deposit")
    @ResponseBody
    public void accountDeposit(@RequestBody @Valid AccountDTO accountDTO,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ExceptionAccount(bindingResult);
        }
        accountServiceImpl.deposit(accountDTO.getId(), accountDTO.getCash());
    }

    @PostMapping("/withDraw")
    @ResponseBody
    public void accountWithDraw(@RequestBody @Valid AccountDTO accountDTO,
                                BindingResult bindingResult) {

        accountValidator.validate(accountDTO, bindingResult);
        bindingResult.getPropertyEditorRegistry();
        if (bindingResult.hasErrors()) {
            ExceptionAccount(bindingResult);
        }
        accountServiceImpl.withDraw(accountDTO.getId(), accountDTO.getCash());
    }

    @PostMapping("/transfer")
    @ResponseBody
    public void accountTransfer(@RequestBody @Valid TransferDTO transferDTO,
                                BindingResult bindingResult) {
        transferValidator.validate(transferDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ExceptionAccount(bindingResult);
        }
        accountServiceImpl.transfer(transferDTO.getSourceId(), transferDTO.getDestinationId(), transferDTO.getCash());
    }

    private void ExceptionAccount(BindingResult bindingResult) {
        StringBuilder errorMassage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMassage.append(error.getField()) // поле на котором совершена ошибка
                    .append(" - ").append(error.getDefaultMessage()) // выдаст ошибку на этом поле
                    .append(";");
        }
        throw new AccountNotCreatedException(errorMassage.toString());
    }

    @ExceptionHandler // Обрабатывает исключение при неудачном добавлении счета
    private ResponseEntity<AccountErrorResponse> handleException(AccountNotCreatedException e) {
        AccountErrorResponse response = new AccountErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
