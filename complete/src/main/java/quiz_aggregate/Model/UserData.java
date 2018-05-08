package quiz_aggregate.Model;

public class UserData {
    private String _rev;
    private String _id;
    private String user_data;

    public UserData(){}

    public UserData(boolean isStub)
    {
        this.user_data = "kenjinak";
    }

    public String getUser_data()
    {
        return user_data;
    }

    public void setUser_data(String user_data)
    {
        this.user_data = user_data;
    }
}
