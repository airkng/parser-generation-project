package ru.medonline.generation.utils.converters.impl;

import org.springframework.stereotype.Component;
import ru.medonline.generation.model.Phone;
import ru.medonline.generation.utils.converters.Converter;

@Component
public class PhoneConverter implements Converter<Phone> {

    @Override
    public Phone convert(String dirtyStr) {
        return convertPhoneToString(dirtyStr);
    }
    private Phone convertPhoneToString(String input) {
        String phoneStr = parsePhone(input);
        return new Phone(phoneStr);
    }

    private String parsePhone(String phone) {
        String ph = phone.replaceAll("[\\(\\)]", "")
                .replaceAll("\"","")
                .replaceAll("[\\-\\+\\.\\^:,]","")
                .replaceAll("\\p{P}\\p{S}", "")
                .replaceAll("\\s+", "")
                .trim();
        if (ph.length() == 11) {
            return ph.substring(1);
        }
        return ph;
    }
}
