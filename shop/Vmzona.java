package shop;

import exceptions.RatingException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
*/


public class Vmzona {

    public static final int MIN_VOTE = 1;
    public static final int MAX_VOTE = 5;

    private String name;
    private int turnover;
    private Map<Categories, TreeMap<Integer, Stock>> stocks = new HashMap<Categories, TreeMap<Integer, Stock>>();
    private Map<String, User> users = new HashMap<String, User>();
    private List<Provider> providers = new ArrayList<Provider>(Arrays.asList(new Provider("Mircho"),
            new Provider("Pesho"),
            new Provider("Lacho"),
            new Provider("Kaloqn")));

    private List<Stock> orderedStocks = new ArrayList<Stock>();

    public Vmzona(String name) {
        this.name = name;
        this.turnover = 0;
    }


    public void provide() {
        Provider randomDostavchik = (Provider) getRandom(this.providers);
        System.out.println("The provider " + randomDostavchik.getName() + " will receive your order within two days!");
    }

    private static Object getRandom(List list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public void addStock(Categories category, Stock stock) {
        if (!stocks.containsKey(category)) {
            stocks.put(category, new TreeMap<>());
        }
        stocks.get(category).put(stock.getIdStock(), stock);
    }

    public Stock removeStock(int idStock) throws Exception {
        for (Map.Entry<Categories, TreeMap<Integer, Stock>> entry : stocks.entrySet()) {
            if (entry.getValue().containsKey(idStock)) {
                Stock stock = entry.getValue().get(idStock);
                entry.getValue().remove(idStock);
                this.turnover += stock.getPrice();
                return stock;
            }
        }
        throw new Exception("The stock not exist");
    }


    Scanner sc = new Scanner(System.in);
    public void showAllExistStocks() {
        System.out.println("Choose criterion for sorting: ");
        System.out.println("1 - price");
        System.out.println("2 - name");
        System.out.println("3 - unique number of stock");
        String choose = sc.next();

        for (Map.Entry<Categories, TreeMap<Integer, Stock>> entry : stocks.entrySet()) {
            System.out.println("Category " + entry.getKey());
            System.out.println("------------------");
            switch (choose) {
                case "1":
                    entry.getValue().values().stream().sorted((stock1, stock2) -> {
                        if (stock1.getPrice() != stock2.getPrice()) {
                            return stock1.getPrice() - stock2.getPrice();
                        }
                        return stock1.getName().compareTo(stock2.getName());
                    }).forEach(stoka -> System.out.println(stoka));
                    System.out.println();
                    continue;
                case "2":
                    entry.getValue().values().stream().sorted((stock1, stock2) -> {
                        if (stock1.getName().compareTo(stock2.getName()) == 0) {
                            return stock1.getPrice() - stock2.getPrice();
                        }
                        return stock1.getName().compareTo(stock2.getName());
                    }).forEach(stock -> System.out.println(stock));
                    System.out.println();
                    continue;
                case "3":
                    entry.getValue().values().stream().forEach(stock -> System.out.println(stock));
                    System.out.println();
                    continue;
                default:
                    System.out.println("Incorrect number!");
                    entry.getValue().values().stream().forEach(stock -> System.out.println(stock));
            }
            System.out.println();
        }
    }
 
    /* 
    
    public void toJson() throws InvalidDataException {

		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setPrettyPrinting()
				.create();
		
		File jsonShop = new File("src\\vmzona\\jsonShop.json");
		if (!jsonShop.exists()) {
			try {
				jsonShop.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String jsonShopUser = gson.toJson(new HashMap(this.users));
		
		try (PrintWriter pw = new PrintWriter(jsonShop)) {
		pw.println(jsonShopUser);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
    }
    
    
    public void loadJson(String fileName) throws IOException {
		String jsonString = readWithBufferedReader(fileName);
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setPrettyPrinting()
				.create();
		
		Type type = new TypeToken<Map<String, User>>(){}.getType();
		Map<String, User> myMap = gson.fromJson(jsonString, type);
		this.users.putAll(myMap); 
    }
    
    private String readWithBufferedReader(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		String content = stringBuilder.toString();
		
		return content;
	}
    
   */


    public void printAllProviders() {
        for (Provider d : providers) {
            System.out.println(d);
        }
    }

    public boolean checkForStock(String number) {
        if (checkForValidNumber(number)) {
            for (Map.Entry<Categories, TreeMap<Integer, Stock>> entry : stocks.entrySet()) {
                if (entry.getValue().containsKey(Integer.parseInt(number))) {
                    return true;
                }
            }
        }
        System.out.println("There is no commodity with such a number !");
        return false;
    }

    public void addOrders(Stock stock) {
        orderedStocks.add(stock);
    }

    public void addUser(User user) {
        if (!users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
        }
    }

    public void removeUser(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
        }
    }

    public void showOrderStocks() {
        int counter = 1;
        for (Stock s : orderedStocks) {
            System.out.println(counter + ". id: " + s.getIdStock() + " - " + s.getName());
            counter++;
        }
    }

    public static void createFileForVote(File votes, int vote, int allVoters) throws IOException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(votes/*, StandardCharsets.UTF_8*/);
            allVoters += vote;
            pw.println(allVoters);
            pw.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            pw.close();
        }
    }

    public static void createFileForCountOfVotes(File countOfVotes, int currentNumberVoters){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(countOfVotes/*, StandardCharsets.UTF_8*/);
            pw.println(++currentNumberVoters);
            pw.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            pw.close();
        }
    }

    public static void messageForVote() throws IOException, RatingException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Are you rate our site with score of 1 to 5?");
        System.out.println("YES or NO");
        String chooseVote = sc.nextLine();

        if (chooseVote.equalsIgnoreCase("YES")) {
            System.out.println("Vote:");
            String giveVote = sc.next();

            if(checkForValidNumber(giveVote)) {
                int vote = giveVote.charAt(0)-'0';
                if (vote < MIN_VOTE) {
                    vote = MIN_VOTE;
                }
                if (vote > MAX_VOTE) {
                    vote = MAX_VOTE;
                }

                int currentNumberVoters = 0;
                int allVoters = 0;

                File votes = new File("files\\votes.txt");
                votes.getParentFile().mkdir();

                if (!votes.exists()) {
                    votes.createNewFile();
                    createFileForVote(votes, vote, allVoters);
                } else {
                    try (Scanner vot = new Scanner(new FileInputStream(votes))) {
                        allVoters = vot.nextInt();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                    createFileForVote(votes, vote, allVoters);
                }

                File countOfVotes = new File("files" + File.separator + "broiGlasuvali.txt");
                countOfVotes.getParentFile().mkdir();
                if (!countOfVotes.exists()) {
                    countOfVotes.createNewFile();
                    createFileForCountOfVotes(countOfVotes, currentNumberVoters);
                } else {
                    try (Scanner vot = new Scanner(new FileInputStream(countOfVotes));) {
                        currentNumberVoters = vot.nextInt();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                    createFileForCountOfVotes(countOfVotes, currentNumberVoters);
                }

                System.out.println("Thank you!");
                System.out.println("Count of voters : " + currentNumberVoters);
                System.out.println("Average vote in the Vmzona is : " + ((allVoters * 1.0) / currentNumberVoters));

                createFileFOrVotes(vote);

            }else {
                System.out.println("Bye, have a nice day and come again ;)");
                throw new RatingException("Invalid rating");
            }
        }
    }

    public static void createFileFOrVotes(int vote) throws IOException {
        LocalDate dt = LocalDate.now();
        LocalTime lt = LocalTime.now();

        File votes1 = new File("files\\DataForVotes.txt");
        votes1.getParentFile().mkdir();

        if (!votes1.exists()) {
            votes1.createNewFile();
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(votes1/*, StandardCharsets.UTF_8*/);
                pw.println("Date: " + dt + " // " + "Time: " + lt + " // " + "Grade: " + vote);
                pw.flush();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }finally{
                pw.close();
            }
        } else {
            FileWriter fw = null;
            try{
                fw = new FileWriter(votes1,true);
                fw.write("Date: " + dt + " // " + "Time: " + lt + " // " + "Grade: " + vote + "\n");
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }finally {
                fw.close();
            }
        }
    }

    private static boolean checkForValidNumber(String number) {
        if(number!=null && number.length()>0) {
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) < '0' || number.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Map<String, User> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    public Map<Categories, TreeMap<Integer, Stock>> getStocks(){
        return Collections.unmodifiableMap(stocks);
    }

    public int getTurnover() {
        return turnover;
    }

    public int getSizeStocks(){
        return stocks.size();
    }

    public int getSizeValueOfStocks(){
        return stocks.values().size();
    }

    public int getSizeOfOrderedStocks(){
        return  orderedStocks.size();
    }

    public int getSizeOfUsers(){
        return users.size();
    }
}