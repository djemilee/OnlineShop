package shop;

import exceptions.AdminException;
import exceptions.RatingException;

import java.util.Scanner;

public class Demo{

    public static final int COUNT_OF_STOCKS = 10;


    public static void messageForExit(Vmzona shop) {
        shop.provide();
        System.out.println("Thank you for shopping with us.");
        System.out.println("Have a nice day! ;)");
        System.out.println();

        System.out.println("Enter 0 for exit or number from MENU(1-Guest, 2-User, 3-Administrator) for continue");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            Vmzona shop = new Vmzona("Vmzona");
            shop.addStock(Categories.AUTOPARTS, new Stock("komplekt(2 gumi)", 25, "iron", "black"));


            for (Categories k : Categories.values()) {
                for (int i = 1; i <= COUNT_OF_STOCKS; i++) {
                    try {
                        shop.addStock(k, Stock.giveStock(k));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            System.out.println("MENU VmZona");
            System.out.println("--------");
            System.out.println("1 - Guest");
            System.out.println("2 - User");
            System.out.println("3 - Administrator");
            System.out.println("0 - Exit");

            String choose = sc.next();
            while (true) {
                if (choose.equalsIgnoreCase("0")) {
                    try {
                        Vmzona.messageForVote();
                        System.out.println("Bye, have a nice day and come again ;)");
                    } catch (RatingException e) {
                        System.err.println((e.getMessage()));
                    }
                    return;
                } else {
                    if (choose.equalsIgnoreCase("1")) {

                        System.out.println("Guest!");

                        Guest guest = new Guest();
                        shop.showAllExistStocks();

                        guest.action(shop);

                        System.out.println("Enter \"Finished\" for Finished");
                        System.out.println("Enter \"Continue\" for Continue");
                        String st = sc.next();
                        if (st.equalsIgnoreCase("Finished")) {
                            Vmzona.messageForVote();
                            messageForExit(shop);
                            choose = sc.next();
                        } else {
                            continue;
                        }
                    }
                    if (choose.equalsIgnoreCase("2")) {
                        System.out.println("Other!!");
                        try {
                            User.actions(shop);

                            System.out.println("Enter 0 for exit or number from MENU(1- Guest,2- User,3- Administrator) for continue ");
                            choose = sc.next();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (choose.equalsIgnoreCase("3")) {
                        Admin admin = new Admin(shop);
                        try {
                            admin.actions();
                        } catch (AdminException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Enter 0 for exit or number from MENU(1- Guest,2- User,3- Administrator) for continue ");
                        choose = sc.next();
                    }
                    if (choose.charAt(0) < '0' || choose.charAt(0) > '3') {
                        System.out.println("Invalid number - you enter as a guest");
                        choose = "1";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}