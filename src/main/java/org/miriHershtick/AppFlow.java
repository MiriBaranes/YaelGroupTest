package org.miriHershtick;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Scanner;

public class AppFlow {
    public static Scanner scanner = new Scanner(System.in);

    public static int printMenuOptionsToUser() {
        boolean validInput = false;
        String input = "";
        do {
            System.out.println("1.Single- Get Single Hebrew date for a given Single Gregorian date");
            System.out.println("2.Range-  Get Range of Hebrew dates for a given Range of Gregorian dates");
            System.out.println("3. Exit");
            input = scanner.nextLine();
            validInput = isValidMenuInput(input);
        } while (!validInput);
        return Integer.parseInt(input);
    }

    public static boolean isValidMenuInput(String input) {
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= 3) {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid menu option. Please enter a number between 1 and 3.");
        }
        return false;
    }

    private static final Map<Integer, Runnable> menuActions = Map.of(
            1, AppFlow::getSingleHebrewDate,
            2, AppFlow::getRangeHebrewDates
    );

    public static void flow() {
        int menuType = printMenuOptionsToUser();
        while (menuType != 3) {
            menuActions.getOrDefault(menuType, () -> System.out.println("Invalid option")).run();
            menuType = printMenuOptionsToUser();
        }
    }

    public static String getValidGregorianDate(String type) {
        System.out.println("Enter a " + type + " in format YYYY-MM-DD");
        String gregorianDate = scanner.nextLine();
        if (DateValidator.isValidDate(gregorianDate)) {
            return gregorianDate;
        } else {
            System.out.println("The format of your input is NOT valid!");
            return getValidGregorianDate(type);
        }
    }

    public static void getSingleHebrewDate() {
        String gregorianDate = getValidGregorianDate("Single Date");
        HDate response = fetchApiResponse("https://www.hebcal.com/converter?cfg=json&date=" + gregorianDate + "&g2h=1&strict=1", HDate.class);
        assert response != null;
        System.out.println("Gregorian Date: " + gregorianDate + " -> " + response);
    }

    public static void getRangeHebrewDates() {
        String startDate = getValidGregorianDate("Start Date");
        String endDate = getValidGregorianDate("End Date");
        if (DateValidator.isValidDateStartBeforeEnd(startDate, endDate)) {
            HebcalResponse response = fetchApiResponse("https://www.hebcal.com/converter?cfg=json&start=" + startDate + "&end=" + endDate + "&g2h=1", HebcalResponse.class);
            assert response != null;
            response.printHDates();
        } else {
            System.out.println("The start date is after end date there is no valid range for this input,Try again");
            getRangeHebrewDates();
        }
    }

    public static <T> T fetchApiResponse(String url, Class<T> responseType) {
        ApiReq apiReq = new ApiReq(url);
        if (apiReq.getStatusCode() == 200) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(apiReq.getResponseBody(), responseType);
            } catch (Exception e) {
                System.out.println("Error parsing response.");
            }
        } else {
            System.out.println("API Error with status code: " + apiReq.getStatusCode());
        }
        return null;
    }
}
