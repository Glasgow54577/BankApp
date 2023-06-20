package com.example.BankingApplicationClient.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Runner {
    private Lock lock = new ReentrantLock();

    public void accountDeposit(int id, int cash) {
        final String url = "http://localhost:8094/deposit";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", id);
        jsonData.put("cash", cash);
        makePostRequestWithJSONData(url, jsonData);
    }

    public void accountWithDraw(int id, int cash) {
        final String url = "http://localhost:8094/withDraw";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", id);
        jsonData.put("cash", cash);
        makePostRequestWithJSONData(url, jsonData);
    }

    public void transferAccount(int sourceId, int destinationId, int cash) {
        final String url = "http://localhost:8094/transfer";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("sourceId", sourceId);
        jsonData.put("destinationId", destinationId);
        jsonData.put("cash", cash);
        makePostRequestWithJSONData(url, jsonData);
    }

    public void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        lock.lock();
        try {
            final RestTemplate restTemplate = new RestTemplate();

            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

            try {
                restTemplate.postForObject(url, request, String.class);
                log.info("Операция успешно выполнена");
            } catch (HttpClientErrorException e) {
                log.error("Операция отклонена");
                System.out.println(e.getMessage());
            }
        } finally {
            lock.unlock();
        }
    }
}

