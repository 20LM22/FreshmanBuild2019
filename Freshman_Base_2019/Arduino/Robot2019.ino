/*
   Robot 2019
   Utilizes the Arduino as an I2C slave of the RoboRio for handling some tasks
*/

#include "I2C.h"
#include "LEDStrip.h"

void setup() {
  Serial.begin(9600);
  if (Serial)
    Serial.println("program started");
  I2C::initialize(0x1);
  LEDStrip::initialize(6, 15, 255);
}

void loop() {
  LEDStrip::updateDisplay(I2C::getAllianceColor(), I2C::getPattern(), I2C::getDiagnosticColor(), I2C::getDiagnosticPattern());
  I2C::setWriteData(PixyCam::getObjInView(), PixyCam::getXValue());
}