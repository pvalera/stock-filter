
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.function.Predicate;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class StockUtil {
	
	final static String API_KEY = "9H24FMC9DSNM6FRT";

	public static void main(String[] args) throws Exception {
//		String symbol = "IBM2";
//		double price = getStockPrice(symbol);
//		if (price >= 0.0) {
//			System.out.println(price);
//		}
	}

	public static StockInfo getPrice(final String ticker) throws Exception {
		return new StockInfo(ticker, getStockPrice(ticker));
	}

	public static Predicate<StockInfo> isPriceLessThan(final double price) {
		return stockInfo -> stockInfo.price < price;
	}

	public static StockInfo pickHigh(final StockInfo stockInfo1,
			final StockInfo stockInfo2) {
		return stockInfo1.price > stockInfo2.price ? stockInfo1 : stockInfo2;
	}

	public static double getStockPrice(String symbol) throws Exception {
		
		String rootURL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE";
		rootURL += "&symbol=" + URLEncoder.encode(symbol, "UTF-8");
		rootURL += "&apikey=" + API_KEY;
		
//		URL request = new URL(rootURL);	
//		InputStream openStream = request.openStream();
//		String response = IOUtils.toString(openStream);
//		
//		JSONObject root = new JSONObject(response);			
//		
//		JSONObject firstTrack;
//		try {
//			firstTrack = (JSONObject) root.get("Global Quote");
//		} catch (JSONException e) {
//			System.out.println("No stock symbol for " + symbol + ".");
//			e.printStackTrace();
//			return (Double) null;
//		}

		JSONObject firstTrack = new JSONObject();
    	try {

            URL url = new URL(rootURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }

            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
	        StringBuilder sb = new StringBuilder();
            String line; 
            
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONObject symbolDetails = new JSONObject(sb.toString());
			firstTrack = (JSONObject) symbolDetails.get("Global Quote");
            conn.disconnect();
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }

		JSONObject symbolDetails = (JSONObject) firstTrack;
        return Double.valueOf((String) symbolDetails.get("05. price"));
    	
	}
}

