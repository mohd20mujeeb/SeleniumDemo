package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtils {
	public static String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:","")+"@gmail.com";
	}

	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {
		 BufferedImage actualBImg=ImageIO.read(new File(actualImagePath));
         BufferedImage expectedBImg= ImageIO.read(new File(expectedImagePath));
         ImageDiffer imgDiff1 = new ImageDiffer();
         ImageDiff diff1 = imgDiff1.makeDiff(expectedBImg, actualBImg);
         return(diff1.hasDiff());

	}
	
	public static Properties loadProperties() 
	{
		Properties prop = new Properties();
		try {
		FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\projectData.properties");
		prop.load(fr);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	
	public static WebDriver takeScreenshot(WebDriver driver,String pathToBeCopied) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcScreenShot=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcScreenShot, new File(System.getProperty("user.dir")+pathToBeCopied));
		}catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
}
