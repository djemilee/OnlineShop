package shop;

import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

public class Profile {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String city;
    private String phoneNumber;
    private String address;

    private static final String[] CITIES = {"Asenovgrad", "Aytos", "Bansko", "Blagoevgrad", "Burgas", "Dimitrovgrad", "Dobrich", "Gabrovo",
            "Gotse Delchev", "Haskovo","Ivaylovgrad", "Kardzhali","Kyustendil","Lovech", "Montana", "Nesebar",
            "Nova Zagora", "Pazardzhik","Pernik", "Petrich", "Pleven", "Plovdiv", "Primorsko", "Razgrad", "Ruse",
            "Sandanski","Shumen", "Silistra","Slivn", "Smolyan", "Sofia City", "Sofia(province)", "Sozopol",
            "Stara Zagora", "Targovishte", "Varna", "Veliko Tarnovo", "Velingrad", "Vidin", "Vratsa", "Yambol"};


    Scanner sc = new Scanner(System.in);

    public Profile(String firstName, String lastName, LocalDate birthDate, String city, String phoneNumber, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Profile() {
        createProfile();
    }

    private void createProfile() {
        this.setFirstName();
        this.setLastName();
        this.setBirthDate();
        this.setCity();
        this.setPhoneNumber();
        this.setAddress();
    }

    private static boolean checkForPhoneNumber(String phoneNumber) {
        String regex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}|\\(\\d{3}\\)-\\d{3}-?\\d{4}|\\(\\d{3}\\) \\d{3} ?\\d{4}|"
                + "\\(\\d{3}\\)-\\d{3} ?\\d{4}|\\(\\d{3}\\) \\d{3}-?\\d{4}";
        return phoneNumber != null && phoneNumber.trim().length() == 10 && phoneNumber.matches(regex);
    }

    public void setFirstName() {
        System.out.println("First Name:");
        String firstName = sc.next();
        if (firstName != null) {
            this.firstName = firstName;
        }else {
            System.out.println("Please write First Name");
        }
    }


    public void setLastName() {
        System.out.println("Last Name:");
        String lastName = sc.next();
        if (lastName != null) {
            this.lastName = lastName;
        }else {
            System.out.println("Please write Last Name");
        }
    }

    private static boolean validDay(int day) {
        return day > 0 && day <= 31;
    }

    private static boolean validMonth(int month) {
        return month > 0 && month <= 12;
    }

    private static boolean validYear(int year) {
        return year > 0 || year <= Year.now().getValue();
    }


    public void setBirthDate() {
        boolean isValid = false;
        while(!isValid) {
            System.out.println("Your Birth Date: \"dd/mm/yyyy\"");
            String date = sc.next();
            String[] dayMonthYear = date.split("/");

            if(dayMonthYear.length == 3) {
                int day = Integer.parseInt(dayMonthYear[0]);
                int month = Integer.parseInt(dayMonthYear[1]);
                int year = Integer.parseInt(dayMonthYear[2]);

                boolean b1 = this.validDay(day);
                boolean b2 = this.validMonth(month);
                boolean b3 = this.validYear(year);
                if(b1 && b2 && b3) {
                    LocalDate locDate = LocalDate.of(year, month, day);
                    this.birthDate = locDate;
                    isValid = true;
                }else {
                    System.out.println("Invalid date! ");
                    System.out.println("Try again: ");
                }
            }
            else {
                System.out.println("Invalid date!");
            }
        }
    }


    public void setCity() {
        System.out.println("Please choose your city:");
        for (int i = 0; i < CITIES.length; i++) {
            System.out.println(i + " - " + CITIES[i]);
        }
        int index = sc.nextInt();
        this.city = CITIES[index];
    }


    public void setPhoneNumber() {
        System.out.print("Phone number: ");
        String phoneNumber = sc.next();
        while (!checkForPhoneNumber(phoneNumber)) {
            System.out.print("Please enter a correct phone number: ");
            phoneNumber = sc.next();
        }
        this.phoneNumber = phoneNumber;
    }


    public void setAddress() {
        System.out.print("Your address: ");
        String address = sc.next();
        if (address != null) {
            this.address = address;
        }
    }

    public static int getCountOfCities(){
        return CITIES.length;
    }

    @Override
    public String toString() {
        return "FirstName: " + firstName + "\nLastName: " + lastName + "\nBirthDate: " + birthDate + "\nCity: "
                + city + "\nAddress: " + address + "\nPhoneNumber: " + phoneNumber ;
    }
}