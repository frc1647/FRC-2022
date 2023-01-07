# Team 1647's Rapid React robot code
This is our code for our 2022 robot! It features code for a fully operational swerve drivetrain (no super-niche features though), multiple autonomus sequences, and much more!

*Introduction*

This readme, and this entire codebase, contains a lot of information about how things work and what things do (at least it should). This includes general explanations of how the wpilib framework (TimedRobot in this case) works and interacts with the written code. This is intended to make the code easier to grasp for less experienced/knowledgeable students, and as intuitive as possible.

*In the beginning, there was Main.java*

As you may expect, the code begins at the Main file. The code there immediately sends us to the initialization sequence in the Robot file. This is the part where the code execution becomes different depending on the framework, with it being Command-Based in this case (specifically the old version of the framework that is no longer supported as of 2023). The documentation for the current Command-Based framework can be found [here](https://docs.wpilib.org/en/stable/docs/software/commandbased/index.html), and the docs for the old version of the framework can be found [here](https://docs.wpilib.org/en/2021/docs/software/old-commandbased/index.html).

The Command-Based framework is built on top of the TimedRobot framework, so Robot.java will include all of the TimedRobot methods. These methods execute at different moments during robot operation, and one type of these methods executes code over-and-over, several times per second (specifically every 20ms, aka 50 times a second, unless manually overridden). With the Command-Based framework it is intended that most robot logic is handled by the OI file and the subsystems, but some code works better in the TimedRobot methods.
