package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                "directory-of-cities.csv"));
        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<City> cityList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            City city = new City();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 1)
                    city.setName(data);
                else if (index == 2)
                    city.setRegion(data);
                else if (index == 3)
                    city.setDistrict(data);
                else if (index == 4)
                    city.setPopulation(Integer.parseInt(data));
                else if (index == 5)
                    city.setFoundation(data);
                index++;
            }
            index = 0;
            cityList.add(city);
        }
        reader.close();
        sortedForNameAndDistrict(cityList);

    }

    private static void sortedForName(List<City> cityList) {
        cityList.stream().sorted(Comparator.comparing(City::getName)).collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void sortedForNameAndDistrict(List<City> cityList) {
        cityList.stream().sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName)).collect(Collectors.toList()).forEach(System.out::println);
    }



}