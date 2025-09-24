package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Drive extends LinearOpMode {

    private RobotHardware robot;

    @Override
    public void runOpMode() {
        robot = new RobotHardware(this);
        robot.init();

        waitForStart();

        while (opModeIsActive()) {
            // Get stick values for mecanum drive
            double rx = gamepad1.right_stick_x; // rotation
            double y  = -gamepad1.left_stick_y; // forward (note: Y is typically reversed)
            double x  = gamepad1.left_stick_x * 1.1; // left/right, may need scaling

            // Calculate wheel powers
            double frontLeftPower  = y + x + rx;
            double backLeftPower   = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower  = y + x - rx;

            // Normalize powers so no wheel power exceeds 1.0
            double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backRightPower));
            if (max > 1.0) {
                frontLeftPower  /= max;
                backLeftPower   /= max;
                frontRightPower /= max;
                backRightPower  /= max;
            }

            // Set the powers
            robot.frontLeftDrive.setPower(frontLeftPower);
            robot.backLeftDrive.setPower(backLeftPower);
            robot.frontRightDrive.setPower(frontRightPower);
            robot.backRightDrive.setPower(backRightPower);

            // Intake/outtake with triggers
            double intakePower = 0;
            if (gamepad1.left_trigger > 0.1) {
                intakePower = 1.0; // Intake
            } else if (gamepad1.right_trigger > 0.1) {
                intakePower = -1.0; // Outtake
            }
            robot.intake.setPower(intakePower);

            // Ball Launcher with A button (may require change)
            double launcherPower = 0;
            if (gamepad1.x) {
                launcherPower = 1.0;  // Launch
            }
            robot.launcher.setPower(launcherPower);

            telemetry.addData("FrontLeft", frontLeftPower);
            telemetry.addData("BackLeft", backLeftPower);
            telemetry.addData("FrontRight", frontRightPower);
            telemetry.addData("BackRight", backRightPower);
            telemetry.addData("Intake", intakePower);
            telemetry.addData("Launcher", launcherPower);
            telemetry.update();
        }
    }
}
