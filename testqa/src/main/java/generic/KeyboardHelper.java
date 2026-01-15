package generic;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyboardHelper {

    // Static method so you can call it directly without creating an object
    public static void resetKeyboardLanguage() {
        try {
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);

            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_ALT);

            Thread.sleep(500);

            System.out.println("✔ Keyboard language reset executed");

        } catch (Exception e) {
            System.out.println("❌ Keyboard language reset failed");
            e.printStackTrace();
        }
    }
}
