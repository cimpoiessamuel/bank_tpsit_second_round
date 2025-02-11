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


        boolean alreadyExists = true;


        // check if file exists, if not then create file
        if (!users_info.exists()) {
            try {
                if (users_info.createNewFile()) {
                    alreadyExists = false;
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


            // if the file already exists, do not write "default: " on the top line
            if (!alreadyExists) {
                outFile.write("default: ");
            }


            // write immediately on disk
            outFile.flush();


            // instancing main frame
            MainFrame mainFrame = new MainFrame(b, outFile, inFile);

        } catch (IOException e) {
            System.err.println("error");
        }
    }
}
