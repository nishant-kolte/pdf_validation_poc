package utilities;
import tests.ui.BaseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InitTestData {

//    public static Properties getTestData(String env) throws IOException {
//            Properties testdata = new Properties();
//            //FileInputStream objfile = new FileInputStream(System.getProperty("C:\\Users\\Ashwini\\Downloads\\IdeaProjects\\IdeaProjects\\Intelli Docs\\IntelliDocs\\Object_Repository.properties"));
//            if (env==null || env.equalsIgnoreCase("qa")){
//                FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\QA_testdata.properties");
//                testdata.load(objfile);
//                BaseTest.log.info("Loaded qa testdata successfully!");
//            }
//            else if (env.equalsIgnoreCase("uat")){
//                FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\UAT_testdata.properties");
//                testdata.load(objfile);
//                BaseTest.log.info("Loaded uat testdata successfully!");
//            }
//            else if (env.equalsIgnoreCase("prod")){
//                FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\PROD_testdata.properties");
//                testdata.load(objfile);
//                BaseTest.log.info("Loaded prod testdata successfully!");
//            }
//            return testdata;
//        }
//
//    public static Properties readConfig(String configFileName){
//        Properties config = new Properties();
//            try{
//                FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\configs\\"+configFileName+".properties");
//                config.load(objfile);
//                BaseTest.log.info("Loaded "+configFileName+" configuration successfully!");
//                return config;
//            }
//            catch (Exception e){
//                BaseTest.log.info("Failed to read configuration for: "+ configFileName);
//                return null;
//            }
//    }
}


