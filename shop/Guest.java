package shop;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Guest{

    Scanner sc = new Scanner(System.in);

    private Set<Stock> stocksInCart = new TreeSet<>();
    private int sumOrder;

    public Guest() {
        this.sumOrder = 0;
    }

    public void buySomething(Stock st) {
        if (st != null) {
            this.stocksInCart.add(st);
            this.sumOrder += st.getPrice();
        } else {
            System.out.println("Stock is null!");
        }
    }

    public void refuseBuyStock(Stock st){
        if (st != null) {
            this.stocksInCart.remove(st);
            this.sumOrder -= st.getPrice();
        }else {
            System.out.println("Stock is null!");
        }
    }

    public void listAllOrders() {
        for (Stock st : this.stocksInCart) {
            System.out.println(st);
        }
        System.out.println("Your total sum is " + this.getSumOrder() + "$");
        System.out.println();
    }


    public void shopping(Vmzona shop) {
        while (true) {
            System.out.println("Enter a number of stock, which you want to order: ");
            String idStock = sc.next();
            if (shop.checkForStock(idStock)) {
                try {
                    Stock stock = shop.removeStock(Integer.parseInt(idStock));
                    buySomething(stock);
                    shop.addOrders(stock);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                messageForShopping();
                String choose1 = sc.next();
                switch (choose1) {
                    case "1":
                        continue;
                    case "2":
                        System.out.println("List of orders:");
                        listAllOrders();
                        break;
                    default:
                        System.out.println("Invalid number!");
                        break;
                }
            }
            break;
        }
    }

    public void messageForShopping() {
        System.out.println("1.Continue with shopping.");
        System.out.println("2.Go to shopping cart!");
    }

    public int getSumOrder() {
        return sumOrder;
    }

    public int countOfStocks(){
        return stocksInCart.size();
    }
}