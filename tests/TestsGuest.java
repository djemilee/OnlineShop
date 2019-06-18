package tests;

import org.junit.Before;
import org.junit.Test;
import shop.Guest;
import shop.Stock;

import static junit.framework.TestCase.*;

public class TestsGuest {

    private Guest guest;
    private Stock stock, stock1;

    @Before
    public void setUp(){
        guest = new Guest();
        stock = new Stock("Bag", 15, "D&G", "white");
        stock1 = new Stock("Book", 18, "Romantic", "black");
    }

    @Test
    public void testEmptyCarteforeBuy(){
        int count = guest.countOfStocks();

        assertEquals(0, count);
    }

    @Test
    public void testSumOrderBeforeBuy(){
        int sum = guest.getSumOrder();

        assertEquals(0, sum);
    }

    @Test
    public void testSumOrderAfterBuyStock(){
        guest.buySomething(stock);
        int price = guest.getSumOrder();

        assertEquals(15, price);
    }

    @Test
    public void testSumOrderAfterBuySomeStocks(){
        guest.buySomething(stock);
        guest.buySomething(stock1);
        int price = guest.getSumOrder();

        assertEquals(33, price);
    }

    @Test
    public void testSumOrderAfterRefuseStock(){
        guest.buySomething(stock);
        guest.buySomething(stock1);
        int sumOrder = guest.getSumOrder();

        guest.refuseBuyStock(stock1);
        int sumOrder1 = guest.getSumOrder();

        assertNotSame(sumOrder, sumOrder1);
    }

    @Test
    public void testSumOrderAfterRefuseStockAgain(){
        guest.buySomething(stock);
        guest.buySomething(stock1);

        guest.refuseBuyStock(stock);
        int sumOrder1 = guest.getSumOrder();

        assertEquals(18, sumOrder1);
    }

    @Test
    public void testAddStockAfterBuy(){
        guest.buySomething(stock);
        int count = guest.countOfStocks();

        assertEquals(1, count);
    }

    @Test
    public void testRemoveStockAfterRefuse(){
        guest.buySomething(stock);
        guest.buySomething(stock1);
        int count = guest.countOfStocks();

        guest.refuseBuyStock(stock);
        int count1 = guest.countOfStocks();

        assertNotSame(count, count1);
    }

    @Test
    public void testRemoveStockAfterRefuseAgain(){
        guest.buySomething(stock);
        guest.refuseBuyStock(stock);
        int count = guest.countOfStocks();

        assertEquals(0, count);
    }

}
