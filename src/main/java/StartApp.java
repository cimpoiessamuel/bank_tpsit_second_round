import java.io.*;

public class StartApp {

  private static final File usersInfo =
      new File("src/main/resources/users-info.csv"); // users_info.txt initialisation
  private static final int key = 5;

  StartApp() {

    // check if file exists, if not then create file
    try {
      if (usersInfo.createNewFile()) {
        // creating users_info file
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(usersInfo))) {
          outFile.write("default;\ntransID;0");
          outFile.newLine();
          outFile.newLine();

          Transaction.setIDCounter(0);

          // write on disk now
          outFile.flush();
        }

        System.out.println("users-info created");
      } else {
        System.out.println("users-info already exists");

        // file already exists; if there is a default user, then don't open auth procedure
        try (BufferedReader inFile = new BufferedReader(new FileReader(usersInfo))) {
          // line for logged user
          String defaultLine = inFile.readLine();

          // line for transID
          String transIDLine = inFile.readLine();

          if (!transIDLine.split(";")[1].equals("0")) {
            Transaction.setIDCounter(Integer.parseInt(transIDLine.split(";")[1]));
          }

          if (!defaultLine.equals("default;")) {
            // set the current user
            MainFrame.setSessionUser(
                new User(
                    defaultLine.split(";")[3], // name
                    defaultLine.split(";")[4], // surname
                    decrypt(defaultLine.split(";")[1], key), // username
                    decrypt(defaultLine.split(";")[2], key))); // password

            // launching the bank app
            new MainFrame();

            return;
          }
        }
      }
    } catch (IOException e) {
      System.out.println("users-info creation failed");
    }

    // instancing main frame for authentication
    new MainFrame(usersInfo);
  }

  public static File getUsersInfo() {
    return usersInfo;
  }

  public static int getKey() {
    return key;
  }

  public static String crypt(String str, int key) {
    StringBuilder newStr = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      char currentChar = str.charAt(i);

      if (Character.isLetter(currentChar)) {
        char base = (Character.isUpperCase(currentChar)) ? 'A' : 'a';

        currentChar = (char) ((currentChar - base + key) % 26 + base);
      }

      newStr.append(currentChar);
    }

    return newStr.toString();
  }

  public static String decrypt(String str, int key) {
    return crypt(str, 26 - (key % 26));
  }
}
