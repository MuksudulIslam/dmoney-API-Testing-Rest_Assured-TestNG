package TestRunners;

import Config.Setup;
import Config.UserModel;
import Pages.DmoneyAPIs;
import Utils.Utils;
import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class DmoneyUserCreateAPIsTestRunner extends Setup {
    DmoneyAPIs dmoneyAPIs;

    public DmoneyUserCreateAPIsTestRunner() throws IOException {
        initConfig();
        dmoneyAPIs = new DmoneyAPIs(prop.getProperty("baseUrl"));

    }


    @Test(priority = 1, description = "Admin Create an Agent Account")
    public void CreateAnAgent() throws IOException, ParseException, InterruptedException {
        UserModel model = new UserModel();
        Faker faker = new Faker();
        model.setName(faker.name().fullName());
        model.setEmail(faker.internet().emailAddress());
        model.setPassword("1234");
        model.setPhone_number("01826"+Utils.generateRandomNumber(100000,999999));
        model.setNid("123458962");
        model.setRole("Agent");
        dmoneyAPIs.createUser(prop.getProperty("token"), prop.getProperty("partnerKey"), model);

    }

    @Test(priority = 2, description = "Admin Create a New Customer Account")
    public void CreateCustomer1() throws IOException, ParseException {
        UserModel model = new UserModel();
        Faker faker = new Faker();
        model.setName(faker.name().fullName());
        model.setEmail(faker.internet().emailAddress());
        model.setPassword("1234");
        model.setPhone_number("01685"+Utils.generateRandomNumber(100000,999999));
        model.setNid("123456789");
        model.setRole("Customer");

        dmoneyAPIs.createUser(prop.getProperty("token"), prop.getProperty("partnerKey"), model);

    }

    @Test(priority = 3, description = "Admin Create Another New Customer Account")
    public void CreateCustomer2() throws IOException, ParseException {
        UserModel model = new UserModel();
        Faker faker =new Faker();
        model.setName(faker.name().fullName());
        model.setEmail(faker.internet().emailAddress());
        model.setPassword("1234");
        model.setPhone_number("01675"+Utils.generateRandomNumber(100000,999999));
        model.setNid("123456789");
        model.setRole("Customer");

        dmoneyAPIs.createUser(prop.getProperty("token"), prop.getProperty("partnerKey"), model);
    }
}
