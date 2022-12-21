package com.example.test.Application.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The AccountDTO class
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountDTO implements Serializable {

    private Integer accountID;
    private Integer initialCredit;
    private Integer newBalance;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateCreation;
}
