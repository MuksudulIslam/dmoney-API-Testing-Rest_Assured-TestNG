package Utils;

import Config.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static void setEvnVariable(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public static void saveUserInfo (UserModel model) throws IOException, ParseException {
        String file = "./src/test/resources/userInfo.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file));
        JSONObject userObj = new JSONObject();
        userObj.put("name", model.getName());
        userObj.put("phone", model.getPhone_number());
        userObj.put("role", model.getRole());

        jsonArray.add(userObj);

        FileWriter writer = new FileWriter(file);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }


    public static JSONArray readUserInfo(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filename));
        return jsonArray;
    }

    public static int generateRandomNumber(int max, int min){
        double rndNum = Math.random()*(max-min)+min;
        return (int)rndNum;
    }



}
