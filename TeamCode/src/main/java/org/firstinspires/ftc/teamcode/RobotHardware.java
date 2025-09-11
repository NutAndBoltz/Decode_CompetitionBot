package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
public class RobotHardware {

    private LinearOpMode myOpMode = null;   // gain access to methods in the calling OpMode.

    // Define Motor and Servo objects  (Make them private so they can't be accessed externally)
    public DcMotor frontLeftDrive   = null;
    public DcMotor frontRightDrive  = null;
    public DcMotor backLeftDrive    = null;
    public DcMotor backRightDrive   = null;

    public DcMotor intake     = null;

    // Define a constructor that allows the OpMode to pass a reference to itself.
    public RobotHardware(LinearOpMode opmode) {
        myOpMode = opmode;
    }

    /**
     * Initialize all the robot's hardware.
     * This method must be called ONCE when the OpMode is initialized.
     * All of the hardware devices are accessed via the hardware map, and initialized.
     */
    public void init()    {
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        frontLeftDrive  = myOpMode.hardwareMap.get(DcMotor.class, "FL_drive");
        frontRightDrive = myOpMode.hardwareMap.get(DcMotor.class, "FR_drive");
        backLeftDrive   = myOpMode.hardwareMap.get(DcMotor.class, "BL_drive");
        backRightDrive  = myOpMode.hardwareMap.get(DcMotor.class, "BR_drive");

        intake = myOpMode.hardwareMap.get(DcMotor.class, "intake");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        intake.setDirection(DcMotor.Direction.FORWARD);

        //slideClawServo  = myOpMode.hardwareMap.get(Servo.class, "slide_claw");

       // slideClawServo.setPosition(SLIDE_CLAW_CLOSED);
        //armServo.setPosition(0.5);

        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

}
