package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class AutoCompilation {

    boolean startTimeSet;
    double startTime;

    public AutoCompilation() {
        startTimeSet = false;
        startTime = 0;
    }

    public boolean pickUpTheBall() {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
        }
        if (Timer.getFPGATimestamp() - startTime <= 5) {
       //     Robot.spinningIntake.spinIntake(true, false, false);
            return false;
        }
        else {
      //      Robot.spinningIntake.spinIntake(false, false, false);
            return true;
        }
    }
    
    public boolean shootTheBall() {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
        }
        if (Timer.getFPGATimestamp() - startTime <= 2) {
            Robot.shootNow.operatorShoot(0, .5);
            return false;
        }
        else {
            Robot.shootNow.operatorShoot(0,0);
            return true;
        }
    }
}