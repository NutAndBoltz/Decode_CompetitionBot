package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//my code is good dw abt it bruh.
@TeleOp(name = "My TeleOp", group = "Main")
public class MyTeleOp extends LinearOpMode {

    private RobotHardware robot;

    @Override
    public void runOpMode() {

        robot = new RobotHardware(this);
        robot.init();


        setDriveRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Ready to start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // simple tank, i mean, mecanum style
            double drive = -gamepad1.left_stick_y;   // forward backward
            double strafe = gamepad1.left_stick_x;   // left right
            double turn = gamepad1.right_stick_x;    // rotate

            // thats basic mecanum math
            double fl = drive + strafe + turn;
            double fr = drive - strafe - turn;
            double bl = drive - strafe + turn;
            double br = drive + strafe - turn;

            // setting em power
            robot.frontLeftDrive.setPower(fl);
            robot.frontRightDrive.setPower(fr);
            robot.backLeftDrive.setPower(bl);
            robot.backRightDrive.setPower(br);

            // intake on gamepad1 A thingy
            if (gamepad1.a) {
                robot.intake.setPower(1.0);
            } else if (gamepad1.b) {
                robot.intake.setPower(-1.0);
            } else {
                robot.intake.setPower(0.0);
            }

            //ADHAM WAS HERE
            if (gamepad1.right_bumper) {
                robot.launcher.setPower(1.0);
            } else {
                robot.launcher.setPower(0.0);
            }

            telemetry.addData("Drive", "FL %.2f FR %.2f", fl, fr);
            telemetry.addData("Intake", robot.intake.getPower());
            telemetry.addData("Launcher", robot.launcher.getPower());
            telemetry.update();
        }
    }

    private void setDriveRunMode(DcMotor.RunMode mode) {
        robot.frontLeftDrive.setMode(mode);
        robot.frontRightDrive.setMode(mode);
        robot.backLeftDrive.setMode(mode);
        robot.backRightDrive.setMode(mode);
    }
}
