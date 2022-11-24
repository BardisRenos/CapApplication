package com.example.test.Application.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The entity class of the Transaction database table
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", unique = true, nullable = false, updatable = false)
    private Integer transactionID;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;

    /**
     * The constructor of the Transaction
     * @param transactionID The transaction id
     * @param amount The amount
     * @param time The time (Year, Month, Day, Hour, minutes, Seconds, and millisecond)
     */
    public Transaction(Integer transactionID, Integer amount, LocalDateTime time) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.time = time;
    }
}
