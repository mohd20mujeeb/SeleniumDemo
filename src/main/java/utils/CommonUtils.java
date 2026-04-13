package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;



import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtils {
	public  String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:","")+"@gmail.com";
	}

	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {
		 BufferedImage actualBImg=ImageIO.read(new File(actualImagePath));
         BufferedImage expectedBImg= ImageIO.read(new File(expectedImagePath));
         ImageDiffer imgDiff1 = new ImageDiffer();
         ImageDiff diff1 = imgDiff1.makeDiff(expectedBImg, actualBImg);
         return(diff1.hasDiff());

	}
	
}
