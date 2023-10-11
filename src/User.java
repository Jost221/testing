import java.util.List;

public class User {
    String Name;
    Gender Gender;

    User(String Name, Gender Gender){
        this.Name = Name;
        this.Gender = Gender;
    }
}

enum Gender {
    Men,
    Woman
}