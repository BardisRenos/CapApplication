package com.example.capgemini.Application.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Transaction {

    @Id
    private Integer transactionID;
    @Column(name = "customerID")
    private Integer customerID;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "date")
    private LocalDateTime time;

}
