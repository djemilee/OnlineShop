package shop;

import exceptions.AdminException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Admin {

    private String email;
    private String password;
    private Vmzona shop;

    Scanner sc = new Scanner(System.in);

    public Admin(Vmzona shop) {
        this.email = "strogoSecretno@abv.bg";
        this.password = "naidobriqadministrator28";
        this.shop = shop;
    }

    public void actions() throws Exception {

        System.out.println("Enter your аdmin email address:");
        String checkEmail = sc.next();
        System.out.println("Enter your admin password:");
        String checkPassword = sc.next();
        if (checkEmail.equals(getEmail()) && checkPassword.equals(getPassword())) {

            System.out.println("Successful");
            this.listOfRights();
            String choose = sc.next();

            while (!choose.equalsIgnoreCase("8")) {
                switch (choose) {
                    case "1":
                        this.removeUser();
                        break;
                    case "2":
                        this.showUsers();
                        break;
                    case "3":
                        this.showOborot();
                        break;
                    case "4":
                        this.printStocks();
                        break;
                    case "5":
                        this.showOrders();
                        break;
                    case "6":
                        this.printProviders();
                        break;
                    case "7":
                        this.printVotes();
                        break;
                    default:
                        System.out.println("Incorrect");
                        break;
                }
                System.out.println();
                choose = sc.next();
            }
            return;
        } else {
            throw new AdminException("You have not access to this information!");
        }
    }

    private void listOfRights() {
        System.out.println("1 - Remove user/s");
        System.out.println("2 - Show all users");
        System.out.println("3 - Check for turnover");
        System.out.println("4 - Show all stocks in store");
        System.out.println("5 - Show all orders in shop");
        System.out.println("6 - Show all providers in shop");
        System.out.println("7 - Show all votes for shop");
        System.out.println("8 - Exit");
    }

    private void printVotes() throws IOException {

        File file = new File("files\\DataForVotes.txt");
        File file1 = new File("files\\broiGlasuvali.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedReader br1 = new BufferedReader(new FileReader(file1));

        String st;
        while ((st = br.readLine()) != null) {
            System.out.println(st);
        }
        System.out.println();
        while ((st = br1.readLine()) != null) {
            System.out.println("Count of voters: " + st);
        }
    }

    private void printProviders() {
        shop.printAllProviders();
    }


    private void printStocks() {
        shop.showAllExistStocks();
    }

    private void showOborot() {
        System.out.println("Turnover= " + shop.getTurnover() + " lv");
    }

    private void showOrders() {
        shop.showOrderStocks();
    }

    private void showUsers() {
        int counter = 1;
        for (Map.Entry<String, User> entry : shop.getUsers().entrySet()) {
            System.out.println("User " + counter + ": " + entry.getValue());
            System.out.println();
            counter++;
        }
    }

    private void removeUser() {
        int counter = 1;
        for (Map.Entry<String, User> entry : shop.getUsers().entrySet()) {
            System.out.println("User " + counter + ": " + entry.getValue().getEmail());
            System.out.println();
            counter++;
        }

        System.out.println("Enter email of user, who you want to remove:  ");
        String email = sc.next();
        if (shop.getUsers().containsKey(email)) {
            shop.removeUser(email);
            this.showUsers();
        } else {
            System.out.println("Wrong email address");
            return;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

}
