package JavaSE.Class;

public class Class{
    public int _year, _month, _day;
    public Class(){
        this(0, 0, 0);
    }
    public Class(int year, int month, int day){
        _year = year; _month = month; _day = day;
    }
    @Override
    public String toString(){
        String str = "[" + _year + "-" + _month + "-" + _day + "]";
        return str;
    }
}
