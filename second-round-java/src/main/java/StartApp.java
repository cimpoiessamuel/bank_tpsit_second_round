import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class StartApp {

    StartApp(BankAccount b) {

        // users_info.txt initialisation
        String filePath = "src/main/resources/users_info.txt";

        File users_info = new File(filePath);


        // check if file exists, if not then create file
        if (!users_info.exists()) {
            try {
                if (users_info.createNewFile()) {
                    System.out.println("file created");
                } else {
                    System.out.println("impossible to create file");
                }
            } catch (IOException e) {
                System.out.println("error");
            }
        }


        // writing and reading from users_info.txt
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(users_info, true));
             BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {

            outFile.write("default: ");

            outFile.flush();

//            String line;
//
//            while ((line = inFile.readLine()) != null) {
//                System.out.println(line);
//            }

            // instancing main frame
            MainGUI mainGUI = new MainGUI(b, outFile, inFile);

        } catch (IOException e) {
            System.err.println("error");
        }


    }
}
