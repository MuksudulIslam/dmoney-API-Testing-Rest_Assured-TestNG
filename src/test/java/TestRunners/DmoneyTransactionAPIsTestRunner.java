package TestRunners;

import Config.Setup;
import Config.UserModel;
import Pages.DmoneyAPIs;
import Utils.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class DmoneyTransactionAPIsTestRunner extends Setup {
    DmoneyAPIs dmoneyAPIs;

    String fileName = "./src/test/resources/userInfo.json";
    public DmoneyTransactionAPIsTestRunner() throws IOException {
        initConfig();
        dmoneyAPIs = new DmoneyAPIs(prop.getProperty("baseUrl"));

    }

    @Test(priority = 1, description = "Deposit Money From System Account to Newly Created Agent Account")
    public void DepositFromSystemToAgent() throws IOException, ParseException, InterruptedException {
        UserModel model = new UserModel();
        JSONArray userList = Utils.readUserInfo(fileName);
        JSONObject userObj = (JSONObject) userList.get(userList.size()-3);
        String agentPhoneNumber = (String) userObj.get("phone");
        String agentName = (String) userObj.get("name");
        model.setFrom_account("SYSTEM");
        model.setTo_account(agentPhoneNumber);
        model.setAmount(2000);

        dmoneyAPIs.deposit(prop.getProperty("token"), prop.getProperty("partnerKey"), model);
        System.out.println("To Agent Name "+agentName);
    }

    @Test(priority = 2, description = "Deposit Money From Created Agent Account to Newly Created Customer 1")
    public void DepositFromAgentToCustomer1() throws IOException, ParseException, InterruptedException {
        UserModel model = new UserModel();
        JSONArray userList = Utils.readUserInfo(fileName);
        JSONObject agentObj = (JSONObject) userList.get(userList.size()-3);
        String agentPhoneNum = (String) agentObj.get("phone");
        String agentName = (String) agentObj.get("name");

        JSONObject custObj = (JSONObject) userList.get(userList.size()-2);
        String customer1PhoneNum = (String) custObj.get("phone");
        String customer1Name = (String) custObj.get("name");

        model.setFrom_account(agentPhoneNum);
        model.setTo_account(customer1PhoneNum);
        model.setAmount(1500);

        dmoneyAPIs.deposit(prop.getProperty("token"), prop.getProperty("partnerKey"), model);
        System.out.println("From Agent: "+agentName);
        System.out.println("To Customer 1 Name: "+customer1Name);


    }

    @Test(priority = 3, description = "Withdraw Money From the Created Agent by the Customer 1 Account")
    public void WithdrawAgentToCustomer() throws IOException, ParseException, InterruptedException {
        UserModel model = new UserModel();
        JSONArray userList = Utils.readUserInfo(fileName);
        JSONObject agentObj = (JSONObject) userList.get(userList.size()-3);
        String agentPhoneNum = (String) agentObj.get("phone");
        String agentName = (String) agentObj.get("name");

        JSONObject custObj = (JSONObject) userList.get(userList.size()-2);
        String customer1PhoneNum = (String) custObj.get("phone");
        String customer1Name = (String) custObj.get("name");

        model.setFrom_account(customer1PhoneNum);
        model.setTo_account(agentPhoneNum);
        model.setAmount(500);

        dmoneyAPIs.withdraw(prop.getProperty("token"), prop.getProperty("partnerKey"),model);

        System.out.println("From Customer 1 Name: "+customer1Name);
        System.out.println("To Agent Name: "+agentName);



    }

    @Test(priority = 4, description = "Send Money From Customer 1 Account to the Newly Created Another Customer Account")
    public void SendMoneyCustomer1ToCustomer2() throws IOException, ParseException {
        UserModel model = new UserModel();
        JSONArray userList = Utils.readUserInfo(fileName);
        JSONObject cust1Obj = (JSONObject) userList.get(userList.size()-2);
        String customer1PhoneNum = (String) cust1Obj.get("phone");
        String customer1Name = (String) cust1Obj.get("name");

        JSONObject cust2Obj = (JSONObject) userList.get(userList.size()-1);
        String customer2PhoneNum = (String) cust2Obj.get("phone");
        String customer2Name = (String) cust2Obj.get("name");

        model.setFrom_account(customer1PhoneNum);
        model.setTo_account(customer2PhoneNum);
        model.setAmount(500);

        dmoneyAPIs.sendMoney(prop.getProperty("token"), prop.getProperty("partnerKey"),model);
        System.out.println("From Customer 1 Name: "+customer1Name);
        System.out.println("To Customer 2 Name: "+customer2Name);



    }

    @Test(priority = 5, description = "Payment Money From Created Customer 2 Account to the Merchant Account")
    public void PaymentRecipientCustomer2ToMerchant() throws IOException, ParseException {
        UserModel model = new UserModel();
        JSONArray userList = Utils.readUserInfo(fileName);
        JSONObject cust2Obj = (JSONObject) userList.get(userList.size()-1);
        String customer2PhoneNum = (String) cust2Obj.get("phone");
        String customer2Name = (String) cust2Obj.get("name");

        JSONObject merchantObj = (JSONObject) userList.get(0);
        String merchantPhoneNum = (String) merchantObj.get("phone");
        String merchantName = (String) merchantObj.get("name");

        model.setFrom_account(customer2PhoneNum);
        model.setTo_account(merchantPhoneNum);
        model.setAmount(100);

        dmoneyAPIs.payment(prop.getProperty("token"), prop.getProperty("partnerKey"),model);
        System.out.println("From Customer 2 Name: "+customer2Name);
        System.out.println("To Merchant Name: "+merchantName);

    }

    @Test(priority = 6, description = "Check Balance of the Customer 2 Account")
    public void CheckBalance() throws IOException, ParseException {
        JSONArray userList = Utils.readUserInfo(fileName);
        JSONObject cust2Obj = (JSONObject) userList.get(userList.size()-1);
        String customer2PhoneNum = (String) cust2Obj.get("phone");
        String customer2Name = (String) cust2Obj.get("name");

        dmoneyAPIs.checkBalance(prop.getProperty("token"), prop.getProperty("partnerKey"),customer2PhoneNum );
        System.out.println("Customer 2 Name: "+customer2Name);

    }





}
