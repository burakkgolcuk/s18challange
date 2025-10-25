package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1) eğer type = JOKER ise value ve color null olmalı
    // 2) eğer type != null ise value null olmalı
    // 3) eğer value != null ise type null olmalı
    // Bu validation'ı controller'a koymayacağız -> util/CardValidation yapacak.

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Type type;

    // normal kart sayısı: 1-13 gibi bir değer olabilir
    private Integer value;
}
