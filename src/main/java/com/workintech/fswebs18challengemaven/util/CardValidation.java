package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {

    public static void validate(Card card) {
        // JOKER kartı ise:
        // - color null olmalı
        // - value null olmalı
        if (card.getType() == Type.JOKER) {
            if (card.getColor() != null || card.getValue() != null) {
                throw new CardException("JOKER cannot have color or value", HttpStatus.BAD_REQUEST);
            }
            return;
        }

        // normal kart ise:
        // - eğer value != null ise type null olmalı
        // - eğer type != null ise value null olmalı
        if (card.getType() != null && card.getValue() != null) {
            throw new CardException("Card cannot have both type and value", HttpStatus.BAD_REQUEST);
        }

        if (card.getType() == null && card.getValue() == null) {
            throw new CardException("Card must have either type or value", HttpStatus.BAD_REQUEST);
        }
    }
}
