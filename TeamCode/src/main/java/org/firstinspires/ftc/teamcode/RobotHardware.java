package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
public class RobotHardware {

    private LinearOpMode myOpMode = null;

    public DcMotor frontLeftDrive   = null;
    public DcMotor frontRightDrive  = null;
    public DcMotor backLeftDrive    = null;
    public DcMotor backRightDrive   = null;

    public DcMotor intake           = null;

    //public DcMotor launcher = null;

    public RobotHardware(LinearOpMode opmode) {
        myOpMode = opmode;
    }

    public void init()    {

        frontLeftDrive  = myOpMode.hardwareMap.get(DcMotor.class, "FL_drive");
        frontRightDrive = myOpMode.hardwareMap.get(DcMotor.class, "FR_drive");
        backLeftDrive   = myOpMode.hardwareMap.get(DcMotor.class, "BL_drive");
        backRightDrive  = myOpMode.hardwareMap.get(DcMotor.class, "BR_drive");

        intake = myOpMode.hardwareMap.get(DcMotor.class, "intake");
        //launcher = myOpMode.hardwareMap.get(DcMotor.class, "launcher");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        intake.setDirection(DcMotor.Direction.FORWARD);
        //launcher.setDirection(DcMotor.Direction.FORWARD);      //Make reverse if necessary

        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

}
