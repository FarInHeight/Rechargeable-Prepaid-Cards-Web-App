package it.unipa.community.sferra.sferraproject.cards;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findCardById(Integer id);

    Float findCreditById(Integer id);

    Card findTopByOrderByIdDesc();

}
