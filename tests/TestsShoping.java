package tests;

import org.junit.Before;
import org.junit.Test;
import shop.Categories;
import shop.Stock;
import shop.User;
import shop.Vmzona;

import static junit.framework.TestCase.*;

public class TestsShoping {

    User user;
    Stock stock, stock1, stock2;
    Vmzona shop;

    @Before
    public void setUp(){
        shop = new Vmzona("Djemi N1");
        user = new User("dj@abv.bg", "123456", shop);

        stock = new Stock("silikonov chasovnik", 35, "adidas", "black");
        stock1 = new Stock("grivna", 28, "zara", "silver");
        stock2 = new Stock("roklq", 40, "nike", "red");
    }

    @Test
    public void testAddStock(){
        shop.addStock(Categories.WATCHES, stock);
        shop.addStock(Categories.JEWELRY, stock1);
        shop.addStock(Categories.CLOTHES, stock2);

        int size = shop.getSizeStocks();
        assertEquals(3, size);
    }

    @Test
    public void testRemoveStock() throws Exception { ///?
        shop.addStock(Categories.WATCHES, stock);
        shop.addStock(Categories.JEWELRY, stock1);
        shop.addStock(Categories.CLOTHES, stock2);
        int size = shop.getSizeStocks();

        shop.remoteStoka(1);
        int newSize = shop.getSizeValueOfStocks();

        assertNotSame(size, newSize);
    }

    @Test
    public void testCheckForExistStock(){
        shop.addStock(Categories.WATCHES, stock);
        assertTrue(shop.checkForStock("1"));
    }

    @Test
    public void testCheckForNotExistStock(){
        shop.addStock(Categories.WATCHES, stock);
        assertEquals(false, shop.checkForStock("2"));
    }

    @Test
    public void testAddOrderedStocks(){
        shop.addOrders(stock2);
        shop.addOrders(stock);
        int size = shop.getSizeOfOrderedStocks();

        assertEquals(2, size);
    }

    @Test ///?
    public void testAddUser(){
        shop.addUser(user);
        int size = shop.getSizeOfUsers();

        assertEquals(1, size);
    }

    @Test ///?
    public void testRemoveUser(){
        shop.addUser(user);
        int size = shop.getSizeOfUsers();

        shop.removeUser("dj@abv.bg");
        int newSize = shop.getSizeOfUsers();

        assertEquals(newSize, size);
    }



}
