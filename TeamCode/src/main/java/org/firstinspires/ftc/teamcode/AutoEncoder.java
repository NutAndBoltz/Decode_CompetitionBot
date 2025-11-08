package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto: Encoder Back-Turn-Shoot", group = "Main")
public class AutoEncoder extends LinearOpMode {

    RobotHardware robot;

    @Override
    public void runOpMode() {
        robot = new RobotHardware(this);
        robot.init();

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status","ready");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            driveTicks(-3500, 0.6);
            turnLeftTicks(-540, 0.4); //Best Results till now (11/8/25)

            robot.launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.launcher.setPower(1.0);
            sleep(2000);

            robot.intake.setPower(1.0);
            sleep(800);
            robot.intake.setPower(0.0);

            robot.launcher.setPower(0.0);
        }
    }

    private void driveTicks(int ticks, double power) {
        robot.frontLeftDrive.setTargetPosition(robot.frontLeftDrive.getCurrentPosition() + ticks);
        robot.frontRightDrive.setTargetPosition(robot.frontRightDrive.getCurrentPosition() + ticks);
        robot.backLeftDrive.setTargetPosition(robot.backLeftDrive.getCurrentPosition() + ticks);
        robot.backRightDrive.setTargetPosition(robot.backRightDrive.getCurrentPosition() + ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftDrive.setPower(power);
        robot.frontRightDrive.setPower(power);
        robot.backLeftDrive.setPower(power);
        robot.backRightDrive.setPower(power);

        while (opModeIsActive()
                && (robot.frontLeftDrive.isBusy()
                || robot.frontRightDrive.isBusy()
                || robot.backLeftDrive.isBusy()
                || robot.backRightDrive.isBusy())) {
            telemetry.addData("drive","busy");
            telemetry.update();
        }

        stopDrive();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void turnLeftTicks(int ticks, double power) {
        robot.frontLeftDrive.setTargetPosition(robot.frontLeftDrive.getCurrentPosition() - ticks);
        robot.backLeftDrive.setTargetPosition(robot.backLeftDrive.getCurrentPosition() - ticks);
        robot.frontRightDrive.setTargetPosition(robot.frontRightDrive.getCurrentPosition() + ticks);
        robot.backRightDrive.setTargetPosition(robot.backRightDrive.getCurrentPosition() + ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftDrive.setPower(power);
        robot.backLeftDrive.setPower(power);
        robot.frontRightDrive.setPower(power);
        robot.backRightDrive.setPower(power);

        while (opModeIsActive()
                && (robot.frontLeftDrive.isBusy()
                || robot.frontRightDrive.isBusy()
                || robot.backLeftDrive.isBusy()
                || robot.backRightDrive.isBusy())) {
            telemetry.addData("turn","busy");
            telemetry.update();
        }

        stopDrive();
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void stopDrive() {
        robot.frontLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.backRightDrive.setPower(0);
    }

    private void setMode(DcMotor.RunMode mode) {
        robot.frontLeftDrive.setMode(mode);
        robot.frontRightDrive.setMode(mode);
        robot.backLeftDrive.setMode(mode);
        robot.backRightDrive.setMode(mode);
    }
}
