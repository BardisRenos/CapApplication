package com.example.capgemini.Application.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @Column(name = "account_id")
    private Integer transactionID;
    @Column(name = "customerID")
    private Integer customerID;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "date")
    private LocalDateTime time;

}
