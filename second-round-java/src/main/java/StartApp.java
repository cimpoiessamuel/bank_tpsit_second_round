import java.io.*;

public class StartApp {

    StartApp(User user) {

        // users_info.txt initialisation
        String filePath = "src/main/resources/users_info.txt";


        // file abstraction
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
                System.out.println("impossible to create file");
            }
        } catch (IOException e) {
            System.out.println("error");
        }


        // instancing main frame
        MainFrame mainFrame = new MainFrame(user, users_info);
    }
}
