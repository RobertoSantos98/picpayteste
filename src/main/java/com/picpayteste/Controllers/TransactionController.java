package com.picpayteste.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpayteste.DTOs.TransactionDTO;
import com.picpayteste.Domain.Transaction.Transaction;
import com.picpayteste.Services.TransactionServices;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionServices _transactionServices;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = _transactionServices.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

}
