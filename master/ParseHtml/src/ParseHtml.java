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
        
        //1. 新宿Motion
        //parseHtml.outShinjukuMotion(pw, month);
		//2. 新宿Marble
		parseHtml.outShinjukuMarble(pw,month);
		//3. 新宿Marz
		parseHtml.outShinjukuMarz(pw,month);
		//4. 新宿LOFT
		parseHtml.outShinjukuLoft(pw,month);
		//5. 秋葉原GOODMAN
		parseHtml.outAkihabaraGoodman(pw,month);
		//6. 下北沢BASEMENT BAR
		parseHtml.outBasementBar(pw, month);
		//7. 下北沢THREE
		parseHtml.outShimokitaThree(pw, month);
		
		pw.close();
		
		System.out.println("done");
	}
	
	private void outShinjukuMotion(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://motion-web.jp/html/201403.html").get();
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
						
						String[] splits = elementStr.split(" ");	//motionはスペースで切り分けられる
						String date = splits[0];
						String[] dateSplits = date.split("/");
						date = String.format("%02d%02d", Integer.valueOf(dateSplits[0]), Integer.valueOf(dateSplits[1])); //MMdd形式の文字列に変換
						String title = "";
						for( int i = 1; i < splits.length;i++ )
						{
							title += splits[i];
						}
						
						pw.println();
						pw.print("1" + "\t");	//ライブハウスNo
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
							pw.print(stringReplaceLineBreakAndRemoveTag(element) + "br2n");
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("1.Motion Failure");
		}
		pw.println();
	}
	
	private void outShinjukuMarble(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://marble-web.jp/html/schedule1403.html").get();
			Elements elements = doc.body().select("table[width=450]");
			
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
						String[] dateSplits = date.split("/");
						date = String.format("%02d%02d", Integer.valueOf(dateSplits[0]), Integer.valueOf(dateSplits[1])); //MMdd形式の文字列に変換
						String title = "";
						for( int i = 1; i < splits.length;i++ )
						{
							title += splits[i];
						}
						
						System.out.println(date);
						System.out.println(title);
						
						pw.println();
						pw.print("2" + "\t");	//ライブハウスNo
						pw.print(date + "\t");
						pw.print(title + "\t");
					}
					else if( className.equals("linehi_10") )
					{
						if( isAct )
						{
							System.out.println(stringReplaceLineBreakAndRemoveTag(element));
							pw.print(stringReplaceLineBreakAndRemoveTag(element) + "\t");
							isAct = false;
						}
						else
						{
							System.out.println(stringReplaceLineBreakAndRemoveTag(element));
							pw.print(stringReplaceLineBreakAndRemoveTag(element) + "br2n");
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("2.Marble Failure");
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
					String tagName = e.tagName();
					String className = e.className();
					
					if( className.equals("img") )
					{					
						pw.print("3" + "\t");	//ライブハウスNo
						
						String elementStr = e.html();
						//不要な曜日データを削る
						for( String str : weekday)
						{
							elementStr = elementStr.replaceAll(str, "");
						}
						elementStr = elementStr.replace(".", "");	//.を削除
						pw.print(Jsoup.parse(elementStr).text() + "\t");
					}
					else if( tagName.equals("h1") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "\t");
					}
					else if( className.equals("entrybody") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "\t");
					}
					else if( className.equals("entryex") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "br2n");
						pw.println();
					}
				}
			}
		} catch(Exception e){
			System.out.println("3.Marz Failure");
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
			System.out.println("4.Loft Failure");
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
						pw.print("5" + "\t");	//ライブハウスNo
						String str = stringReplaceLineBreakAndRemoveTag(e);
						pw.print(String.format("%02d%02d", month, Integer.valueOf(str)) + "\t");
					}
					else if( className.equals("event") )
					{
						String event = stringReplaceLineBreakAndRemoveTag(e);
						String title = "";
						Elements eventTitle = e.select("span[class=event-title]");
						if( eventTitle != null && eventTitle.size() > 0 )
						{
							title = stringReplaceLineBreakAndRemoveTag(eventTitle.first());
							event = event.replace(title+"br2n ", "");
							event = event.replace(title+" br2n", "");
							event = event.replace(title+"br2n", "");
							event = event.replace(title, "");
						}
						pw.print(title + "\t");
						pw.print(event + "\t");
					}
					else if( className.equals("o-s") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "br2n");
					}
					else if( className.equals("price") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + "\t");
						pw.println();
					}
				}	
			}
		} catch(Exception e){
			System.out.println("5.GOODMAN Failure");
		}
	}
	
	private void outBasementBar(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.toos.co.jp/basementbar/schedule/bb2014_03.html").get();
			Elements baseElements = doc.body().select("Table[width=624] tr[valign=middle] td");
			
			int count = 0;
			for( Element element : baseElements)
			{
				count++;
				if( count == 1 )
				{
					String date = String.format("%02d%02d", 3, Integer.valueOf(element.text()));
					pw.print("6" + "\t");
					pw.print(date + "\t");
				}
				else if(count == 2)
				{
					//do nothing
				}
				else
				{
					count = 0;
					
					String title = "";
					String act = "";
					String other = "";
					//タイトル
					for( Element e : element.getElementsByTag("font") )
					{
						if(e.text().equals(""))
						{
							continue;
						}
						String color = e.attr("color");
						
						if( color.contains("#000099") )
						{
							title += e.text();
						}
						else if( color.contains("#006699") )
						{
							other += e.text();
						}
						else
						{
							act += e.text();
						}
					}
					pw.print(title + "\t");
					pw.print(act + "\t");
					pw.println(other);	
				}
					
			}
		} catch(Exception e){
			System.out.println("6.BASEMENT Failure");
		}
	}
	
	private void outShimokitaThree(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.toos.co.jp/3/3_schedule.html").get(); //TODO: 当月のURLが違う
			Elements baseElements = doc.body().select("table[width=625][border=0][cellspacing=2][cellpadding=1] tr");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for( Element element : baseElements)
			{
				String text = stringReplaceLineBreakAndRemoveTag(element);
				if( text.equals("") )
				{
					continue;
				}
				for(Element e : element.select("h1") )
				{
					date = stringReplaceLineBreakAndRemoveTag(e);
					text = text.replace(date, "");
				}
				for(Element e : element.select("h2") )
				{
					title = stringReplaceLineBreakAndRemoveTag(e);
					text = text.replace(title, "");
				}
				for(Element e : element.select("span[class=style9]") )
				{
					if( !e.text().contains("open") && !e.text().contains("start") )
					{
						continue;
					}
					other = stringReplaceLineBreakAndRemoveTag(e);
					text = text.replace(other, "");
				}
				
				pw.print("7" + "\t");
				String[] dateSplits = date.split("\\.");
				pw.print(dateSplits[1]+dateSplits[2]+ "\t");
				pw.print(title + "\t");
				act = text.replace("br2n","");
				pw.print(act + "\t");
				pw.println(other + "\t");
			}
		} catch(Exception e){
			System.out.println("7.THREE Failure");
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
