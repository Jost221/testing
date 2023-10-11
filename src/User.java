import java.util.List;
import java.util.stream.Collectors;

public class User {
    String name;
    Gender gender;

    User(String Name, Gender Gender){
        this.name = Name;
        this.gender = Gender;
    }

    public static List<User> sortByGender(List<User> list, Gender gender){
        return list.stream().filter(user -> user.gender == gender).collect(Collectors.toList());
    }
    @Override
    public String toString() {
        return String.format("Name: %s, Gender: %s", name, gender);
    }
}

enum Gender {
    Men,
    Woman
}