package ru.medonline.generation.utils.converters.impl;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeConverterTest {
    private final DateTimeConverter testingObject = new DateTimeConverter();

    @Test
    void firstFormat_rightData_shouldReturnOk() {
        String date = "1 01 12";
        LocalDate expected = LocalDate.of(2012, 01, 01);
        var result = testingObject.convert(date);
        assertEquals(expected, result);

    }

    @Test
    void df2_rightData_shouldReturnOk() {
        String date = "01 01 12";
        LocalDate expected = LocalDate.of(2012, 01, 01);
        var result = testingObject.convert(date);
        assertEquals(expected, result);

    }

    @Test
    void df3_rightData_shouldReturnOk() {
        String date = "01 1 12";
        LocalDate expected = LocalDate.of(2012, 01, 01);
        var result = testingObject.convert(date);
        assertEquals(expected, result);

    }

    @Test
    void df4_rightData_shouldReturnOk() {
        String date = "01 2 2012";
        LocalDate expected = LocalDate.of(2012, 02, 01);
        var result = testingObject.convert(date);
        assertEquals(expected, result);

    }

    @Test
    void df5_rightData_shouldReturnOk() {
        String date = "01 02 2012";
        LocalDate expected = LocalDate.of(2012, 02, 01);
        var result = testingObject.convert(date);
        assertEquals(expected, result);

    }

    @Test
    void df6_rightData_shouldReturnOk() {
        String date = "1 2 2012";
        LocalDate expected = LocalDate.of(2012, 02, 01);
        var result = testingObject.convert(date);
        assertEquals(expected, result);

    }


    @Test
    void df7_rightData_shouldReturnOk() {
        String date = "1 2 12";
        LocalDate expected = LocalDate.of(2012, 02, 01);
        var result = testingObject.convert(date);
        assertEquals(expected, result);

    }

    @Test
    void dateWithComma_rightDate_shouldReturnOk() {
        String date = "01.02.12";
        String date2 = "01.02.2012";
        String date1 = "1.2.12";
        String date5 = "1.2.2012";
        String date4 = "1.02.12";
        String date3 = "01.2.12";
        String date6 = "01.2.12";
        LocalDate expected = LocalDate.of(2012, 02, 01);
        var result = testingObject.convert(date);
        var result1 = testingObject.convert(date1);
        var result2 = testingObject.convert(date2);
        var result3 = testingObject.convert(date3);
        var result4 = testingObject.convert(date4);
        var result5 = testingObject.convert(date5);
        var result6 = testingObject.convert(date6);

        assertEquals(expected, result);
        assertEquals(expected, result1);
        assertEquals(expected, result2);
        assertEquals(expected, result3);
        assertEquals(expected, result4);
        assertEquals(expected, result5);
        assertEquals(expected, result6);

    }

    @Test
    public void formatWithSlash_CorrectData_shouldReturnOk() {
        LocalDate expected = LocalDate.of(2000, 9, 3);
        String date1 = "09/03/2000";
        String date2 = "9/03/2000";
        String date3 = "9/03/00";
        String date4 = "9/3/00";
        String date5 = "9/3/2000";
        String date6 = "09/3/2000";
        String date7 = "09/3/00";
        String date8 = "09/03/00";

        assertEquals(expected, testingObject.convert(date1));
        assertEquals(expected, testingObject.convert(date2));
        assertEquals(expected, testingObject.convert(date3));
        assertEquals(expected, testingObject.convert(date4));
        assertEquals(expected, testingObject.convert(date5));
        assertEquals(expected, testingObject.convert(date6));
        assertEquals(expected, testingObject.convert(date7));
        assertEquals(expected, testingObject.convert(date8));
    }

    @Test
    void dirtyDate_randomSymbols_shouldParse() {
        LocalDate expected = LocalDate.of(2000, 9, 3);
        String date2 = " ds 9/03/2000 gifun";
        String date1 = "9/03/2000";
        assertEquals(expected, testingObject.convert(date1));
        assertEquals(expected, testingObject.convert(date2));
    }

    @Test
    void addNewFormat_newFormatInputData_shouldParse() {
        String date1 = "17_05_2020";
        LocalDate expected = LocalDate.of(2020, 05, 17);
        testingObject.addNewFormat("dd_MM_yyyy");
        var result = testingObject.convert(date1);
        assertEquals(expected, result);

    }

    @Test
    void addNewFormat_newBadData_shouldThrowException() {
        String date1 = "30_15_2020";
        testingObject.addNewFormat("dd_MM_YYYY");
        assertThrows(DateTimeException.class, () -> {
                    testingObject.convert(date1);
                }
        );

    }
}
