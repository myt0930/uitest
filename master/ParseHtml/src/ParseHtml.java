import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class ParseHtml
{
	static ParseHtml parseHtml = new ParseHtml();
	static String[] lineBreakCode = {"< br/>","< br/ >", "<br/>", "<br />", "< BR/>", "< BR/ >", "<BR/>","<BR />"};
	
	public static void main(String[] args) throws IOException 
	{
		//2. 新宿Marble
		//parseHtml.outShinjukuMarble();
		//3. 新宿Marz
		parseHtml.outShinjukuMarz();
	}
	
	private void outShinjukuMarble() 
	{
		try{
			Document doc = Jsoup.connect("http://marble-web.jp/html/schedule1403.html").get();
			Elements ele = doc.body().select("tbody td tr td");
			
			final String weekday[] = {"sun.", "mon.", "tue.", "wed.", "thu.", "fri.", "sat."};
			boolean isAct = true;
			for( org.jsoup.nodes.Element element : ele)
			{
				if(element.ownText().length() > 0)
				{		
					String className = element.className();
					if( className.equals("font_bold") )
					{
						isAct = true;
						
						String elementStr = element.text();
						//不要な曜日データを削る
						for( String str : weekday)
						{
							elementStr = elementStr.replaceAll(str, "");
						}
						
						String[] splits = elementStr.split(" ");	//marbleはスペース二つで切り分けられる
						String date = splits[0];
						String title = "";
						for( int i = 1; i < splits.length;i++ )
						{
							title += splits[i];
						}
						System.out.println(date);
						System.out.println(title);
					}
					else if( className.equals("linehi_10") )
					{
						if( isAct )
						{
							String elementStr = element.html();
							for( String str : lineBreakCode )
							{
								elementStr = elementStr.replaceAll(str, "\n");
							}
							System.out.println(elementStr);
							
							isAct = false;
						}
						else
						{
							String elementStr = element.text();
							elementStr = elementStr.replaceAll("■", "\n■");
							
							System.out.println(elementStr);
						}
					}
				}
			}
		} catch(Exception e){
			
		}
	}
	
	private void outShinjukuMarz() 
	{
		try{
			Document doc = Jsoup.connect("http://www.marz.jp/schedule/2014/03/").get();
			Elements ele = doc.body().select("article");
			
			final String weekday[] = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};
			boolean isAct = true;
			System.out.println(ele);
			for( org.jsoup.nodes.Element element : ele)
			{
				if(element.ownText().length() > 0)
				{		
					String className = element.className();
					if( className.equals("img") )
					{
						isAct = true;
						
						String elementStr = element.text();
						//不要な曜日データを削る
						for( String str : weekday)
						{
							elementStr = elementStr.replaceAll(str, "");
						}
						System.out.println(elementStr);
					}
					else if( className.equals("entrybody") )
					{
						String elementStr = element.html();
						System.out.println(elementStr);
					}
					else if( className.equals("entryex") )
					{
						String elementStr = element.html();
						System.out.println(elementStr);
					}
				}
			}
		} catch(Exception e){
			
		}
	}
}
