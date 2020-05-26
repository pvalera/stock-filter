
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StockUtil {
	public static StockInfo getPrice(final String ticker) {
		return new StockInfo(ticker, prices.get(ticker));
	}

	public static Predicate<StockInfo> isPriceLessThan(final double price) {
		return stockInfo -> stockInfo.price < price;
	}

	public static StockInfo pickHigh(final StockInfo stockInfo1,
			final StockInfo stockInfo2) {
		return stockInfo1.price > stockInfo2.price ? stockInfo1 : stockInfo2;
	}

	static Map<String, Double> prices = new HashMap<String, Double>() {
		{
			put("AMD", 53.19);
			put("HPQ", 17.06);
			put("IBM", 121.76);
			put("TXN", 115.73);
			put("VMW", 137.93);
			put("XRX", 17.43);
			put("AAPL", 316.73);
			put("ADBE", 376.63);
			put("AMZN", 2421.86);
			put("FB", 232.20);
			put("CSCO", 44.84);
			put("SNE", 62.18);
			put("GOOG", 1417.02);
			put("INTC", 62.34);
			put("INTU", 282.20);
			put("MSFT", 181.57);
			put("ORCL", 39.02);
			put("BABA", 201.72);
			put("VRSN", 208.22);
			put("BTC-USD", 9655.32);
		}
	};
}
