package com.example.BankingApplicationClient.feignClient;

import com.example.BankingApplicationClient.modelDTO.AccountDTO;
import com.example.BankingApplicationClient.modelDTO.TransferDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableFeignClients
@Slf4j
//@ComponentScan(basePackages = "com.example.BankingApplicationClient.feignClient")
public class FeignAccountController {

    @Autowired
    private FeignAccountClient feignAccountClient;

    @PostMapping("/deposit")
    public ResponseEntity<HttpStatus> accDeposit(@RequestBody AccountDTO accountDTO) {
        feignAccountClient.accountDeposit(accountDTO);
        log.info("Деньги на счет внесены");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/withDraw")
    public ResponseEntity<HttpStatus> accWithDraw(@RequestBody AccountDTO accountDTO) {
        feignAccountClient.accountWithDraw(accountDTO);
        log.info("Деньги сняты со счета");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> accTransfer(@RequestBody TransferDTO transferDTO) {
        feignAccountClient.accountTransfer(transferDTO);
        log.info("Деньги переведены на другой счет");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
