
//THIS CODE IS NOT TESTED YET
//THIS CODE IS NOT TESTED YET
//THIS CODE IS NOT TESTED YET

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto: Backward-Turn-Shoot", group = "Main")
public class AutoSimple extends LinearOpMode {

    private RobotHardware robot;

    @Override
    public void runOpMode() {
        robot = new RobotHardware(this);
        robot.init();
        setDriveRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status", "Ready for start");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            drivePower(-0.4, -0.4, -0.4, -0.4);
            sleep(3000);
            stopDrive();

            drivePower(-0.4, 0.4, -0.4, 0.4);
            sleep(700);
            stopDrive();

            robot.launcher.setPower(1);
            sleep(1500);
            robot.launcher.setPower(0);
            telemetry.addData("Status", "Auto complete");
            telemetry.update();
        }
    }

    private void drivePower(double fl, double fr, double bl, double br) {
        robot.frontLeftDrive.setPower(fl);
        robot.frontRightDrive.setPower(fr);
        robot.backLeftDrive.setPower(bl);
        robot.backRightDrive.setPower(br);
    }

    private void stopDrive() {
        drivePower(0, 0, 0, 0);
    }

    private void setDriveRunMode(DcMotor.RunMode mode) {
        robot.frontLeftDrive.setMode(mode);
        robot.frontRightDrive.setMode(mode);
        robot.backLeftDrive.setMode(mode);
        robot.backRightDrive.setMode(mode);
    }
}
