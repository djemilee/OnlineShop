package tests;

import org.junit.Before;
import org.junit.Test;
import shop.Categories;
import shop.Stock;
import shop.User;
import shop.Vmzona;

import java.util.Map;
import java.util.TreeMap;

import static junit.framework.TestCase.*;

public class TestsShoping {

    User user;
    Stock stock, stock1, stock2;
    Vmzona shop;

    @Before
    public void setUp() {
        shop = new Vmzona("Djemi N1");
        user = new User();

        stock = new Stock("silikonov chasovnik", 35, "adidas", "black");
        stock1 = new Stock("grivna", 28, "zara", "silver");
        stock2 = new Stock("roklq", 40, "nike", "red");
    }

    @Test
    public void testAddStock() {
        shop.addStock(Categories.WATCHES, stock);
        shop.addStock(Categories.JEWELRY, stock1);
        shop.addStock(Categories.CLOTHES, stock2);

        int size = shop.getSizeStocks();
        assertEquals(3, size);
    }

    @Test
    public void testAddStockAgain() {
        shop.addStock(Categories.JEWELRY, stock1);

        boolean isFind = false;
        for (Map.Entry<Categories, TreeMap<Integer, Stock>> entry : shop.getStocks().entrySet()) {
            if (entry.getValue().containsValue(stock1)) {
                isFind = true;
            }
        }
        assertEquals(true, isFind);
    }


    @Test ///?
    public void testRemoveStock() throws Exception {
        shop.addStock(Categories.WATCHES, stock);
        shop.addStock(Categories.JEWELRY, stock1);
        shop.addStock(Categories.CLOTHES, stock2);

        shop.removeStock(1);
        int newSize = shop.getSizeValueOfStocks();

        assertEquals(2, newSize);
    }


    @Test
    public void testCheckForExistStock() {
        shop.addStock(Categories.WATCHES, stock);
        assertEquals(true, shop.checkForStock("1"));
    }

    @Test
    public void testCheckForNotExistStock() {
        shop.addStock(Categories.CLOTHES, stock2);
        assertEquals(false, shop.checkForStock("2"));
    }

    @Test
    public void testAddOrderedStocks() {
        shop.addOrders(stock2);
        shop.addOrders(stock);
        int size = shop.getSizeOfOrderedStocks();

        assertEquals(2, size);
    }

    @Test
    public void testAddUser() {
        shop.addUser(user);
        int size = shop.getSizeOfUsers();

        assertEquals(1, size);
    }

    @Test
    public void testRemoveUser() {
        shop.addUser(user);
        int size = shop.getSizeOfUsers();

        shop.removeUser("dj@abv.bg");
        int newSize = shop.getSizeOfUsers();

        assertNotSame(size, newSize);
    }

    @Test
    public void testRemoveUserAgain() {
        shop.addUser(user);
        shop.removeUser("dj@abv.bg");
        int size = shop.getSizeOfUsers();

        assertEquals(0, size);
    }
}