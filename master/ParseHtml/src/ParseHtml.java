import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class ParseHtml
{
	static ParseHtml parseHtml = new ParseHtml();
	static String[] lineBreakCode = {"< br/>","< br/ >", "<br/>", "<br />", "< BR/>", "< BR/ >", "<BR/>","<BR />"};
	
	public static void main(String[] args) throws IOException 
	{
		//出力先を作成する
        FileWriter fw = new FileWriter("out.csv", false);
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        int month = 3;
        
		//2. 新宿Marble
		parseHtml.outShinjukuMarble(pw,month);
		//3. 新宿Marz
		parseHtml.outShinjukuMarz(pw,month);
		//4. 新宿LOFT
		parseHtml.outShinjukuLoft(pw,month);
		//5. 秋葉原GOODMAN
		parseHtml.outAkihabaraGoodman(pw,month);
		
		pw.close();
	}
	
	private void outShinjukuMarble(PrintWriter pw, int month) 
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
						pw.println();
						pw.print(date + "\t");
						pw.print(title + "\t");
					}
					else if( className.equals("linehi_10") )
					{
						if( isAct )
						{
							pw.print(stringReplaceLineBreakAndRemoveTag(element) + "\t");
							isAct = false;
						}
						else
						{
							pw.print(stringReplaceLineBreakAndRemoveTag(element) + "\t");
						}
					}
				}
			}
		} catch(Exception e){
			
		}
		pw.println();
	}
	
	private void outShinjukuMarz(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.marz.jp/schedule/2014/03/").get();
			Elements baseElements = doc.body().select("article");
			
			final String weekday[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

			for( Element element : baseElements)
			{
				for( Element e : element.getAllElements())
				{
					String className = e.className();
					System.out.println(className);
					if( className.equals("img") )
					{						
						String elementStr = e.html();
						//不要な曜日データを削る
						for( String str : weekday)
						{
							elementStr = elementStr.replaceAll(str, "");
						}
						pw.print(Jsoup.parse(elementStr).text() + "\t");
					}
					else if( className.equals("entrybody") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "\t");
					}
					else if( className.equals("entryex") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "\t");
						pw.println();
					}
				}
			}
		} catch(Exception e){
			
		}
	}
	
	private void outShinjukuLoft(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.loft-prj.co.jp/schedule/loft/date/2014/03").get();
			Elements baseElements = doc.body().select("tr tr");
			
			//URLを取得
			//loft/date/2014/03/01などにアクセスし、内容を引っ張って来る
			
			System.out.println(baseElements);
			for( Element element : baseElements)
			{
				for( Element e : element.getAllElements())
				{
					//System.out.println(e);
					String className = e.className();
					if( className.equals("day") )
					{						
					//	System.out.println(stringReplaceLineBreakAndRemoveTag(e));
					}
					else if( className.equals("month_content") )
					{
						System.out.println(stringReplaceLineBreakAndRemoveTag(e));
					}
					else if( className.equals("time_text") || className.equals("ticket") )
					{
						System.out.println(stringReplaceLineBreakAndRemoveTag(e));
					}
				}
			}
		} catch(Exception e){
			
		}
	}
	
	private void outAkihabaraGoodman(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://clubgoodman.com/schedule/schedule14-3.html").get();
			Elements baseElements = doc.body().select("table tbody tr[class!=head_line]");// getElementsByTag("tr");
			
			for( Element element : baseElements)
			{
				for( Element e : element.getAllElements())
				{
					String className = e.className();
					if( className.equals("date") || 
						className.equals("saturday_date") || 
						className.equals("sunday_date") )
						
					{						
						String str = stringReplaceLineBreakAndRemoveTag(e);
						pw.print(String.format("%02d%02d", month, Integer.valueOf(str)) + "\t");
					}
					else if( className.equals("event") ||
							className.equals("o-s") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "\t");
					}
					else if( className.equals("price") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "\t");
						pw.println();
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
		//text = text.replaceAll("br2n", "\n");
		return text;
	}
}
