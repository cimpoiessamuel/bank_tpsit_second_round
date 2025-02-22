import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import javax.swing.*;

public class InternalFrame {

  InternalFrame(JFrame mainFrame, File users_info) {

    // initialising the sub-frames container
    JDesktopPane subWindowPane = new JDesktopPane();
    subWindowPane.setBounds(0, 0, 1600, 1024);

    // adding subWindow to the main-frame
    mainFrame.add(subWindowPane);

    // setting sub-frames container visible
    subWindowPane.setVisible(true);

    // initialising main sub-frame
    JInternalFrame internalFrame = new JInternalFrame("", false, false, false, false);

    internalFrame.setSize(500, 700);
    internalFrame.setLayout(null);
    internalFrame.setFocusable(true);
    internalFrame.setLocation(
        (subWindowPane.getWidth() - internalFrame.getWidth()) / 2,
        (subWindowPane.getHeight() - internalFrame.getHeight()) / 2);

    // adding the internalFrame to the sub-frames container
    subWindowPane.add(internalFrame);

    // initializing the title-bar icon
    ImageIcon titleBarLogo = new ImageIcon("src/main/resources/images/vBank2-rounded-16x16.png");
    internalFrame.setFrameIcon(titleBarLogo);

    // instantiating login interface -> the default sub-frame displayed if default user is missing
    LoginInternalFrame loginInternalFrame =
        new LoginInternalFrame(mainFrame, internalFrame, users_info);

    // everytime the main-frame is resized, the internal-frame is re-centered
    mainFrame.addComponentListener(
        new ComponentAdapter() {
          @Override
          public void componentResized(ComponentEvent e) {
            int xA = (mainFrame.getWidth() - internalFrame.getWidth()) / 2;
            int yA = (mainFrame.getHeight() - internalFrame.getHeight()) / 2;

            internalFrame.setLocation(xA, yA);

            // everytime the main-frame is resized, the subWindowPane is resized too
            int xB = mainFrame.getWidth();
            int yB = mainFrame.getHeight();

            subWindowPane.setBounds(0, 0, xB, yB);
          }
        });

    // everytime the internal-frame is moved, it re-center itself
    internalFrame.addComponentListener(
        new ComponentAdapter() {
          @Override
          public void componentMoved(ComponentEvent e) {
            int x = (mainFrame.getWidth() - internalFrame.getWidth()) / 2;
            int y = (mainFrame.getHeight() - internalFrame.getHeight()) / 2;

            internalFrame.setLocation(x, y);
          }
        });
  }
}
