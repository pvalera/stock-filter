
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MakeMeRich {
//	public static final List<String> symbols = Arrays.asList("AMwD", "HPQ",
//			"IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE", "AMZN", "FB", "CSCO",
//			"SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "BABA", "VRSN","BTC-USD");
	public static final List<String> symbols = Arrays.asList("AMwD", "HPQ",
	"IBM", "TXN","VMW");

	public static void main(String[] args) throws Exception {
		
		// 1. Print these symbols using a Java 8 for-each and lambdas
		Stream<Object> stockSymbol = symbols.stream().map(String -> String);
		System.out.println("Print stock symbols:");
		stockSymbol.forEach(System.out::println);
		
		// 2. Use the StockUtil class to print the price of Bitcoin
		System.out.println("Price of Bitcoin: " + StockUtil.getPrice("BTC-USD"));
		
		// 3. Create a new List of StockInfo that includes the stock price
		System.out.println("New List of StockInfo with the stock price: ");
		List<StockInfo> stockInfoList = symbols.stream().map(symbol -> {
			try {
				return StockUtil.getPrice(symbol);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		stockInfoList.forEach(System.out::println);
	
		// 4. Find the highest-priced stock under $500
		StockInfo symbolStream = stockInfoList.stream()
				.filter(StockUtil.isPriceLessThan(500))
				.sorted(((s1, s2) -> Double.compare(s1.price, s2.price))).reduce((a, b) -> b).orElse(null);
		System.out.println("The highest priced stock under $500: " + symbolStream);

	}
}
