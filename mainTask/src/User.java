public class User {
    private String firstName;
    private String lastName;
    private int age;

    User(String firstName, String lastName, int age) throws IllegalArgumentException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        if (age <= 0){
            throw new IllegalArgumentException("Некорректный возраст: " + firstName);
        }
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAge(){
        return age;
    }
    public String toString(){
        return "Name: " + firstName + ". LastName: " + lastName + " . Age: " + age;
    }
}

