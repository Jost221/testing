import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(minusData(date, 5));
    }

    private  static LocalDate minusData(LocalDate date, int day){
        return date.plusDays(day);
    }
}