package com.fsknso.competition.core.categories;

import lombok.Getter;

@Getter
public enum Grade {
    KYU_0("0 KYU"),
    KYU_10("10 KYU"),
    KYU_9("9 KYU"),
    KYU_8("8 KYU"),
    KYU_7("7 KYU"),
    KYU_6("6 KYU"),
    KYU_5("5 KYU"),
    KYU_4("4 KYU"),
    KYU_3("3 KYU"),
    KYU_2("2 KYU"),
    KYU_1("1 KYU"),
    DAN_1("1 DAN"),
    DAN_2("2 DAN"),
    DAN_3("3 DAN"),
    DAN_4("4 DAN"),
    DAN_5("5 DAN"),
    DAN_6("6 DAN"),
    DAN_7("7 DAN"),
    DAN_8("8 DAN"),
    DAN_9("9 DAN"),
    DAN_10("10 DAN");

    private final String label;

    Grade(String label) {
        this.label = label;
    }

}