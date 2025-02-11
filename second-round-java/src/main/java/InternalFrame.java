import javax.swing.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.io.BufferedWriter;
import java.io.BufferedReader;

public class InternalFrame {

    InternalFrame(JFrame mainFrame, BufferedWriter outFile, BufferedReader inFile) {

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
        internalFrame.setLocation((subWindowPane.getWidth() - internalFrame.getWidth()) / 2, (subWindowPane.getHeight() - internalFrame.getHeight()) / 2);


        // adding the internalFrame to the sub-frames container
        subWindowPane.add(internalFrame);


        // initializing the title-bar icon
        ImageIcon titleBarLogo = new ImageIcon("src/main/resources/vBank2-rounded-16x16.png");
        internalFrame.setFrameIcon(titleBarLogo);


        // instantiating login interface -> the default sub-frame displayed if default is missing
        LoginInternalFrame loginInternalFrame = new LoginInternalFrame(internalFrame, outFile, inFile);


        // everytime the main-frame is resized, the internal-frame is re-centered
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int x = (mainFrame.getWidth() - internalFrame.getWidth()) / 2;
                int y = (mainFrame.getHeight() - internalFrame.getHeight()) / 2;

                internalFrame.setLocation(x, y);


                // everytime the main-frame is resized, the subWindowPane is resized too
                x = mainFrame.getWidth();
                y = mainFrame.getHeight();

                subWindowPane.setBounds(0, 0, x, y);
            }
        });


        // everytime the internal-frame is moved, it re-center itself
        internalFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                int x = (mainFrame.getWidth() - internalFrame.getWidth()) / 2;
                int y = (mainFrame.getHeight() - internalFrame.getHeight()) / 2;

                internalFrame.setLocation(x, y);
            }
        });
    }
}
