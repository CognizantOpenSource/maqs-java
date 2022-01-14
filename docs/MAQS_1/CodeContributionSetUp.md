# <img src="resources/MAQS.jpg" height="32" width="32"> MAQS Contribution Set Up Guide

---

[Github Desktop](#Github Desktop)  
[VS Code](#VS Code)  
[IntelliJ](#IntelliJ)

## Cloning the MAQS Repository

---

## Github Desktop
For these steps, Git Bash is required.  
Download Git Bash here: [https://git-scm.com/download](https://git-scm.com/download)

1. Navigate to [https://github.com/Magenic/JMAQS](https://github.com/Magenic/JMAQS)
2. Click the **Clone or download button**
3. Copy the URL - [https://github.com/Magenic/JMAQS.git](https://github.com/Magenic/JMAQS.git)
   ![alt text](../resources/installationImages/MagenicJMAQS.png)
4. Open Git Bash and navigate to where you want the repo to be
5. Right click in Git Bash, paste the URL copied from GitHub, and hit enter
   ![alt text](../resources/installationImages/NavigateToFile.png)
6. You should now have the MAQS project in your file explorer
   ![alt text](../resources/installationImages/JMAQS_InFileExplorer.png)

---

## VS Code

### Setting up the MAQS Java Repository
#### If you have cloned via Git Bash
1. Open VS Code
2. Click Open Folder
![alt text](../resources/installationImages/vsCode/OpenFolder.png)


3. Navigate to the MAQS folder
![alt text](../resources/installationImages/vsCode/NavigateToJMAQS.png)


4. Click **Select Folder**
5. The MAQS project should appear in the Explorer section
![alt text](../resources/installationImages/vsCode/ExplorerSection.png)

#### if the repo has not been cloned
1. Open VS Code
2. Click the "Clone Git Repository" button
![alt text](../resources/installationImages/vsCode/OpenGitRepo.png)


3. Click **Clone from GitHub** in the dropdown
![alt text](../resources/installationImages/vsCode/CloneFromGitHub.png)


4. Login to your git-hub account  
(if you don't have an account, use the Git Bash instructions)
5. Type in: CognizantOpenSource/maqs-java and select it in the dropdown
![alt text](../resources/installationImages/vsCode/GitHubJMAQSLink.png)


6. Select the location you want the repo to be
7. Select the **Select Repository Location** button
![alt text](../resources/installationImages/vsCode/GitHubInstallationLocation.png)

   
8. You should now be able to open a new window to the MAQS project
 ![alt text](../resources/installationImages/vsCode/ExplorerSection.png)

### Setting up the WebDriver Manager

---

## IntelliJ
1. Open IntelliJ
   Note:  You can download IntelliJ Idea from: [https://www.jetbrains.com/idea/download/#section=windows](https://www.jetbrains.com/idea/download/#section=windows)
2. Click **Check out from Version Control**
3. Select **Git**  
   ![alt text](../resources/installationImages/intellij/IntelliJ_Idea.png)


4. Paste in the MAQS Git URL
5. Select a directory for the project
6. Click **Clone**  
   ![alt text](../resources/installationImages/intellij/clone01.png)


7. Click  **No**  
   ![alt text](../resources/installationImages/intellij/clone02.png)

---  
## II. Settings and Configurations – MAQS Framework

1. Click **Import Project**  
   ![alt text](../resources/installationImages/intellij/IntelliJ_Idea_Import.png)


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


11. Verify **com.magenic.jmaqs:jmaqs-framework** is selected
12. Click **Next**  
    ![alt text](../resources/installationImages/intellij/jmaqsframework.png)


13. Select the project SDK (JDK 11 or higher is required)
14. Click **Next**  
    ![alt text](../resources/installationImages/intellij/projectSDK.png)


15. Verify the project name and file location
16. Click **Finish**  
    ![alt text](../resources/installationImages/intellij/projectLocation.png)


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
     ![alt text](../resources/installationImages/intellij/MAQSformatter.png)


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
15.  Set the **Checkstyle version to 7.6.1**
16.  Set the **Scan Scope** to **All sources (including tests)**
17.  Under Configuration File, click the **+** (add) button  
     ![alt text](../resources/installationImages/intellij/AddCheckStyle.png)


18.  Click **Browse** under **Use a local Checkstyle file**
19.  Navigate to the project directory, expand the Framework folder, select **maqs_checks.xml**
20.  Click **OK**  
     ![alt text](../resources/installationImages/intellij/JMAQS_Checkstyle.png)


21.  Enter a Description
22.  Check **Store relative to project location**
23.  Click **Next**  
     ![alt text](../resources/installationImages/intellij/MAQScheckstyleDescription.png)


24.  Click **Finish**  
     ![alt text](../resources/installationImages/intellij/Finish.png)


25. Check the **Active** box next to the added Configuration File
26. Click **Apply**
27. Click **OK**  
     ![alt text](../resources/installationImages/intellij/TheEnd.png) 

#### Setting up the Web Driver manager
1. Open a file explorer and find your .m2 file
   ![alt text](../resources/installationImages/intellij/m2FileExplorer.png)
2. Copy the file path
3. In IntelliJ Click Test/Build configurations drop down
4. Click **Edit Configurations**
5. In the test config, click **Edit configuration templates...**
6. Click the **Test NG** template
7. In VM options enter **-javaagent:""**
8. Next, paste in the copied file path
9. After that, paste in this path: 
**repository/io/github/bonigarcia/webdrivermanager/5.0.3/webdrivermanager-5.0.3.jar**
10. Your VM Options should look something like this:  
-ea -javaagent:"/Users/MY_NAME/.m2/repository/io/github/bonigarcia/webdrivermanager/5.0.3/webdrivermanager-5.0.3.jar"
![alt text](../resources/installationImages/intellij/VM_Options.png)


11. In the Configuration template menu:  
Click **Apply** and **OK**
12. In the Run/Debug configuration menu:  
Click **Apply** and **OK**
![alt text](../resources/installationImages/intellij/OKandApply.png)