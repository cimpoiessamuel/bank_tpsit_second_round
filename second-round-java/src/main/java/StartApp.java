import java.io.*;

public class StartApp {

    StartApp() {

        // users_info.txt initialisation
        String filePath = "second-round-java/src/main/resources/users_info.txt";


        // file initialization
        File users_info = new File(filePath);


        // check if file exists, if not then create file
        try {
            if (users_info.createNewFile()) {
                try (BufferedWriter outFile = new BufferedWriter(new FileWriter(users_info))) {
                    outFile.write("default: ");

                    // write on disk now
                    outFile.flush();
                }

                System.out.println("file created");
            } else {
                System.out.println("file already exists");
            }
        } catch (IOException e) {
            System.out.println("file creation failed");
        }


        // instancing main frame for authentication
        MainFrame authenticateMainFrame = new MainFrame(users_info);
    }
}
