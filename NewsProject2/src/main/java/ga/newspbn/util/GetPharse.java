/**
 * 
 */
package ga.newspbn.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class GetPharse {
	
	private Document doc = null;
	
	public void getSuyongso() {

			String addrs[] = {
//					"https://www.suyongso.com/anidong/19056694",
//					"https://www.suyongso.com/anidong/19056781",
//					"https://www.suyongso.com/anidong/19057073",
//					"https://www.suyongso.com/anidong/19057165",
//					"https://www.suyongso.com/anidong/19057246",
//					"https://www.suyongso.com/anidong/19058302",
//					"https://www.suyongso.com/anidong/19058352",
//					"https://www.suyongso.com/anidong/19058412",
//					"https://www.suyongso.com/anidong/19058469",
//					"https://www.suyongso.com/anidong/19059415",
//					"https://www.suyongso.com/anidong/19059461",
					"https://www.suyongso.com/anidong/19059543",
					"https://www.suyongso.com/anidong/19059829",
					"https://www.suyongso.com/anidong/19059665"
			};
			List<String> im = new ArrayList<String>();
			URL url = null;
			for(String addr : addrs) {
				try {
					doc =  Jsoup.connect(addr).userAgent("Mozilla/5.0").timeout(10000).get();
					String title = doc.select("div.rd.rd_nav_style2.clear > div.rd_hd.clear > div.board.clear > div.top_area.ngeb > h1 > a").text();
					Elements content = doc.select("div.rd.rd_nav_style2.clear > div.rd_body.clear");
					Elements imgs = doc.select("div.rd.rd_nav_style2.clear > div.rd_body.clear > article > div > img");
					for(Element img : imgs) {	
						String imgold = img.attr("src").replace("/www.suyongso.com/", "").replace("./files", "/files");
						String imgsrc = "https://www.suyongso.com" + imgold;
						url = new URL(imgsrc);
						InputStream is = url.openStream();
						OutputStream os = new FileOutputStream(new File("C://Users//KEN//Desktop//짤"+ "//" + imgsrc.substring(imgsrc.lastIndexOf("/")+1)));
						int data;
						byte buff[] = new byte[1024];
						while(true) {
							data = is.read(buff);
							if(data == -1) {
								break;
							}
							os.write(buff, 0, data);
						}
						is.close();
						os.close();
						im.add(imgsrc);
						System.out.println(imgsrc.substring(imgsrc.lastIndexOf(".")+1));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
}

		

