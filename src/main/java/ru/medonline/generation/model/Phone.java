package ru.medonline.generation.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@ToString(exclude = {"fullPhone", "code"}, includeFieldNames = false, callSuper = false,)
public class Phone {
    private String phone;
    private String code = "+7";
    private String fullPhone;

    public Phone(String phone) {
        this.phone = phone;
        this.fullPhone = code + phone;
    }

    @Override
    public String toString() {
        return phone;
    }
}
