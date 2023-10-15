package TestRunners;

import Config.Setup;
import Config.UserModel;
import Pages.DmoneyAPIs;
import Utils.Utils;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class DmoneyLoginAPIsTestRunner extends Setup {
    DmoneyAPIs dmoneyAPIs;

    String fileName = "./src/test/resources/userInfo.json";
    public DmoneyLoginAPIsTestRunner() throws IOException {
        initConfig();
        dmoneyAPIs = new DmoneyAPIs(prop.getProperty("baseUrl"));

    }

    @Test(priority = 1, description = "Admin Logged in by Valid Credentials")
    public void AdminLogin() throws ConfigurationException, IOException, ParseException {
        UserModel model = new UserModel();
        JSONArray userList = Utils.readUserInfo(fileName);
        JSONObject adminObj = (JSONObject) userList.get(1);
        String adminEmail = (String) adminObj.get("email");
        String adminPassword = (String) adminObj.get("password");
        model.setEmail(adminEmail);
        model.setPassword(adminPassword);
        String Token = dmoneyAPIs.callingLoginAPI(model);
        Utils.setEvnVariable("token", Token);

    }
}
