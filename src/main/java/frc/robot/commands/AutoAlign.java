package frc.robot.commands;

import frc.robot.subsystems.CANDriveSubsystem;

public class AutoAlign {
    private final CANDriveSubsystem driveSubsystem;
    private final String limelightName;
    private final double kP = 0.035;

    public AutoAlign(CANDriveSubsystem driveSubsystem, String limelightName){
        this.driveSubsystem = driveSubsystem;
        this.limelightName = limelightName;
        //addRequirements(driveSubsystem);
    }

    public void execute(){
        
    }

}
