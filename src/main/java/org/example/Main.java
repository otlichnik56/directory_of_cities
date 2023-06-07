package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathFile = "directory-of-cities.csv";
        numberOfCities(readFileAdnCreateCityList(pathFile));

    }

    private static void sortedForName(List<City> cityList) {
        if (cityList == null) {
            System.out.println("directory not found");
        } else {
            cityList.stream().sorted(Comparator.comparing(City::getName))
                    .collect(Collectors.toList()).forEach(System.out::println);
        }
    }

    private static void sortedForNameAndDistrict(List<City> cityList) {
        if (cityList == null) {
            System.out.println("directory not found");
        } else {
            cityList.stream().sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
                    .collect(Collectors.toList()).forEach(System.out::println);
        }
    }

    private static void maxPopulation(List<City> cityList) {
        if (cityList == null) {
            System.out.println("directory not found");
        } else {
            City city = cityList.stream().max(Comparator.comparingInt(City::getPopulation)).get();
            int number = cityList.indexOf(city);
            System.out.println("[" + number + "] = " + city.getPopulation());
        }
    }

    private static void numberOfCities(List<City> cityList) {
        if (cityList == null) {
            System.out.println("directory not found");
        } else {
            Map<String, Integer> counts = new HashMap<>();
            for (City city : cityList) {
                counts.merge(city.getRegion(), 1, Integer::sum);
            }
            for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    private static List<City> readFileAdnCreateCityList(String pathFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathFile));
        String line;
        int index = 0;
        List<City> cityList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            City city = new City();
            Scanner scanner = new Scanner(line);
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
        return cityList;
    }

}