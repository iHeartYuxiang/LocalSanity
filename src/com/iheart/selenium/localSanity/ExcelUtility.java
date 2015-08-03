package com.iheart.selenium.localSanity;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.util.Iterator;

//import jxl.*; 

public class ExcelUtility {
	
		private static final String SITE_LIST_FILE = "C:\\Users\\1111128\\workspace\\LocalSanity\\siteList.xlsx";
	    private static final String FILE_PATH = "C:\\Users\\1111128\\workspace\\LocalSanity\\EXCEL";
	    
	    //We are making use of a single instance to prevent multiple write access to same file.
	    private static final ExcelUtility INSTANCE = new ExcelUtility();

	    public static ExcelUtility getInstance() {
	        return INSTANCE;
	    }

	    private ExcelUtility() {
	    }

	    public static List getSiteListFromExcel() {
	        List<String> siteList = new ArrayList<String>();
	        FileInputStream fis = null;
	        try {
	            fis = new FileInputStream(SITE_LIST_FILE);

	            // Using XSSF for xlsx format, for xls use HSSF
	            Workbook workbook = new XSSFWorkbook(fis);

	            int numberOfSheets = workbook.getNumberOfSheets();

	            //looping over each workbook sheet
	            for (int i = 0; i < numberOfSheets; i++) {
	                Sheet sheet = workbook.getSheetAt(i);
	                Iterator rowIterator = sheet.iterator();

	                //iterating over each row
	                while (rowIterator.hasNext()) {

	                   
	                    Row row = (Row) rowIterator.next();
	                    Iterator cellIterator = row.cellIterator();

	                    //Iterating over each cell (column wise)  in a particular row.
	                    while (cellIterator.hasNext()) {

	                        Cell cell = (Cell)cellIterator.next();
	                        //end iterating a row, add all the elements of a row in list
	                        siteList.add(cell.getStringCellValue());
	                    }
	                }   
	            }
	                

	            fis.close();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        for (String site: siteList)
	        	System.out.println("Read in site:" + site);
	        
	        return siteList;
	    }
	    
	    public static void writeToExcel( List<BadLink> brokenLinkList)
	    {  
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
   			Date date = new Date();
   			//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
	       String fileName = "BadLinks_" +  dateFormat.format(date) + ".xls";
	    	
	    	try {
	    	    
				FileOutputStream fileOut = new FileOutputStream(fileName);
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
				
				HSSFRow row = worksheet.createRow((short) 0);
				
				HSSFCell cellA1 = row.createCell((short) 0);
				
				
				HSSFCellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			//	cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellA1.setCellStyle(cellStyle);
				
				
				int rowCount = brokenLinkList.size();
				if (rowCount < 1)
					cellA1.setCellValue("Check:" + Page.getURL() + ": WOW WOW WOW! All good! Nothing is broken!");
				else
				{	cellA1.setCellValue("Check:" + Page.getURL());
				
				
				    // MAKE SOME TITLE
					row = worksheet.createRow((short) (1));
					
					cellA1 = row.createCell((short) 0);
					cellA1.setCellValue("Bad Link URL");
		
					HSSFCell cellB1 = row.createCell((short) 1);
					cellB1.setCellValue("Link Text or Image Source");
					
					HSSFCell cellC1 = row.createCell((short) 2);
					cellC1.setCellValue("Status Code");
					
					cellA1.setCellStyle(cellStyle);
					cellB1.setCellStyle(cellStyle);
					cellC1.setCellStyle(cellStyle);
				
				}
	
				
				
				for (int i = 0 ; i < rowCount; i++)
				{	
					// index from 0,0... cell A1 is cell(0,0)
					
					row = worksheet.createRow((short) (i+2));
		
					cellA1 = row.createCell((short) 0);
					cellA1.setCellValue(brokenLinkList.get(i).getUrl());
		
					HSSFCell cellB1 = row.createCell((short) 1);
					cellB1.setCellValue(brokenLinkList.get(i).getLinkText());
					
					HSSFCell cellC1 = row.createCell((short) 2);
					cellC1.setCellValue(brokenLinkList.get(i).getStatusCode());
					
				}	
	
	
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

        //APPEND RESULT AT THE END OF FILE
	    /*
	    public static void writeToExcel( List<BadLink> brokenLinkList, String fileName)
	    {   //Don't create anything if no bad links
	    	if (brokenLinkList == null || brokenLinkList.size() < 1)  return; 
	    	
	    	Workbook workbook = Workbook.getWorkbook(new File(fileName));
	    	WritableWorkbook copy = Workbook.createWorkbook(new File("output.xls"), workbook);
	    	WritableSheet sheet2 = copy.getSheet(1); 
	    	Label label = new Label(5,2,"ssssssssss"); 
	    	sheet2.addCell(label); 
	    	
	    	
	    	
	    	try {
	    	    
				FileOutputStream fileOut = new FileOutputStream(fileName);
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
				
				HSSFRow row = worksheet.createRow((short) 0);
				
				HSSFCell cellA1 = row.createCell((short) 0);
				
				
				HSSFCellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			//	cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellA1.setCellStyle(cellStyle);
				
				
				int rowCount = brokenLinkList.size();
				if (rowCount < 1)
					cellA1.setCellValue("Check:" + Page.getURL() + ": WOW WOW WOW! All good! Nothing is broken!");
				else
				{	cellA1.setCellValue("Check:" + Page.getURL());
				
				
				    // MAKE SOME TITLE
					row = worksheet.createRow((short) (1));
					
					cellA1 = row.createCell((short) 0);
					cellA1.setCellValue("Bad Link URL");
		
					HSSFCell cellB1 = row.createCell((short) 1);
					cellB1.setCellValue("Link Text or Image Source");
					
					HSSFCell cellC1 = row.createCell((short) 2);
					cellC1.setCellValue("Status Code");
					
					cellA1.setCellStyle(cellStyle);
					cellB1.setCellStyle(cellStyle);
					cellC1.setCellStyle(cellStyle);
				
				}
	
				
				
				for (int i = 0 ; i < rowCount; i++)
				{	
					// index from 0,0... cell A1 is cell(0,0)
					
					row = worksheet.createRow((short) (i+2));
		
					cellA1 = row.createCell((short) 0);
					cellA1.setCellValue(brokenLinkList.get(i).getUrl());
		
					HSSFCell cellB1 = row.createCell((short) 1);
					cellB1.setCellValue(brokenLinkList.get(i).getLinkText());
					
					HSSFCell cellC1 = row.createCell((short) 2);
					cellC1.setCellValue(brokenLinkList.get(i).getStatusCode());
					
				}	
	
	
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

*/
	
}
