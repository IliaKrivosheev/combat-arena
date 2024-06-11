package com.fsknso.competition.core.categories;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский"),
    MIXED("Смешанный");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

}
