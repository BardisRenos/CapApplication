package com.example.test.Application.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity class of the Account database table
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", unique = true, nullable = false, updatable = false)
    private Integer accountID;
    @Column(name = "initial_credit")
    private Integer initialCredit;
    @Column(name = "date_creation")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateCreation;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_fk")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_fk")
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * The constructor of the Account
     * @param accountID The account id
     * @param initialCredit The initial credit of the account
     * @param dateCreation The date of creation of the account
     */
    public Account(Integer accountID, Integer initialCredit, LocalDateTime dateCreation) {
        this.accountID = accountID;
        this.initialCredit = initialCredit;
        this.dateCreation = dateCreation;
    }

    public Account() {
    }

    public Integer getInitialCredit() {
        return initialCredit;
    }
}
