package com.cndatacom.rbac.system.web.action;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class test {
    
    public  Document getDocument (String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, WriteException {
    	test t = new test();
//        Document doc = t.getDocument("https://cn.konest.com/contents/food_map.html?id=9403");
//        // 获取目标HTML代码
//        Elements elements1 = doc.select("div[class=detail_taxi]");
//        System.out.println("韩文店名："+elements1.select("p[class=tit1]").text().toString());
//        System.out.println("中文店名："+elements1.select("p[class=tit2]").text().toString());
////        System.out.println("电话："+elements1.html().toString());
//        
//        Elements elements2 = elements1.select("div[class=con_area]");
//        System.out.println("韩文地址："+elements2.select("p").get(0).text().toString());
//        System.out.println("中文地址："+elements2.select("p").get(2).text().toString().split("/")[0].trim());
//        
//        Elements elements3 = elements2.select("div[class=info2]");
//        System.out.println("营业时间："+elements3.select("li[class=b1]").text().toString());
//        System.out.println("休息日："+elements3.select("li[class=b2]").text().toString());
//        System.out.println("交通："+elements3.select("li[class=b3]").text().toString());
    	
        
        Document doc = t.getDocument("https://cn.konest.com/contents/food_map.html?id=17372");
        Elements elements3 = doc.select("div[class=bot]");
        System.out.println(elements3.select("a").text().toString());
        
    }
	
    
    public static void outputExcelData() throws IOException, WriteException {  
        /**定义需要导出的实体*/  
    	List result = new ArrayList();
        User user = new User();
        user.setId("1");
        user.setName("yfli");
        result.add(user);
        User user2 = new User();
        user2.setId("1");
        user2.setName("zhangjie");
        result.add(user2);
        User user3 = new User();
        user3.setId("1");
        user3.setName("lzhang");
        result.add(user3);
      
      //创建文件本地文件   
        String fileName = "F:\\sfData.xls";

        //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象      
        WritableWorkbook wwb = Workbook.createWorkbook(new File(fileName));
        File dbfFile = new File(fileName);
        if (!dbfFile.exists() || dbfFile.isDirectory()) {
            dbfFile.createNewFile();
        }

        int totle = result.size();//获取List集合的size
        int mus = 2;//每个工作表格最多存储2条数据（注：excel表格一个工作表可以存储65536条）
        int avg = totle / mus;
        for (int i = 0; i < avg + 1; i++) {
            WritableSheet ws = wwb.createSheet("列表" + (i + 1), i);  //创建一个可写入的工作表  

            //添加excel表头
            ws.addCell(new Label(0, 0, "序号")); 
            ws.addCell(new Label(1, 0, "姓名"));
            int num = i * mus; 
            int index = 0; 
            for (int m = num; m < result.size(); m++) {
                if (index == mus) {//判断index == mus的时候跳出当前for循环
                    break;
                }
                User use = (User) result.get(m);

		//将生成的单元格添加到工作表中 
		//（这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行）     
                ws.addCell(new Label(0, index + 1, use.getId()));
                ws.addCell(new Label(1, index + 1, use.getName()));
                index++;
            }
        }
        wwb.write();//从内存中写入文件中   
        wwb.close();//关闭资源，释放内存      
    }
}