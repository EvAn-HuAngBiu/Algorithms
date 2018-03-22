
public class SmartDate {
    private final int year;
    private final int month;
    private final int day;

    public SmartDate(int y, int m, int d) {
        if(!isRightDate(y, m, d)) throw new IllegalArgumentException("Illegal date");
        year = y;
        month = m;
        day = d;
    }

    private boolean isRightDate(int y, int m, int d) {
        if(y < 0 || m < 0 || m > 12 || d < 0) return false;
        switch(m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:if(d <= 31) return true;
                    break;
            case 4:
            case 6:
            case 9:
            case 11:if(d <= 30) return true;
                    break;
            case 2:
                if(y % 4 == 0) {
                    if(d <= 29) return true; }
                else {
                    if(d <= 28) return true; }
        }
        return false;
    }

    public String dayOfTheWeek() {
        int d = day;
        int m = month;
        int y = year;
        //Kim larsson calculation formula
        int week = (d + 2*m + 3*(m+1)/5 + y + y/4 - y/100 + y/400 + 1) % 7;
        switch (week) {
            case 1:return "Monday";
            case 2:return "Tuesday";
            case 3:return "Wednesday";
            case 4:return "Thursday";
            case 5:return "Friday";
            case 6:return "Saturday";
            case 7:return "Sunday";
        }
        return null;
    }

    public static void main(String[] s) {
        SmartDate d1 = new SmartDate(2018, 3, 21);
        System.out.println(d1.dayOfTheWeek());
    }
}
