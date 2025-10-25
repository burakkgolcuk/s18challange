package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class CardRepositoryImpl implements CardRepository {

    private final EntityManager entityManager;

    // Testte new CardRepositoryImpl(null) çağrıldığı için null kabul eden ctor lazım.
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.color = :color", Card.class);
        query.setParameter("color", Color.valueOf(color));
        List<Card> result = query.getResultList();
        if (result.isEmpty()) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c", Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.value = :val", Card.class);
        query.setParameter("val", value);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.type = :type", Card.class);
        query.setParameter("type", Type.valueOf(type));
        return query.getResultList();
    }

    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Override
    public Card remove(Long id) {
        Card found = entityManager.find(Card.class, id);
        entityManager.remove(found);
        return found;
    }
}
