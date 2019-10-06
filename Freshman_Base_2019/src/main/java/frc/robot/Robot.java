package frc.robot;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.autos.AutoCompilation;
import frc.robot.autos.RealAutos;
import frc.robot.controls.DriverControls;
import frc.robot.controls.OperatorControls;
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchCollector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LineFollower;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.DriverStation;

import java.lang.Math;

public class Robot extends TimedRobot {
    public static Joystick driverJoy;
    static Joystick operatorJoy;

    public static boolean firstTime = true;
    static boolean buttonEnable = false, buttonPrev = false;

    final int firstDistance = 120;
    final int secondDistance = 60;

    static int autostage = 0;

    public static AHRS NAVXgyro = new AHRS(SerialPort.Port.kMXP);

    DriverControls driver = new DriverControls(2);
    OperatorControls operator = new OperatorControls(1);

    static Drivetrain drivingClass = new Drivetrain();
    public static Intake spinningIntake = new Intake();
    public static Shooter shootNow = new Shooter();
    static HatchCollector maticsMatics = new HatchCollector();
    static LineFollower lineFollower = new LineFollower();
    static RealAutos autos = new RealAutos();
    static AutoCompilation tryAutosAgain = new AutoCompilation();

    boolean startAuto = false;
    boolean startAutoSet = false;

    boolean startTimeSet = false;
    boolean startTimeSetTwo = false;
    public static double startTime = 0;
    static double goNow = 0;

    double pi = Math.PI;

    @Override
    public void robotInit() {
        Arduino.setAllianceColor(DriverStation.getInstance().getAlliance());

        driverJoy = new Joystick(0);
        operatorJoy = new Joystick(1);
        CameraServer.getInstance().startAutomaticCapture();
        NAVXgyro.reset();
        drivingClass.initQuadrature();
    }

	@Override
    public void autonomousInit() {

        if (startTimeSet == false) {
            startTime = Timer.getFPGATimestamp();
            startTimeSet = true;
        }

        if (startTimeSetTwo == false) {
            goNow = Timer.getFPGATimestamp();
            startTimeSetTwo = true;
        }
    }

    @Override
    public void autonomousPeriodic() {
        if (!drivingClass.moveDistance(60, .25)) {
            drivingClass.moveDistance(60, .25);
        } else if (drivingClass.moveDistance(60, .25)) {
            frc.robot.subsystems.Shooter.autoShoot();
        }
    }

    @Override
    public void teleopPeriodic() {

       // driver.controls();
       // operator.controls();        
        
        Drivetrain.drive(driverJoy.getRawAxis(1) * 1, driverJoy.getRawAxis(2) * .75, driverJoy.getRawAxis(3) * .75);
        spinningIntake.spinIntake(operatorJoy.getRawButton(2), operatorJoy.getRawButton(4));
        shootNow.operatorShoot(operatorJoy.getRawAxis(1), operatorJoy.getRawAxis(5));
        maticsMatics.openClaw(operatorJoy.getRawAxis(2));
        maticsMatics.moveToSafety(operatorJoy.getRawButton(3), operatorJoy.getRawButton(1));
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }
}

// Need to put LED code into the regular code