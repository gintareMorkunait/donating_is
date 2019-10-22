package ktu.edu.donatingis;

public class Friend {
    private String FName;
    private String FAddress;
    private String FBloodType;

    public Friend(String fname, String faddress, String fbloodType) {
        FName = fname;
        FAddress = faddress;
        FBloodType = fbloodType;
    }
    public Friend()
    {
        FName = "";
        FAddress = "";
        FBloodType = "";
    }

    public String getFName() {
        return FName;
    }

    public String getFAddress() {
        return FAddress;
    }

    public String getFBloodType() {
        return FBloodType;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setFAddress(String FAddress) {
        this.FAddress = FAddress;
    }

    public void setFBloodType(String FBloodType) {
        this.FBloodType = FBloodType;
    }
}
