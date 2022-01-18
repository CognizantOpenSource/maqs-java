# <img src="resources/MAQS.jpg" height="32" width="32"> MAQS Contribution Set Up Guide

---

[Github Desktop](#Github Desktop)  
[VS Code](#VS Code)  
[IntelliJ](#IntelliJ)

## Cloning the MAQS Repository

---

## Github Desktop
Before going to your IDE, downloading Git Desktop is recommending
Download Git Desktop here: [https://desktop.github.com/](https://desktop.github.com/)

1. Navigate to [https://github.com/CognizantOpenSource/maqs-java](https://github.com/CognizantOpenSource/maqs-java)
2. Click the **Clone** button
3. Click the **Open with GitHub Desktop** button
   ![alt text](../resources/installationImages/githubDesktop/CopyMAQS.png)
 

4. Select the **Choose...** button to dictate where you want the repo to be (or leave it to the default path)
5. Then click the **Clone** button
   ![alt text](../resources/installationImages/githubDesktop/GithubDesktopFileSet.png)


7. Wait for the cloning download to complete
8. You should now see the **maqs-java** project under the current repository drop down
   ![alt text](../resources/installationImages/githubDesktop/FrameworkInGithubDesktop.png)

---

## VS Code

### Setting up the MAQS Java Repository
#### If you have cloned via GitHub Desktop
1. Open VS Code
2. Click Open Folder
![alt text](../resources/installationImages/vsCode/OpenFolder.png)


3. Navigate to the MAQS folder
4. Click **Select Folder**
![alt text](../resources/installationImages/vsCode/NavigateToMAQS.png)


6. The MAQS project should appear in the Explorer section
![alt text](../resources/installationImages/vsCode/ExplorerSection.png)

#### if the repo has not been cloned
1. Open VS Code
2. Click the "Clone Git Repository" button
![alt text](../resources/installationImages/vsCode/OpenGitRepo.png)


3. Click **Clone from GitHub** in the dropdown
![alt text](../resources/installationImages/vsCode/CloneFromGitHub.png)


4. Login to your git-hub account  
(if you don't have an account, use the Git Bash instructions)
5. Type in: **CognizantOpenSource/maqs-java** and select it in the dropdown
![alt text](../resources/installationImages/vsCode/GitHubMAQSLink.png)


6. Select the location you want the repo to be
7. Select the **Select Repository Location** button
![alt text](../resources/installationImages/vsCode/GitHubInstallationLocation.png)

   
8. You should now be able to open a new window to the MAQS project
 ![alt text](../resources/installationImages/vsCode/ExplorerSection.png)

---

## IntelliJ
Before setting up the project, make sure you have IntelliJ installed  
Note:  You can download IntelliJ Idea from: [https://www.jetbrains.com/idea/download/#section=windows](https://www.jetbrains.com/idea/download/#section=windows)

### If you have cloned via GitHub Desktop
1. Click the **Open** button
![alt text](../resources/installationImages/intellij/OpenProject.png)


2. Navigate to the **maqs-java** file and select it
3. Click the **OK** button
![alt text](../resources/installationImages/intellij/MAQS_Location.png)


4. The project should show up in the project window (on the left side)
   ![alt text](../resources/installationImages/intellij/MAQS_Framework.png)

### if the repo has not been cloned
1. Click the **Get From VCS** button



4. The project should show up in the project window (on the left side)
   ![alt text](../resources/installationImages/intellij/MAQS_Framework.png)

----

2. Paste in the MAQS Git URL
3. Select a directory for the project
4. Click **Clone**  
   ![alt text](../resources/installationImages/intellij/clone01.png)

7. Click  **No**  
   ![alt text](../resources/installationImages/intellij/clone02.png)

---  
## II. Settings and Configurations – MAQS Framework

1. Click **Import Project**  
   ![alt text](../resources/installationImages/intellij/IntelliJ_ImportProject.png)


2. Navigate to the project directory and select the **Framework** folder
3. Click **OK**  
   ![alt text](../resources/installationImages/intellij/FileDirImport.png)


4. Select **Import project from external model**
5. Select **Maven**
6. Click **Next**  
   ![alt text](../resources/installationImages/intellij/ImportProject.png)


7. Check **Search for projects recursively**
8. Check **Import Maven projects automatically**
9. Check **Create module groups for multi-module Maven projects**
10. Click **Next**  
    ![alt text](../resources/installationImages/intellij/SearchProject.png)


11. Verify **com.cognizantsoftvision.maqs** is selected
12. Click **Next**  
    ![alt text](../resources/installationImages/intellij/jmaqsframework.png)


13. Select the project SDK (JDK 11 or higher is required)
14. Click **Next**  
    ![alt text](../resources/installationImages/intellij/projectSDK.png)


15. Verify the project name and file location
16. Click **Finish**  
    ![alt text](../resources/installationImages/intellij/jmaqsProjectLocation.png)


17. When finished loading, the project structure should have Framework at the top-level:  
    ![alt text](../resources/installationImages/intellij/FrameworkTop.png)


18. Framework expanded:  
    ![alt text](../resources/installationImages/intellij/FrameworkExpanded.png)

---   
## III. Code Style Settings - MAQS Java Formatter

#### Set up CheckStyle
1.  Navigate to File > **Settings**
2.   Navigate to Editor > **Code Style**
3.   Select the gear icon next to Scheme
4.   Select Import Scheme > **Eclipse XML Profile**  
     ![alt text](../resources/installationImages/intellij/EclipseXMLprofile.png)


5.   Navigate to the project directory and select **maqs_formatter.xml**
6.   Click **OK**  
     ![alt text](../resources/installationImages/intellij/MAQS_Formatter.png)


7.   Click **OK**  
     ![alt text](../resources/installationImages/intellij/EclipseJMAQSbase.png)


8.   A success popup should be displayed
9.    Click **Apply**  
      ![alt text](../resources/installationImages/intellij/CodeStyle.png)

#### Plugins: CheckStyle-IDEA and SonarLint
1.   Navigate to (File > Settings) **Plugins**
2.   Enter **Checkstyle** into the search bar(Note:Make sure Marketplace tab is selected)
3.   Select **CheckStyle-IDEA**
4.   Click **Install**  
     ![alt text](../resources/installationImages/intellij/plugins1.png)


5.   If the Third-party Plugins Privacy Note popup is displayed, read the popup and click **Accept**  
     ![alt text](../resources/installationImages/intellij/plugins2.png)


6.   Restart IDE should be displayed next to CheckStyle-IDEA after installation  
     ![alt text](../resources/installationImages/intellij/plugins3.png)


7.   Enter **SonarLint** into the search bar
8.   Select **SonarLint**
9.   Click **Install**  
     ![alt text](../resources/installationImages/intellij/InstallSonarlint.png)


10.   **Restart IDE** should be displayed next to SonarLint after installation
11.  Click **OK** or **Restart IDE**  
     ![alt text](../resources/installationImages/intellij/RestartIDE.png)


12.  Click **Restart**  
     ![alt text](../resources/installationImages/intellij/RestartConfirm.png)


13.  When the project has reloaded, navigate to File > **Settings**
14.  Navigate to Other Settings > **Checkstyle**
15.  Set the **Checkstyle version** to the latest one
16.  Set the **Scan Scope** to **All sources (including tests)**
17.  Under Configuration File, click the **+** (add) button  
     ![alt text](../resources/installationImages/intellij/AddCheckStyle.png)


18.  Click **Browse** under **Use a local Checkstyle file**
19.  Navigate to the project directory, expand the Framework folder, select **maqs_checks.xml**
20.  Click **OK**  
     ![alt text](../resources/installationImages/intellij/MAQS_Checkstyle.png)


21.  Enter a Description
22.  Check **Store relative to project location**
23.  Click **Next**  
     ![alt text](../resources/installationImages/intellij/MAQS_CheckstyleDescription.png)


24.  Click **Finish**  
     ![alt text](../resources/installationImages/intellij/Finish.png)


25. Check the **Active** box next to the added Configuration File
26. Click **Apply**
27. Click **OK**  
     ![alt text](../resources/installationImages/intellij/TheEnd.png)