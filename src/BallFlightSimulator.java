import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class BallFlightSimulator extends JPanel {
    Scanner scanner = new Scanner(System.in);

    public double angle; // throw angle in degrees
    public double velocity; //initial speed in meters per second
    private double time = 0.0; // flight duration in seconds
    private double x = 0.0; // x position of the ball in meters
    private double y = 0.0; // y position of the ball in meters
    private final double g = 9.81; //gravitational acceleration in meters per square second

    public BallFlightSimulator() {
        System.out.println("Enter the throw angle in degrees: ");
        angle = scanner.nextDouble();
        System.out.println("Enter the initial speed in meters per second: ");
        velocity = scanner.nextDouble();
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval((int) x, getHeight() - (int) y, 20, 20); //draw ball
    }

    public void simulate() {
        while (y >= 0.0) { //until the ball hits the ground
            double vx = velocity * Math.cos(Math.toRadians(angle)); // velocity X ball in mps
            double vy = velocity * Math.cos(Math.toRadians(angle)) - g * time; // velocity Y ball in mps
            x += vx * 0.1; //move the ball 1/10 of a second
            y += vy * 0.1; // move the ball 1/10 of a second
            time += 0.1; //increase the time by 1/10 of a second
            repaint(); //refresh the screen to draw the new position of the ball
            try {
                Thread.sleep(100); //delay the simulation by 1/10th of a second for a realistic effect
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        BallFlightSimulator simulator = new BallFlightSimulator();
        simulator.simulate();
    }
}
