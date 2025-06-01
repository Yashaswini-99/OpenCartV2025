package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getLoginData() throws IOException{
		
	String path=".//testData//OpenCart_LoginData.xlsx";
	ExcelUtility xlutil=new ExcelUtility(path);
	
	int totalrows=xlutil.getRowCount("Sheet1");
	int totalcol=xlutil.getCellCount("Sheet1", 1);
	
	String data[][]=new String[totalrows][totalcol];
	
	for(int r=1;r<=totalrows;r++) 
	{
		for(int c=0;c<totalcol;c++) 
		{
			data[r-1][c]=xlutil.getCellData("Sheet1", r, c);
			
		}
	}
	
	return data;
	}
}
