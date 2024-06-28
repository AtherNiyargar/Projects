import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

class Database_Access {
    private static final String url = "jdbc:mysql://localhost:3306/Hospital_Patients";
    private static final String username = "root";
    private static final String password = "";
    private final Scanner scanner = new Scanner(System.in);
    private Connection connection;
    private String phone_no, aadhar_no, disease, first_Name, last_Name;

    public Database_Access() {

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception exception) {
            System.out.println("Exception occurred : " + exception.getMessage());
        }
    }

    void add_Patient() {


        System.out.print("Enter the patient's First Name : ");
        first_Name = scanner.nextLine();
        while (first_Name.isEmpty()) {
            System.out.println("Please enter the first name");
            first_Name = scanner.nextLine();
        }

        System.out.print("Enter the patient's Last Name : ");
        last_Name = scanner.nextLine();
        while (last_Name.isEmpty()) {
            System.out.println("Please enter the last name");
            last_Name = scanner.nextLine();
        }

        System.out.print("Enter the patient's Phone Number : ");
        phone_no = scanner.nextLine();
        while (true) {
            if (phone_no.length() != 10) {
                System.out.println("Please enter a valid phone number");
                phone_no = scanner.nextLine();
                continue;
            }
            try {
                PreparedStatement tempPS = connection.prepareStatement("SELECT COUNT(*) FROM Patient_Details WHERE Phone_No = ?");
                tempPS.setString(1, phone_no);
                ResultSet resultSet = tempPS.executeQuery();

                resultSet.next();

                if (resultSet.getInt(1) > 0) {
                    System.out.println("The number is already registered!\nPlease enter a valid number : ");
                    phone_no = scanner.nextLine();
                    resultSet.close();
                    tempPS.close();
                    continue;
                }

            } catch (Exception e) {
                System.out.println("There is a problem in the database! -> " + e.getMessage());
                break;
            }
            break;
        }

        System.out.print("Enter the patient's Disease : ");
        disease = scanner.nextLine();
        System.out.print("Enter the patient's Aadhar Number : ");
        aadhar_no = scanner.nextLine();
        while (true) {
            if (aadhar_no.length() != 12) {
                System.out.println("Please enter a valid Aadhar number");
                phone_no = scanner.nextLine();
                continue;
            }
            try {
                PreparedStatement tempPS = connection.prepareStatement("SELECT COUNT(*) FROM Patient_Details WHERE Aadhar_No = ?");
                tempPS.setString(1, aadhar_no);
                ResultSet resultSet = tempPS.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) > 0) {
                    System.out.println("The Aadhar number is already registered!\nPlease enter a valid Aadhar number : ");
                    phone_no = scanner.nextLine();
                    resultSet.close();
                    tempPS.close();
                    continue;
                }
            } catch (Exception e) {
                System.out.println("There is a problem in the database!");
            }
            break;
        }

        try {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO `Patient_Details` (`Phone_No`, `Aadhar_No`, `Disease`, `First_Name`, `Last_Name`) VALUES (?, ?, ?, ?, ?);");
            pStatement.setString(1, phone_no);
            pStatement.setString(2, aadhar_no);
            pStatement.setString(3, disease);
            pStatement.setString(4, first_Name);
            pStatement.setString(5, last_Name);


            if (pStatement.executeUpdate() == 1) {
                System.out.println("Patient added Successfully");
            } else throw new Exception("Something wrong happened");
        } catch (Exception e) {
            System.out.println("Error occurred in the add_patient method!" + e.getMessage());
        }
    }

    void remove_Patient() {
        System.out.println("Enter the patient's Patient's Phone number : ");
        phone_no = scanner.nextLine();
        try {
            PreparedStatement pStatement = connection.prepareStatement("Select `First_Name`, `Last_Name` FROM `Patient_Details` WHERE `Phone_No` = ?");
            pStatement.setString(1, phone_no);
            ResultSet rsTemp = pStatement.executeQuery();

            if (rsTemp.next()) {
                String fname = rsTemp.getString(1);
                String lname = rsTemp.getString(2);
                System.out.println("The person -> " + fname + " " + lname + " has been removed from the records");
            } else {
                System.out.println("There is no one with number : " + phone_no);
                pStatement.close();
                rsTemp.close();
                return;
            }

            pStatement = connection.prepareStatement("DELETE FROM `Patient_Details` WHERE `Phone_No` = ?");
            pStatement.setString(1, phone_no);
            pStatement.executeUpdate();
            pStatement.close();
            rsTemp.close();

        } catch (Exception exception) {
            System.out.println("Exception occurred (DOWN): " + exception.getMessage());
        }
    }

    void view_patient() {
        System.out.println("Enter the number of the patient : ");
        phone_no = scanner.nextLine();
        while (true) {
            if (phone_no.length() != 10) {
                System.out.println("Please enter a valid phone number");
                phone_no = scanner.nextLine();
                continue;
            }
            try {
                PreparedStatement tempPS = connection.prepareStatement("SELECT * FROM `Patient_Details` WHERE `Phone_No` = ?");
                tempPS.setString(1, phone_no);
                ResultSet resultSet = tempPS.executeQuery();


                if (resultSet.next()) {
                    System.out.println("Id\t\tPhone No\t\tAadhar No\t\t\tDisease\t\t\t\tFirst Name\t\tLast Name");
                    System.out.println(resultSet.getString(1) + "\t\t" + resultSet.getString(2) +
                            "\t\t" + resultSet.getString(3) + "\t\t" + resultSet.getString(4) + "\t\t" + resultSet.getString(5) + "\t\t\t" + resultSet.getString(6));

                } else {
                    System.out.println("No Patient found with number : " + phone_no);

                }
                break;
            } catch (Exception e) {
                System.out.println("(View Data) There is a problem in the database! -> " + e.getMessage());
                break;
            }
        }
    }

    void search_disease() {
        System.out.print("Enter the disease name : ");
        disease = scanner.nextLine();
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM `Patient_Details` where `disease` = ?");
            pStatement.setString(1, disease);
            ResultSet rSet = pStatement.executeQuery();
            if (!rSet.next()) {
                System.out.println("No Patient found of disease : " + disease);
            } else {
                System.out.println("Id\t\tPhone No\t\tAadhar No\t\t\tDisease\t\t\t\tFirst Name\t\tLast Name");

                System.out.println(rSet.getString(1) + "\t\t" + rSet.getString(2) +
                        "\t\t" + rSet.getString(3) + "\t\t" + rSet.getString(4) + "\t\t" + rSet.getString(5) + "\t\t\t" + rSet.getString(6));
                while (rSet.next()) {
                    System.out.println(rSet.getString(1) + "\t\t" + rSet.getString(2) +
                            "\t\t" + rSet.getString(3) + "\t\t" + rSet.getString(4) + "\t\t" + rSet.getString(5) + "\t\t\t" + rSet.getString(6));
                }
            }

        } catch (Exception e) {
            System.out.println("Error in the Disease view method");
        }
    }
}


public class Hospital {
    public static void main(String[] args) {

        Database_Access access = new Database_Access();
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("""
                    Welcome to City Hospital
                    1. Admit a Patient
                    2. Remove a Patient
                    3. View Patient Details
                    4. Search by disease
                    5. Exit
                    :\s"""); // I don't know what is the use of this '\s' thing.
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, Try again!");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    access.add_Patient();
                    break;
                case 2:
                    access.remove_Patient();
                    break;

                case 3:
                    access.view_patient();
                    break;

                case 4:
                    access.search_disease();
                    break;

                case 5:
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }

    }
}





/*
    * I know this code does not follow good coding practices.
    * And it can be Improved.
    * There can be some issues where the program may get crashed (even when I used try-catch block).
    * But I am just lazy to fix this.
*/
