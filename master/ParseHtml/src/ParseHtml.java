import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import net.sourceforge.htmlunit.corejs.javascript.Function;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;


//TODO: 出来たらやること
//20140412: QUATTROが正しく出ているか調査、野音の入力

//ORG
//RUIDO project
//渋谷QUATTRO　価格がとれていない

public class ParseHtml
{
	static int debugFlag = 1;
	static boolean isOutDifficultLiveHouse = false;
	static boolean isOutNormalLiveHouse = false;
	
	static ParseHtml parseHtml = new ParseHtml();
	static String[] lineBreakCode = {"< br/>","< br/ >", "<br/>", "<br />", "< BR/>", "< BR/ >", "<BR/>","<BR />"};
	final static String TAB = "\t";
	final static String LINE_BREAK = "br2n";
	
	String date = "";
	String title = "";
	String act = "";
	String other = "";
	
	int currentMonth;
	int currentDate;
	
	public static void main(String[] args) throws IOException 
	{
		//出力先を作成する
        FileWriter fw = new FileWriter("out.csv", false);
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        int month = 7;
        
        parseHtml.currentMonth	= Calendar.getInstance().get(Calendar.MONTH) + 1;
        parseHtml.currentDate	= Calendar.getInstance().get(Calendar.DATE);
        
        //安定して取得できるライブハウス
        if(isOutNormalLiveHouse){
	        //1. 新宿Motion
			parseHtml.outShinjukuMotion(pw, month, 1);
			//2. 新宿Marble
			parseHtml.outShinjukuMarble(pw,month, 2);
			//3. 新宿Marz
			parseHtml.outShinjukuMarz(pw,month, 3);
//			if(isOutDifficultLiveHouse){
//	        	//4. 新宿LOFT
//				parseHtml.outShinjukuLoft(pw,month, 4);
//			}
			//5. 秋葉原GOODMAN
			parseHtml.outAkihabaraGoodman(pw,month, 5);
			//6. 下北沢BASEMENT BAR
			parseHtml.outBasementBar(pw, month, 6);
			//7. 下北沢THREE
			parseHtml.outShimokitaThree(pw, month, 7);
			//8. 下北沢DAISY BAR
			parseHtml.outShimokitaDaisyBar(pw, month, 8);
//			if(isOutDifficultLiveHouse){
//				//9. 下北沢SHELTER
//				parseHtml.outShimokitaShelter(pw, month, 9);
//			}
			//10. 下北沢QUE
			parseHtml.outShimokitaQue(pw, month, 10);
			//11. 下北沢251
			parseHtml.outShimokita251(pw, month, 11);
			//12. 下北沢ERA
			parseHtml.outShimokitaERA(pw, month, 12);
			//13. 下北沢GARDEN
			parseHtml.outShimokitaGarden(pw, month, 13);
			//14. 新代田FEVER
			parseHtml.outShindaitaFever(pw, month, 14);
//			if(isOutDifficultLiveHouse){
//				//15. 東高円寺U.F.O.CLUB
//				parseHtml.outKoenjiUFO(pw, month, 15);
//			}
			//16. 東高円寺二万電圧
			parseHtml.outKoenjiNiman(pw, month, 16);
			//17. 渋谷O-EAST
			parseHtml.outShibuyaEast(pw, month, 17);
			//18. 渋谷O-WEST
			parseHtml.outShibuyaWest(pw, month, 18);
			//19. 渋谷O-NEST
			parseHtml.outShibuyaNest(pw, month, 19);
			//20. 渋谷O-CREST
			parseHtml.outShibuyaCrest(pw, month, 20);
			//21. 渋谷BURROW
			parseHtml.outShibuyaBurrow(pw, month, 21);
			//22. 渋谷CHELSEA HOTEL
			
			//24. 渋谷LUSH
			parseHtml.outShibuyaLush(pw, month, 24);
			//25. 渋谷CLUB QUATTRO
			parseHtml.outShibuyaQuattro(pw, month, 25);
			//26. 渋谷WWW
			parseHtml.outShibuyaWWW(pw, month, 26);
			//27. 渋谷7thFLOOR
			        
			//28. 渋谷DUO
			parseHtml.outShibuyaDuo(pw, month, 28);
			//29. 代官山UNIT
			parseHtml.outDaikanyamaUnit(pw, month, 29);
			//30. 原宿ASTRO HALL
			parseHtml.outHarajukuAstroHall(pw, month, 30);
			//31. 恵比寿LIQUIDROOM
			parseHtml.outEbisuLiquidroom(pw, month, 31);
//			if(isOutDifficultLiveHouse){
//				//32. 池袋music org
//				parseHtml.outIkebukuroOrg(pw, month, 32);
//				//33. 池袋RUIDO K3
//				parseHtml.outIkebukuroRuidoK3(pw, month, 33);
//				//34. 渋谷RUIDO K2
//				parseHtml.outShibuyaRuidoK2(pw, month, 34);
//				//35. 新宿RUIDO K4
//				parseHtml.outShinjukuRuidoK4(pw, month, 35);
//				
//	        }
	        //36. 赤坂BLITZ
	        parseHtml.outAkasakaBlitz(pw, month, 36);
	        //37. ZEPP TOKYO
	        parseHtml.outZeppTokyo(pw, month, 37);
	        //38. ZEPP DIVERCITY
	        parseHtml.outZeppDiverCity(pw, month, 38);
	        //39. 新宿RedCloth
	        parseHtml.outShinjukuRedCloth(pw, month, 39);
	        //40. warp
//	        if(isOutDifficultLiveHouse){
//	        	parseHtml.outKichijoujiWarp(pw, month, 40);
//	        }
	        //41.
	        
	        //42. 新木場STUDIO COAST
//	        if(isOutDifficultLiveHouse){
//	        	parseHtml.outShinkibaStudioCoast(pw, month, 42);
//	        }
	        //43. 新宿JAM 
	        
	        //46. 高円寺HIGH
	        parseHtml.outKoenjiHigh(pw, month, 46);
			//47. 小岩bushbash
			parseHtml.outKoiwaBushbash(pw, month, 47);
	        //48. 高円寺CLUB MISSION'S
			parseHtml.outKoenjiMissions(pw, month, 48);
			//49. 八王子MatchBox
			parseHtml.outHachiojiMatchBox(pw, month, 49);
	        //50. 八王子RIPS
			parseHtml.outHachiojiRIPS(pw, month, 50);
	        //51. 西荻窪FLAT
			parseHtml.outNishiOgikuboFLAT(pw, month, 51);
	        //52. 新宿JAM TODO: 時間などの分離がかなり厳しい
	//		parseHtml.outShinjukuJam(pw, month, 52);
			//53. 新宿NINESPICES
			parseHtml.outShinjukuNineSpices(pw, month, 53);
			//54. 大塚MEETS
			parseHtml.outOtsukaMeets(pw, month, 54);
	        //55. 高田馬場AREA
			parseHtml.outTakadanobabaArea(pw, month, 55);
	        //56. 池袋EDGE        
			parseHtml.outIkebukuroEdge(pw, month, 56);
	        //57. 立川BABEL
			parseHtml.outTachikawaBabel(pw, month, 57);
			 //58. 下北沢MOSAiC
			parseHtml.outShimokitaMosaic(pw, month, 58);
			//59. 四谷OUTBREAK
	        parseHtml.outYotsuyaOutBreak(pw, month, 59);
	        //60. 四谷天窓
	        parseHtml.outYotsuyaTenmado(pw, month, 60);
	        //61. 四谷天窓.comfort
	        parseHtml.outYotsuyaTenmadoComfort(pw, month, 61);
	        //62. 恵比寿天窓.switch
	        parseHtml.outEbisuTenmadoSwitch(pw, month, 62);
	        //63. 新大久保EARTHDOM
	        parseHtml.outShinokuboEarthdom(pw, month, 63);
	        //64. 初台WALL
	        parseHtml.outHatsudaiWall(pw, month, 64);
	        //65. 高円寺ROOTS
	        parseHtml.outKoenjiRoots(pw, month, 65);
	        //66.渋谷La.mama
	        parseHtml.outShibuyaLamama(pw, month, 66);
	        //67. 渋谷VISION
	        parseHtml.outShibuyaVision(pw, month, 67);
	        //68. 渋谷eggman
	        parseHtml.outShibuyaEggman(pw, month, 68);
	        //69. 新宿BLAZE
	        parseHtml.outShinjukuBlaze(pw, month, 69);
	        //70. 下北沢GARAGE
	        parseHtml.outShimokitaGarage(pw, month, 70);
	        //71. 下北沢屋根裏
	        parseHtml.outShimokitaYaneura(pw, month, 71);
	        //72. 下北沢REG
	        parseHtml.outShimokitaReg(pw, month, 72);
	        //73. 代官山LOOP
	        parseHtml.outDaikanyamaLoop(pw, month, 73);
	        //74. 渋谷LOOP ANNEX
	        parseHtml.outShibuyaLoopAnnex(pw, month, 74);
	        //75. 高田馬場PHASE
	        parseHtml.outTakadanobabaPhase(pw, month, 75);
	        //76. 代官山晴れたら空に豆まいて
	        parseHtml.outDaikanyamaHaremame(pw, month, 76);
	        //77. 学芸大学MAPLEHOUSE
	        parseHtml.outGakugeidaigakuMapleHouse(pw, month, 77);
	        //78. 青山月見ル君想フ
	        parseHtml.outAoyamaTsukimiru(pw, month, 78);
	        //79. 千葉LOOK
	        parseHtml.outChibaLook(pw, month, 79);
	        //80. 新松戸FIREBIRD
	        parseHtml.outShinmatsudoFirebird(pw, month, 80);
	        //81. 横浜BAYSIS
	        parseHtml.outYokohamaBaysis(pw, month, 81);
        }

        parseHtml.outYokohamaGalaxy(pw, month, 82);
        
        if(isOutDifficultLiveHouse){
        	//4. 新宿LOFT
			parseHtml.outShinjukuLoft(pw,month, 4);
		}
        if(isOutDifficultLiveHouse){
			//9. 下北沢SHELTER
			parseHtml.outShimokitaShelter(pw, month, 9);
		}
        if(isOutDifficultLiveHouse){
			//15. 東高円寺U.F.O.CLUB
			parseHtml.outKoenjiUFO(pw, month, 15);
			
			//23. 渋谷乙
			parseHtml.outShibuyaKinoto(pw, month, 23);
		}
        if(isOutDifficultLiveHouse){
			//32. 池袋music org
			parseHtml.outIkebukuroOrg(pw, month, 32);
			//33. 池袋RUIDO K3
			parseHtml.outIkebukuroRuidoK3(pw, month, 33);
			//34. 渋谷RUIDO K2
			parseHtml.outShibuyaRuidoK2(pw, month, 34);
			//35. 新宿RUIDO K4
			parseHtml.outShinjukuRuidoK4(pw, month, 35);
			
        }
        if(isOutDifficultLiveHouse){
        	parseHtml.outKichijoujiWarp(pw, month, 40);
        }
        if(isOutDifficultLiveHouse){
        	//read　timeoutが多い
        	parseHtml.outShinkibaStudioCoast(pw, month, 42);
        }
        
		pw.close();
		
		System.out.println("done");
	}
	
	private boolean outShinjukuMotion(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{		
			Document doc = Jsoup.connect("http://motion-web.jp/html/2014" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("table[width=450] td[colspan=4]");

			this.initParam();
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
							if(!title.equals("")){
								this.outParam(pw, 1);
								title = "";
								act = "";
								other = "";
							}
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
							
							this.outParam(pw, 1);
							title = "";
							act = "";
							other = "";
							
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("1.Motion Failure::" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuMotion(pw, ++month, liveHouseNo);
		return true;
	}

	private boolean outShinjukuMarble(PrintWriter pw, int month, int liveHouseNo) 
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://marble-web.jp/html/schedule14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("table[width=510] td[colspan=3]");

			this.initParam();
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
							if(!title.equals("") || !act.equals("")){
								this.outParam(pw, 2);
								title = "";
								act = "";
								other = "";
								
								title = str;
								continue;
							}
							
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
								this.outParam(pw, 2);
								title = "";
								act = "";
								other = "";
							}
						}else if(className.equals("linehi_12")){
							act += str;
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("2.Marble Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuMarble(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShinjukuMarz(PrintWriter pw, int month, int liveHouseNo) 
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
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
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuMarz(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShinjukuLoft(PrintWriter pw, int month, int liveHouseNo) 
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.loft-prj.co.jp/schedule/loft/date/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("tr");
			
			outLoftProject(pw, baseElements, 4, month);
		} catch(Exception e){
			System.out.println("4.Loft Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuLoft(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outAkihabaraGoodman(PrintWriter pw, int month, int liveHouseNo) 
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
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
						String str = stringReplaceLineBreakAndRemoveTag(e);
						date = this.makeDate(month, str);
					}
					else if( className.equals("event") )
					{
						act = stringReplaceLineBreakAndRemoveTag(e);
						
						Elements eventTitle = e.select("span[class=event-title]");
						if( eventTitle != null && eventTitle.size() > 0 )
						{
							title = stringReplaceLineBreakAndRemoveTag(eventTitle.first());
							act = act.replace(title+"br2n ", "");
							act = act.replace(title+" br2n", "");
							act = act.replace(title+LINE_BREAK, "");
							act = act.replace(title, "");
							act = act.replace("■イープラス", "");
							act = act.replace("●PCから購入する＞＞", "");
						}
					}
					else if( className.equals("o-s") )
					{
						other += stringReplaceLineBreakAndRemoveTag(e) + LINE_BREAK;
					}
					else if( className.equals("price") )
					{
						other += stringReplaceLineBreakAndRemoveTag(e) + LINE_BREAK;
						title = this.removeStartEndLineBreak(title);
						title = this.removeEndSpace(title);
						act = this.removeStartEndLineBreak(act);
						act = this.removeEndSpace(act);
						if(!title.equals("") || !act.equals("")){
							this.outParam(pw, 5);							
						}
						title = "";
						act = "";
						other = "";
					}
				}	
			}
		} catch(Exception e){
			System.out.println("5.GOODMAN Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outAkihabaraGoodman(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outBasementBar(PrintWriter pw, int month, int liveHouseNo) 
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
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
					String str = element.text();
					if(str.contains("BASEMENT BAR 19th ANNIVERSARY")){
						count = 0;
						continue;
					}
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
						String str = this.stringReplaceLineBreakAndRemoveTag(e);
						if(e.text().equals(""))
						{
							continue;
						}
						String color = e.attr("color");
						
						if( color.contains("#000099") )
						{
							title += str;
						}
						else if( color.contains("#006699") )
						{
							other += str;
						}
						else
						{
							act += str;
						}
					}
					other = other.replace("●", LINE_BREAK + "●");
					outTitle(pw, title);
					outAct(pw, act, date);
					outOther(pw, other, date);
					
					//その場しのぎ
					if(act.contains("Mounthill's Orchestra / Yees. / オレモリカエル / THE BABA BAND")){
						count = 2;
					}
				}
					
			}
		} catch(Exception e){
			System.out.println("6.BASEMENT Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outBasementBar(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokitaThree(PrintWriter pw, int month, int liveHouseNo) 
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			//TODO: 当月のURLが違う
			String url = month == currentMonth ? "http://www.toos.co.jp/3/3_schedule.html" : "http://www.toos.co.jp/3/3_schedule_14" + String.format("%02d", month) +".html";
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("table[width=625][border=0][cellspacing=2][cellpadding=1] tr");
			
			this.initParam();
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
				
				String[] dateSplits = date.split("\\.");
				if(dateSplits.length < 3){
					continue;
				}
				date = String.format("%02d%02d", Integer.valueOf(dateSplits[1]),Integer.valueOf(dateSplits[2]));
				act = text;
				
				if(!act.contains("***")){
					this.outParam(pw, 7);
				}
				title = "";
				act = "";
				other = "";
			}
		} catch(Exception e){
			System.out.println("7.THREE Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaThree(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokitaDaisyBar(PrintWriter pw, int month, int liveHouseNo) 
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.daisybar.jp/schedule/14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("tbody tr td");
			
			this.initParam();
			for( Element element : baseElements)
			{
				String className = element.className();
				if(className.equals("day"))
				{
					date = stringReplaceLineBreakAndRemoveTag(element);
					date = String.format("%02d%02d", month, Integer.valueOf(date));
				}
				else if(className.equals("about"))
				{
					for(Element e : element.select("h3") )
					{
						title = stringReplaceLineBreakAndRemoveTag(e);
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
					
					if(!title.equals("") || !act.equals("")){
						this.outParam(pw, 8);
					}
					title = "";
					act = "";
					other = "";
				}
			}
		} catch(Exception e){
			System.out.println("8.DAISY BAR Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaDaisyBar(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokitaShelter(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.loft-prj.co.jp/schedule/shelter/date/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("tr");
			
			outLoftProject(pw, baseElements, 9, month);
		} catch(Exception e){
			System.out.println("9.Shelter Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaShelter(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokitaQue(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			//TODO:今月、来月っていう取り方しか出来ない
			if(currentMonth + 2 <= month){
				return false;
			}
			String url = month == currentMonth ? "http://www.ukproject.com/que/schedule/thismonth.html" : "http://www.ukproject.com/que/schedule/nextmonth.html";
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("tr tr");
			
			this.initParam();
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
								
								if(!title.equals("") || !act.equals("")){
									this.outParam(pw, 10);
								}
								title = "";
								act = "";
								other = "";
							}
						}
					}
				}
			}
		} catch(Exception e){
			System.out.println("10.Que Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaQue(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokita251(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.club251.com/sche/14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("div[class=day clearfix]");
			
			for(Element element : baseElements)
			{
				this.initParam();
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
				
				this.outParam(pw, 11);
			}
		} catch(Exception e){
			System.out.println("11.251 Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokita251(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokitaERA(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			if(month > 12)return true;
			Document doc = Jsoup.connect("http://s-era.jp/2014/" + String.format("%02d", month) + "/?cat=20").get();
			Elements baseElements = doc.body().select("div[id=schedule] div[class*=schedule-day]");
			
			for(Element element : baseElements)
			{
				this.initParam();
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
				
				if(!title.equals("EVENT") || !act.equals("")){
					this.outParam(pw, 12);
				}
			}
		} catch(Exception e){
			System.out.println("12.ERA Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaERA(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokitaGarden(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://gar-den.in/?m=2014" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("article[id*=post]");
			
			this.initParam();
			for(Element element : baseElements){
				for(Element e : element.getAllElements()){
					String c = e.className();
					String t = e.tagName();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.contains("entry-date")){
						date = this.makeDate(month, str.substring(0, 2));
					}else if(c.equals("open")){
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(t.equals("h6")){
								other += str + " ";
							}else if(t.equals("p")){
								other += str + LINE_BREAK;
							}
						} 
					}else if(c.contains("entry-title")){
						title = str;
					}else if(c.contains("entry_headerpost")){
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							if(t.equals("p")){
								act += this.stringReplaceLineBreakAndRemoveTag(e2);
							}
						}
						act = act.replace("■", "");
						this.outParam(pw, 13);
						
						title = "";
						act = "";
						other = "";
					}
				}
			}
		} catch(Exception e){
			System.out.println("13.Garden Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaGarden(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShindaitaFever(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.fever-popo.com/schedule/2014/" + String.format("%02d", month) + "/").get();
			Elements baseElements = doc.body().select("div[id*=entry-][class=entry-asset asset hentry]");
			
			this.initParam();
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
				
				other = other.replace("ADV", LINE_BREAK + "ADV");
				this.outParam(pw, 14);
				title = "";
				act = "";
				other = "";
			}
		} catch(Exception e){
			System.out.println("14.Fever Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShindaitaFever(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outKoenjiUFO(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			print("UFOCLUB はちゃんとurlが更新されているか確認しないと危ない！");
			if(currentMonth + 2 <= month){
				return false;
			}
			String url = month == currentMonth ? "http://www.ufoclub.jp/schedule" : "http://www.ufoclub.jp/schedule/next-month";
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("tbody tr");
			
			this.initParam();
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
						title = "";
						date = this.stringReplaceLineBreakAndRemoveTag(e);
						date = String.format("%02d%02d", month,Integer.valueOf(date));
					}
					else if(className.equals("td_open") || className.equals("td_charge"))
					{
						other += this.stringReplaceLineBreakAndRemoveTag(e) + LINE_BREAK;
					}
//					else if(tagName.equals("strong"))
//					{
//						title += this.stringReplaceLineBreakAndRemoveTag(e);
//						act = act.replace(title, "");
//						
//					}
					else if(tagName.equals("td"))
					{
						String str = this.stringReplaceLineBreakAndRemoveTag(e);
						String[] split = str.split("br2n");
						
						for(int i = 0;i < split.length;i++){
							if(i==0){
								title = split[0];
							}else{
								act += split[i];
							}
						}
						
						act = act.replace("/LIVE; ", "");
						act = act.replace(",", " /");
					}
				}
				if(date=="")continue;
				other = this.removeStartEndLineBreak(other);
				this.outParam(pw, 15);
				title = "";
			}
		} catch(Exception e){
			System.out.println("15.UFO Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outKoenjiUFO(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outKoenjiNiman(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.den-atsu.com/?schedule=2014-" + String.format("%d", month) + "-schedule").get();
			Elements baseElements = doc.body().select("div[class=hpb-entry-content] p");
			
			this.initParam();
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
									this.outParam(pw, 16);
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
			return false;
		}
		if(month >= 12){
			return true;
		}
		outKoenjiNiman(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaEast(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/east/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 17, month);
		} catch(Exception e){
			System.out.println("17.EAST FAILURE" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaEast(pw, ++month, liveHouseNo);
		return true;
	}
	private boolean outShibuyaWest(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/west/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 18, month);
		} catch(Exception e){
			System.out.println("18.WEST FAILURE" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaWest(pw, ++month, liveHouseNo);
		return true;
	}
	private boolean outShibuyaNest(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/nest/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 19, month);
		} catch(Exception e){
			System.out.println("19.NEST FAILURE" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaNest(pw, ++month, liveHouseNo);
		return true;
	}
	private boolean outShibuyaCrest(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/crest/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 20, month);
		} catch(Exception e){
			System.out.println("20.CREST FAILURE" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaCrest(pw, ++month, liveHouseNo);
		return true;
	}
	private boolean outShibuyaBurrow(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://shibuya-o.com/burrow/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=post-list]");
			
			this.outTsutayaOGroup(pw,baseElements, 21, month);
		} catch(Exception e){
			System.out.println("21.BURROW FAILURE" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaBurrow(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaChelseaHotel(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.chelseahotel.jp/14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("");
			
			this.initParam();
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					
				}
			}
		}catch(Exception e){
			System.out.println("22.ChelseaHotel Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaChelseaHotel(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaKinoto(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			String url = "http://kinoto.jp/sched/" + String.format("%02d", month) +"-2014/";
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("td");
			
			this.initParam();
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
						
						this.outParam(pw, 23);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("23.KINOTO Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaKinoto(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaLush(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.toos.co.jp/lush/l_schedule/l_2014" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("table[cellpadding=5] tr");
			
			this.initParam();
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
						this.outParam(pw, 24);
					}
				}
			}
		}catch(Exception e){
			System.out.println("24.LUSH Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaLush(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaQuattro(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.club-quattro.com/shibuya/schedule/?ym=2014" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().getAllElements();
			
			this.initParam();
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
							
							this.outParam(pw, 25);
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("25.QUATTRO Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaQuattro(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaWWW(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www-shibuya.jp/schedule/14" + String.format("%02d", month) + "/").get();
			Elements baseElements = doc.body().select("div[id=contents] div[class*=event]");
			
			this.initParam();
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
							if(date.equals("0723"))
							{
							}else{
								title = title.replaceFirst(this.stringReplaceLineBreakAndRemoveTag(span), "");
							}
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

									this.outParam(pw, 26);
								}
								type = 0;
							}
						}
						
					}
				}
			}
		}catch(Exception e){
			System.out.println("26.WWW Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaWWW(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaDuo(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.duomusicexchange.com/schedule/2014/" + String.format("%02d", month) + "/index.html").get();
			Elements baseElements = doc.body().select("td[width=670]");
			
			this.initParam();
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
							this.outParam(pw, 28);
							
							title = "";
							act = "";
							other = "";
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("28.Duo Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaDuo(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outDaikanyamaUnit(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.unit-tokyo.com/schedule//2014/" + String.format("%02d", month) + "/all_schedule.php").get();
			Elements baseElements = doc.body().select("div[class=content]");
			
			this.initParam();
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
					else if(className.contains("event_title_"))
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
						
						this.outParam(pw, 29);
					}
				}
			}
		}catch(Exception e){
			System.out.println("29.UNIT Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outDaikanyamaUnit(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outHarajukuAstroHall(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.astro-hall.com/category/schedule/?d=2014" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[id=content]");
			
			this.initParam();
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
							this.outParam(pw, 30);
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("30.ASTRO Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outHarajukuAstroHall(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outEbisuLiquidroom(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.liquidroom.net/schedule/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("dl[class=schedulelist clfx]");
			
			this.initParam();
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
						
						this.outParam(pw, 31);
					}
				}
			}
		}catch(Exception e){
			System.out.println("31.LIQUID Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outEbisuLiquidroom(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outIkebukuroOrg(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			print("ORGは1ヶ月ごとの管理をしていない！");
			Document doc = Jsoup.connect("http://minamiikebukuromusic.org/category/schedule/").get();	//月ごとの管理は無い
			Elements baseElements = doc.body().select("div[class=entry-summary]");
			
			this.initParam();
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
						
						this.outParam(pw, 32);
					}
				}
			}
		}catch(Exception e){
			System.out.println("32.ORG Failure" + e);
			return false;
		}
		return true;
	}
	
	private boolean outIkebukuroRuidoK3(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			if(currentMonth + 2 <= month){
				return false;
			}
			String url = month == currentMonth ? "http://www.ruido.org/k3/schedule/month_this/" : "http://www.ruido.org/k3/schedule/month_" + String.format("%d", month);
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("div[id=schedule_place]");
			
			this.outRuidoProject(pw, baseElements, 33, month);
		}catch(Exception e){
			System.out.println("33.RuidoK3 Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outIkebukuroRuidoK3(pw, ++month, liveHouseNo);
		return true;
	}
	private boolean outShibuyaRuidoK2(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			if(currentMonth + 2 <= month){
				return false;
			}
			String url = month == currentMonth ? "http://www.ruido.org/k2/schedule/month_this/" : "http://www.ruido.org/k2/schedule/next_month/";
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("div[id=schedule_place]");
			
			this.outRuidoProject(pw, baseElements, 34, month);
		}catch(Exception e){
			System.out.println("34.RuidoK2 Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaRuidoK2(pw, ++month, liveHouseNo);
		return true;
	}
	private boolean outShinjukuRuidoK4(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			if(currentMonth + 2 <= month){
				return false;
			}
			String url = month == currentMonth ? "http://www.ruido.org/k4/schedule/month_this/" : "http://www.ruido.org/k4/schedule/next_month/";
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("div[id=schedule_place]");
			
			this.outRuidoProject(pw, baseElements, 35, month);
		}catch(Exception e){
			System.out.println("35.RuidoK4 Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuRuidoK4(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outAkasakaBlitz(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.tbs.co.jp/blitz/schedule_a/schedule2014" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("tr[id*=s20]");
			
			this.initParam();
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
						
						this.outParam(pw, 36);
					}
				}
			}
		}catch(Exception e){
			System.out.println("36.BLITZ Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outAkasakaBlitz(pw, ++month, liveHouseNo);
		return true;
	}

	private boolean outZeppTokyo(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://hall.zepp.co.jp/tokyo/schedule.html?year=2014&month=" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class*=date_box]");
			
			this.outZeppProject(pw, baseElements, 37, month);
		}catch(Exception e){
			System.out.println("37.ZEPP TOKYO Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outZeppTokyo(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outZeppDiverCity(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://hall.zepp.co.jp/divercity/schedule.html?year=2014&month=" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class*=date_box]");
			
			this.outZeppProject(pw, baseElements, 38, month);
		}catch(Exception e){
			System.out.println("38.ZEPP DiverCity Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outZeppDiverCity(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShinjukuRedCloth(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.sputniklab.com/redcloth/14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("table[width=480][cellpadding=0] tr");
			
			this.initParam();
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
									this.outParam(pw, 39);
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
							this.outParam(pw, 39);
							title = "";
							act = "";
							other = "";
						}
					}
				}
			}
			
			if(!title.equals("") || (!act.equals("") && !act.equals(LINE_BREAK)))
			{
				this.outParam(pw, 39);
			}
		}catch(Exception e){
			System.out.println("39 RedCloth Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuRedCloth(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShinkibaStudioCoast(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.studio-coast.com/schedule/2014/"+ String.format("%02d", month) +".html").get();
			Elements baseElements = doc.body().select("section[id*=event]");
			
			this.initParam();
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
						this.outParam(pw, 42);
					}
				}
			}
		}catch(Exception e){
			System.out.println("42 coast Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinkibaStudioCoast(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outKoenjiHigh(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://koenji-high.com/schedule/?sy=2014&sm=" + month).get();
			Elements baseElements = doc.body().select("div[class=eventlist clearfix]");
			
			this.initParam();
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
									
									this.outParam(pw, 46);
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("46 high Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outKoenjiHigh(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outKoiwaBushbash(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://bushbash.org/schedule14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("td");
			
			this.initParam();
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
						
						this.outParam(pw, 47);
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("47 bushbash Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outKoiwaBushbash(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outKoenjiMissions(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.live-missions.com/schedule.html?ym=2014-"+String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("section[id*= liveDate]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("day")){
						date = String.format("%02d", month) + str;
					}else if(t.equals("h2")){
						title = str;
					}else if(c.equals("data")){
						int type = 0;
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							
							if(t.equals("dt"))
							{
								if(str.contains("ACT")) type = 1;
								else if(str.contains("OPEN")) type = 2;
								else if(str.contains("START")) type = 3;
								else if(str.contains("CHARGE")) type = 4;
							}
							else if(t.equals("dd"))
							{
								if(type == 1) 	   act = str;
								else if(type == 2) other = "OPEN " + str + " ";
								else if(type == 3) other += "START " + str + LINE_BREAK;
								else if(type == 4) other += str;
								type = 0;
							}
						}
						this.outParam(pw, 48);
					}
				}
			}
		}catch(Exception e){
			System.out.println("48.Missions Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outKoenjiMissions(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outHachiojiMatchBox(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://matchvox.rinky.info/schedule/sche10.cgi?mode=main&year=2014&mon=" + month).get();
			Elements baseElements = doc.body().select("td");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(e.attr("height").equals("20")){
						String[] split = str.split("　　");
						date = split[0];
						title = split[1];
						
						date = date.split("\\(")[0];
						date = date.split("年")[1];
						date = date.replace("日", "");
						split = date.split("月");
						date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
					}else if(e.attr("colspan").equals("2")){
						act = str;
						String[] split;
						if(act.contains("br2nOPEN")){
							split = str.split("br2nOPEN");
							act = split[0];
							other = "OPEN" + split[1];
						}else if(act.contains("br2nopen")){
							split = str.split("br2nopen");
							act = split[0];
							other = "open" + split[1];
						}else if(act.contains("br2nadv")){
							split = str.split("br2nadv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n adv")){
							split = str.split("br2n adv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n時間")){
							split = str.split("br2n時間");
							act = split[0];
							other = split[1];
						}
						this.outParam(pw, 49);
						other = "";
						act = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("49.MatchBox Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outHachiojiMatchBox(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outHachiojiRIPS(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			String url = "http://rips.rinky.info/schedule/sche10.cgi?mode=main&year=2014&mon="+month;
			Document doc = Jsoup.parse(new URL(url).openStream(),"Shift_JIS", url);
			Elements baseElements = doc.body().select("tr");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(t.equals("td")){
						if(e.attr("height").equals("20")){
							String[] split = str.split("\\)");
							title = split[1];
							date = split[0];
							date = date.split("年")[1];
							date = date.split("\\(")[0];
							split = date.split("月");
							date = this.makeDate(split[0], split[1].replace("日", ""));
						}else if(e.attr("colspan").equals("2")){
							String[] split = str.split("OPEN");
							act = split[0];
							if(split.length > 1)
							{
								other = "OPEN" + split[1];
							}
							this.outParam(pw, 50);
							title = "";
							act = "";
							other = "";
						}
					}
					
//					if(t.equals("font")){
//						String fontSize = e.attr("size"); 
//						if(fontSize.equals("+1")){
//							date = String.format("%02d%02d", month, Integer.valueOf(str));
//						}else if(fontSize.equals("3")){
//							title = str;
//						}else if(fontSize.equals("-1")){
//							if(str.equals(LINE_BREAK)){
//								continue;
//							}
//							other += str;
//						}else if(fontSize.equals("2")){
//							act = str;
//							if(title.contains("HALL RENTAL")){
//								continue;
//							}
//							String[] split = act.split(LINE_BREAK);
//							for(String s : split){
//								if(s.startsWith("チケット：")){
//									other += LINE_BREAK + s;
//									act = act.replace(s, "");
//								}
//							}
//							this.outParam(pw, 50);
//							title = "";
//							act = "";
//							other = "";
//						}
//					}
				}
			}
		}catch(Exception e){
			System.out.println("50.RIPS Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outHachiojiRIPS(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outNishiOgikuboFLAT(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://flat.rinky.info/schedule/sche10.cgi?mode=main&year=2014&mon=" + month).get();
			Elements baseElements = doc.body().select("td");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(e.attr("height").equals("20")){
						String[] split = str.split("　　");
						date = split[0];
						title = split[1];
						
						date = date.split("\\(")[0];
						date = date.split("年")[1];
						date = date.replace("日", "");
						split = date.split("月");
						date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
					}else if(e.attr("colspan").equals("2")){
						act = str;
						String[] split;
						if(act.contains("br2nOPEN")){
							split = str.split("br2nOPEN");
							act = split[0];
							other = "OPEN" + split[1];
						}else if(act.contains("br2nopen")){
							split = str.split("br2nopen");
							act = split[0];
							other = "open" + split[1];
						}else if(act.contains("br2nadv")){
							split = str.split("br2nadv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n adv")){
							split = str.split("br2n adv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n時間")){
							split = str.split("br2n時間");
							act = split[0];
							other = split[1];
						}
						this.outParam(pw, 51);
						other = "";
						act = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("51.FLAT Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outNishiOgikuboFLAT(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShinjukuJam(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://jam.rinky.info/sche/sche10.cgi?mode=main&year=2014&mon=" + month).get();
			Elements baseElements = doc.body().select("td");
			this.initParam();
			
			//TODO: フォーマットがバラバラで厳しい
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(e.attr("height").equals("20")){
						String[] split = str.split("　　");
						date = split[0];
						if(split.length > 1){
							title = split[1];
						}
						date = date.split("\\(")[0];
						date = date.split("年")[1];
						date = date.replace("日", "");
						split = date.split("月");
						date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
					}else if(e.attr("colspan").equals("2")){
						
						String[] split = str.split("br2n");
						
						for(String s : split){
							if((s.contains("OPEN") && s.contains("START")) || 
								(s.contains("open") && s.contains("start")) ||
								(s.contains("adv") && s.contains("door")) ||
								(s.contains("adv") && s.contains("day")) ||
								(s.contains("ADV") && s.contains("DOOR")) ||
								(s.contains("ADV") && s.contains("DAY")) ||
								s.contains("前売") ||
								s.contains("当日") ||
								s.contains("")){
								other += s + LINE_BREAK;
							}else{
								act += s + LINE_BREAK;
							}
						}
						
						this.outParam(pw, 52);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("52.JAM Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuJam(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShinjukuNineSpices(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://9spices.rinky.info/schedule/sche10.cgi?mode=main&year=2014&mon=" + month).get();
			Elements baseElements = doc.body().select("td");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(e.attr("height").equals("20")){
						String[] split = str.split("　　");
						date = split[0];
						if(split.length > 1){
							title = split[1];
						}
						date = date.split("\\(")[0];
						date = date.split("年")[1];
						date = date.replace("日", "");
						split = date.split("月");
						date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
					}else if(e.attr("colspan").equals("2")){
						act = str;
						String[] split;
						if(act.contains("br2nOPEN")){
							split = str.split("br2nOPEN");
							act = split[0];
							other = "OPEN" + split[1];
						}else if(act.contains("br2nopen")){
							split = str.split("br2nopen");
							act = split[0];
							other = "open" + split[1];
						}else if(act.contains("br2nadv")){
							split = str.split("br2nadv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n adv")){
							split = str.split("br2n adv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n時間")){
							split = str.split("br2n時間");
							act = split[0];
							other = split[1];
						}
						if(title.equals("") && act.equals("")){
							continue;
						}
						if(title.contains("HALL RENTAL")){
							continue;
						}
						this.outParam(pw, 53);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("53.NINESPICES Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuNineSpices(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outOtsukaMeets(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://meets.rinky.info/schedule/sche10.cgi?mode=main&year=2014&mon=" + month).get();
			Elements baseElements = doc.body().select("td");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String tagName = e.tagName();
					String className = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(e.attr("height").equals("20")){
						String[] split = str.split("　　");
						date = split[0];
						if(split.length > 1){
							title = split[1];
						}
						date = date.split("\\(")[0];
						date = date.split("年")[1];
						date = date.replace("日", "");
						split = date.split("月");
						date = String.format("%02d%02d", Integer.valueOf(split[0]), Integer.valueOf(split[1]));
					}else if(e.attr("colspan").equals("2")){
						act = str;
						String[] split;
						if(act.contains("br2nOPEN")){
							split = str.split("br2nOPEN");
							act = split[0];
							other = "OPEN" + split[1];
						}else if(act.contains("br2nopen")){
							split = str.split("br2nopen");
							act = split[0];
							other = "open" + split[1];
						}else if(act.contains("br2nadv")){
							split = str.split("br2nadv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n adv")){
							split = str.split("br2n adv");
							act = split[0];
							other = "adv" + split[1];
						}else if(act.contains("br2n時間")){
							split = str.split("br2n時間");
							act = split[0];
							other = split[1];
						}
						if(title.equals("") && act.equals("")){
							continue;
						}
						if(title.contains("HALL RENTAL")){
							continue;
						}
						this.outParam(pw, 54);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("54.MEETS Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outOtsukaMeets(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outTakadanobabaArea(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.xxxrecords.jp/area/sche_14" + String.format("%02d", month) +".html").get();
			Elements baseElements = doc.body().select("table[class=style1] tr");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(t.equals("img")){
						if(e.attr("src").contains("c_")){
							date = e.attr("src");
							date = date.replace(".gif", "");
							date = date.replace("c_", "");
							date = String.format("%02d%02d", month, Integer.valueOf(date));
						}
					}else if(!c.equals("style2") && e.attr("style").contains("85px")){
						other = str;
					}else if(!c.equals("style2")  && 
							 e.attr("style").contains("219px")){
						str = this.removeEndSpace(str);
						String[] split = str.split(LINE_BREAK+" "+LINE_BREAK);
						for(int i = 0;i < split.length;i++){
							if(i == 0)title = split[i];
							else	  act += split[i];
						}
						
						this.outParam(pw, 55);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("55.AREA Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outTakadanobabaArea(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outIkebukuroEdge(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://edge-ikebukuro.com/2014"+ String.format("%02d", month) +".html").get();
			Elements baseElements = doc.body().select("div[class=contents] tr");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(t.equals("img")){
						if(e.attr("src").contains("img/") && e.attr("src").contains(".gif")){
							date = e.attr("src");
							date = date.replace(".gif", "");
							date = date.replace("img/", "");
							date = date.replace("_h", "");
							date = String.format("%02d%02d", month, Integer.valueOf(date));
						}
					}else if(t.equals("td") && e.attr("width").equals("150")){
						other = str;
					}else if(t.equals("td") && e.attributes().size() == 0){
						act = str;
						for(Element e2 : e.getAllElements()){
							if(e2.tagName().equals("span")){
								title = this.stringReplaceLineBreakAndRemoveTag(e2);
								break;
							}
						}
						act = act.replace(title, "");
						
						this.outParam(pw, 56);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("56.EDGE Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outIkebukuroEdge(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outTachikawaBabel(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.babel-rocktower.net/schedule/2014" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("dl[class=schesettxt]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("daytxt")){
						date = str;
						date = date.split("\\(")[0];
						date = this.removeEndSpace(date);
						String[] split = date.split("/");
						date = String.format("%02d%02d", Integer.valueOf(split[0]),Integer.valueOf(split[1]));
					}else if(t.equals("dt")){
						title = "[" + str.split("\\[")[1];
					}else if(t.equals("dd")){
						String[] split = str.split(LINE_BREAK);
						other = split[0];
						if(split.length > 1){
							for(int i = 1;i < split.length;i++){
								act += split[i];
							}
						}
						
						this.outParam(pw, 57);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("57.BABEL Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outTachikawaBabel(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShimokitaMosaic(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			String url = month == currentMonth ? "http://www.studio-museum.com/mosaic/schedule/index.html" : "http://www.studio-museum.com/mosaic/schedule/2014" + String.format("%02d", month) +".html";
			Document doc = Jsoup.connect(url).get();
			
			Elements baseElements = doc.body().select("td[align=center]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				if(element.hasAttr("class")){
					continue;
				}
				boolean isAct = false;
				boolean isInfo = false;
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("font18")){
						date = this.makeDate(month, str);
					}else if(c.contains("title")){
						title = str;
					}else if(c.contains("artist")){
						isAct = true;
					}else if(c.contains("information")){
						isInfo = true;
					}else if(t.equals("td")){
						if(isAct){
							act = str;
							isAct = false;
						}else if(isInfo){
							other = str;
							isInfo = false;
							
							this.outParam(pw, 58);
						}
					}
					
				}
			}
		}catch(Exception e){
			System.out.println("58.mosaic Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaMosaic(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outYotsuyaOutBreak(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.hor-outbreak.com/live.php?ym=2014-" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("section");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("day")){
						date = this.makeDate(month, str);
					}else if(c.contains("liveInfo01")){
						int type = 0;
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(t.equals("dt")){
								if(str.contains("ACT")) 		type = 1;
								else if(str.contains("OPEN")) 	type = 2;
								else if(str.contains("START"))	type = 3;
								else if(str.contains("CHARGE"))	type = 4;
							}else if(t.equals("dd")){
								switch(type){
								case 1:
									act = str;
									break;
								case 2:
									other = "OPEN " + str + LINE_BREAK;
									break;
								case 3:
									other += "START " + str + LINE_BREAK;
									break;
								case 4:
									other += "CHARGE " + str;
									break;
								}
							}else if(t.equals("h2")){
								title = str;
							}
						}
						this.outParam(pw, 59);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("59.OUTBREAK Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outYotsuyaOutBreak(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outYotsuyaTenmado(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.otonami.com/tenmado/schedule/14" + String.format("%02d", month) +".htm").get();
			Elements baseElements = doc.body().getAllElements();
			
			this.outTenmadoProject(pw, baseElements, 60, month);
		}catch(Exception e){
			System.out.println("60.tenmado Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outYotsuyaTenmado(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outYotsuyaTenmadoComfort(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.otonami.com/comfort/schedule/14" + String.format("%02d", month) +".htm").get();
			Elements baseElements = doc.body().getAllElements();
			
			this.outTenmadoProject(pw, baseElements, 61, month);
		}catch(Exception e){
			System.out.println("61.comfort Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outYotsuyaTenmadoComfort(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outEbisuTenmadoSwitch(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.otonami.com/ebisu/schedule/14" + String.format("%02d", month) +".htm").get();
			Elements baseElements = doc.body().getAllElements();
			
			this.outTenmadoProject(pw, baseElements, 62, month);
		}catch(Exception e){
			System.out.println("62.switch Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outEbisuTenmadoSwitch(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShinokuboEarthdom(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www1.odn.ne.jp/~cfs81480/index.html/2014." + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("tr td");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(e.attr("width").contains("11%")){
						str = str.split("\\(")[0];
						date = this.makeDate(month, str);
					}else if(e.attr("width").contains("59%")){
						act = str;
						for(Element e2 : e.getAllElements()){
							if(e2.attr("color").contains("#ff0000")){
								title = this.stringReplaceLineBreakAndRemoveTag(e2);
								act = act.replace(title, "");
								break;
							}
						}
					}else if(e.attr("width").contains("17%")){
						other = str;
						if(!act.contains("各種イベント、出演バンド 募集中です")){
							this.outParam(pw, 63);
						}
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("63 earthdom Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinokuboEarthdom(pw, ++month, liveHouseNo);
		return true;
	}
	
	
	
	private boolean outHatsudaiWall(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://wall-moonstep.com/wall/wall_mobile/schedule/2014/2014_" + String.format("%02d", month) + "_mobile.html").get();
			Elements baseElements = doc.body().getAllElements();
			this.initParam();
			
			for(Element e : baseElements)
			{
				String t = e.tagName();
				String c = e.className();
				String str = this.stringReplaceLineBreakAndRemoveTag(e);
				if(t.equals("font")){
					String size = e.attr("size");
					if(size.equals("5")){
						if(!title.equals("") && !title.contains("----")){
							this.outParam(pw, 64);
							title = "";
							act = "";
						}
						date = str;
						String[] split = date.split("\\(");
						split = split[0].split("/");
						date = this.makeDate(split[0], split[1]);
					}else if(size.equals("4")){
						title = str;
					}else if(size.equals("2")){
						act = str;
					}else if(size.equals("1")){
						if(e.attr("color").equals("gray")){
							other = str;
							if(!act.equals("----")){
								this.outParam(pw, 64);
							}
							title = "";
							act = "";
							other = "";
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("64 wall Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outHatsudaiWall(pw, ++month, liveHouseNo);
		return true;
	}
	
	
	
	private boolean outKoenjiRoots(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.muribushi.jp/schedule/2014/" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("tr td[valign=top]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(c.contains("dropcap_")){
						date = str.substring(0, 2);
						date = this.makeDate(month, date);
					}else if(t.equals("p")){
						if(str.equals("")){
							continue;
						}
						other = str;
						for(Element e2 : e.getAllElements()){
							c = e2.className();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(c.contains("color2")){
								title = str;
								other = other.replace(title, "");
							}else if(c.contains("color3")){
								act = str;
								other = other.replace(act, "");
							}
						}
						if(title.equals("") && act.equals("")){
							continue;
						}
						this.outParam(pw, 65);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("65 roots Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outKoenjiRoots(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaLamama(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			String url = month == currentMonth ? "http://lamama.net/staticpages/index.php/schedule" : "http://lamama.net/staticpages/index.php/schedule2014" + String.format("%02d", month);
			Document doc = Jsoup.connect(url).get();
			Elements baseElements = doc.body().select("div[class=block-center] tr td p");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.contains("decotitle")){
						date = str;
						date = date.split("\\(")[0];
						date = this.makeDate(month, date);
					}else{
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							c = e2.className();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(t.equals("span")){
								if(e2.attr("style").contains("#3366ff")){
									title = str;
								}else if(e2.attr("style").contains("large")){
									act = str;
								}
							}else{
								if(str.contains("OPEN/") || str.contains("ADV/")){
									other = str;
									other = other.split("※")[0];
									this.outParam(pw, 66);
									title = "";
									act = "";
									other = "";
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("66 Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaLamama(pw, ++month, liveHouseNo);
		return true;
	}
	
	
	private boolean outShibuyaVision(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.vision-tokyo.com/schedule/2014-" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("td[class*=tribe-events-thismonth]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(t.equals("div") && e.attr("id").contains("daynum_")){
						if(str.length() > 5){
							str = str.substring(0, 4);
						}
						String[] split  = str.split("\\.");
						if(split.length > 1) {
							str = split[1];
						}
						if(str.length() > 2){
							str = str.substring(0, 2);
						}
						date = this.makeDate(month, str);
					}else if(c.equals("entry-title")){
						title = str;
					}else if(c.equals("entry-summary")){
						act = str;
						other = "時間・チケット価格は公式HPをご確認下さい。";
						
						this.outParam(pw, 67);
						title = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("67 Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaVision(pw, ++month, liveHouseNo);
		return true;
	}
	
	
	private boolean outShibuyaEggman(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://eggman.jp/daytime/schedule/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("dl");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(t.equals("img") && e.attr("src").contains("schedule/num/")){
						String[] split = e.attr("src").split("/");
						date = split[split.length-1];
						date = date.split("\\.")[0];
						date = this.makeDate(month, date);
					}else if(t.equals("h2")){
						title = str;
					}else if(t.equals("p")){
						act = str;
						other = "時間・チケット価格は公式HPをご確認下さい。";
						
						this.outParam(pw, 68);
						title = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("68 eggman Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaEggman(pw, ++month, liveHouseNo);
		return true;
	}
	

	private boolean outShinjukuBlaze(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://shinjuku-blaze.com/event/date/2014/" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=eventlist]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("day")){
						date = this.makeDate(month, str);
					}else if(t.equals("h1")){
						title = str;
					}else if(c.contains("eventlist-detail")){
						int type = 0;
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							c = e2.className();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(t.equals("dt")){
								if(str.contains("OPEN"))	 type = 1;
								else if(str.contains("ADV"))type = 2;
								else if(str.contains("LINE UP")) type = 3;
							}else if(t.equals("dd")){
								if(type==1){
									other = "OPEN/START " + str + LINE_BREAK;
								}else if(type==2){
									other +="ADV/DOOR " + str;
								}else if(type==3){
									act = str;
									this.outParam(pw, 69);
									title = "";
									other = "";
									type = 0;
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("69 blaze Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShinjukuBlaze(pw, ++month, liveHouseNo);
		return true;
	}

	
	private boolean outShimokitaGarage(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.garage.or.jp/wp/2014" + String.format("%02d", month) +"sch").get();
			Elements baseElements = doc.body().select("table");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(t.equals("h4")){
						if(str.equals("")){
							continue;
						}
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							c = e2.className();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							if(t.equals("span")){
								if(e2.attr("style").contains("background-color: #000000")){
							
									String[] split = str.split("\\(");
									split = split[0].split("/");
									date = this.makeDate(split[0], this.removeEndSpace(split[1]));
								}else if(e2.attr("style").contains("ff0099") || e2.attr("style").contains("ff1493")){
									title += str;
								}
							}else if(t.equals("h4")){
								String html = e.html();
								if(!html.contains("ff0099") && !html.contains("ff1493")){
									if(str.contains("http://")){
										print("■■http contains::" + str);
										continue;
									}
									act += str;
								}
							}
						}
					}else if(t.equals("p")){
						if(str.contains("open") || str.contains("前売")){
							other = str;
							if(!title.equals("") || !act.equals("")){
								this.outParam(pw, 70);
							}
							title = "";
							act = "";
							other = "";
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("70 garage Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaGarage(pw, ++month, liveHouseNo);
		return true;
	}

	private boolean outShimokitaYaneura(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://shimoyane.com/cgi-bin/el-calendar/index.php").get();
			Elements baseElements = doc.body().select("tr td");
			this.initParam();
			
			String targetMonth = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("td-month01")){
						targetMonth = str;
					}else if(c.equals("td-calendar01")){
						date = this.makeDate(targetMonth, str);
					}else if(c.equals("td-calendar03")){
						if(str.equals(" ")){
							continue;
						}
						
						int type = 1;
						for(String s : str.split(LINE_BREAK)){
							//before
							if(s.contains("00/") || 
								s.contains("15/") ||
								s.contains("30/") || 
								s.contains("45/")){
								type = 3;
							}
							//----
							
							if(type==1){
								title += s + LINE_BREAK;
							}else if(type==2){
								act += s + LINE_BREAK;
							}else if(type==3){
								other += s + LINE_BREAK;
							}
							
							//after
							if(s.contains("YEN/") || s.endsWith("YEN")){
								this.outParam(pw, 71);
								title = "";
								act = "";
								other = "";
								type = 1;
							}
							if(s.contains("】")){
								type = 2;
							}
						}
						
					}
				}
			}
		}catch(Exception e){
			System.out.println("71　屋根裏 Failure" + e);
			return false;
		}
		return true;
	}

	
	private boolean outShimokitaReg(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.reg-r2.com/?page_id=7250&ym=2014-" + String.format("%02d", month)).get();
			Elements baseElements = doc.body().select("div[class=textbody]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(t.equals("td") && c.contains("date_")){
						date = str.split(LINE_BREAK)[0];
						date = date.replace(" ", "");
						String[] split = date.split("/");
						date = this.makeDate(this.removeEndSpace(split[0]), this.removeEndSpace(split[1]));
					}else if(c.contains("live_title")){
						title += str + LINE_BREAK;
					}else if(c.equals("performer_name")){
						act = str;
						this.outParam(pw, 72);
						title = "";
						act = "";
						other = "";
					}else if(c.equals("time_price")){
						other = str;
					}
				}
			}
		}catch(Exception e){
			System.out.println("72 ReG Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShimokitaReg(pw, ++month, liveHouseNo);
		return true;
	}

	private boolean outDaikanyamaLoop(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.live-loop.com/schedule/dl/schedule_14" + String.format("%02d", month) +".html").get();
			Elements baseElements = doc.body().select("div[id=schedule]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("date")){
						date = str.substring(0, 5);
						String[] split = date.split("\\.");
						date = this.makeDate(split[0], split[1]);
					}else if(c.equals("title")){
						title = str;
					}else if(c.equals("name")){
						act = str;
					}else if(c.equals("info")){
						other = str;
						this.outParam(pw, 73);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("73 LOOP Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outDaikanyamaLoop(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outShibuyaLoopAnnex(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.live-loop.com/schedule/sl/schedule_14" + String.format("%02d", month) +".html").get();
			Elements baseElements = doc.body().select("div[id=schedule]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(c.equals("date")){
						date = str.substring(0, 5);
						String[] split = date.split("\\.");
						date = this.makeDate(split[0], split[1]);
					}else if(c.equals("title")){
						title = str;
					}else if(c.equals("name")){
						act = str;
					}else if(c.equals("info")){
						other = str;
						this.outParam(pw, 74);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("74 LOOP Annex Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outShibuyaLoopAnnex(pw, ++month, liveHouseNo);
		return true;
	}
	
	
	private boolean outTakadanobabaPhase(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.club-phase.com/contents/sched/14" + String.format("%02d", month) + ".html").get();
			Elements baseElements = doc.body().select("table[id=sched]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(c.equals("sc_date")){
						if(!title.equals("") || !act.equals("") || !title.equals("NOW PLANNING")){
							this.outParam(pw, 75);
							title = "";
							act = "";
							other = "";
						}
						date = this.makeDate(month, str);
					}else if(t.equals("td")){
						title = str;
					}else if(c.equals("sc_artist")){
						act = str;
						title = title.replace(act, "");
					}else if(c.equals("sc_price")){
						other = str;
						title = title.replace(other, "");
						this.outParam(pw, 75);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("75.Phase Failure" + e);
			return false;
		}
		if(month >= 12){
			return true;
		}
		outTakadanobabaPhase(pw, ++month, liveHouseNo);
		return true;
	}

	
	
	private boolean outDaikanyamaHaremame(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://mameromantic.com/?cat=1").get();
			Elements baseElements = doc.body().select("div[class=entry]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(t.equals("h3")){
						String[] split = str.split("｜");
						date = split[0];
						for(int i = 1;i < split.length;i++){
							title += split[i];
						}
						
						split = date.split("\\.");
						date = this.makeDate(split[1], split[2]);
					}else if(t.equals("p")){
						act += str;
						other = "時間・チケット価格は公式HPをご確認下さい。";
						this.outParam(pw, 76);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("76 Failure" + e);
			return false;
		}
		return true;
	}
	
	private boolean outGakugeidaigakuMapleHouse(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			//TODO:
			print("HPのURLの作りが現時点で予想できない");
			Document doc = Jsoup.connect("http://www.maplehouse.jp/schedule/schedule.html").get();
			Elements baseElements = doc.body().select("div[id=area-main]");
			this.initParam();
			
			int type = 0;
			String stage = "";
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(c.equals("fsize_l")){
						if(e.hasAttr("style")){
							if(e.attr("style").contains("#0000ff") || e.attr("style").contains("#ff0000") || e.attr("style").contains("#ff00ff") || e.attr("style").contains("#4e4650")){
								type = 1;
							}else{
								print("■77.continue::"+str);
								continue;
							}
						}
						if(str.equals("3000-")){
							continue;
						}
						
						if(str.startsWith("[")){
							type = 2;
						}
						if(str.startsWith("open") || str.contains("前売券") || str.contains("charge¥")){
							type = 4;
						}
						switch(type){
						case 0:
							String monthString = str.split("月Live")[0];
							month = Integer.valueOf(monthString);
							break;
						case 1:
							if(str.contains("A-stage")){
								stage = "A-stage";
							}else if(str.contains("B-stage")){
								stage = "B-stage";
							}else{
								stage = "";
							}
							String tmp = str;
							
							if(!tmp.equals("日"))
							{
								tmp = tmp.split("日")[0];
								if(NumberUtils.isNumber(tmp))
								{
									date = this.makeDate(month, tmp);
								}
							}
							type = 3;
							break;
						case 2:
							title = str + " " + stage;
							type = 3;
							break;
						case 3:
							act += str + LINE_BREAK;
							break;
						case 4:
							other = str;
							if(!title.equals("") || !act.equals("")){
								this.outParam(pw, 77);
							}
							title = "";
							act = "";
							other = "";
							type = 0;
							break;
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("77 MapleHouse Failure" + e);
			return false;
		}
		return true;
	}
	
	private boolean outAoyamaTsukimiru(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.moonromantic.com/?cat=1").get();
			Elements baseElements = doc.body().select("div[class=entry]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					if(t.equals("h3")){
						String[] split = str.split("｜");
						date = split[0];
						for(int i = 1;i < split.length;i++){
							title += split[i];
						}
						
						split = date.split("\\.");
						date = this.makeDate(split[1], split[2]);
					}else if(t.equals("p")){
						act += str;
						other = "時間・チケット価格は公式HPをご確認下さい。";
						this.outParam(pw, 78);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("78 月見る Failure" + e);
			return false;
		}
		return true;
	}
	
	private boolean outChibaLook(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://inochigake.com/schedule/index.html").get();
			Elements baseElements = doc.body().select("table[width=560] td");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(e.attr("width").equals("560")){
						title = "";
						act = "";
						other = "";
						
						String[] split = str.split(" ");
						if(split.length > 2){
							date = this.makeDate(month, split[2]);
						}
						else
						{
							print(str + " continue");
						}
					}else if(e.attr("width").equals("480")){
						String[] split = str.split("br2n");
						title = split[0];
						
						for(int i = 0;i < split.length;i++){
							if(i == 0){
								title = split[i];
							}else{
								act += split[i] + "br2n";
							}
						}
						act = act.split("＊")[0];
					}else if(e.attr("width").equals("50")){
						if(other.equals("")){
							other += "OPEN:";
						}else{
							other += "CHARGE:";
						}
						other += str + "br2n";
						if(other.contains("￥")){
							this.outParam(pw, liveHouseNo);
							title = "";
							act = "";
							other = "";
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("79 LOOK Failure" + e);
			return false;
		}
		return true;
	}
	
	private boolean outShinmatsudoFirebird(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://www.aj-group.co.jp/schedule/2014/" + String.format("%02d", month) +".html").get();
			Elements baseElements = doc.body().select("body");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(t.equals("h4")){
						title = "";
						act = "";
						other = "";
						
						String[] split = str.split("\\(");						
						split = split[0].split("/");
						date = this.makeDate(split[0], split[1]);
						
						split = str.split("\\)");
						for(int i = 1;i < split.length;i++){
							title += split[1];
						}
					}else if(t.equals("b")){
						act = str;
						other = "時間・チケット価格は公式HPをご確認下さい。";
						this.outParam(pw, liveHouseNo);
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("80 FIREBIRD Failure" + e);
			return false;
		}
		this.outShinmatsudoFirebird(pw, ++month, liveHouseNo);
		return true;
	}
	
	private boolean outYokohamaBaysis(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			int count = month - currentMonth;
			if(month > 12){
				return true;
			}
			Document doc = Jsoup.connect("http://www.yokohamabaysis.com/schedule.php?mt=" + count + "#schedule_list").get();
			Elements baseElements = doc.body().select("div[class=sbox clearfix]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(c.equals("day")){
						date = this.makeDate(month, str.substring(0, 2));
					}else if(t.equals("h4")){
						title = str;
					}else if(c.equals("guest")){
						act = str;
					}else if(c.equals("open")){
						other = str;
						other = other.replace("│", "br2n");
						other = other.replace("L:", "br2nL:");
						this.outParam(pw, liveHouseNo);
						
						title = "";
						act = "";
						other = "";
					}
				}
			}
		}catch(Exception e){
			System.out.println("81 BAYSIS Failure" + e);
			return false;
		}
		
		this.outYokohamaBaysis(pw, ++month, liveHouseNo);
		
		return true;
	}
	
	private boolean outYokohamaGalaxy(PrintWriter pw, int month, int liveHouseNo)
	{
		if(month > 12){
			return true;
		}
		
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			Document doc = Jsoup.connect("http://yokohama-galaxy.com/htdocs/wordpress/?yr=2014&month=" + month + "&dy=&cid=all").get();
			Elements baseElements = doc.body().select("ul[class=mc-list]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(c.equals("event-date")){
						String[] split = str.split("日");
						split = split[0].split("月");
						date = this.makeDate(month, split[1]);
					}else if(c.equals("details")){
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							c = e2.className();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							
							if(t.equals("h3")){
								title = str;
							}else if(c.equals("longdesc")){
								for(Element e3 : e2.select("p")){
									str = this.stringReplaceLineBreakAndRemoveTag(e3);
									if(str.contains("info)")){
										continue;
									}
									
									if(str.contains("open") && str.contains("/")){
										other += str;
										continue;
									}
									act += str + "br2n";
								}
								
								this.outParam(pw, liveHouseNo);
								title = "";
								act = "";
								other = "";
							}
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("82 Galaxy Failure" + e);
			return false;
		}
		
		this.outYokohamaGalaxy(pw, ++month, liveHouseNo);
		
		return true;
	}
	
	private boolean outKichijoujiWarp(PrintWriter pw, int month, int liveHouseNo)
	{
		print("■■" + liveHouseNo + "-" +  String.format("%02d", month));
		try{
			//TODO: まだ不完全。１ヶ月ずつ確実に取る。
			WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
			String url = month == currentMonth ? "http://warp.rinky.info/schedules/calendarSchedules" : "http://warp.rinky.info/schedules/";
			HtmlPage page = client.getPage(url);
			String body;
			if(month == currentMonth)
			{
				body = page.asXml();
			}else{
				int count = month - currentMonth;
				if(count >= 2){
					//翌月まで取得
					return true;
				}
				HtmlPage nextPage = null;
				HtmlSpan article = ((List<HtmlSpan>)page.getByXPath("//span[@id='next']")).get(0);
				
				for(int i = 0;i < count;i++){
		            nextPage = article.click();
		        }
				body = nextPage.asXml();
			}
			print(body);
			Document doc = Jsoup.parse(body);
			Elements baseElements = doc.body().select("div[class=scheduleInner]");
			this.initParam();
			
			for(Element element : baseElements)
			{
				for(Element e : element.getAllElements())
				{
					String t = e.tagName();
					String c = e.className();
					String str = this.stringReplaceLineBreakAndRemoveTag(e);
					
					if(c.equals("day")){
						date = this.makeDate(month, str);
					}else if(c.equals("contents")){
						for(Element e2 : e.getAllElements()){
							t = e2.tagName();
							c = e2.className();
							str = this.stringReplaceLineBreakAndRemoveTag(e2);
							
							if(t.equals("h2")){
								title = str + LINE_BREAK;
							}else if(t.equals("h3")){
								title += str;
							}else if(c.equals("artist")){
								act = str;
							}
						}
					}else if(c.equals("time")){
						other = str + LINE_BREAK;
					}else if(c.equals("price")){
						other += str;
						if(!act.contains("comming soon") && !(title.equals("") && act.equals(""))){
							this.outParam(pw, 40);
						}
						title = "";
						act = "";
						other = "";
					}
				}
			}
			outKichijoujiWarp(pw, ++month, liveHouseNo);
		}catch(Exception e){
			System.out.println("40.WARP Failure" + e);
			return false;
		}
		return true;
	}
	
	//TODO: 最終ライン
//	private void out(PrintWriter pw, int month, int liveHouseNo)
//	{
//		try{
//			Document doc = Jsoup.connect("").get();
//			Elements baseElements = doc.body().select("");
//			this.initParam();
//			
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String t = e.tagName();
//					String c = e.className();
//					String str = this.stringReplaceLineBreakAndRemoveTag(e);
//				}
//			}
//		}catch(Exception e){
//			System.out.println(" Failure" + e);
//		}
//	}
//	private void out(PrintWriter pw, int month, int liveHouseNo)
//	{
//		try{
//			Document doc = Jsoup.connect("").get();
//			Elements baseElements = doc.body().select("");
//			this.initParam();
//			
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String t = e.tagName();
//					String c = e.className();
//					String str = this.stringReplaceLineBreakAndRemoveTag(e);
//				}
//			}
//		}catch(Exception e){
//			System.out.println(" Failure" + e);
//		}
//	}
//	private void out(PrintWriter pw, int month, int liveHouseNo)
//	{
//		try{
//			Document doc = Jsoup.connect("").get();
//			Elements baseElements = doc.body().select("");
//			this.initParam();
//			
//			for(Element element : baseElements)
//			{
//				for(Element e : element.getAllElements())
//				{
//					String t = e.tagName();
//					String c = e.className();
//					String str = this.stringReplaceLineBreakAndRemoveTag(e);
//				}
//			}
//		}catch(Exception e){
//			System.out.println(" Failure" + e);
//		}
//	}

	private void outTenmadoProject(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		this.initParam();
		
		for(Element e : baseElements)
		{
			String t = e.tagName();
			String c = e.className();
			String str = this.stringReplaceLineBreakAndRemoveTag(e);
			
			if(t.equals("img")){
				String width = e.attr("width");
				if(!width.equals("23")){
					continue;
				}
				String srcAttr = e.attr("src");
				String[] split = srcAttr.split("/");
				srcAttr = split[split.length - 1];
				srcAttr = srcAttr.split("\\.")[0];
				if(srcAttr.matches("^[0-9]{1,2}$")) {
					date = this.makeDate(month, srcAttr);
				}
			}else if(c.equals("style20")){
				title = str;
			}else if(t.equals("tr")){
				if(!str.contains("op/") && !str.contains("ド別")){
					continue;
				}
				String[] split = str.split(LINE_BREAK);
				boolean isAct = true;
				for(int i = 0;i < split.length;i++){
					if(split[i].contains("op/") || split[i].contains("ド別")){
						isAct = false;
					}
					if(isAct) act += split[i] + LINE_BREAK;
					else 	other += split[i] + LINE_BREAK;
				}
				if(!title.equals("") || !other.equals("")){
					this.outParam(pw, liveHouseNo);
				}
				title = "";
				act = "";
				other = "";
			}
		}
	}
	private void outZeppProject(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		this.initParam();
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
							
							this.outParam(pw, liveHouseNo);
						}
					}
				}
			}
		}
	}
	
	private void outRuidoProject(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		this.initParam();
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
					
					this.outParam(pw, liveHouseNo);
				}
			}
		}
	}
	private void outLoftProject(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		this.initParam();
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
						//	print("■■" + liveHouseNo + "::" + date + "::other Failure");
						}
					}
				}
			}
		}
	}
	
	private void outTsutayaOGroup(PrintWriter pw, Elements baseElements, int liveHouseNo, int month)
	{
		this.initParam();
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
							this.outParam(pw, liveHouseNo);
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
			if(s.startsWith(" ") || s.startsWith("　") || s.startsWith("  "))
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
		act = this.removeEndSpace(act);
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
		act = act.replace("d/i/s/c/o/s", "d／i／s／c／o／s");
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
		other = other.replace("\\", "¥");
		if(debugFlag == 1){
			print(other);
		}else{
			pw.println(other);
		}
		if(other.contains("こちら") || other.contains("コチラ") || other.contains("http") )
		{
		//	print("■■FAILURE::" + date + "::" + other + "::リンクあり");
		}
	}
	
	private void print(String s)
	{
		System.out.println(s);
	}
	
	private void initParam(){
		date = "";
		title = "";
		act = "";
		other = "";
	}
	
	private void outParam(PrintWriter pw, int liveHouseNo){
		if( date.compareTo(this.makeDate(currentMonth, String.valueOf(currentDate))) < 0 )
		{
			return;
		}
		if((title.contains("ホールレンタル") 	&& act.equals("")) ||  
			(title.contains("HALL RENTAL") 	&& act.equals("")) ||
			(title.equals("") 				&& act.equals("")) ){
			return;
		}
		pw.print(liveHouseNo + TAB);
		this.outDate(pw, date);
		this.outTitle(pw, title);
		this.outAct(pw, act, date);
		this.outOther(pw, other, date);
	}
	
	private String makeDate(String month, String day){
		return String.format("%02d%02d", Integer.valueOf(month), Integer.valueOf(day));
	}
	private String makeDate(int month, String day){
		return String.format("%02d%02d", month, Integer.valueOf(day));
	}
}
