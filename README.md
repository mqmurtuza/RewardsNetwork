# RewardsNetwork
This is a sbt based scala program developed using sbt,scala,FunSuite and IntelliJ. This project has all source code and "TestReport" for test coverage.

# References
https://alvinalexander.com/scala/
https://docs.scala-lang.org/
https://github.com/rockthejvm

# How to dowload the program
- Either clone the repo or download as zip 
    1. git clone RewardsNetwork 
    2. cd RewardsNetwork

# How to build the package 
 - sbt clean package

# How to run the test
- sbt test

# How to run the test with the target jar artifact
- Once you build the package, the jar file will get generated in the /target/

      # $ scala target/scala-2.12/rewardsnetwork_2.12-0.1.jar HOT 8,6,4,2,1,7
      SUCCESS
      INPUT: HOT,8, 6, 4, 2, 1, 7
      OUTPUT: Removing PJs, shorts, shirt, sunglasses, sandals, leaving house

      # scala target/scala-2.12/rewardsnetwork_2.12-0.1.jar HOT 8,6,6
      FAILURE
      INPUT: HOT,8, 6, 6
      OUTPUT: Removing PJs, shorts, fail

      # scala target/scala-2.12/rewardsnetwork_2.12-0.1.jar HOT 8,6,3
      FAILURE
      INPUT: HOT,8, 6, 3
      OUTPUT: Removing PJs, shorts, fail

      # scala target/scala-2.12/rewardsnetwork_2.12-0.1.jar COLD 8,6,3,4,2,5,1,7
      SUCCESS
      INPUT: COLD,8, 6, 3, 4, 2, 5, 1, 7
      OUTPUT: Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house

      # scala target/scala-2.12/rewardsnetwork_2.12-0.1.jar COLD 8,6,3,4,2,5,7
      FAILURE
      INPUT: COLD,8, 6, 3, 4, 2, 5, 7
      OUTPUT: Removing PJs, pants, socks, shirt, hat, jacket, fail

      # scala target/scala-2.12/rewardsnetwork_2.12-0.1.jar COLD 6
      FAILURE
      INPUT: COLD,6
      OUTPUT: fail

# The project directory structure
1. The program follows maven based style of creating directoriies in the project. 
   - src/main/scala - All the source code is in this package.
    - com/rewardsnetwork
       1. RewardsMainApplication: It is the main driver of the program
       2. /service/DressEvent:  It has the main logic to process the entire logic to dress up in both HOT or COLD event.
       3. /service/DressHotEvent : This object is invoked by the DressEvent to apply all appropriate rules in regards to HOT event
       4. /service/DressColdEvent : This object is invoked by the DressEvent to apply all appropriate rules in regards to COLD event
   - src/test/scala - All the test code is in this package
    - com/rewardsmetwork
       1. /service/DressEvent:  It is using FunSuite to run the different scenarios of successful HOT & COLD, Failures and Invalid arguments with all exceptions
       3. /service/DressHotEvent : It has test coverage specific for HOT events
       4. /service/DressColdEvent : It has test coverage specific for COLD events
       
       # Test Coverage details 
          $ sbt test
              [info] Loading settings from idea.sbt ...
              [info] Loading global plugins from C:\Users\mmurtuza\.sbt\1.0\plugins
              [info] Loading project definition from D:\Professional\scala\RewardsNetwork\project
              [info] Loading settings from build.sbt ...
              [info] Set current project to RewardsNetwork (in build file:/D:/Professional/scala/RewardsNetwork/)
              [info] Compiling 3 Scala sources to D:\Professional\scala\RewardsNetwork\target\scala-2.12\test-classes ...
              [info] Done compiling.
              SUCCESS
              INPUT: HOT,8, 6, 4, 2, 1, 7
              OUTPUT: Removing PJs, shorts, shirt, sunglasses, sandals, leaving house
              FAILURE
              INPUT: HOT,8, 6, 6
              OUTPUT: Removing PJs, shorts, fail
              FAILURE
              INPUT: HOT,8, 6, 3
              OUTPUT: Removing PJs, shorts, fail
              SUCCESS
              INPUT: COLD,8, 6, 3, 4, 2, 5, 1, 7
              OUTPUT: Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house
              FAILURE
              INPUT: COLD,8, 6, 3, 4, 2, 5, 7
              OUTPUT: Removing PJs, pants, socks, shirt, hat, jacket, fail
              FAILURE
              INPUT: COLD,6
              OUTPUT: fail
              Usage: RewardsMainApplication <temperature Type> HOT|COLD <dressingCommands> 1,2,3,4,5,6,7,8 | 8,2,5 |...>
               valid arguments HOT 8,4,6,1,2,7
              Usage: RewardsMainApplication <temperature Type> HOT|COLD <dressingCommands> 1,2,3,4,5,6,7,8 | 8,2,5 |...>
               valid arguments HOT 8,4,6,1,2,7
              Usage: RewardsMainApplication <temperature Type> HOT|COLD <dressingCommands> 1,2,3,4,5,6,7,8 | 8,2,5 |...>
               valid arguments HOT 8,4,6,1,2,7
              [info] DressColdEventTest:
              [info] - test COLD SUCCESS scenarios 8, 6, 3, 4, 2, 5, 1, 7
              [info] - test COLD FAILURE scenarios 8, 6, 3, 4, 2, 5, 7
              [info] - test COLD FAILURE scenarios 6
              [info] - test COLD Invalid scenarios 8, 6, 4, 2, 1, 85677
              [info] - test COLD Invalid scenarios 8, 6, 85677
              [info] - test COLD Invalid scenarios 8, 2, 85677
              [info] DressEventTest:
              [info] - test HOT SUCCESS scenarios HOT, 8,6,4,2,1,7
              [info] - test HOT FAILURE scenarios HOT, 8,6,6
              [info] - test HOT FAILURE scenarios HOT, 8,6,3
              [info] - test COLD SUCCESS scenarios COLD, 8,6,3,4,2,5,1,7
              [info] - test COLD FAILURE scenarios COLD, 8,6,3,4,2,5,7
              [info] - test COLD FAILURE scenarios COLD, 6
              [info] - test Invalid scenarios COLD, 8,6,3,4,2,5,14567,7
              [info] - test Invalid scenarios HOT, 8,6,4,2,1,85677
              [info] DressHotEventTest:
              [info] - test HOT SUCCESS scenarios 8, 6, 4, 2, 1, 7
              [info] - test HOT FAILURE scenarios 8, 6, 6
              [info] - test HOT FAILURE scenarios 8, 6, 3
              [info] - test Invalid scenarios 8, 6, 4, 2, 1, 85677
              [info] - test Invalid scenarios 8, 6, 85677
              [info] - test Invalid scenarios 8, 2, 85677
              [info] - test Invalid scenarios HOT, 8,6,85677
              [info] - test Invalid scenarios HOT, 8,2,85677
              [info] - test Invalid scenarios COLD
              [info] - test Invalid scenarios HOTTIE, 8,6,7
              [info] - test Invalid scenarios
              [info] Run completed in 496 milliseconds.
              [info] Total number of tests run: 25
              [info] Suites: completed 3, aborted 0
              [info] Tests: succeeded 25, failed 0, canceled 0, ignored 0, pending 0
              [info] All tests passed.

   
  
