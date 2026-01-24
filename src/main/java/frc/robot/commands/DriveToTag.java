package frc.robot.commands;
import frc.robot.subsystems.LimelightHelpers;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class DriveToTag extends Command {
    private final DriveSubsystem drivetrain;
    private final String limelightName;

    //CONSTANTS
    private static final double kPAim = -0.02; //how much to turn to center the tag
    private static final double kPDistance = -0.08; //driving forward to reach target area
    private static final double MAX_SPEED = 0.3; //max allowed forward speed
    private static final double minAim = 0.05;
    private static final double TARGET_AREA = 18.3; //how close it drives

    public DriveToTag(DriveSubsystem drivetrain, String limelightName){
        this.drivetrain = drivetrain;
        this.limelightName = limelightName;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        LimelightHelpers.setLEDMode_ForceOn(limelightName);
        System.out.println("DriveToTag Command Initialized");
    }

    //if limelight has target
    @Override
    public void execute() { 
        System.out.println("DriveToTag Command Executed");
        if (LimelightHelpers.getTV(limelightName)) { 
    
  //aiming/ranging at the same time using TA instead of TY - from limelight page
  double tx = LimelightHelpers.getTX(limelightName);  //horizontal offset
  double ta = LimelightHelpers.getTA(limelightName); //target area (bigger = closer)

  //aiming ranging errors
  double headingError = tx; // negative because +tx = tag to right
  double distanceError = TARGET_AREA - ta; // positive if we’re too far
  double steeringAdjust = 0.0; 

    //AIMING (rotation)
    if (tx > 1.0) {
            steeringAdjust = (kPAim * headingError) - minAim;
    } else if (tx < -1.0) {
            steeringAdjust = (kPAim * headingError) + minAim;
    }

    //RANGING (forward speed)
    double distanceAdjust = kPDistance * distanceError; //moves robot towards tag

    // Clamp forward speed
    distanceAdjust = Math.max(Math.min(distanceAdjust, MAX_SPEED), -MAX_SPEED);

    //Combine both controls (simultaneous aim + range)
    //For swerve: forward = xSpeed, strafe = 0, rotation = steeringAdjust
    drivetrain.drive(distanceAdjust, 0.0, steeringAdjust, false);
    }
}

//if command is interrupted, stop
@Override
public void end(boolean interrupted) {
    drivetrain.drive(0.0, 0.0, 0.0, false);
    LimelightHelpers.setLEDMode_ForceOff(limelightName);
}

//end when we've reached the target area
@Override
public boolean isFinished() {
    return LimelightHelpers.getTV(limelightName) && LimelightHelpers.getTA(limelightName) >= TARGET_AREA;
}

}
