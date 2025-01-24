package com.picpayteste.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.picpayteste.Domain.Transaction.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
