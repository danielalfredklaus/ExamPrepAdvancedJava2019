package Enums;

public class MyDate {

    public enum Month{
        January("Januar"),
        February("Februar"),
        March("März"),
        April("April"),
        May("Mai"),
        June("Juni"),
        July("Juli");

        String germanName;
        Month(String gerName){
            this.germanName=gerName;
        }

        public String getEnglishName (Month month) {
            String englishName = month.toString();
            return englishName;
        }

        public String getGermanName (Month month) {
            String germanName = month.germanName;
            return germanName;
        }
    }

    public enum WeekDay {
        Monday,Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    }

}
