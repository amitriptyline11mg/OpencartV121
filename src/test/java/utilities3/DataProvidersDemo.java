package utilities3;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProvidersDemo {

	@DataProvider (name = "LoginData")
	public Object[][] getData() throws IOException {

		String path = ".\\testData\\Opencart_LoginpData.xlsx";

		ExcelUtility xlUtil = new ExcelUtility(path);

		int totalrows = xlUtil.getRowCount("Sheet1");
		int totalcols = xlUtil.getcellCount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				logindata[i - 1][j] = xlUtil.getCellData("Sheet1", i, j); // 1, 0
			}

		}

		return logindata; // Returning two dimension array
	}


}
