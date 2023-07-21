package it.unipa.community.sferra.sferraproject.transactions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDTO {
    
    private String username;

    private Integer cardId;

    private Float credit;

    private String description;

    private Date creationTimestamp;
    
}
