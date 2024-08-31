import java.util.Calendar;
import java.util.GregorianCalendar;

class ch1014
{
    public static void main(String[] args)
    {
        ch1014 testing = new ch1014();
        MyDate myDate01 = testing.new MyDate();
        MyDate myDate02 = testing.new MyDate(34355555133101L);
        MyDate myDate03 = testing.new MyDate(561555550000L);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(34355555133101L);

        System.out.println("Current Date:\n year=" + myDate01.GetYear() + "\n month=" + myDate01.GetMonth() + "\n day=" + myDate01.GetDay() + "\n");
        System.out.println("MyDate02:\n year=" + myDate02.GetYear() + "\n month=" + myDate02.GetMonth() + "\n day=" + myDate02.GetDay() + "\n");
        System.out.println("MyDate03:\n year=" + myDate03.GetYear() + "\n month=" + myDate03.GetMonth() + "\n day=" + myDate03.GetDay() + "\n");
    }

    public class MyDate
    {
        GregorianCalendar calendar = new GregorianCalendar();
        private int year;
        private int month;
        private int day;

        public MyDate()
        {
            this(System.currentTimeMillis());
        }

        public MyDate(Long millisecondLongTime)
        {
            setDate(millisecondLongTime);
        }

        public MyDate(int year, int month, int day)
        {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int GetYear()
        {
            return year;
        }
        public void SetYear(int newYear)
        {
            this.year = newYear;
        }

        public int GetMonth()
        {
            return month;
        }
        public void SetMonth(int newMonth)
        {
            this.year = newMonth;
        }

        public int GetDay()
        {
            return day;
        }
        public void SetDay(int newDay)
        {
            this.year = newDay;
        }

        // the book says we can use this
        public void setDate(Long millisecondLongTime)
        {
            calendar.setTimeInMillis(millisecondLongTime);
            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH);
            this.day = calendar.get(Calendar.DAY_OF_MONTH);
        }
    }
}