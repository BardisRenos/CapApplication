package com.example.capgemini.Application.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The entity class of the Account database table
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @Column(name = "account_id")
    private Integer accountID;
    @Column(name = "initial_credit")
    private Integer initialCredit;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transactions_fk", referencedColumnName = "account_id")
    private List<Transaction> transactions;

}
