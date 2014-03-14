import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class ParseHtml
{
	static ParseHtml parseHtml = new ParseHtml();
	static String[] lineBreakCode = {"< br/>","< br/ >", "<br/>", "<br />", "< BR/>", "< BR/ >", "<BR/>","<BR />"};
	
	public static void main(String[] args) throws IOException 
	{
		//2. 新宿Marble
		parseHtml.outShinjukuMarble();
		//3. 新宿Marz
		parseHtml.outShinjukuMarz();
	}
	
	private void outShinjukuMarble() 
	{
		try{
			Document doc = Jsoup.connect("http://marble-web.jp/html/schedule1403.html").get();
			Elements elements = doc.body().select("tbody td tr td");
			
			final String weekday[] = {"sun.", "mon.", "tue.", "wed.", "thu.", "fri.", "sat."};
			boolean isAct = true;
			for( Element element : elements)
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
						
						String[] splits = elementStr.split(" ");	//marbleはスペースで切り分けられる
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
							System.out.println(stringReplaceLineBreakAndRemoveTag(element));
							System.out.println("----------------------");
							isAct = false;
						}
						else
						{
							System.out.println(stringReplaceLineBreakAndRemoveTag(element));
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
			Elements baseElements = doc.body().select("article");
			
			final String weekday[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

			for( org.jsoup.nodes.Element element : baseElements)
			{
				for( org.jsoup.nodes.Element e : element.getAllElements())
				{
					String className = e.className();
					if( className.equals("img") )
					{						
						String elementStr = e.html();
						//不要な曜日データを削る
						for( String str : weekday)
						{
							elementStr = elementStr.replaceAll(str, "");
						}
						System.out.println(Jsoup.parse(elementStr).text());
					}
					else if( className.equals("entrybody") )
					{
						System.out.println(stringReplaceLineBreakAndRemoveTag(e));
					}
					else if( className.equals("entryex") )
					{
						System.out.println(stringReplaceLineBreakAndRemoveTag(e));
					}
				}
			}
		} catch(Exception e){
			
		}
	}
	
	private String stringReplaceLineBreakAndRemoveTag(Element e)
	{
		String html = e.html();
		String text = Jsoup.parse(html.replaceAll("(?i)<br[^>]*>", "br2n")).text();
		text = text.replaceAll("br2n", "\n");
		return text;
	}
}
