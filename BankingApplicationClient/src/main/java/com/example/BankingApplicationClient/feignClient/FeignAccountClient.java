package com.example.BankingApplicationClient.feignClient;

import com.example.BankingApplicationClient.modelDTO.AccountDTO;
import com.example.BankingApplicationClient.modelDTO.TransferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "Account", url = "http://localhost:8092/")
@Component
//@Configuration
//@ComponentScan(basePackages = "com.example.BankingApplicationClient.feignClientController")
public interface FeignAccountClient {
    @PostMapping("/account/deposit")
    void accountDeposit(@RequestBody AccountDTO accountDTO);

    @PostMapping("/account/withDraw")
    void accountWithDraw(@RequestBody AccountDTO accountDTO);

    @PostMapping("/account/transfer")
    void accountTransfer(@RequestBody TransferDTO transferDTO);


}
