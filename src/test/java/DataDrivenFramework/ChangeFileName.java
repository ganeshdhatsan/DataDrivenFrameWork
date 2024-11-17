package DataDrivenFramework;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

public class ChangeFileName {
	
	@Test
	void modifyExixtingFileName(){
		
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String format = sf.format(date);
		

//		String actualPath=System.getProperty("user.dir")+"src\\test\\resources\\TestData\\Tricentis_TestData2.xlsx";		
//		
//	File file = new File(actualPath);
//	String name = file.getName();
//	System.out.println("fileName "+name);
	
	// Specify the original file path and the new file name
    String originalFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Tricentis_TestData2.xlsx";
    String newFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Tricentis_TestData2_"+format+".xlsx";

    // Create File objects
    File originalFile = new File(originalFilePath);
    File newFile = new File(newFilePath);

    // Rename the file
    if (originalFile.exists()) {
        boolean success = originalFile.renameTo(newFile);
        if (success) {
            System.out.println("File renamed successfully to: " + newFile.getName());
        } else {
            System.out.println("Failed to rename the file.");
        }
    } else {
        System.out.println("The original file does not exist.");
    }
}
	
	}


