import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

//TODO: 出来たらやること
//URL確認

//ORG
//RUIDO project
//渋谷QUATTRO　価格がとれていない

public class ParseHtml
{
	static int debugFlag = 0;
	
	static ParseHtml parseHtml = new ParseHtml();
	static String[] lineBreakCode = {"< br/>","< br/ >", "<br/>", "<br />", "< BR/>", "< BR/ >", "<BR/>","<BR />"};
	final static String TAB = "\t";
	final static String LINE_BREAK = "br2n";
	
	public static void main(String[] args) throws IOException 
	{
		//出力先を作成する
        FileWriter fw = new FileWriter("out.csv", false);
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        int month = 4;

        
        int count = 1;
        /*
        //1. 新宿Motion
        parseHtml.print(String.valueOf(count++));
		parseHtml.outShinjukuMotion(pw, month);
		//2. 新宿Marble
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShinjukuMarble(pw,month);
		//3. 新宿Marz
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShinjukuMarz(pw,month);
		//4. 新宿LOFT
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShinjukuLoft(pw,month);
		//5. 秋葉原GOODMAN
		parseHtml.print(String.valueOf(count++));
		parseHtml.outAkihabaraGoodman(pw,month);
		//6. 下北沢BASEMENT BAR
		parseHtml.print(String.valueOf(count++));
		parseHtml.outBasementBar(pw, month);
		//7. 下北沢THREE
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShimokitaThree(pw, month);
		//8. 下北沢DAISY BAR
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShimokitaDaisyBar(pw, month);
		//9. 下北沢SHELTER
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShimokitaShelter(pw, month);
		//10. 下北沢QUE
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShimokitaQue(pw, month);
		//11. 下北沢251
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShimokita251(pw, month);
		//12. 下北沢ERA
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShimokitaERA(pw, month);
		//13. 下北沢GARDEN
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShimokitaGarden(pw, month);
		//14. 新代田FEVER
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShindaitaFever(pw, month);
		//15. 東高円寺U.F.O.CLUB
		parseHtml.print(String.valueOf(count++));
		parseHtml.outKoenjiUFO(pw, month);
		//16. 東高円寺二万電圧
		parseHtml.print(String.valueOf(count++));
		parseHtml.outKoenjiNiman(pw, month);
		//17. 渋谷O-EAST
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaEast(pw, month);
		//18. 渋谷O-WEST
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaWest(pw, month);
		//19. 渋谷O-NEST
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaNest(pw, month);
		//20. 渋谷O-CREST
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaCrest(pw, month);
		//21. 渋谷BURROW
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaBurrow(pw, month);
		//22. 渋谷CHELSEA HOTEL
		parseHtml.print(String.valueOf(count++));
		
		//23. 渋谷乙
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaKinoto(pw, month);
		//24. 渋谷LUSH
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaLush(pw, month);
		//25. 渋谷CLUB QUATTRO
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaQuattro(pw, month);
		//26. 渋谷WWW
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaWWW(pw, month);
		//27. 渋谷7thFLOOR
		parseHtml.print(String.valueOf(count++));
		        
		//28. 渋谷DUO
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaDuo(pw, month);
		//29. 代官山UNIT
		parseHtml.print(String.valueOf(count++));
		parseHtml.outDaikanyamaUnit(pw, month);
		//30. 原宿ASTRO HALL
		parseHtml.print(String.valueOf(count++));
		parseHtml.outHarajukuAstroHall(pw, month);
		//31. 恵比寿LIQUIDROOM
		parseHtml.print(String.valueOf(count++));
		parseHtml.outEbisuLiquidroom(pw, month);
		//32. 池袋music org
		parseHtml.print(String.valueOf(count++));
		parseHtml.outIkebukuroOrg(pw, month);
		
		//33. 池袋RUIDO K3
		parseHtml.print(String.valueOf(count++));
		parseHtml.outIkebukuroRuidoK3(pw, month);
		//34. 渋谷RUIDO K2
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShibuyaRuidoK2(pw, month);
		//35. 新宿RUIDO K4
		parseHtml.print(String.valueOf(count++));
		parseHtml.outShinjukuRuidoK4(pw, month);
        //36. 赤坂BLITZ
		parseHtml.print(String.valueOf(count++));
        parseHtml.outAkasakaBlitz(pw, month);
        //37. ZEPP TOKYO
    	parseHtml.print(String.valueOf(count++));
        parseHtml.outZeppTokyo(pw, month);
        //38. ZEPP DIVERCITY
        parseHtml.print(String.valueOf(count++));
        parseHtml.outZeppDiverCity(pw, month);
        //39. 新宿RedCloth
        parseHtml.print(String.valueOf(count++));
        parseHtml.outShinjukuRedCloth(pw, month);
        
        
        
        //42. 新木場STUDIO COAST
        //parseHtml.print(String.valueOf(count++));
        parseHtml.outShinkibaStudioCoast(pw, month);
        
        //46. 高円寺HIGH
        //parseHtml.print(String.valueOf(count++));
        parseHtml.outKoenjiHigh(pw, month);
 */
		//47. 小岩bushbash
		//parseHtml.print(String.valueOf(count++));
		parseHtml.outKoiwaBushbash(pw, month);
        
		pw.close();
		
		System.out.println("done");
	}
	
	private void outShinjukuMotion(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://motion-web.jp/html/2014" + String.format("%02d", month) + ".html").get();
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
							other = other.replace(" ■", "■");
							other = other.replace(" ★", "★");
							
							pw.print("1" + TAB);
							outDate(pw, date);
							outTitle(pw, title);
							outAct(pw, act, date);
							outOther(pw, other, date);
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
			Document doc = Jsoup.connect("http://marble-web.jp/html/schedule14" + String.format("%02d", month) + ".html").get();
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
					if(tagName.equals("td"))
					{				
						String str = this.stringReplaceLineBreakAndRemoveTag(e);
						if(className.equals("font_bold"))
						{
							title = "";
							act = "";
							other = "";
							
							String[] split = str.split(LINE_BREAK);
							for(int i = 1;i < split.length;i++)
							{
								title += split[i];
							}
							date = split[0];
							date = (date.split(" "))[0];
							split = date.split("/");
							date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
						}
						else if(className.equals("linehi_10"))
						{
							if(str.contains("開場/開演") || str.contains("前売/当日"))
							{
								other += str;
								other = other.replace("※", LINE_BREAK+"※");
								other = other.replace("■開場/開演", LINE_BREAK + "■開場/開演");
							}
							else if(str.contains("お問合せ：Marble") || str.contains("22時以降は18歳未満の方は入場出来ません"))
							{
								pw.print("2" + TAB);
								outDate(pw, date);
								outTitle(pw, title);
								outAct(pw, act, date);
								outOther(pw, other, date);
							}
							else
							{
								act += str;
							}
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("2.Marble Failure" + e);
		}
	}
	
	private void outShinjukuMarz(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.marz.jp/schedule/2014/" + String.format("%02d", month) + "/").get();
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
						this.outTitle(pw, stringReplaceLineBreakAndRemoveTag(e));
					}
					else if( className.equals("entrybody") )
					{
						this.outAct(pw, stringReplaceLineBreakAndRemoveTag(e), "");
					}
					else if( className.equals("entryex") )
					{
						this.outOther(pw, stringReplaceLineBreakAndRemoveTag(e), "");
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
			Document doc = Jsoup.connect("http://www.loft-prj.co.jp/schedule/loft/date/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("tr");
			
			outLoftProject(pw, baseElements, 4, month);
		} catch(Exception e){
			System.out.println("4.Loft Failure" + e);
		}
	}
	
	private void outAkihabaraGoodman(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://clubgoodman.com/schedule/schedule14-" + String.format("%d", month) + ".html").get();
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
						outTitle(pw, title);
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
			Document doc = Jsoup.connect("http://www.toos.co.jp/basementbar/schedule/bb2014_" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("Table[width*=62] tr[valign=middle] td");
			
			int count = 0;
			for( Element element : baseElements)
			{
				count++;
				String date = "";
				if( count == 1 )
				{
					date = String.format("%02d%02d", month, Integer.valueOf(element.text()));
					pw.print("6" + TAB);
					outDate(pw, date);
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
					other = other.replace("●", LINE_BREAK + "●");
					outTitle(pw, title);
					outAct(pw, act, date);
					outOther(pw, other, date);	
				}
					
			}
		} catch(Exception e){
			System.out.println("6.BASEMENT Failure" + e);
		}
	}
	
	private void outShimokitaThree(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.toos.co.jp/3/3_schedule_14" + String.format("%02d", month) +".html").get(); //TODO: 当月のURLが違う
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
					other = other.replace("adv", LINE_BREAK + "adv");
				}
				
				pw.print("7" + TAB);
				String[] dateSplits = date.split("\\.");
				pw.print(dateSplits[1]+dateSplits[2]+ TAB);
				this.outTitle(pw, title);
				act = text.replace(LINE_BREAK,"");
				this.outAct(pw, act, date);
				this.outOther(pw, other, date);
			}
		} catch(Exception e){
			System.out.println("7.THREE Failure" + e);
		}
	}
	
	private void outShimokitaDaisyBar(PrintWriter pw, int month) 
	{
		try{
			Document doc = Jsoup.connect("http://www.daisybar.jp/schedule/14" + String.format("%02d", month) + ".html").get();
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
					outDate(pw, date);
				}
				else if(className.equals("about"))
				{
					for(Element e : element.select("h3") )
					{
						title = stringReplaceLineBreakAndRemoveTag(e);
						this.outTitle(pw, title);
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
					other = other.replace("前売", LINE_BREAK + "前売");
					this.outAct(pw, act, date);
					this.outOther(pw, other, date);
				}
			}
		} catch(Exception e){
			System.out.println("8.DAISY BAR Failure" + e);
		}
	}
	
	private void outShimokitaShelter(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.loft-prj.co.jp/schedule/shelter/date/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("tr");
			
			outLoftProject(pw, baseElements, 9, month);
		} catch(Exception e){
			System.out.println("9.Shelter Failure" + e);
		}
	}
	
	private void outShimokitaQue(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.ukproject.com/que/schedule/nextmonth.html").get();	//TODO:今月、来月っていう取り方しか出来ない
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
								this.outDate(pw, date);
								this.outTitle(pw, title);
								this.outAct(pw, act, date);
								this.outOther(pw, other, date);
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
			Document doc = Jsoup.connect("http://www.club251.com/sche/14" + String.format("%02d", month) + ".html").get();
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
					other = other.replace("adv", LINE_BREAK + "adv");
				}
				
				pw.print("11" + TAB);
				outDate(pw, date);
				outTitle(pw, title);
				outAct(pw, act, date);
				outOther(pw, other, date);
			}
		} catch(Exception e){
			System.out.println("11.251 Failure" + e);
		}
	}
	
	private void outShimokitaERA(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://s-era.jp/2014/" + String.format("%02d", month) + "/?cat=20").get();
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
				outDate(pw, date);
				outTitle(pw, title);
				outAct(pw, act, date);
				outOther(pw, other, date);
			}
		} catch(Exception e){
			System.out.println("12.ERA Failure" + e);
		}
	}
	
	private void outShimokitaGarden(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.gar-den.in/pc/plist.php?m=2014" + String.format("%02d", month)).get();
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
					outDate(pw, date);
					outTitle(pw, title);
					outAct(pw, act, date);
					outOther(pw, other, date);
				}
			}
		} catch(Exception e){
			System.out.println("13.Garden Failure" + e);
		}
	}
	
	private void outShindaitaFever(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.fever-popo.com/schedule/2014/" + String.format("%02d", month) + "/").get();
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
				outDate(pw, date);
				outTitle(pw, title);
				outAct(pw, act, date);
				other = other.replace("ADV", LINE_BREAK + "ADV");
				outOther(pw, other, date);
			}
		} catch(Exception e){
			System.out.println("14.Fever Failure" + e);
		}
	}
	
	private void outKoenjiUFO(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.ufoclub.jp/schedule/next-month").get();	//TODO: 
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
						act = act.replace(title, "");
					}
					else if(tagName.equals("td"))
					{
						if(e.html().contains("strong")){
							act += this.stringReplaceLineBreakAndRemoveTag(e);
							act = act.replace("/LIVE; ", "");
							act = act.replace(",", " /");
						}
					}
				}
				if(date=="")continue;
				other = this.removeStartEndLineBreak(other);
				pw.print("15" + TAB);
				outDate(pw, date);
				outTitle(pw, title);
				outAct(pw, act, date);
				outOther(pw, other, date);
			}
		} catch(Exception e){
			System.out.println("15.UFO Failure" + e);
		}
	}
	
	private void outKoenjiNiman(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.den-atsu.com/?schedule=2014-" + String.format("%d", month) + "-schedule").get();
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
									other 	= other.replace("Adv.", LINE_BREAK + "Adv.");
									pw.print("16" + TAB);
									outDate(pw, date);
									outTitle(pw, title);
									outAct(pw, act, date);
									outOther(pw, other, date);
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
			Document doc = Jsoup.connect("http://shibuya-o.com/east/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 17, month);
		} catch(Exception e){
			System.out.println("17.EAST FAILURE" + e);
		}
	}
	private void outShibuyaWest(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/west/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 18, month);
		} catch(Exception e){
			System.out.println("18.WEST FAILURE" + e);
		}
	}
	private void outShibuyaNest(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/nest/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 19, month);
		} catch(Exception e){
			System.out.println("19.NEST FAILURE" + e);
		}
	}
	private void outShibuyaCrest(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/crest/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 20, month);
		} catch(Exception e){
			System.out.println("20.CREST FAILURE" + e);
		}
	}
	private void outShibuyaBurrow(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/burrow/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 21, month);
		} catch(Exception e){
			System.out.println("21.BURROW FAILURE" + e);
		}
	}
	
	private void outShibuyaChelseaHotel(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.chelseahotel.jp/14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					
				}
			}
		}catch(Exception e){
			System.out.println("22.ChelseaHotel Failure" + e);
		}
	}
	
	private void outShibuyaKinoto(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://kinoto.jp/sched/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("td");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.equals("sched_date"))
					{
						String[] split = str.split("\\(");
						date = String.format("%02d%02d", month, Integer.valueOf(split[0]));
						split = split[1].split("\\)");
						other = split[1];
					}
					else if(className.equals("sched_detail"))
					{
						Element e2 = e.select("p[class=font_12_h]").first();
						if(e2 != null)
						{
							title = this.stringReplaceLineBreakAndRemoveTag(e2);
						}
						act = str.replace(title, "");
						act = act.replace("※", LINE_BREAK+"※");
						
						pw.print("23" + TAB);
						outDate(pw, date);
						outTitle(pw, title);
						outAct(pw, act, date);
						outOther(pw, other, date);
						
					}
				}
			}
		}catch(Exception e){
			System.out.println("23.KINOTO Failure" + e);
		}
	}
	
	private void outShibuyaLush(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.toos.co.jp/lush/l_schedule/l_2014" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("table[cellpadding=5] tr");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.children())
				{
					if(e.attr("style").contains("numberformat"))
					{
						date = this.stringReplaceLineBreakAndRemoveTag(e);
						String[] split = date.split(LINE_BREAK);
						date = split[0];
						date = this.removeEndSpace(date);
						date = String.format("%02d%02d", month, Integer.valueOf(date));
					}
					else
					{
						if(e.text().equals("") || !e.attr("bgcolor").equals("white"))
						{
							continue;
						}
						act = this.stringReplaceLineBreakAndRemoveTag(e);
						
						for(Element e2 : e.getAllElements())
						{
							if(e2.attr("color").equals("#660000") || e2.attr("color").equals("#000066"))
							{
								title = this.stringReplaceLineBreakAndRemoveTag(e2);
								act = act.replace(title, "");
								break;
							}
						}
						String[] split = act.split("■");
						if(split.length < 2)
						{
							continue;
						}
						act = split[0];
						other = "";
						for(int i = 1;i < split.length;i++ )
						{
							other += split[i];
						}
						
						other = other.replace("Advance", LINE_BREAK + "Advance");
						pw.print("24" + TAB);
						this.outDate(pw, date);
						this.outTitle(pw, title);
						this.outAct(pw, act, date);
						this.outOther(pw, other, date);
					}
				}
			}
		}catch(Exception e){
			System.out.println("24.LUSH Failure" + e);
			}
	}
	
	private void outShibuyaQuattro(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.club-quattro.com/shibuya/schedule/?ym=2014" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().getAllElements();
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				String tagName = element.tagName();
				String className = element.className();

				if(tagName.equals("th"))
				{
					Element e = element.child(0);
					date = e.attr("alt");
					String[] split = date.split("日");
					date = String.format("%02d%02d", month, Integer.valueOf(split[0]));
				}
				else if(className.equals("body"))
				{
					for(Element e : element.children())
					{
						tagName = e.tagName();
						className = e.className();
						if(className.equals("lead"))
						{
							title = this.stringReplaceLineBreakAndRemoveTag(e);
							title = title.replace("\"", "");
						}
						else if(tagName.equals("h3"))
						{
							act = this.stringReplaceLineBreakAndRemoveTag(e);
						}
						else if(className.equals("foot clearfix"))
						{
							other = "OPEN/START ";
							boolean isFirst = true;
							for(Element e2 : e.select("span"))
							{
								if(!isFirst)
								{
									other += "/";
								}
								other += this.stringReplaceLineBreakAndRemoveTag(e2);
								isFirst = false;
							}
							
							pw.print("25" + TAB);
							this.outDate(pw, date);
							this.outTitle(pw, title);
							this.outAct(pw, act, date);
							this.outOther(pw, other, date);
						}
					}
				}
				
			}
		}catch(Exception e){
			System.out.println("25.QUATTRO Failure" + e);
		}
	}
	
	private void outShibuyaWWW(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www-shibuya.jp/schedule/14" + String.format("%02d", month) + "/").get();
			Elements baseElements = doc.body().select("div[id=contents] div[class*=event]");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.equals("date"))
					{
						date = String.format("%02d%02d", month, Integer.valueOf(str));
					}
					else if(tagName.equals("h3"))
					{
						title = str;
						Element span = e.getElementsByTag("span").first();
						if(span != null)
						{
							title = title.replaceFirst(this.stringReplaceLineBreakAndRemoveTag(span), "");
						}
					}
					else if(className.equals("data"))
					{
						int type = 0;
						for(Element e2 : e.children())
						{
							tagName = e2.tagName();
							if(tagName.equals("dt"))
							{
								String dt = this.stringReplaceLineBreakAndRemoveTag(e2);
								if(dt.contains("START")) type = 1;
								else if(dt.contains("DOOR")) type = 2;
								else if(dt.contains("LINE")) type = 3;
							}
							else if(tagName.equals("dd"))
							{
								String dd = this.stringReplaceLineBreakAndRemoveTag(e2);
								if(type == 1)
								{
									other =  "OPEN / START : " + dd + LINE_BREAK;
								}
								else if(type == 2)
								{
									other += "前売 / 当日 : " + dd;
								}
								else if(type == 3)
								{
									act = dd;

									pw.print("26" + TAB);
									this.outDate(pw, date);
									this.outTitle(pw, title);
									this.outAct(pw, act, date);
									this.outOther(pw, other, date);
								}
								type = 0;
							}
						}
						
					}
				}
			}
		}catch(Exception e){
			System.out.println("26.WWW Failure" + e);
		}
	}
	
	private void outShibuyaDuo(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.duomusicexchange.com/schedule/2014/" + String.format("%02d", month) + "/index.html").get();
			Elements baseElements = doc.body().select("td[width=670]");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(tagName.equals("img")
							&& e.attr("width").equals("30")
							&& e.attr("height").equals("30"))
					{
						String imgNo = e.attr("src");
						imgNo = imgNo.substring(imgNo.length()-6, imgNo.length()-4);
						date = String.format("%02d%02d", month, Integer.valueOf(imgNo));
					}
					else if(tagName.equals("td")
							&& e.attr("width").equals("385")
							&& e.attr("valign").equals("middle"))
					{
						title = str;
					}
					else if(tagName.equals("td")
							&& e.attr("class").equals("css5")
							&& e.attr("valign").equals("middle"))
					{
						act = str;
					}
					else if(className.equals("css3_schduletext"))
					{
						other += str + LINE_BREAK;
						if(other.contains("¥") || other.contains("￥") || other.contains("無料") )
						{
							pw.print("28" + TAB);
							this.outDate(pw, date);
							this.outTitle(pw, title);
							this.outAct(pw, act, date);
							this.outOther(pw, other, date);
							
							title = "";
							act = "";
							other = "";
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("28.Duo Failure" + e);
		}
	}
	
	private void outDaikanyamaUnit(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.unit-tokyo.com/schedule//2014/" + String.format("%02d", month) + "/all_schedule.php").get();
			Elements baseElements = doc.body().select("div[class=content]");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.contains("day"))
					{
						date = String.format("%02d", month) + str;
					}
					else if(className.equals("event_title_live"))
					{
						title = str;
					}
					else if(className.equals("event_line_up"))
					{
						act = str;
						act = act.replace("LINE UP:", "");
					}
					else if(className.equals("event_info"))
					{
						other = str;
						other = other.replace("INFORMATION : ", "");
						
						pw.print("29" + TAB);
						this.outDate(pw, date);
						this.outTitle(pw, title);
						this.outAct(pw, act, date);
						this.outOther(pw, other, date);
					}
				}
			}
		}catch(Exception e){
			System.out.println("29.UNIT Failure" + e);
		}
	}
	
	private void outHarajukuAstroHall(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.astro-hall.com/category/schedule/?d=2014" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[id=content]");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.contains("date date"))
					{
						date = str;
						date = String.format("%02d", month) + date;
					}
					else if(className.contains("month") && tagName.equals("h4"))
					{
						title = str;
					}
					else if(className.contains("month") && tagName.equals("h5"))
					{
						act = str;
					}
					else if(className.equals("open"))
					{
						other = str + LINE_BREAK;
					}
					else if(className.equals("ticket"))
					{
						other += str;
						
						if(act.length() > 0)
						{
							pw.print("30" + TAB);
							this.outDate(pw, date);
							this.outTitle(pw, title);
							this.outAct(pw, act, date);
							this.outOther(pw, other, date);
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("30.ASTRO Failure" + e);
		}
	}
	
	private void outEbisuLiquidroom(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.liquidroom.net/schedule/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("dl[class=schedulelist clfx]");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.contains("date"))
					{
						date = str;
						String[] split = date.split("/");
						date = String.format("%02d%02d", Integer.valueOf(split[0]),Integer.valueOf(split[1]));
					}
					else if(tagName.contains("h3"))
					{
						title = str;
					}
					else if(tagName.contains("h4"))
					{
						act = str;
					}
					else if(className.equals("outline clfx"))
					{
						boolean isExistLineup = str.contains("LINE UP");
						if(!isExistLineup)
						{
							//LINEUPが無い時はタイトルと出演者を入れ替え
							String tmp = act;
							act = title;
							title = tmp;
						}
						other = "";
						int type = 0;
						for(Element e2 : e.getAllElements())
						{
							tagName = e2.tagName();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(tagName.equals("dt"))
							{
								if(str.contains("OPEN")) type = 1;
								else if(str.contains("DOOR")) type = 2;
								else if(str.contains("LINE")) type = 3;
								else if(str.contains("LINE")) type = 4;
							}
							else if(tagName.equals("dd"))
							{
								if(type == 1) 		other += "OPEN / START : " + str + LINE_BREAK;
								else if(type == 2) 	other += "ADV / DOOR : " + str + LINE_BREAK;
								else if(type == 3)	act = str;
								type = 0;
							}
						}
						
						pw.print("31" + TAB);
						this.outDate(pw, date);
						this.outTitle(pw, title);
						this.outAct(pw, act, date);
						this.outOther(pw, other, date);
					}
				}
			}
		}catch(Exception e){
			System.out.println("31.LIQUID Failure" + e);
		}
	}
	
	private void outIkebukuroOrg(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://minamiikebukuromusic.org/category/schedule/").get();	//月ごとの管理は無い
			Elements baseElements = doc.body().select("div[class=entry-summary]");
			
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
					
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.equals("entry-summary"))
					{
						str = str.replaceFirst("Tweet ", "");
						String[] split = str.split(LINE_BREAK);
						int type = 0;
						for(String s : split)
						{
							s = s.split("予約・")[0];
							s = s.split("■")[0];
							s = s.split("詳細はこちら")[0];
							s = s.split("Continue reading")[0];
							if(s.contains("年") && s.contains("月") && s.contains("日")){
								type = 2;
							}
							switch(type){
							case 0:
								act = "";
								other = "";
								title = "";
								type++;
							case 1:
								title += s + LINE_BREAK;
								break;
							case 2:
							{
								date = s;
								date = date.split("年")[1];
								date = date.split("日")[0];
								String[] sp = date.split("月");
								date = String.format("%02d%02d", Integer.valueOf(sp[0]),Integer.valueOf(sp[1]));
								type++;
								break;
							}
							case 3:
								other = s + LINE_BREAK;
								type++;
								break;
							case 4:
								other += s;
								type++;
								break;
							default:
								act += s + LINE_BREAK;
								break;
							}
						}
						
						pw.print("32" + TAB);
						this.outDate(pw, date);
						this.outTitle(pw, title);
						this.outAct(pw, act, date);
						this.outOther(pw, other, date);
					}
				}
			}
		}catch(Exception e){
			System.out.println("32.ORG Failure" + e);
		}
	}
	
	private void outIkebukuroRuidoK3(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.ruido.org/k3/schedule/month_4/").get();
			Elements baseElements = doc.body().select("div[id=schedule_place]");
			
			this.outRuidoProject(pw, baseElements, 33, month);
		}catch(Exception e){
			System.out.println("33.RuidoK3 Failure" + e);
		}
	}
	private void outShibuyaRuidoK2(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.ruido.org/k2/schedule/month_4/").get();
			Elements baseElements = doc.body().select("div[id=schedule_place]");
			
			this.outRuidoProject(pw, baseElements, 34, month);
		}catch(Exception e){
			System.out.println("34.RuidoK2 Failure" + e);
		}
	}
	private void outShinjukuRuidoK4(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.ruido.org/k4/schedule/month_4/").get();
			Elements baseElements = doc.body().select("div[id=schedule_place]");
			
			this.outRuidoProject(pw, baseElements, 35, month);
		}catch(Exception e){
			System.out.println("35.RuidoK4 Failure" + e);
		}
	}
	
	private void outAkasakaBlitz(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.tbs.co.jp/blitz/schedule_a/schedule2014" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("tr[id*=s20]");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				String idName = element.attr("id");
				date = element.attr("id").substring(idName.length() - 4, idName.length());
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(tagName.equals("p"))
					{
						if(className.equals("artist"))
						{
							act = str;
						}
						else if(className.equals("evttl"))
						{
							title = str;
						}
					}
					else if(tagName.equals("dl"))
					{
						other = "";
						for(Element e2 : e.children())
						{
							other += this.stringReplaceLineBreakAndRemoveTag(e2) + " ";
						}
						
						other = other.replace("開場", LINE_BREAK + "開場");
						other = other.replace("料金", LINE_BREAK + "料金");
						other = other.replace("問合せ", LINE_BREAK + "問合せ");
						
						pw.print("36" + TAB);
						this.outDate(pw, date);
						this.outTitle(pw, title);
						this.outAct(pw, act, date);
						this.outOther(pw, other, date);
					}
				}
			}
		}catch(Exception e){
			System.out.println("36.BLITZ Failure" + e);
		}
	}

	private void outZeppTokyo(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://hall.zepp.co.jp/tokyo/schedule.html?year=2014&month=" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class*=date_box]");
			
			this.outZeppProject(pw, baseElements, 37, month);
		}catch(Exception e){
			System.out.println("37.ZEPP TOKYO Failure" + e);
		}
	}
	
	private void outZeppDiverCity(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://hall.zepp.co.jp/divercity/schedule.html?year=2014&month=" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class*=date_box]");
			
			this.outZeppProject(pw, baseElements, 38, month);
		}catch(Exception e){
			System.out.println("38.ZEPP DiverCity Failure" + e);
		}
	}
	
	private void outShinjukuRedCloth(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.sputniklab.com/redcloth/14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("table[width=480][cellpadding=0] tr");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(tagName.equals("td") && e.attr("valign").equals("top") && !e.attr("align").equals("right")){
						if(str.length() > 0){
							if(e.html().contains("color=\"white\"") && e.html().contains("class=\"ss\"")){
								if(!title.equals("") || (!act.equals("") && !act.equals(LINE_BREAK)))
								{
									pw.print("39" + TAB);
									this.outDate(pw, date);
									this.outTitle(pw, title);
									this.outAct(pw, act, date);
									this.outOther(pw, other, date);
									title = "";
									act = "";
									other = "";
								}
								
								date = String.format("%02d", month) + str.split("\\.")[0];
							}else{
								act = str;
							}
						}
					}else if(tagName.equals("font") && (e.attr("color").equals("#220000") || e.attr("color").equals("#131313"))){
						title += str;
						act = act.replace(title, "");
					}else if(tagName.equals("td") && e.attr("align").equals("right")){
						other += str;
						if(other.contains("前")){
							pw.print("39" + TAB);
							this.outDate(pw, date);
							this.outTitle(pw, title);
							this.outAct(pw, act, date);
							this.outOther(pw, other, date);
							
							title = "";
							act = "";
							other = "";
						}
					}
				}
			}
			
			if(!title.equals("") || (!act.equals("") && !act.equals(LINE_BREAK)))
			{
				pw.print("39" + TAB);
				this.outDate(pw, date);
				this.outTitle(pw, title);
				this.outAct(pw, act, date);
				this.outOther(pw, other, date);
			}
		}catch(Exception e){
			System.out.println(" Failure" + e);
		}
	}
	
	private void outKichijoujiWarp(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://warp.rinky.info/schedules").get();
			print(doc.body().html());
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String tagName = e.tagName();
//					String className = e.className();
//				}
//			}
		}catch(Exception e){
			System.out.println(" Failure" + e);
		}
	}
	
	private void outShinkibaStudioCoast(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://www.studio-coast.com/schedule/2014/"+ String.format("%02d", month) +".html").get();
			Elements baseElements = doc.body().select("section[id*=event]");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.equals("day")){
						title = "";
						act = "";
						other = "";
						
						date = str;
						String[] split = date.split("/");
						date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
					}else if(className.equals("eventTitle")){
						for(Element e2 : e.getAllElements())
						{
							tagName = e2.tagName();
							if(tagName.equals("h2")){
								title = this.stringReplaceLineBreakAndRemoveTag(e2);
							}else if(tagName.equals("h3")){
								act = this.stringReplaceLineBreakAndRemoveTag(e2);
							}
						}
					}else if(className.equals("time") || className.equals("price")){
						for(Element e2 : e.getAllElements())
						{
							tagName = e2.tagName();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(tagName.equals("dt")){
								other += str + ":";
							}else if(tagName.equals("dd")){
								other += str + LINE_BREAK;
							}
						}
					}else if(className.equals("contact")){
						pw.print("42" + TAB);
						this.outDate(pw, date);
						this.outTitle(pw, title);
						this.outAct(pw, act, date);
						this.outOther(pw, other, date);
					}
				}
			}
		}catch(Exception e){
			System.out.println(" Failure" + e);
		}
	}
	
	private void outKoenjiHigh(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://koenji-high.com/schedule/?sy=2014&sm=" + month).get();
			Elements baseElements = doc.body().select("div[class=eventlist clearfix]");
			
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
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(className.equals("daynum")){
						date = String.format("%02d%02d", month, Integer.valueOf(str));
					}else if(className.equals("titleblock")){
						title = str;
					}else if(tagName.equals("table") && className.equals("")){
						String menuName = "";
						for(Element e2 : e.getAllElements()){
							tagName = e2.tagName();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							
							if(tagName.equals("th")){
								menuName = str;
							}else if(tagName.equals("td")){
								if(menuName.contains("LINE")){
									act = str;
									if(act.equals("")){
										break;
									}
								}else if(menuName.contains("OPEN/START")){
									other = menuName + ":" + str + LINE_BREAK;
								}else if(menuName.contains("ADV/DOOR")){
									other += menuName + ":" + str;
									
									pw.print("46" + TAB);
									this.outDate(pw, date);
									this.outTitle(pw, title);
									this.outAct(pw, act, date);
									this.outOther(pw, other, date);
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println(" Failure" + e);
		}
	}
	
	private void outKoiwaBushbash(PrintWriter pw, int month)
	{
		try{
			Document doc = Jsoup.connect("http://bushbash.org/schedule14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("td");
			
			String date = "";
			String title = "";
			String act = "";
			String other = "";
			for(Element element : baseElements)
			{
				String str = this.stringReplaceLineBreakAndRemoveTag(element);
				if(element.attr("align").contains("center")){
					if(str.contains("sun") || str.contains("mon") || str.contains("tue") || str.contains("wed") || str.contains("thu") || str.contains("fri") || str.contains("sat") ){
						date = str.split(LINE_BREAK)[0];
						date = String.format("%02d%02d", month, Integer.valueOf(date));
					}
				}else{
					String[] split = str.split("■");
					if(split.length > 1){
						int type = 0;
						for(int i = 0; i < split.length;i++){
							if(split[i].contains("open") && split[i].contains("start")){
								type = 2;
							}
							switch(type){
							case 0:
								title = split[i];
								type++;
								break;
							case 1:
								act += split[i];
								break;
							default:
								other += split[i];
								break;
							}
						}
						
						pw.print("47" + TAB);
						this.outDate(pw, date);
						this.outTitle(pw, title);
						this.outAct(pw, act, date);
						this.outOther(pw, other, date);
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("47 bushbash Failure" + e);
		}
	}
	
//	private void out(PrintWriter pw, int month)
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
//			System.out.println(" Failure" + e);
//		}
//	}
	
	private void outZeppProject(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
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
				String str = this.stringReplaceLineBreakAndRemoveTag(e);
				if(className.equals("date_day")){
					for(Element e2 : e.getAllElements())
					{
						tagName = e2.tagName();
						if(tagName.equals("span")){
							date = String.format("%02d", month) + this.stringReplaceLineBreakAndRemoveTag(e2);
						}
					}
				}else if(tagName.equals("h2")){
					title = str;
				}else if(className.equals("artist")){
					act = str;
				}else if(className.equals("detail")){
					for(Element e2 : e.getAllElements())
					{
						tagName = e2.tagName();
						if(tagName.equals("dl")){
							other = this.stringReplaceLineBreakAndRemoveTag(e2);
							other = other.replace("料金：", LINE_BREAK + "料金：");
							other = other.replace("¥", "￥");
							pw.print(liveHouseNo + TAB);
							this.outDate(pw, date);
							this.outTitle(pw, title);
							this.outAct(pw, act, date);
							this.outOther(pw, other, date);
						}
					}
				}
			}
		}
	}
	
	private void outRuidoProject(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		String date = "";
		String title = "";
		String act = "";
		String other = "";
		for(Element element : baseElements)
		{
			for(Element e : element.getAllElements())
			{
				String tagName = e.tagName();
				String str = this.stringReplaceLineBreakAndRemoveTag(e);
				if(tagName.equals("th") && e.attr("width").equals("70"))
				{
					str = str.substring(0, 2);
					date = String.format("%02d", month) + str;
				}
				else if(tagName.equals("table") && e.attr("id").equals("table02"))
				{
					for(Element e2 : e.getAllElements())
					{
						if(e2.tagName().equals("th"))
						{
							title = this.stringReplaceLineBreakAndRemoveTag(e2);
						}
						else if(e2.tagName().equals("td"))
						{
							act = this.stringReplaceLineBreakAndRemoveTag(e2);
						}
					}
				}
				else if(tagName.equals("table") && e.attr("id").equals("table03"))
				{
					other = str;
					other = other.split("e+")[0];
					other = other.split("Lコード")[0];
					other = other.split("Ｌコード")[0];
					other = other.split("Pコード")[0];
					other = other.split("Ｐコード")[0];
					other = other.split("info:")[0];
					
					pw.print(liveHouseNo + TAB);
					this.outDate(pw, date);
					this.outTitle(pw, title);
					this.outAct(pw, act, date);
					this.outOther(pw, other, date);
				}
			}
		}
	}
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
						outDate(pw, date);
						outTitle(pw, title);
					}
					for( Element e2 : e.select("p[class=month_content]") )
					{
						act = stringReplaceLineBreakAndRemoveTag(e2);
						act = removeStartEndLineBreak(act);
						outAct(pw, act, date);
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
						outOther(pw, other, date);
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
							outDate(pw, date);
							outTitle(pw, title);
							outAct(pw, act, date);
							outOther(pw, other, date);
							
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
			else if(s.endsWith("br2n"))
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
			if(s.startsWith(" ") || s.startsWith("　"))
			{
				s = s.substring(1, s.length());
			}
			else if(s.endsWith(" ") || s.startsWith("　"))
			{
				s = s.substring(0, s.length()-1);
			}
			else
			{
				return s;
			}
		}while(true);
	}
	private String removeDuplicateLineBreak(String s)
	{
		s = s.replace(LINE_BREAK + "" + LINE_BREAK, LINE_BREAK);
		s = s.replace(LINE_BREAK + " " + LINE_BREAK, LINE_BREAK);
		s = s.replace(LINE_BREAK + "  " + LINE_BREAK, LINE_BREAK);
		s = s.replace(LINE_BREAK + "   " + LINE_BREAK, LINE_BREAK);
		s = s.replace("/" + LINE_BREAK, "/");
		s = s.replace("/ " + LINE_BREAK, "/");
		s = s.replace("/  " + LINE_BREAK, "/");
		s = s.replace("/   " + LINE_BREAK, "/");
		s = s.replace("/　" + LINE_BREAK, "/");
		s = s.replace(LINE_BREAK + " ", LINE_BREAK);
		s = s.replace(LINE_BREAK + "　", LINE_BREAK);

		return s;
	}
	
	private void outDate(PrintWriter pw, String date){
		if(debugFlag == 1){
			print(date + TAB);
		}else{
			pw.print(date + TAB);
		}
	}
	private void outTitle(PrintWriter pw, String title){
		title = this.removeStartEndLineBreak(title);
		title = this.removeEndSpace(title);
		title = this.removeDuplicateLineBreak(title);
		title = title.replace("\"", "”");
		if(debugFlag == 1){
			print(title + TAB);
		}else{
			pw.print(title + TAB);
		}
	}
	private void outAct(PrintWriter pw, String act, String date){
		act = this.removeStartEndLineBreak(act);
		act = this.removeEndSpace(act);
		act = this.removeDuplicateLineBreak(act);
		act = act.replace("\"", "”");

		//全角スラッシュを半角スラッシュに置き換え(全角スラッシュは括弧内のみ使う)
		act = act.replace("／", "/");
		
		//括弧内のスラッシュを全角スラッシュに置き換える
		String[] split = act.split("\\(");
		if(split.length > 1)
		{
			act = split[0];
			for(int i = 1;i < split.length;i++)
			{
				act +=  "(";
				
				String[] split2 = split[i].split("\\)");
				split2[0] = split2[0].replace("/", "／");
				
				act += split2[0] + ")";
				for(int j = 1;j < split2.length;j++)
				{
					act += split2[j];
				}
			}
		}
		
		//名前にスラッシュが入るバンドは個別置き換え（随時追加）
		act = act.replace("V/ACATION", "V／ACATION");
		
		if(debugFlag == 1){
			print(act + TAB);
		}else{
			pw.print(act + TAB);
		}
		if(act.contains("こちら") || act.contains("コチラ") || act.contains("http") )
		{
			print("■■FAILURE::" + date + "::" + act + "::リンクあり");
		}
	}
	private void outOther(PrintWriter pw, String other, String date){
		other = this.removeStartEndLineBreak(other);
		other = this.removeEndSpace(other);
		other = this.removeDuplicateLineBreak(other);
		other = other.replace("\"", "”");
		if(debugFlag == 1){
			print(other);
		}else{
			pw.println(other);
		}
		if(other.contains("こちら") || other.contains("コチラ") || other.contains("http") )
		{
			print("■■FAILURE::" + date + "::" + other + "::リンクあり");
		}
	}
	
	private void print(String s)
	{
		System.out.println(s);
	}
}
