// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.Constants.MotorIDs;

public class Drive extends SubsystemBase {

  private SparkMax objSparkLF = new SparkMax(MotorIDs.iSparkLF, MotorType.kBrushed);
  private SparkMax objSparkLB = new SparkMax(MotorIDs.iSparkLB, MotorType.kBrushed);
  private SparkMax objSparkRF = new SparkMax(MotorIDs.iSparkRF, MotorType.kBrushed);
  private SparkMax objSparkRB = new SparkMax(MotorIDs.iSparkRB, MotorType.kBrushed);

  private SparkMaxConfig objLeadConfig = new SparkMaxConfig();
  private SparkMaxConfig objLeftFollowConfig = new SparkMaxConfig();
  private SparkMaxConfig objRightFollowConfig = new SparkMaxConfig();

  /** Creates a new Drive. */
  public Drive() {

    objLeadConfig.idleMode(IdleMode.kBrake);
    objLeftFollowConfig.idleMode(IdleMode.kBrake);
    objRightFollowConfig.idleMode(IdleMode.kBrake);

    objLeftFollowConfig.follow(MotorIDs.iSparkLF);
    objRightFollowConfig.follow(MotorIDs.iSparkRF);
    
    for (int i = 1; i < 5; i++) {
      objSparkLF.configure(objLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
      objSparkRF.configure(objLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
      objSparkLB.configure(objLeftFollowConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
      objSparkRB.configure(objRightFollowConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    }
  }

  @Override
  public void periodic() {

      // This method will be called once per scheduler run
  }

  public void driveFWD(DoubleSupplier dsSpeed){
    objSparkLF.set(dsSpeed.getAsDouble());
    objSparkRF.set(-dsSpeed.getAsDouble());
  }

  public void turn(DoubleSupplier dsSpeed){
    objSparkLF.set(dsSpeed.getAsDouble());
    objSparkRF.set(dsSpeed.getAsDouble());
  }

  public void stopMotor(){
    objSparkLF.stopMotor();
    objSparkRF.stopMotor();
  }


  public void totalDrive(DoubleSupplier dsFwd, DoubleSupplier dsTurn){
    objSparkLF.set(dsFwd.getAsDouble());
    objSparkRF.set(-dsFwd.getAsDouble());
    objSparkLF.set(dsTurn.getAsDouble());
    objSparkRF.set(dsTurn.getAsDouble());
  } 
}
