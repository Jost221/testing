import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.*;


public class Main {
    private static List<User> sortByGender(List<User> users, Gender gender){
        List<User> usersNew = new ArrayList<User>();
        for(var usr : users) {
            if(usr.gender == gender){
                usersNew.add(usr);
            }
        }
        return usersNew;
    }

    public static void main(String[] args) {
        String[] names = {"Jon", "Alex", "Kata", "Yoogin", "George", "Olivia", "Ethan", "Emma", "Liam", "Charlotte"};
        List<User> users = new ArrayList<>();
        for(int i = 0; i < names.length; i++)
            users.add(new User(names[i], (new Random().nextInt(2) == 1 ? Gender.Men : Gender.Woman)));

        List<User> men = User.sortByGender(users, Gender.Men);
        List<User> women = User.sortByGender(users, Gender.Woman);

        System.out.println("Users");
        printList(users);
        System.out.println("\nMan");
        printList(men);
        System.out.println("\nWoman");
        printList(women);
    }

    private static <T> void printList(List<T> users) {
        System.out.print("[\n\t");
        for(T usr: users) {
            System.out.print(usr);
            System.out.print(",\n\t");
        }
        System.out.print("\r]");
    }

//    private static void printUsers(List<User> users){
//        for(var usr: users){
//            System.out.print(usr);
//            System.out.print(" { Name = ");
//            System.out.print(usr.name);
//            System.out.print(", Gender = ");
//            System.out.print(usr.gender);
//            System.out.println(" }");
//        }
//    }
}