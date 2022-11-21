package com.example.capgemini.Application.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    private Integer accountID;
    @Column(name = "initial_credit")
    private Integer initialCredit;
    @Column(name = "transactions")
    private Transaction transaction;


}
