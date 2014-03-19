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
	final static String TAB = "\t";
	final static String LINE_BREAK = "br2n";
	
	public static void main(String[] args) throws IOException 
	{
		//出力先を作成する
        FileWriter fw = new FileWriter("out.csv", false);
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        int month = 3;
        
        //1. 新宿Motion
        parseHtml.outShinjukuMotion(pw, month);
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
		//8. 下北沢DAISY BAR
		parseHtml.outShimokitaDaisyBar(pw, month);
		//9. 下北沢SHELTER
		parseHtml.outShimokitaShelter(pw, month);
		//10. 下北沢QUE
		parseHtml.outShimokitaQue(pw, month);
		//11. 下北沢251
		parseHtml.outShimokita251(pw, month);
		//12. 下北沢ERA
		parseHtml.outShimokitaERA(pw, month);
		//13. 下北沢GARDEN
		parseHtml.outShimokitaGarden(pw, month);
		//14. 新代田FEVER
		parseHtml.outShindaitaFever(pw, month);
		//15. 東高円寺U.F.O.CLUB
		parseHtml.outKoenjiUFO(pw, month);
		//16. 東高円寺二万電圧
		parseHtml.outKoenjiNiman(pw, month);
		//17. 渋谷O-EAST
		parseHtml.outShibuyaEast(pw, month);
		//18. 渋谷O-WEST
		parseHtml.outShibuyaWest(pw, month);
		//19. 渋谷O-NEST
		parseHtml.outShibuyaNest(pw, month);
		//20. 渋谷O-CREST
		parseHtml.outShibuyaCrest(pw, month);
		//21. 渋谷BURROW
		parseHtml.outShibuyaBurrow(pw, month);
		
		
		
		pw.close();
		
		System.out.println("done");
	}
	
	private void outShinjukuMotion(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://motion-web.jp/html/201403.html").get();
			Elements baseElements = doc.body().select("table[width=450] td[colspan=4]");

			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for( Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(tagName.equals("td"))
					{
						String width = e.attr("width");
						String height = e.attr("height");
						if( width.equals("15%") && height.equals("30"))
						{
							date = str;
							date = date.replace(".", "");
							date = date.substring(0,4);
							
						}
						else if(width.equals("85%"))
						{
							act = str;
							for(Element e2 : e.getAllElements())
							{
								className = e2.className();
								if(className.equals("font_bold"))
								{
									title = this.stringReplaceLineBreakAndRemoveTag(e2);
								}
							}
							act = act.replace(title, "");
							
							title = this.removeStartEndLineBreak(title);
							act = this.removeStartEndLineBreak(act);
						}
						else if(className.equals("linehi_10"))
						{
							String html = e.html();
							if(html.contains("img src"))
							{
								break;
							}
							other = str;
							
							pw.print("1" + TAB);
							pw.print(date + TAB);
							pw.print(title + TAB);
							pw.print(act + TAB);
							pw.println(other);
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("1.Motion Failure::" + e);
		}
	}

	private void outShinjukuMarble(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://marble-web.jp/html/schedule1403.html").get();
			Elements baseElements = doc.body().select("table[width=510] td[colspan=3]");

			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for( Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(tagName.equals("td"))
					{
						String width = e.attr("width");
						String height = e.attr("height");
						if( width.equals("15%") && height.equals("30"))
						{
							date = str;
							date = date.replace(".", "");
							date = date.substring(0,4);
							
						}
						else if(width.equals("85%"))
						{
							act = str;
							for(Element e2 : e.getAllElements())
							{
								className = e2.className();
								if(className.equals("font_bold"))
								{
									title = this.stringReplaceLineBreakAndRemoveTag(e2);
								}
							}
							act = act.replace(title, "");
							
							title = this.removeStartEndLineBreak(title);
							act = this.removeStartEndLineBreak(act);
						}
						else if(className.equals("linehi_10"))
						{
							String html = e.html();
							if(html.contains("img src"))
							{
								break;
							}
							other = str;
							
							pw.print("2" + TAB);
							pw.print(date + TAB);
							pw.print(title + TAB);
							pw.print(act + TAB);
							pw.println(other);
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("2.Marble Failure" + e);
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
						pw.print("3" + TAB);	//ライブハウスNo
						
						String elementStr = e.html();
						//不要な曜日データを削る
						for( String str : weekday)
						{
							elementStr = elementStr.replaceAll(str, "");
						}
						elementStr = elementStr.replace(".", "");	//.を削除
						pw.print(Jsoup.parse(elementStr).text() + TAB);
					}
					else if( tagName.equals("h1") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + TAB);
					}
					else if( className.equals("entrybody") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + TAB);
					}
					else if( className.equals("entryex") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + LINE_BREAK);
						pw.println();
					}
				}
			}
		} catch(Exception e){
			System.out.println("3.Marz Failure" + e);
		}
	}
	
	private void outShinjukuLoft(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.loft-prj.co.jp/schedule/loft/date/2014/03").get();
			Elements baseElements = doc.body().select("tr");
			
			outLoftProject(pw, baseElements, 4, month);
		} catch(Exception e){
			System.out.println("4.Loft Failure" + e);
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
						pw.print("5" + TAB);	//ライブハウスNo
						String str = stringReplaceLineBreakAndRemoveTag(e);
						pw.print(String.format("%02d%02d", month, Integer.valueOf(str)) + TAB);
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
							event = event.replace(title+LINE_BREAK, "");
							event = event.replace(title, "");
						}
						pw.print(title + TAB);
						pw.print(event + TAB);
					}
					else if( className.equals("o-s") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + LINE_BREAK);
					}
					else if( className.equals("price") )
					{
						pw.print(stringReplaceLineBreakAndRemoveTag(e) + TAB);
						pw.println();
					}
				}	
			}
		} catch(Exception e){
			System.out.println("5.GOODMAN Failure" + e);
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
					pw.print("6" + TAB);
					pw.print(date + TAB);
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
					pw.print(title + TAB);
					pw.print(act + TAB);
					pw.println(other);	
				}
					
			}
		} catch(Exception e){
			System.out.println("6.BASEMENT Failure" + e);
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
				
				pw.print("7" + TAB);
				String[] dateSplits = date.split("\\.");
				pw.print(dateSplits[1]+dateSplits[2]+ TAB);
				pw.print(title + TAB);
				act = text.replace(LINE_BREAK,"");
				pw.print(act + TAB);
				pw.println(other + TAB);
			}
		} catch(Exception e){
			System.out.println("7.THREE Failure" + e);
		}
	}
	
	private void outShimokitaDaisyBar(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.daisybar.jp/schedule/1403.html").get();
			Elements baseElements = doc.body().select("tbody tr td");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for( Element element : baseElements)
			{
				String className = element.className();
				if(className.equals("day"))
				{
					date = stringReplaceLineBreakAndRemoveTag(element);
					date = String.format("%02d%02d", month, Integer.valueOf(date));
					pw.print("8" + TAB);
					pw.print(date + TAB);
				}
				else if(className.equals("about"))
				{
					for(Element e : element.select("h3") )
					{
						title = stringReplaceLineBreakAndRemoveTag(e);
						pw.print(removeStartEndLineBreak(title) + TAB);
					}
					String spanText = "";
					int count = 0;
					for(Element e : element.select("li") )
					{
						if( count == 0 )
							act 	= stringReplaceLineBreakAndRemoveTag(e);
						else
							other	= stringReplaceLineBreakAndRemoveTag(e);
						
						for(Element e2 : e.select("span"))
						{
							spanText = stringReplaceLineBreakAndRemoveTag(e2);
						}
						act = act.replace(spanText, "");
						count++;
					}
					other += spanText;
					pw.print(removeStartEndLineBreak(act) + TAB);
					pw.println(removeStartEndLineBreak(other));
				}
			}
		} catch(Exception e){
			System.out.println("8.DAISY BAR Failure" + e);
		}
	}
	
	private void outShimokitaShelter(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.loft-prj.co.jp/schedule/shelter/date/2014/03").get();
			Elements baseElements = doc.body().select("tr");
			
			outLoftProject(pw, baseElements, 9, month);
		} catch(Exception e){
			System.out.println("9.Shelter Failure" + e);
		}
	}
	
	private void outShimokitaQue(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.ukproject.com/que/schedule/thismonth.html").get();	//TODO:今月、来月っていう取り方しか出来ない
			Elements baseElements = doc.body().select("tr tr");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			boolean isExistSchedule = true;
			for( Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String className = e.className();
					if(className.equals("date") || className.equals("dateSat") || className.equals("dateSun"))
					{
						if(!isExistSchedule)
						{
							print("■10::" + date + "::schedule NULL");
						}
						isExistSchedule = false;
						
						title = "";
						act = "";
						other = "";
						
						date = stringReplaceLineBreakAndRemoveTag(e);
						String[] split = date.split(LINE_BREAK);
						date = removeEndSpace(split[0]);
						date = date.replace(".", "/");
						split = date.split("/");
						date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
					}
					else if(className.equals("cell"))
					{
						for( Element e2 :e.select("h3"))
						{
							isExistSchedule = true;
							title = stringReplaceLineBreakAndRemoveTag(e2);
						}
						for( Element e2 :e.select("p"))
						{
							if(e2.className().equals("artists") || e2.className().equals(" artists"))
							{
								act = stringReplaceLineBreakAndRemoveTag(e2);
								act = this.removeStartEndLineBreak(act);
							}
							else
							{
								other = stringReplaceLineBreakAndRemoveTag(e2);
								if(other.equals(""))
								{
									continue;
								}
								String[] split = other.split("単日券チケット発売日");
								other = split[0];
								split = other.split("チケット発売日");
								other = split[0];
								other = this.removeStartEndLineBreak(other);
								title = this.removeStartEndLineBreak(title);
								title = title.replace("\"", "");
								act = this.removeStartEndLineBreak(act);
								
								pw.print("10" + TAB);
								pw.print(date + TAB);
								pw.print(title + TAB);
								pw.print(act + TAB);
								pw.println(other);
							}
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("10.Que Failure" + e);
		}
	}
	
	private void outShimokita251(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.club251.com/sche/1403.html").get();
			Elements baseElements = doc.body().select("div[class=day clearfix]");
			
			for(Element element : baseElements)
			{
				String date = "";
				String title = "";
				String act = "";
				String other = "";
				for(Element e : element.select("h4"))
				{
					date = this.stringReplaceLineBreakAndRemoveTag(e);
					String[] split = date.split("\\.");
					date = split[0];
					split = date.split("/");
					date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
				}
				for(Element e : element.select("h3"))
				{
					title = this.stringReplaceLineBreakAndRemoveTag(e);
					title = this.removeStartEndLineBreak(title);
				}
				for(Element e : element.select("h2"))
				{
					act = this.stringReplaceLineBreakAndRemoveTag(e);
					act = this.removeStartEndLineBreak(act);
				}
				for(Element e : element.select("h5"))
				{
					other = this.stringReplaceLineBreakAndRemoveTag(e);
				}
				
				pw.print("11" + TAB);
				pw.print(date + TAB);
				pw.print(title + TAB);
				pw.print(act + TAB);
				pw.println(other);
			}
		} catch(Exception e){
			System.out.println("11.251 Failure" + e);
		}
	}
	
	private void outShimokitaERA(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://s-era.jp/2014/03/?cat=20").get();
			Elements baseElements = doc.body().select("div[id=schedule] div[class*=schedule-day]");
			
			for(Element element : baseElements)
			{
				String date = "";
				String title = "";
				String act = "";
				String other = "";
				for(Element e : element.select("span[class=d]"))
				{
					date = this.stringReplaceLineBreakAndRemoveTag(e);
					date = this.removeEndSpace(date);
					date = String.format("%02d%02d", month, Integer.valueOf(date));
				}
				for(Element e : element.select("a[class=event-title]"))
				{
					title = this.stringReplaceLineBreakAndRemoveTag(e);
					title = this.removeStartEndLineBreak(title);
				}
				for(Element e : element.select("div[class=schedule-announce]"))
				{
					act = this.stringReplaceLineBreakAndRemoveTag(e);
					act = act.replace(LINE_BREAK, "");	//スラッシュが入っているから要らない
					act = this.removeStartEndLineBreak(act);
				}
				for(Element e : element.select("div[class=schedule-note]"))
				{
					other = this.stringReplaceLineBreakAndRemoveTag(e);
					String[] split = other.split("予約");
					other = split[0];
					other = this.removeStartEndLineBreak(other);
					other = other.replace("TICKET", LINE_BREAK + "TICKET");
				}
				
				pw.print("12" + TAB);
				pw.print(date + TAB);
				pw.print(title + TAB);
				pw.print(act + TAB);
				pw.println(other);
			}
		} catch(Exception e){
			System.out.println("12.ERA Failure" + e);
		}
	}
	
	private void outShimokitaGarden(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.gar-den.in/pc/plist.php?m=201403").get();
			Elements baseElements = doc.body().select("div[class=txt_box_ms] div");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				String className = element.className();
				if(className.equals("box_left"))
				{
					for(Element e : element.getAllElements())
					{
						className = e.className();
						if(className.equals("day"))
						{
							other = "";
							
							date = this.stringReplaceLineBreakAndRemoveTag(e);
							date = String.format("%02d", month) + date;
						}
						else if(className.equals("m_black") )
						{
							other += this.stringReplaceLineBreakAndRemoveTag(e);
						}
						else if(className.equals("m_black_txt") )
						{
							other += this.stringReplaceLineBreakAndRemoveTag(e) + LINE_BREAK;
						}
						
					}
				}
				else if(className.equals("box_right"))
				{
					for(Element e : element.select("div[class=title]"))
					{
						title = this.stringReplaceLineBreakAndRemoveTag(e);
					}
						
					Element e = element.select("p").first();
					act = this.stringReplaceLineBreakAndRemoveTag(e);
					
					pw.print("13" + TAB);
					pw.print(date + TAB);
					pw.print(title + TAB);
					pw.print(act + TAB);
					pw.println(other);
				}
			}
		} catch(Exception e){
			System.out.println("13.Garden Failure" + e);
		}
	}
	
	private void outShindaitaFever(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.fever-popo.com/schedule/2014/03/").get();
			Elements baseElements = doc.body().select("div[id*=entry-][class=entry-asset asset hentry]");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					//print(tagName);
					
					if(tagName.equals("h2"))
					{
						other = "";
						String str = this.stringReplaceLineBreakAndRemoveTag(e);
						date = str.substring(3, 8);
						date = date.replace(".", "");
						title = str.substring(15,str.length());
						title = this.removeStartEndLineBreak(title);
					}
					else if(tagName.equals("h3"))
					{
						act = this.stringReplaceLineBreakAndRemoveTag(e);
						act = this.removeStartEndLineBreak(act);
					}
					else if(tagName.equals("div") && className.equals(""))
					{
						String str = this.stringReplaceLineBreakAndRemoveTag(e);
						if(str.contains("OPEN") || str.contains("ADV"))
						{
							other += str;
						}
					}
				}
				
				pw.print("14" + TAB);
				pw.print(date + TAB);
				pw.print(title + TAB);
				pw.print(act + TAB);
				pw.println(other);
			}
		} catch(Exception e){
			System.out.println("14.Fever Failure" + e);
		}
	}
	
	private void outKoenjiUFO(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.ufoclub.jp/schedule/").get();	//TODO: 
			Elements baseElements = doc.body().select("tbody tr");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					if(className.equals("day"))
					{
						other = "";
						act = "";
						date = this.stringReplaceLineBreakAndRemoveTag(e);
						date = String.format("%02d%02d", month,Integer.valueOf(date));
					}
					else if(className.equals("td_open") || className.equals("td_charge"))
					{
						other += this.stringReplaceLineBreakAndRemoveTag(e) + LINE_BREAK;
					}
					else if(tagName.equals("strong"))
					{
						title = this.stringReplaceLineBreakAndRemoveTag(e);
					}
					else if(tagName.equals("p"))
					{
						act += this.stringReplaceLineBreakAndRemoveTag(e);
						act = act.replace("/LIVE; ", "");
						act = act.replace(",", " /");
						
					}
				}
				if(date=="")continue;
				other = this.removeStartEndLineBreak(other);
				pw.print("15" + TAB);
				pw.print(date + TAB);
				pw.print(title + TAB);
				pw.print(act + TAB);
				pw.println(other);
			}
		} catch(Exception e){
			System.out.println("15.UFO Failure" + e);
		}
	}
	
	private void outKoenjiNiman(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.den-atsu.com/?schedule=2014-3-schedule").get();
			Elements baseElements = doc.body().select("div[class=hpb-entry-content] p");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					if(tagName.equals("p"))
					{
						String html = e.html();
						String str = this.stringReplaceLineBreakAndRemoveTag(e);
						if(str.substring(0, 1).equals("■"))
						{
							date 	= "";
							title 	= "";
							act	 	= "";
							other	= "";
							
							date = str.substring(1, str.length());
							String[] split = date.split("\\(");
							date = split[0];
							split = date.split("/");
							date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1])); 
						}
						else if(html.contains("<span style"))
						{
							if(html.contains("#ff0000"))
							{
								title += str + LINE_BREAK;
							}
						}
						else
						{
							if( (str.contains("Open.") || str.contains("open.") ) && (str.contains("Adv.") || str.contains("adv.")) )
							{
								if( other.equals("") )
								{
									other = str + LINE_BREAK;
									
									title 	= this.removeStartEndLineBreak(title);
									act 	= this.removeStartEndLineBreak(act);
									other 	= this.removeStartEndLineBreak(other);
									pw.print("16" + TAB);
									pw.print(date + TAB);
									pw.print(title + TAB);
									pw.print(act + TAB);
									pw.println(other);
								}
							}
							else
							{
								act += str + LINE_BREAK;
							}
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("16.20000 Failure" + e);
		}
	}
	
	private void outShibuyaEast(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/east/2014/03").get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 17, month);
		} catch(Exception e){
			System.out.println("17.EAST FAILURE" + e);
		}
	}
	private void outShibuyaWest(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/west/2014/03").get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 18, month);
		} catch(Exception e){
			System.out.println("18.WEST FAILURE" + e);
		}
	}
	private void outShibuyaNest(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/nest/2014/03").get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 19, month);
		} catch(Exception e){
			System.out.println("19.NEST FAILURE" + e);
		}
	}
	private void outShibuyaCrest(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/crest/2014/03").get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 20, month);
		} catch(Exception e){
			System.out.println("20.CREST FAILURE" + e);
		}
	}
	private void outShibuyaBurrow(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/burrow/2014/03").get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 20, month);
		} catch(Exception e){
			System.out.println("21.BURROW FAILURE" + e);
		}
	}
	
	private void outShibuyaChelseaHotel(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.chelseahotel.jp/1403.html").get();
			Elements baseElements = doc.body().select("");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
				}
			}
		}catch(Exception e){
			System.out.println("22.ChelseaHotel Failure" + e);
		}
	}
	
	private void outShibuyaKinoto(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("").get();
			Elements baseElements = doc.body().select("");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
				}
			}
		}catch(Exception e){
			System.out.println("22.ChelseaHotel Failure" + e);
		}
	}
	
//	private void outShibuyaChelseaHotel(PrintWriter pw, int month)
//	{
//		try{
//			Document doc = Jsoup.connect("").get();
//			Elements baseElements = doc.body().select("");
//			
//			String date = "";
//			String title = "";
//			String act = "";
//			String other = "";
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String tagName = e.tagName();
//					String className = e.className();
//				}
//			}
//		}catch(Exception e){
//			System.out.println("22.ChelseaHotel Failure" + e);
//		}
//	}
//	
//	private void outShibuyaChelseaHotel(PrintWriter pw, int month)
//	{
//		try{
//			Document doc = Jsoup.connect("").get();
//			Elements baseElements = doc.body().select("");
//			
//			String date = "";
//			String title = "";
//			String act = "";
//			String other = "";
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String tagName = e.tagName();
//					String className = e.className();
//				}
//			}
//		}catch(Exception e){
//			System.out.println("22.ChelseaHotel Failure" + e);
//		}
//	}
//	
//	private void outShibuyaChelseaHotel(PrintWriter pw, int month)
//	{
//		try{
//			Document doc = Jsoup.connect("").get();
//			Elements baseElements = doc.body().select("");
//			
//			String date = "";
//			String title = "";
//			String act = "";
//			String other = "";
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String tagName = e.tagName();
//					String className = e.className();
//				}
//			}
//		}catch(Exception e){
//			System.out.println("22.ChelseaHotel Failure" + e);
//		}
//	}
//	
//	private void outShibuyaChelseaHotel(PrintWriter pw, int month)
//	{
//		try{
//			Document doc = Jsoup.connect("").get();
//			Elements baseElements = doc.body().select("");
//			
//			String date = "";
//			String title = "";
//			String act = "";
//			String other = "";
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String tagName = e.tagName();
//					String className = e.className();
//				}
//			}
//		}catch(Exception e){
//			System.out.println("22.ChelseaHotel Failure" + e);
//		}
//	}
	
	private void outLoftProject(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		String date = "";
		String title = "";
		String act = "";
		String other = "";
		for( Element element : baseElements)
		{
			for(Element e : element.getAllElements())
			{
				String className = e.className();
				if(className.equals("day"))
				{
					date = stringReplaceLineBreakAndRemoveTag(e);
					String[] split = date.split(LINE_BREAK);
					date = String.format("%02d", month) + split[0];
					
				}
				else if(className.equals("event clearfix program1") || className.equals("event clearfix program2") || className.equals("event clearfix program3"))
				{
					for( Element e2 : e.select("h3") )
					{
						title = stringReplaceLineBreakAndRemoveTag(e2);
						title = removeStartEndLineBreak(title);
						
						pw.print(liveHouseNo + TAB);
						pw.print(date + TAB);
						pw.print(title + TAB);
					}
					for( Element e2 : e.select("p[class=month_content]") )
					{
						act = stringReplaceLineBreakAndRemoveTag(e2);
						act = removeStartEndLineBreak(act);
						pw.print(act + TAB);
						if( act.contains("..."))
						{
							print("■■" + liveHouseNo + "::" + date + "::act Failure");
						}
					}
					for( Element e2 : e.select("p[class=time_text]") )
					{
						other = stringReplaceLineBreakAndRemoveTag(e2) + LINE_BREAK;
					}
					for( Element e2 : e.select("p[class=ticket]") )
					{
						other += stringReplaceLineBreakAndRemoveTag(e2);
						String[] split = other.split("【");
						other = split[0];
						other = removeStartEndLineBreak(other);
						pw.println(other);
						if( other.contains("..."))
						{
							print("■■" + liveHouseNo + "::" + date + "::other Failure");
						}
					}
				}
			}
		}
	}
	
	private void outTsutayaOGroup(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		String date = "";
		String title = "";
		String act = "";
		String other = "";
		for(Element element : baseElements)
		{
			for(Element e : element.getAllElements())
			{
				String className = e.className();
				String tagName = e.tagName();
				if(className.equals("post-date"))
				{
					title = "";
					act = "";
					other = "";
					
					date = this.stringReplaceLineBreakAndRemoveTag(e);
					date = date.substring(5, 10);
					date = date.replace(".", "");
				}
				else if(tagName.equals("h3"))
				{
					title += this.stringReplaceLineBreakAndRemoveTag(e);
				}
				else if(className.equals("lineup"))
				{
					for(Element e2 : e.getAllElements())
					{
						tagName = e2.tagName();
						if( tagName.equals("dd"))
						{
							act += this.stringReplaceLineBreakAndRemoveTag(e2);
							
							if(act.contains("こちら") || act.contains("コチラ") || act.contains("http://"))
							{
								print("■■FAILURE::" + liveHouseNo + "::" + date + "::リンクあり");
							}
						}
					}
				}
				else if(className.equals("information"))
				{
					for(Element e2 : e.getAllElements())
					{
						tagName = e2.tagName();
						if( tagName.equals("dd"))
						{
							other += this.stringReplaceLineBreakAndRemoveTag(e2);
						}
						
						if(other.contains("ADV:") || other.contains("DOOR:"))
						{
							title = this.removeStartEndLineBreak(title);
							act = this.removeStartEndLineBreak(act);
							pw.print(liveHouseNo + TAB);
							pw.print(date + TAB);
							pw.print(title + TAB);
							pw.print(act + TAB);
							pw.println(other);
							
							title = "";
							act = "";
							other = "";
						}
					}
					
				}
			}
		}
	}
	
	private String stringReplaceLineBreakAndRemoveTag(Element e)
	{
		String html = e.html();
		String text = Jsoup.parse(html.replaceAll("(?i)<br[^>]*>", LINE_BREAK)).text();
		//text = text.replaceAll("br2n", "\n");
		return text;
	}
	
	private String removeStartEndLineBreak(String s)
	{
		do {
			if(s.startsWith("br2n  "))
			{
				s = s.substring(6, s.length());
			}
			else if(s.startsWith("br2n "))
			{
				s = s.substring(5, s.length());
			}
			else if(s.startsWith("br2n"))
			{
				s = s.substring(4, s.length());
			}
			
			if(s.endsWith("br2n"))
			{
				s = s.substring(0, s.length()-4);
			}
			else if(s.endsWith("br2n "))
			{
				s = s.substring(0, s.length()-5);
			}
			else if(s.endsWith("br2n  "))
			{
				s = s.substring(0, s.length()-6);
			}
			else
			{
				return s;
			}
		}while(true);
	}
	private String removeEndSpace(String s)
	{
		do {
			if(s.endsWith(" "))
			{
				s = s.substring(0, s.length()-1);
			}
			else
			{
				return s;
			}
		}while(true);
	}
	
	
	private void print(String s)
	{
		System.out.println(s);
	}
}
