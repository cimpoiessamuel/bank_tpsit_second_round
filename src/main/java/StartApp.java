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
          outFile.write("default;\ntransID;0");

          //
          Transaction.setIDCounter(0);

          // write on disk now
          outFile.flush();
        }

        System.out.println("users-info created");
      } else {
        System.out.println("users-info already exists");

        // file already exists; if there is a default user, then don't open auth procedure
        try (BufferedReader inFile = new BufferedReader(new FileReader(users_info))) {
          //
          String defaultLine = inFile.readLine();

          //
          String transIDLine = inFile.readLine();

          //
          if (!transIDLine.split(";")[1].equals("0")) {
            Transaction.setIDCounter(Integer.parseInt(transIDLine.split(";")[1]));
          }

          if (!defaultLine.equals("default;")) {
            //
            MainFrame.setSessionUser(
                new User(
                    defaultLine.split(";")[3], // name
                    defaultLine.split(";")[4], // surname
                    defaultLine.split(";")[1], // username
                    defaultLine.split(";")[2])); // password

            //
            new MainFrame();

            return;
          }
        }
      }
    } catch (IOException e) {
      System.out.println("users-info creation failed");
    }

    // instancing main frame for authentication
    new MainFrame(users_info);
  }
}
