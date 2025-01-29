package com.picpayteste.Domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpayteste.Domain.User.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "senderId")
    private User senderId;
    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiverId;
    private LocalDateTime timestamp;
}
