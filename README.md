# Semantic Test Repair for Android Apps

The repository stores our implementations for the approach **SEMTROID**  proposed in paper "Semantic-enhanced Test Repair for Android Apps: A Hybrid
Approach to Combining On-screen and Cross-screen Semantics".

## Requirement

- Java 8, Python 3, Android SDK 9(API Level 28) as well as necessary packages are installed.
- Install [Jep](https://github.com/ninia/jep/).
- Use compiler [Ajc](https://github.com/eclipse/org.aspectj/releases).
- Install [Appium](https://github.com/appium/appium).
- Use Android physical device or emulator(e.g., [Genymotion](https://www.genymotion.com/))

## Benchmark Applications

The following are download links for the _Swo_ Apps used in this paper:

- [Days Matter](https://days-matter.en.uptodown.com/android)
- [FotMob](https://soccer-scores.en.uptodown.com/android)
- [Google News](https://google-play-newsstand.en.uptodown.com/android)
- [Google Translate](https://traductor-de-google.en.uptodown.com/android)
- [Lark Player](https://lark-player-video-and-music-player.en.uptodown.com/android)
- [Reddit](https://reddit-official-app.en.uptodown.com/android/versions)
- [Uber Eats](https://ubereats.en.uptodown.com/android)
- [YouTube](https://youtube.en.uptodown.com/android)

If the above links are no longer valid, you can visit [Uptodown](https://www.uptodown.com/) or [APKPure](https://apkpure.com/) to download these apps.

The following are the source code URLs of the _Sw_ Apps

- [AnkiDroid](https://github.com/ankidroid/Anki-Android)
- [ConnectBot](https://github.com/connectbot/connectbot)
- [GnuCash](https://github.com/codinguser/gnucash-android)
- [Network-monitor](https://github.com/caarmen/network-monitor)
- [SoundBoard](https://github.com/meonwax/soundboard)
- [WiFiAnalyzer](https://github.com/VREMSoftwareDevelopment/WifiAnalyzer)

## Test cases

- The test cases for the base versions of applications are in the folder `resources`(including the 6 Apps used in the extra experiment).
- The test cases for the updated versions of applications are in the folder `correctTestCases`.

## How to run

1. Create the root directory, and `git clone https://github.com/SemTroid/SEMTROID.git` to the directory.
2. Run the Appium server.
3. Install and run the base version of the application under test on the Android device, use class `runner.TraceRunner` to run and record the execution trace of a test script on the base version. The results are saved in folder `output.AppInfo`.
4. Install and run the updated version of the application under test on the Android device，Use class `runner.RepairRunner` to repair a test script on the updated version. The results are saved in folder `output.RepairedTC`.

## About experiments

1. The specific implementation of the three cross-screen context semantic extraction methods involved in RQ1 is in the `UtilsRepair` class.
2. The values of parameters involved in RQ3 can be modified in the `config.Threshold` class.

## About semantic model

The semantic model fine-tuning code `train.py`, the dataset folder `data` and label file `label.csv` used for semantic model fine-tuning are in the directory `resource.resnet50`.



 