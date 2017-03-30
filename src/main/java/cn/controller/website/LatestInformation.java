package cn.controller.website;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.base.util.PropertiesConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
//@RequestMapping("latestInformation")
public class LatestInformation {
	@RequestMapping(value="latestInformation" ,method = RequestMethod.GET)
	public String demo_kline(Model model,HttpServletRequest request) throws IOException{
		
        String commodity = request.getParameter("commodity");
        String readLastLine = "";
        String name = "";
        String latestPrice = ""; //最新价
        String openPrice = ""; //开盘价
        String highestPrice = ""; //最高价
        String lowestPrice = ""; //最低价
        String averagePrice = ""; //均价
        String balancePrice = ""; //结算价
        String yesterdayClose = ""; //昨收
        String yesterdayBalance = ""; //昨结
        String volume = ""; //成交量
        String obv = "";  //成交额
        String openInterest = ""; //持仓量
        String buy1 = ""; //买1
        String sale1 = ""; //卖1
        String upsAndDowns = ""; //涨跌幅百分比
        StringBuilder jsonString = new StringBuilder("");
        List<String> list = new ArrayList<String>();
        list.add("HTAg");
        list.add("AgSTS");
        list.add("AgJC");

        if (commodity == null){
            //List去重 start
            HashSet h  =   new HashSet(list);
            list.clear();
            list.addAll(h);
            //List去重 end
            for (int i = 0; i < list.size(); i++) {
//            	File file = new File("D:\\montain\\"+ list.get(i) +".txt");
                File file = new File(PropertiesConfig.readData("website.properties", "latestinformation.path") + list.get(i) +".txt");

                readLastLine = readLastLine(file, "UTF-8");
                name = readLastLine.substring(readLastLine.indexOf("14=")+3,readLastLine.indexOf("15=")-1); //产品中文名
                latestPrice = readLastLine.substring(readLastLine.indexOf("15=")+3,readLastLine.indexOf("16=")-1); //最新价
                openPrice = readLastLine.substring(readLastLine.indexOf("16=")+3,readLastLine.indexOf("17=")-1); //开盘价
                highestPrice = readLastLine.substring(readLastLine.indexOf("17=")+3,readLastLine.indexOf("18=")-1); //最高价
                lowestPrice = readLastLine.substring(readLastLine.indexOf("18=")+3,readLastLine.indexOf("19=")-1); //最低价
                averagePrice = readLastLine.substring(readLastLine.indexOf("19=")+3,readLastLine.indexOf("20=")-1); //均价
                balancePrice = readLastLine.substring(readLastLine.indexOf("20=")+3,readLastLine.indexOf("21=")-1); //结算价
                yesterdayClose = readLastLine.substring(readLastLine.indexOf("21=")+3,readLastLine.indexOf("22=")-1); //昨收
                yesterdayBalance = readLastLine.substring(readLastLine.indexOf("22=")+3,readLastLine.indexOf("23=")-1); //昨结
                volume = readLastLine.substring(readLastLine.indexOf("23=")+3,readLastLine.indexOf("24=")-1); //成交量
                obv = readLastLine.substring(readLastLine.indexOf("24=")+3,readLastLine.indexOf("25=")-1);  //成交额
                openInterest = readLastLine.substring(readLastLine.indexOf("25=")+3,readLastLine.indexOf("50=")-1); //持仓量
                buy1 = readLastLine.substring(readLastLine.indexOf("50=")+3,readLastLine.indexOf("70=")-1); //买1
                sale1 = readLastLine.substring(readLastLine.indexOf("90=")+3,readLastLine.indexOf("100=")-1); //卖1
                Double newestPrice = Double.parseDouble(latestPrice);
                Double yesterPrice = Double.parseDouble(yesterdayClose);
                //涨跌百分比
                NumberFormat nt = NumberFormat.getPercentInstance();
                nt.setMinimumFractionDigits(2);
                upsAndDowns = nt.format((newestPrice - yesterPrice) / yesterPrice); //涨跌幅百分比
                Double valueOfUpOrDown = newestPrice - yesterPrice; //涨跌值
                if(list.get(i).equals("AgSTS")){
                    name = "白银升贴水1000";
                }
                String information = "\"commodity\"" + ":" + "\"" + list.get(i) + "\"" + "," +
                        "\"name\"" + ":" + "\"" + name + "\"" + "," +
                        "\"openPrice\""+":" +"\"" + openPrice +"\"" + "," +
                        "\"latestPrice\"" + ":" + "\"" + latestPrice + "\"" + "," +
                        "\"highestPrice\"" + ":" + "\"" + highestPrice + "\"" + "," +
                        "\"lowestPrice\"" + ":" + "\"" + lowestPrice + "\"" + "," +
                        "\"averagePrice\"" + ":" + "\"" + averagePrice + "\"" + "," +
                        "\"balancePrice\"" + ":" + "\"" + balancePrice + "\"" + "," +
                        "\"yesterdayClose\"" + ":" + "\"" + yesterdayClose + "\"" + "," +
                        "\"yesterdayBalance\"" + ":" + "\"" + yesterdayBalance + "\"" + "," +
                        "\"volume\"" + ":" + "\"" + volume + "\"" + "," +
                        "\"obv\"" + ":" + "\"" + obv + "\"" + "," +
                        "\"openInterest\"" + ":" + "\"" + openInterest + "\"" + "," +
                        "\"buy1\"" + ":" + "\"" + buy1 + "\"" + "," +
                        "\"sale1\"" + ":" + "\"" + sale1 + "\"" + "," +
                        "\"upsAndDowns\"" + ":" + "\"" + upsAndDowns + "\"" + "," +
                        "\"valueOfUpOrDown\"" + ":" + "\"" + valueOfUpOrDown + "\"";
                if(i == 0) {
                    jsonString.append("[" + "{" +
                            information +
                            "},");
                }else if(i == list.size() -1){
                    jsonString.append("{" +
                            information +
                            "}]");
                }else {
                    jsonString.append("{" +
                            information +
                            "},");
                }
            }

        }else {
//            File file = new File("D:\\montain\\"+commodity+".txt");
        	File file = new File(PropertiesConfig.readData("website.properties", "latestinformation.path")+commodity+".txt");

            readLastLine = readLastLine(file, "UTF-8");
            name = readLastLine.substring(readLastLine.indexOf("14=")+3,readLastLine.indexOf("15=")-1); //产品中文名
            latestPrice = readLastLine.substring(readLastLine.indexOf("15=")+3,readLastLine.indexOf("16=")-1); //最新价
            openPrice = readLastLine.substring(readLastLine.indexOf("16=")+3,readLastLine.indexOf("17=")-1); //开盘价
            highestPrice = readLastLine.substring(readLastLine.indexOf("17=")+3,readLastLine.indexOf("18=")-1); //最高价
            lowestPrice = readLastLine.substring(readLastLine.indexOf("18=")+3,readLastLine.indexOf("19=")-1); //最低价
            averagePrice = readLastLine.substring(readLastLine.indexOf("19=")+3,readLastLine.indexOf("20=")-1); //均价
            balancePrice = readLastLine.substring(readLastLine.indexOf("20=")+3,readLastLine.indexOf("21=")-1); //结算价
            yesterdayClose = readLastLine.substring(readLastLine.indexOf("21=")+3,readLastLine.indexOf("22=")-1); //昨收
            yesterdayBalance = readLastLine.substring(readLastLine.indexOf("22=")+3,readLastLine.indexOf("23=")-1); //昨结
            volume = readLastLine.substring(readLastLine.indexOf("23=")+3,readLastLine.indexOf("24=")-1); //成交量
            obv = readLastLine.substring(readLastLine.indexOf("24=")+3,readLastLine.indexOf("25=")-1);  //成交额
            openInterest = readLastLine.substring(readLastLine.indexOf("25=")+3,readLastLine.indexOf("50=")-1); //持仓量
            buy1 = readLastLine.substring(readLastLine.indexOf("50=")+3,readLastLine.indexOf("70=")-1); //买1
            sale1 = readLastLine.substring(readLastLine.indexOf("90=")+3,readLastLine.indexOf("100=")-1); //卖1
            Double newestPrice = Double.parseDouble(latestPrice);
            Double yesterPrice = Double.parseDouble(yesterdayClose);
            //涨跌百分比
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(2);
            upsAndDowns = nt.format((newestPrice - yesterPrice) / yesterPrice); //涨跌幅百分比
            Double valueOfUpOrDown = newestPrice - yesterPrice; //涨跌值
            if(commodity.equals("AgSTS")){
                name = "白银升贴水1000";
            }
            jsonString.append("{"+
                    "\"commodity\""+":" +"\"" + commodity +"\"" + "," +
                    "\"name\""+":" +"\"" + name +"\"" + "," +
                    "\"openPrice\""+":" +"\"" + openPrice +"\"" + "," +
                    "\"latestPrice\""+":" +"\"" + latestPrice +"\"" + "," +
                    "\"highestPrice\""+":" +"\"" + highestPrice +"\"" + "," +
                    "\"lowestPrice\""+":" +"\"" + lowestPrice +"\"" + "," +
                    "\"averagePrice\""+":" +"\"" + averagePrice +"\"" + "," +
                    "\"balancePrice\""+":" +"\"" + balancePrice +"\"" + "," +
                    "\"yesterdayClose\""+":" +"\"" + yesterdayClose +"\"" + "," +
                    "\"yesterdayBalance\""+":" +"\"" + yesterdayBalance +"\"" + "," +
                    "\"volume\""+":" +"\"" + volume +"\"" + "," +
                    "\"obv\""+":" +"\"" + obv +"\"" + "," +
                    "\"openInterest\""+":" +"\"" + openInterest +"\"" + "," +
                    "\"buy1\""+":" +"\"" + buy1 +"\"" + "," +
                    "\"sale1\""+":" +"\"" + sale1 +"\"" + "," +
                    "\"upsAndDowns\""+":" +"\"" + upsAndDowns +"\"" + "," +
                    "\"valueOfUpOrDown\""+":" +"\"" + valueOfUpOrDown +"\"" +
                    "}");
        }

        model.addAttribute("latestInformation", jsonString);
        return "/website/latestInformation";
    }

    public static String readLastLine(File file, String charset) throws IOException {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            long len = raf.length();
            if (len == 0L) {
                return "";
            } else {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raf.seek(0);
                }
                byte[] bytes = new byte[(int) (len - pos)];
                raf.read(bytes);
                if (charset == null) {
                    return new String(bytes);
                } else {
                    return new String(bytes, charset);
                }
            }
        } catch (FileNotFoundException e) {
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception e2) {
                }
            }
        }
        return null;
    }
}
