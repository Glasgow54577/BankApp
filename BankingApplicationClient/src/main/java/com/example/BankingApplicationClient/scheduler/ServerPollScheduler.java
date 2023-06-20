package com.example.BankingApplicationClient.scheduler;

import com.example.BankingApplicationClient.request.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class ServerPollScheduler {
    Random random = new Random();
    Runner runner = new Runner();

    @Scheduled(fixedRate = 100)
    public void accountDeposit() {
        int id = (1 + random.nextInt(7));
        int cash = random.nextInt(100);
        log.info("Сумма пополнения = " + cash);
        runner.accountDeposit(id, cash);
    }

    @Scheduled(fixedRate = 100)
    public void accountWithDraw() {
        int id = (1 + random.nextInt(7));
        int cash = random.nextInt(100);
        log.info("Сумма снятия = " + cash);
        runner.accountWithDraw(id, cash);
    }

    @Scheduled(fixedRate = 100)
    public void transferAccount() {
        int sourceId = (1 + random.nextInt(7));
        int destinationId = (1 + random.nextInt(7));
        int cash = random.nextInt(100);
        log.info("Сумма перевода = " + cash);
        runner.transferAccount(sourceId, destinationId, cash);
    }
}
