package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANDriveSubsystem;


public class AutoAlign extends Command{
    private final CANDriveSubsystem driveSubsystem;
    private final String limelightName;
    private final double kP = 0.035;

    public AutoAlign(CANDriveSubsystem driveSubsystem, String limelightName){
        this.driveSubsystem = driveSubsystem;
        this.limelightName = limelightName;
        addRequirements(driveSubsystem);
    }

    public void execute(){
        //double rotSpeed = TagAlignment.aimAtTag(limelightName, kP);
        //driveSubsystem.drive(0, 0, rotSpeed, true);
    }

    public boolean isFinished(){
        //return TagAlignment.isAlignedWithTag(limelightName, 2.0);
        return false; 
    }

    public void end(boolean interrupted){
        //drive.drive(0, 0, 0, true);
    }
}
