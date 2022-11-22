package com.example.capgemini.Application.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity class of the user database table
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`user`")
public class User implements Serializable {

    @Id
    @Column(name = "customer_id")
    private Integer customerID;
    @Column(name = "initial_credit")
    private Integer initialCredit;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "balance")
    private Integer balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

}
