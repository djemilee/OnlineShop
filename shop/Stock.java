package shop;

import java.util.*;

public class Stock implements Comparable<Stock> {

    private class StockRoom {

        private Map<Categories, List<String>> categoryStocks = new HashMap<>();

        private List<String> autoParts = new ArrayList<>(Arrays.asList("Rims, Accumulator", "Headlamp", "Wipers"));
        private List<String> perfumes = new ArrayList<>(Arrays.asList("Refan", "Paris", "Playboy"));
        private List<String> underwear = new ArrayList<>(Arrays.asList("Briefs", "Thongs with lace", "Boxers"));
        private List<String> bathrobes = new ArrayList<>(Arrays.asList("Cotton Bathrobe", "Silk bathrobe", "Bathrobe with applications"));
        private List<String> bags = new ArrayList<>(Arrays.asList("Leather case", "Bag made of artificial leather", "Polished bag"));
        private List<String> shoes = new ArrayList<>(Arrays.asList("Black boots", "High-heeled shoes", "Pink sneakers", "Sandals", "Casual shoes"));
        private List<String> watches = new ArrayList<>(Arrays.asList("Roleks", "French waterproof watch", "Silicon watch"));
        private List<String> jewelry = new ArrayList<>(Arrays.asList("Bracelet", "Bracelet with heart", "Necklace with diamond", "Ring", "Silver earrings"));
        private List<String> clothes = new ArrayList<>(Arrays.asList("Blouse with bare back", "Shirt", "Jeans", "Summer vest", "Leather pants",
                "T-shirt with applications", "Black top", "Elegant red dress", "Casual dress", "Short skirt", "Leather jacket", "Wool with rabbit skin"));
        private List<String> gifts = new ArrayList<>(Arrays.asList("Rabbit toys", "Santa Claus", "Gift bag"));
        private List<String> sportStocks = new ArrayList<>(Arrays.asList("Ball for baseball", "Featherball" , "Tennis table", "Running trail"));
        private List<String> books = new ArrayList<>(Arrays.asList("War and Peace", "Java for everyone", "Hansel and Gretel", "Harry Potter",
                "Peter Pan", "Rapunzel", "Children's and Household Tales ", "Love novel", "Живот в скалите","Frozen", "Little Pretty Liars"));


        private List<String> marks = new ArrayList<>(Arrays.asList("Hitachi", "Adidas", "Puma", "D&G", "Pandora", "zara"));
        private List<String> colours = new ArrayList<>(Arrays.asList("yellow", "blue", "red", "pink", "green", "black"));



        public StockRoom() {
            loadStocks(Categories.AUTOPARTS, autoParts);
            loadStocks(Categories.PERFUMES, perfumes);
            loadStocks(Categories.UNDERWEAR, underwear);
            loadStocks(Categories.BATHROBES, bathrobes);
            loadStocks(Categories.BAGS, bags);
            loadStocks(Categories.SHOES, shoes);
            loadStocks(Categories.WATCHES, watches);
            loadStocks(Categories.JEWELRY, jewelry);
            loadStocks(Categories.CLOTHES, clothes);
            loadStocks(Categories.GIFTS, gifts);
            loadStocks(Categories.SPORTSTOCKS, sportStocks);
            loadStocks(Categories.BOOKS, books);
        }
        private void loadStocks(Categories category, List<String> list){
            categoryStocks.put(category, list);
        }
    }

    private String name;
    private int price;
    private String mark;
    private String color;
    private static StockRoom stockRoom;
    private int idStock;
    private static int counterForStocks = 0;

    public Stock(String name, int price, String mark, String color) {
        this.name = name;
        this.price = price;
        this.mark = mark;
        this.color = color;
        this.idStock = ++counterForStocks;
        stockRoom = new StockRoom();
    }

    public static Stock giveStock(Categories k) throws Exception {
        if (k != null) {
            return buildStock(stockRoom.categoryStocks.get(k));
        } else {
            throw new Exception("null");
        }
    }

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static int randomForCollections(List<String> kolekciq) {
        return new Random().nextInt(kolekciq.size());
    }

    private static Stock buildStock(List<String> names) {
        return new Stock(names.get(randomForCollections(names)),
                random(10, 100), stockRoom.marks.get(randomForCollections(stockRoom.marks)),
                stockRoom.colours.get(randomForCollections(stockRoom.colours)));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public int compareTo(Stock o) {
        return this.idStock - o.idStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stoka = (Stock) o;
        return price == stoka.price &&
                Objects.equals(name, stoka.name) &&
                Objects.equals(mark, stoka.mark) &&
                Objects.equals(color, stoka.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, mark, color);
    }

    @Override
    public String toString() {
        return "id: " + idStock +
                " name: " + name +
                ", price: " + price
                + "$" +
                ", mark: " + mark +
                ", color: " + color;
    }

    public int getIdStock() {
        return idStock;
    }
}