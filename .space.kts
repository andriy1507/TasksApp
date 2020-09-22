/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, refer to https://www.jetbrains.com/help/space/automation.html
*/

job("Build Android Debug") {
    gradlew("openjdk:11","assembleDebug")
}
