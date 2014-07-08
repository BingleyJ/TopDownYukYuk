
        import javax.swing.*;
        import java.awt.event.*;

        public class Main extends JPanel implements KeyListener, Runnable {
            boolean isUpPressed, isDownPressed, isSpacePressed;
            static JFrame f;

            public static void main(String[] args) {
                f = new JFrame();
                f.setSize(600,300);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setContentPane(new Main());
                f.setVisible(true);
            }

            public Main() {
                setFocusable(true);
                addKeyListener(this);
                new Thread(this).start();
            }

            @Override
			public void keyTyped(KeyEvent ke) {
            }

            @Override
			public void keyPressed(KeyEvent ke) {
                switch(ke.getKeyCode()) {
                    case KeyEvent.VK_UP: isUpPressed = true; break;
                    case KeyEvent.VK_DOWN: isDownPressed = true; break;
                    case KeyEvent.VK_SPACE: isSpacePressed = true; break;
                }
            }

            @Override
			public void keyReleased(KeyEvent ke) {
                switch(ke.getKeyCode()) {
                    case KeyEvent.VK_UP: isUpPressed = false; break;
                    case KeyEvent.VK_DOWN: isDownPressed = false; break;
                    case KeyEvent.VK_SPACE: isSpacePressed = false; break;
                }
            }

            @Override
			public void run() {
                while(true) {
                    try {
                        String s = "up pressed: " + isUpPressed + ", down pressed: " + isDownPressed +
                                ", spacePressed: " + isSpacePressed;
                        f.setTitle(s);
                        Thread.sleep(200);
                    } catch(Exception exc) {
                        exc.printStackTrace();
                        break;
                    }
                }
            }
        }
