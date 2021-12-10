package Task2;

public class UserCommand implements InterfaceCommand {

    @Override
    public String getResult(String data) {

        int len = data.length();
        data = data.substring(0, len - 2);
        System.out.println("Username is：" + data);
        String response = "true";
        return response;

    }
}
