package com.picpayteste.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpayteste.DTOs.TransactionDTO;
import com.picpayteste.Domain.Transaction.Transaction;
import com.picpayteste.Domain.User.User;
import com.picpayteste.Repositories.TransactionRepository;

@Service
public class TransactionServices {

    @Autowired
    private UserServices userServices;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationServices notificationServices;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userServices.findUserById(transaction.senderId());
        User receiver = this.userServices.findUserById(transaction.receiverId());

        userServices.validateTransition(sender, transaction.amount());

        boolean authorized = this.authorizeTransaction(sender, transaction.amount());
        if (!authorized) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setReceiverId(receiver);
        newTransaction.setSenderId(sender);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        receiver.setBalance(receiver.getBalance().add(transaction.amount()));

        this.repository.save(newTransaction);
        this.userServices.saveUser(sender);
        this.userServices.saveUser(receiver);

        this.notificationServices.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationServices.sendNotification(receiver, "Transação recebida com sucesso");
        
        return newTransaction;
        
    }
    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("status");

            return "Success".equalsIgnoreCase(message);
        } else return false;
    }

}
