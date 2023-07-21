package it.unipa.community.sferra.sferraproject.cards;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import it.unipa.community.sferra.sferraproject.transactions.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "CARDS")
public class Card {
    
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean blocked;

    @Column(nullable = false)
    private Float credit;

    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date creationTimestamp;
    
    @OneToMany(mappedBy = "cardId")
    private List<Transaction> transactions;
    
}
