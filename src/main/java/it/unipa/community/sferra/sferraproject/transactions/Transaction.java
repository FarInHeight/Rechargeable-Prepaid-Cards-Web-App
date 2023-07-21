package it.unipa.community.sferra.sferraproject.transactions;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import it.unipa.community.sferra.sferraproject.cards.Card;
import it.unipa.community.sferra.sferraproject.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false, referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "card_id",  nullable = false, referencedColumnName = "id")
    private Card cardId;

    @Column(nullable = false)
    private Float credit;

    @Column(nullable = false)
    private String description;

    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date creationTimestamp;

}
