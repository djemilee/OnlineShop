import java.util.*;

public class Stock implements Comparable<Stock> {

    private class StockRoom {

        private Map<Categories, List<String>> categoryStocks = new HashMap<>();

        private List<String> autoParts = new ArrayList<String>(Arrays.asList("djanti, akumulator", "far", "chstachki"));
        private List<String> perfumes = new ArrayList<String>(Arrays.asList("refan", "paris", "playboy"));
        private List<String> underwear = new ArrayList<String>(Arrays.asList("slip", "prashki s dantela", "bokserki"));
        private List<String> bathrobes = new ArrayList<String>(Arrays.asList("pamuchen halat", "koprinen halat", "halat s aplikacii"));
        private List<String> bags = new ArrayList<String>(Arrays.asList("chanta ot istinska koja", "chanta ot izkustvena koja", "polirana chanta"));
        private List<String> shoes = new ArrayList<String>(Arrays.asList("cherni botushi", "boti s visok tok", "rozovi kecove", "sandali", "ejednevni obuvki"));
        private List<String> watches = new ArrayList<String>(Arrays.asList("roleks", "frenski vodoustoichiv chasovnik", "silikonov chasovnik"));
        private List<String> jewelry = new ArrayList<String>(Arrays.asList("grivna", "grivna sys syrce", "kulie s diamant", "prysten", "srebarni obeci"));
        private List<String> clothes = new ArrayList<String>(Arrays.asList("bluza s gol grab", "riza", "danki", "lqtna jiletka", "kojen klin",
                "teniska s aplikaciq", "cheren potnik", "elegantna chervena roklq",
                "ejednevna roklq", "kysa pola", "kojeno qke", "puhen elek ot zaek"));
        private List<String> gifts = new ArrayList<String>(Arrays.asList("podaryk zaek", "podaryk dqdo mraz", "podarychna chasha"));
        private List<String> sportStocks = new ArrayList<String>(Arrays.asList("topka za beizbol", "federbal", "masa za tenis", "bqgashta pateka"));
        private List<String> books = new ArrayList<String>(Arrays.asList("voina i mir", "java za vseki", "detska kniga s prikazki", "Harry Potter", "Peter Pan",
                "Liboven roman", "Jivot v skalite", "Zamryznaloto kralstvo", "Little Pretty Liars"));


        private List<String> marks = new ArrayList<String>(Arrays.asList("hitachi", "adidas", "puma", "D&G", "kompas", "zara"));
        private List<String> colours = new ArrayList<String>(Arrays.asList("yellow", "blue", "red", "pink", "green", "black"));



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
                stockRoom.colours.get(randomForCollections(stockRoom.colours))) {

        };
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
        return "id = " + idStock +
                " ime = " + name +
                ", cena = " + price +
                ", marka = " + mark +
                ", cvqt = " + color;
    }

    public int getIdStock() {
        return idStock;
    }
}