package ru.medonline.generation.utils.converters.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneConverterTest {
    PhoneConverter helper = new PhoneConverter();

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

        assertEquals(ex1, helper.convert(first));
        assertEquals(ex2, helper.convert(sec));
        assertEquals(ex3, helper.convert(third));
        assertEquals(ex4, helper.convert(fourth));
        assertEquals(ex5, helper.convert(fifth));
        assertEquals(ex6, helper.convert(sixth));
        assertEquals(ex7, helper.convert(seventh));
        assertEquals(ex8, helper.convert(eight));
        assertEquals(ex9, helper.convert(ninth));
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

        assertEquals(ex1, helper.convert(first));
        assertEquals(ex2, helper.convert(sec));
        assertEquals(ex3, helper.convert(third));
        assertEquals(ex4, helper.convert(fourth));
        assertEquals(ex5, helper.convert(fifth));
        assertEquals(ex6, helper.convert(sixth));
        assertEquals(ex7, helper.convert(seventh));
        assertEquals(ex8, helper.convert(eight));
        assertEquals(ex9, helper.convert(ninth));
    }

}
