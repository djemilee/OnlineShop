package shop;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {

    private static final int MIN_LENGTH_OF_PASSWORD = 6;
    private static final Scanner sc = new Scanner(System.in);

    private String email;
    private String password;
    private Set<Stock> stocksInCart = new TreeSet<>();
    private int sumOrder;
    private Profile ownProfile;
    private static Vmzona shop;


    public User(){
        this.email = "dj@abv.bg";
        this.password = "123456";
        this.ownProfile = new Profile("Dj", "Ba", LocalDate.of(1998, 12, 23),
                                                "Blagoevgrad", "0895674326", "dsfdsfes");
    }

    public User(String email, String password, Vmzona shop) {
        this.setEmail(email, shop);
        this.setPassword(password);
        this.shop = shop;
        this.ownProfile = new Profile();
    }

    private void setPassword(String password) {
        while (!isValidPassword(password)) {
            System.out.println("Try again: ");
            password = sc.next();
        }
        this.password = password;
    }

    private static boolean isValidPassword(String password) {
        if (password != null && password.trim().length() >= MIN_LENGTH_OF_PASSWORD) {
            return true;
        }
        System.out.println("Password must be " + MIN_LENGTH_OF_PASSWORD + "or more than "
                                                + MIN_LENGTH_OF_PASSWORD + " characters!");
        return false;
    }


    private void setEmail(String email, Vmzona shop) {
        while (!isValidEmail(email) || shop.getUsers().containsKey(email)) {
            if (!isValidEmail(email)) {
                System.out.println("Invlid email address.Try again: ");
            } else {
                System.out.println("This email exist, please enter other email!");
            }
            email = sc.next();
        }
        this.email = email;
    }

    private static boolean isValidEmail(String email1) {
        Pattern regexPt = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regexMt = regexPt.matcher(email1);
        if (regexMt.matches()) {
            return true;
        }
        System.out.println("Not correct email adress!");
        return false;
    }

    public static void actions(Vmzona shop) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 New User");
        System.out.println("2.Exist User");
        String choose = sc.next();

        while ((!choose.equalsIgnoreCase( "1")) && (!choose.equalsIgnoreCase( "2"))) {
            System.out.println("1 New User");
            System.out.println("2.Exist User");
            choose = sc.next();
        }
        if (choose.equalsIgnoreCase( "1")) {
            createNewUser(shop);
        }
        User currentUser = logInLIkeExistUser(shop);
        actionsWhenYouLogged(currentUser);
    }

    private static void actionsWhenYouLogged(User user) {
        shop.showAllExistStocks();
        while (true) {

            System.out.println();
            System.out.println("Exit from site with 3 or continue with some other number.");
            String comand = sc.next();
            if (comand.equalsIgnoreCase("3")) {
                System.out.println("Bye!");
                return;
            }

            System.out.println("Enter a number of stock, which you want to order: ");
            String idStock = sc.next();
            if (shop.checkForStock(idStock)) {
                try {
                    Stock stock = shop.removeStock(Integer.parseInt(idStock));
                    user.stocksInCart.add(stock);
                    shop.addOrders(stock);
                    user.sumOrder += stock.getPrice();
                    System.out.println();

                    user.whatYouWant(shop);

                    System.out.println("Enter \"Finished\" for Finished");
                    System.out.println("Enter \"Continue\" for Continue");
                    String st = sc.next();
                    if (st.equalsIgnoreCase("Finished")) {
                        user.forExit(shop);
                        break;
                    } else {
                        continue;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("This stock not exist!");
                continue;
            }
        }
    }

    private void forExit(Vmzona shop) {
        shop.provide();
        System.out.println("Thank you for shopping with us.");
        System.out.println("Have a nice day! ;)");
        System.out.println();
    }

    private void whatYouWant(Vmzona shop) {
        System.out.println("1.Continue with shopping.");
        System.out.println("2.Go to shopping cart!");
        String choose = sc.next();
        switch (choose) {
            case "1":
                System.out.println();
                System.out.println("Enter a number of stock, which you want to order: ");
                int idStock = sc.nextInt();
                try {
                    Stock stoka = shop.removeStock(idStock);
                    this.stocksInCart.add(stoka);
                    shop.addOrders(stoka);
                    this.sumOrder += stoka.getPrice();
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.whatYouWant(shop);
                break;
            case "2":
                System.out.println("List of orders:");
                this.listAllOrders();
                break;
            default:
                System.out.println("Incorrect number!");
        }
    }

    private static User logInLIkeExistUser(Vmzona shop) {
        System.out.println("LOGIN PAGE");
        System.out.println("Enter email");
        String email1 = sc.next();
        System.out.println("Enter password");
        String password1 = sc.next();

        User user = shop.getUsers().get(email1);
        while (user == null || !user.getPassword().equals(password1)) {
            System.out.println("Sorry, wrong email or password!");
            System.out.println("if you do not have a profile, press 0, or press another key to continue.");
            String press = sc.next();
            if (press.equalsIgnoreCase("0")) {
                createNewUser(shop);
            } else {
                System.out.println("Enter email");
                email1 = sc.next();
                System.out.println("Enter password");
                password1 = sc.next();
                user = shop.getUsers().get(email1);
            }
        }
        System.out.println("Show my profile: ");
        System.out.println("Yes or No");
        String s = sc.next();
        if (s.equalsIgnoreCase("Yes")) {
            User user1 = shop.getUsers().get(email1);
            System.out.println(user1.getOwnProfile());
            System.out.println();
        }
        return user;
    }

    private static void createNewUser(Vmzona shop) {
        System.out.println("REGISTER PAGE");
        System.out.println("Enter email");
        String email = sc.next();

        System.out.println("Enter password");
        String password = sc.next();
        User user = new User(email, password, shop);
        shop.addUser(user);
    }


    public void listAllOrders() {
        for (Stock st : this.stocksInCart) {
            System.out.println(st);
        }
        System.out.println("Your total sum is " + this.getSumOrder() + "$");
        System.out.println();
    }

    public void changePassword() {
        System.out.println("Old password: ");
        String oldPassword = sc.next();
        while (!oldPassword.equals(this.password)) {
            System.out.println("It's not your old password!");
            System.out.println("Try again: ");
            oldPassword = sc.next();
        }
        System.out.println("Successful!");
        String newPass = sc.next();
        this.setPassword(newPass);
    }


    @Override
    public boolean equals(Object otherUser) {
        if (otherUser instanceof User)
            return this.email.equals(((User) otherUser).email);
        return false;
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return "[email -> " + email + ", password -> " + password + "]";
    }

    public Profile getOwnProfile() {
        return ownProfile;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public int getSumOrder() {
        return sumOrder;
    }
}