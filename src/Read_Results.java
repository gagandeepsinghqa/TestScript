import java.io.File;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Read_Results {

	public static String results() throws BiffException, IOException {

		Workbook wb = Workbook.getWorkbook(new File("C:\\Users\\kodion Softwares\\workspace\\Infosys\\Infosys.csv"));
		Sheet mySheet = wb.getSheet("Project");
		int rows = mySheet.getRows();

		int rows_2 = rows-1;
		System.out.println(rows_2);

		String html = "<table cellspacing='0' style='width:1000px; border= '0'>";
	
		html += "<table border = '0'>";
		html += "<tr>";
		html += "<td>";
		html += "<font size='3' color='#1F618D' font face='verdana'>";
		html += "<strong>";
		html += "Infosys Automation Test Results";
		html += "</strong>";
		html += "</td>";
		html += "<td>";
		html += "<font size='3' color='#1F618D' font face='verdana'>";
		html += "</td>";
		html += "</tr>";
	
		html += "<tr>";
		html += "<td>";
		html += "<font size='2' color='#3498DB' font face='verdana' border-top:1px>";
		html += "<strong>";
		html += "Number of Test Cases Executed : "+rows_2;
		html += "</strong>";
		html += "</td>";
		html += "</tr>";
		html += "<table>";
		
		for (int i = 0; i < rows; i++) {

			String backColor = "";
			if (i % 2 == 0)
				backColor = "background-color: #D4E6F1;";

			String headerColumn = "";
			if (i == 0) {
				headerColumn = "font-size:14px; border-top:2px solid #2980B9;border-bottom:1px;";
				html += " <tr style='border-top:1px solid #2980B9;border-bottom:1px; text-align:left; font-weight: bold;"  + backColor + "'> ";
			} else {
				headerColumn = "font-size:14px; border-top:2px solid #2980B9;border-bottom:1px";
				html += " <tr style='" + backColor + "'> ";
			}			

			// 0th column and 1st row
			for (int j = 0; j < 6; j++) {

				jxl.Cell cell = mySheet.getCell(j, i);
				String contents = cell.getContents().toString();
				System.out.println(contents);

				if (contents.equalsIgnoreCase("WORKING"))
					html += "<td style='padding:10px;color:green; padding-left:20px; border-top:1px solid #9A2EFE; font-weight: bold;" + headerColumn + "'>" + contents + "</td>";
				else if (contents.equalsIgnoreCase("NOT WORKING"))
					html += "<td style='padding:10px;color:red; padding-left:20px; border-top:1px solid #9A2EFE; font-weight: bold;" + headerColumn + "'>" + contents + "</td>";
				else
					html += "<td style='padding:10px; padding-left:20px; border-top:1px solid #9A2EFE;" + headerColumn + "'>" + contents + "</td>";
			}			
			html += "</tr>";
		}
		html += "</table>";
		return html;
	}
}
