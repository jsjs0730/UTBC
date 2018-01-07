/**
 * 
 */
package tk.utbc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class GetParse {
private Document doc = null;
	
	public List<Map> getWeather() {
			String[] row = null;
			List<Map> list = new ArrayList();
			Map<String, Object> ww = new HashMap<>(); 
			
			String addrs = "http://www.weather.go.kr/weather/main-now-weather.jsp?myPointCode=1159068000";
				try {
					doc =  Jsoup.connect(addrs).userAgent("Mozilla/5.0").timeout(1000).get();
					Elements lie = doc.select("div.weather>dl");
					int i=0;
					for (Element le : lie) {
						ww = new HashMap<>(); //map 개체는 for문이 돌 때마다 새로 생성해야 중복되지 않음
						String location  = le.select("dt").text().replace("기온", "");
						String temp =  le.select("dd").text();
						String status = le.select("dd>a>img").attr("alt");
						ww.put("location", location);
						ww.put("temp", temp);
						ww.put("status", status);
						list.add(ww);
					}
					System.out.println(list);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
	}
	
}
