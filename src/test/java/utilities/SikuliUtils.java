package utilities;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliUtils {
   static Pattern pattern;

   static Screen screen=new Screen();
    public static void loadImage(String imageName,String folderName) {
         pattern = new Pattern(System.getProperty("user.dir")+"\\src\\test\\resources\\SikuliObjects\\"+folderName+"\\"+imageName+".png");
    }
    public static void skClick(String imageName,String folderName) throws FindFailed {
        loadImage(imageName,folderName);
        screen.wait(pattern,5);
        screen.click(pattern);
        CommonUtils.logStepAsPassedInExtentReport(String.format("Click the '%s' on '%s'",imageName,folderName));
    }
    public static void skSendKey(String imageName,String folderName,String text) throws FindFailed {
        loadImage(imageName,folderName);
        screen.wait(pattern,5);
        screen.type(pattern,text);
        CommonUtils.logStepAsPassedInExtentReport(String.format("enter the text in '%s' on '%s'",imageName,folderName));
    }
    public static void skDoubleClick(String imageName,String folderName) throws FindFailed {
        loadImage(imageName,folderName);
        screen.wait(pattern,5);
        screen.doubleClick(pattern);
        CommonUtils.logStepAsPassedInExtentReport(String.format("Double Click on '%s' in '%s'",imageName,folderName));
    }
    public static void skHover(String imageName,String folderName) throws FindFailed {
        loadImage(imageName,folderName);
        screen.wait(pattern);
        screen.hover(pattern);
        CommonUtils.logStepAsPassedInExtentReport(String.format("Mouse Hover on '%s' in '%s'",imageName,folderName));
    }
    public static void skFindImage(String imageName,String folderName) throws FindFailed {
        loadImage(imageName,folderName);
        screen.wait(pattern);
        screen.find(pattern);
    }
    public static void skClickUntilImageIsVisible(String imageName,String folderName) throws FindFailed {
        loadImage(imageName, folderName);
        boolean flag = true;
        while (flag) {
            try {
                screen.wait(pattern, 5);
                screen.click(pattern);
            } catch (FindFailed e) {
                flag=false;
            }
        }
    }
}
