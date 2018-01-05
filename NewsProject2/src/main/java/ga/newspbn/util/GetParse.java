/**
 * 
 */
package ga.newspbn.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class GetParse {
private Document doc = null;
	
	public List<String[]> getWeather() {
			List<String[]> list = null;
			String[] row = null;
			String addrs = "http://www.weather.go.kr/weather/main-now-weather.jsp?myPointCode=1159068000";
				try {
					doc =  Jsoup.connect(addrs).userAgent("Mozilla/5.0").timeout(1000).get();
					Elements lie = doc.select("div.weather>dl");
//					System.out.println(location);
					int i=0;
					for (Element le : lie) {
						String location  = le.select("dt").text().replace("기온", "");
						String temp =  le.select("dd").text();
						String status = le.select("a>img").attr("alt");
						
						list = new ArrayList<String[]>();
						list.add(new String[] {location, temp, status});
						row = list.get(i);
						System.out.println(Arrays.toString(row));
						System.out.println(row[i]+ row[i+1]+ row[i+2]);
					}

					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
	}
	/*public static void main(String[] args) {
		GetParse gp = new GetParse();
		gp.getWeather();
	}*/
	
}
