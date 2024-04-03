package ru.medonline.generation.utils.converters.impl;

import lombok.extern.slf4j.Slf4j;
import ru.medonline.generation.model.Address;
import ru.medonline.generation.utils.readers.impl.SimpleFileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AddressConverter {
    private SimpleFileReader reader = new SimpleFileReader();
    private List<String> cities = new ArrayList<>();

    {
        String[] cities = reader.readLines("src/main/resources/cities.csv");
        this.cities = Arrays.asList(cities);
    }
    private List<String> streetsNames = List.of(
            "ул",
            "ул ",
            "Ул",
            "Ул ",
            "улица",
            "улица ",
            "Улица",
            "Улица ",
            "ул.",
            "ул. ",
            "Ул.",
            "Ул. ",
            "Улица.",
            "Улица. ",
            "улица.",
            "улица. "
    );
    private List<String> prospectsNames = List.of(
            "Проспект",
            "Проспект ",
            "проспект",
            "проспект ",
            "Пр-т",
            "Пр-т ",
            "Пр-т.",
            "Пр-т. ",
            "Прос-т",
            "Прос-т ",
            "пр-кт",
            "пр-кт ",
            "Пр-кт",
            "Пр-кт ",
            "прос-т",
            "прос-т ",
            "П-кт",
            "П-кт ",
            "П-кт.",
            "П-кт. ",
            "п-кт",
            "п-кт ",
            "п-кт.",
            "п-кт. ",
            "П.",
            "П. ",
            "п.",
            "п. "
    );

    private List<String> squaresNames = List.of(
            "Площадь",
            "площадь",
            "Пл.",
            "пл.",
            "Пл-дь",
            "пл-дь",
            "пл-дь"
    );
    private List<String> citiesNames = List.of (
            "г.",
            "г. ",
            "г ",
            "Г ",
            "Г.",
            "Гор. ",
            "Гор ",
            "гор. ",
            "гор.",
            "гор ",
            "город.",
            "Город.",
            "Город ",
            "город "
    );

    private List<String> districts = List.of(
            "Район",
            "район",
            "Р-н",
            "Р-н.",
            "Р-он",
            "Р-он.",
            "р-н",
            "р-н.",
            "р-он",
            "р-он.",
            "рн.",
            "рн",
            "Рн.",
            "Рн"
    );
    public Address convertToAddress(String addressStr) {
        String city = findCity(addressStr);
        return null;
    }

    private  String findCity(String addressStr) {
        String resultCity = "";
        String correctedIndex = "";
        for (int i = 0; i < citiesNames.size(); i++) {
            String format = citiesNames.get(i);
           // int lastIndex = addressStr.lastIndexOf(format);
            int index = addressStr.indexOf(format);
            if (index != -1) {
                if (index == 0) {
                    int lastIndex = index + format.length();
                    String city = addressStr.substring(lastIndex);
                    resultCity = findOverlap(city);
                    if (resultCity == null) {
                        resultCity = predictCity(city, lastIndex - 1);
                    }
                    break;
                } else {
                    int lastIndex = index + format.length();
                    String addressIndex = addressStr.substring(0, index);
                    correctedIndex = prettyIndex(addressIndex);
                    if (correctedIndex == null) {
                       log.error("Найденный объект не является индексом: {}", correctedIndex);
                    }
                    String city = addressStr.substring(lastIndex - 1);
                    resultCity = findOverlap(city);
                    if (resultCity == null) {
                        //Если города нет в списке городов, то пробуем предугадать. Скорее всего это одно либо два слова сразу
                        // с нулевого индекса
                        resultCity = predictCity(city, lastIndex);
                    }
                    break;
                }
                //если индекс символа начинается не с нуля, значит перед ним стоит индекс
                //если с нуля, то делаем обрезание префиксов города а дальше через список
            }
        }
        if (resultCity.isBlank() || resultCity.isEmpty()) {
            String city = findCityWithoutPrefix(addressStr);
            // значит не найден, возможно спереди есть индекс, это нужно учесть
        }
        //если паттерны не подошли, значит там может быть в начале индекс либо сразу город. Следовательно, нужно

        return null;
    }

    private String findCityWithoutPrefix(String addressStr) {
        //тут нужно посмотреть сколько слов начинаются с заглавной буквы
        //если есть сокращения например через . то это адрес
        //если слово 1 значит это адрес
        //если 2 слова получается что первое слово - город - второе адрес
        // если три слова, то либо город из двух слов либо адрес из 2 слов
        return null;
    }

    private String predictCity(String address, int index) {
        int startIndex = -1;
        String city = null;
        startIndex = hasAddressPartName(address, districts);
        if (startIndex == -1) {
            startIndex = hasAddressPartName(address, streetsNames);
            if (startIndex == -1) {
                startIndex = hasAddressPartName(address, prospectsNames);
                if (startIndex == -1) {
                    startIndex = hasAddressPartName(address, squaresNames);
                    if (startIndex == -1) {
                        city = predictByCapitalLetters(address, index);
                        return city;
                    }
                }
            }
            //если найден, делаем обрезание до этой буквы и находим до нее все слова с заглавной буквы
            //это и будет нашим ответом
        }
        city = prettyCity(address, index, startIndex);
        return city;
        // если есть район, то скорее всего, он до названия Район. Если найден только 1 слово с заглавной буквы, то это сто проц город
        // если 3
        //делаем сабстринг

    }

    private String prettyCity(String address, int index, int startIndex) {
        String pretty = address.substring(index, startIndex);
        if (pretty.contains(",")) {
            var res = pretty.split(",")[0];
            res = res.trim(). replaceAll("[,]|[_]|[.]", "");
            return res;
        }
        if (pretty.contains(";")) {
            var res = address.split(";")[0];
            res = res.trim().replaceAll("[,]|[_]|[.]", "");
            return res;
        }
        return pretty.trim().replaceAll("[,]|[_]|[.]", "");
    }

    private String predictByCapitalLetters(String address, int index) {
        //тут нужно посмотреть сколько слов начинаются с заглавной буквы
        //если есть сокращения например через . то это адрес
        //если слово 1 значит это адрес
        //если 2 слова получается что первое слово - город - второе адрес
        // если три слова, то либо город из двух слов либо адрес из 2 слов
        return null;
    }

    private int hasAddressPartName(String address, List<String> names) {
        for (int i = 0; i < names.size(); i++) {
            String format = names.get(i);
            int index = address.indexOf(format);
            if (index != -1) {
                return index;
            }
        }
        return -1;
    }


    private String prettyIndex(String addressIndex) {
        addressIndex = addressIndex.replaceAll("[^0-9]", "").trim();
        if (addressIndex.length() == 6) {
            return addressIndex;
        }
        return null;
    }

    private String findOverlap(String city) {
        String input = city.replaceAll(",", "").trim().replaceAll("\\s+", " ");
        int maxValue = 0;
        for (int i = 0; i < cities.size(); i++) {
            String possibleCity = cities.get(i);
            if (possibleCity.isBlank() || possibleCity.isEmpty()) {
                continue;
            }
            int index = input.toLowerCase().lastIndexOf(possibleCity.toLowerCase());
            if (index == -1) {
                continue;
            }
            int lastIndex = index + possibleCity.length();
            if (lastIndex > maxValue) {
                maxValue = lastIndex;
            }
        }
        if (maxValue == 0) {
            return null;
        }
        input = input.substring(0,1).toUpperCase() + input.substring(1, maxValue);
        return input;
    }

    public static void main(String[] args) {
        String s1 = "г.Казань, ул.Глушко, д.22, кв.34";
        String s2 = "420100, г.Казань, ул.Хайдара Бигичева, д.27, кв.43";
        String s3 = "420140, г. Казань, ул. Ломжинская, д.3, кв.67";
        String s4 = "город Казань, ул. пр-кт Победы , д. 78,кв. 55";
        String s5 = "г. Казань, ул. Ак. Глушко д.16/24, кв.72";
        String s7 = "165241 г. Набережные Челны , ул. Ак. Глушко д.16/24, кв.72";
        String s8 = "г Казань, ул.Глушко, д.22, кв.34";
        String s9 = "гор Казань, ул.Глушко, д.22, кв.34";
        String s10 = "гор.Казань, ул.Глушко, д.22, кв.34";
        String s11 = "г казань, ул.Глушко, д.22, кв.34";
        String s12 = "123345 г казань, ул.Глушко, д.22, кв.34";
        String s13 = "Город казань, ул.Глушко, д.22, кв.34";
        String s14 = "132456 Город казань, ул.Глушко, д.22, кв.34";
        String s15 = "132456 Город казань, ул.Глушко, д.22, кв.34";
        String s16 = "г.Казань, ул. Ак. Глушко д.16/24, кв.72";
        String s17 = "г. Васькино , ул. Ак. Глушко д.16/24, кв.72";
        //   String s6 = "Казань , ул. Ак. Глушко д.16/24, кв.72";
        var helper = new AddressConverter();
      /*  System.out.println(helper.findCity(s1));
        System.out.println(helper.findCity(s2));
        System.out.println(helper.findCity(s3));
        System.out.println(helper.findCity(s4));
        System.out.println(helper.findCity(s5));
        System.out.println(helper.findCity(s7));
        System.out.println(helper.findCity(s8));
        System.out.println(helper.findCity(s9));
        System.out.println(helper.findCity(s10));
        System.out.println(helper.findCity(s11));
        System.out.println(helper.findCity(s12));
        System.out.println(helper.findCity(s13));
        System.out.println(helper.findCity(s14));
        System.out.println(helper.findCity(s15));
        System.out.println(helper.findCity(s16));*/
        System.out.println(helper.findCity(s17));
    }
}
