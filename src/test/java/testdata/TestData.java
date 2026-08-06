package testdata;

import com.github.javafaker.Faker;

public class TestData {

    Faker faker = new Faker();

    public  String firstName = faker.name().firstName();
    public  String lastName = faker.name().lastName();
    public  String userEmail = faker.internet().emailAddress();
    public  String genderWrapper = faker.options().option("Male", "Female", "Other");
    public  String userNumber = faker.phoneNumber().subscriberNumber(10);
    public  String BirthMonth = faker.options().option(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");
    public  String BirthYear = String.valueOf(faker.number().numberBetween(1960, 2015));;
    public  String BirthDay = String.valueOf(faker.number().numberBetween(1, 30));
    public  String picture = "photo.jpg";
    public  String subjects = faker.options().option(
"Art", "Civics", "History", "Social Studies", "Economics", "Accounting", "Commerce",
        "Computer Science", "Biology", "Chemistry", "Physics", "Maths", "English", "Hindi");
    public  String hobbies = faker.options().option("Sports", "Reading", "Music") ;
    public  String currentAddress = faker.address().fullAddress();

    public  String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public  String city = randomCity(state);

    private String randomCity(String state){
        switch (state){
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                throw new IllegalArgumentException("Неизвестная страна: " + state);
        }
    };
    public  String textSuccessfulRegistrationForm = "Thanks for submitting the form";
}