package Tubes;

public class StringBoolPair {

    public StringBoolPair(boolean bool, String str) {
        this.bool = bool;
        this.str = str;
    }

    private boolean bool= false;
    private String str = null;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str=str;
    }

    public boolean getBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

}
