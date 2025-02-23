import java.io.*;

public class StartApp {

  StartApp() {

    // users_info.txt initialisation
    String filePath = "src/main/resources/users-info.csv";

    // file initialization
    File users_info = new File(filePath);

    // check if file exists, if not then create file
    try {
      if (users_info.createNewFile()) {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(users_info))) {
          outFile.write("default;");

          // write on disk now
          outFile.flush();
        }

        System.out.println("users-info created");
      } else {
        System.out.println("users-info already exists");
      }
    } catch (IOException e) {
      System.out.println("users-info creation failed");
    }

    // instancing main frame for authentication
    MainFrame authenticationMainFrame = new MainFrame(users_info);
  }
}
