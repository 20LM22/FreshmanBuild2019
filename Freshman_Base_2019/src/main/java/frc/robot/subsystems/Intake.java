package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class Intake {
    static TalonSRX intake;
    double intakeSpeed;
    TalonSRX shooter;

    public Intake() {
        intake = new TalonSRX(5);
    }

    public static void setSpeed(double speed) {
        intake.set(ControlMode.PercentOutput, speed);
    }

    public static void stop() {
        intake.set(ControlMode.PercentOutput, 0);
    }
    
    public void spinIntake(boolean forward, boolean stop) {
        if (forward == true) { // a = 1, b = 2, x = 3, y =4
            intake.set(ControlMode.PercentOutput, -1);
        }  else if (stop == true) {
            intake.set(ControlMode.PercentOutput, 0);
        }
    }

    public boolean autoIntake() {
        if ((Timer.getFPGATimestamp() - Robot.startTime) <= 3) {
            System.out.println("This is running.");
            intake.set(ControlMode.PercentOutput, -1);
            return false;
        } else if ((Timer.getFPGATimestamp() - Robot.startTime) > 3) {
            System.out.println("This is done.");
            intake.set(ControlMode.PercentOutput, 0);
            return true;
        } else {
            return false;
        }
    }
}
