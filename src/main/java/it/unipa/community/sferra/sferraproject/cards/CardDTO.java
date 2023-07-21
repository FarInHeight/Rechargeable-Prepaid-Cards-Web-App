package it.unipa.community.sferra.sferraproject.cards;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CardDTO {

    private boolean blocked;

    @Min(value = 10, message = "Credit must be greater or equal to 10.")
    private Float credit;
    
}
