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
        // creating users_info file
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(users_info))) {
          outFile.write("default;");

          // write on disk now
          outFile.flush();
        }

        System.out.println("users-info created");
      } else {
        // file already exists; if there is a default user, then don't open auth procedure
        try (BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {
          String[] data = new String[4];
          String line;
          if (!(line = inFile.readLine()).equals("default;")) {
            //
            data[0] = line.split(";")[1]; // username
            data[1] = line.split(";")[2]; // password
            data[2] = line.split(";")[3]; // name
            data[3] = line.split(";")[4]; // surname

            MainFrame.setSessionUser(new User(data[2], data[3], data[0], data[1]));

            //
            new MainFrame();

            return;
          }
        }

        System.out.println("users-info already exists");
      }
    } catch (IOException e) {
      System.out.println("users-info creation failed");
    }

    // instancing main frame for authentication
    new MainFrame(users_info);
  }
}
