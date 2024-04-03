package ru.medonline.generation.utils.converters.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.medonline.generation.model.Phone;
import ru.medonline.generation.utils.converters.Converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {PhoneConverter.class})
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PhoneConverterTest {
    private final Converter<Phone> helper;

    @Test
    void parsingPhone_differentFormats_shouldReturnOk() {
        String first = "8(999)111-99-99";
        String sec = "+7(999)999-99-99";
        String third = "+7(999)999-99-99";
        String fourth = "81111111111";
        String fifth = "\"+7(999)999-99-99\"\"\"";
        String sixth = "\"+8999999-99-99\"\"\"";
        String seventh = "\"8999999-99-99\"\"\"";
        String eight = "\"1111111111\"\"\"";
        String ninth = "(999)999-99-99";

        String ex1 = "9991119999";
        String ex2 = "9999999999";
        String ex3 = "9999999999";
        String ex4 = "1111111111";
        String ex5 = "9999999999";
        String ex6 = "9999999999";
        String ex7 = "9999999999";
        String ex8 = "1111111111";
        String ex9 = "9999999999";

        assertEquals(ex1, helper.convert(first).getPhone());
        assertEquals(ex2, helper.convert(sec).getPhone());
        assertEquals(ex3, helper.convert(third).getPhone());
        assertEquals(ex4, helper.convert(fourth).getPhone());
        assertEquals(ex5, helper.convert(fifth).getPhone());
        assertEquals(ex6, helper.convert(sixth).getPhone());
        assertEquals(ex7, helper.convert(seventh).getPhone());
        assertEquals(ex8, helper.convert(eight).getPhone());
        assertEquals(ex9, helper.convert(ninth).getPhone());
    }

    @Test
    void parsingPhone_differentFormats_shouldReturnFullNumber() {
        String first = "8(999)111-99-99";
        String sec = "+7(999)999-99-99";
        String third = "+7(999)999-99-99";
        String fourth = "81111111111";
        String fifth = "\"+7(999)999-99-99\"\"\"";
        String sixth = "\"+8999999-99-99\"\"\"";
        String seventh = "\"8999999-99-99\"\"\"";
        String eight = "\"1111111111\"\"\"";
        String ninth = "(999)999-99-99";

        String ex1 = "+79991119999";
        String ex2 = "+79999999999";
        String ex3 = "+79999999999";
        String ex4 = "+71111111111";
        String ex5 = "+79999999999";
        String ex6 = "+79999999999";
        String ex7 = "+79999999999";
        String ex8 = "+71111111111";
        String ex9 = "+79999999999";

        assertEquals(ex1, helper.convert(first).getFullPhone());
        assertEquals(ex2, helper.convert(sec).getFullPhone());
        assertEquals(ex3, helper.convert(third).getFullPhone());
        assertEquals(ex4, helper.convert(fourth).getFullPhone());
        assertEquals(ex5, helper.convert(fifth).getFullPhone());
        assertEquals(ex6, helper.convert(sixth).getFullPhone());
        assertEquals(ex7, helper.convert(seventh).getFullPhone());
        assertEquals(ex8, helper.convert(eight).getFullPhone());
        assertEquals(ex9, helper.convert(ninth).getFullPhone());
    }

}
