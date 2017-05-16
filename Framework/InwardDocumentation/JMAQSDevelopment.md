## Overview of Structure
1. Framework is the root
[Structure]
A Parent level POM (maven reference) holds the structure information.
 Modules represent the JMAQS functional code areas for testing needs
 jmaqs-utilities module
 jmaqs-baseTest modules
 jmaqs-selenium module
 jmaqs-webservices module

2. Branches
[PR's vs Dev vs Master](link here)

## PR
0. Create a branch for your work from within your PBI or Task item by clicking Create Branch link  
1. Commit your branch (push)
2. The creating developer is responsible for their own PR which is able to be assigned to a reviewer
3. Code Review a single reviewer for your branch is expected  

## Dev
1. Long lived branch that all code is merged to after PR complete and approved.
2. Delete your branch after completing code merger to dev branch
3. Remains as the most current branch at all times

## Master
1. Master is last in the line to receive the updated dev branch code
2. Master is used for releases


## Development
1. Find your work in VSTS  
[Find your work](BranchesLink.png)
2. In the work item (PBI) click the create a new branch link  
[Create branch link](JMAQS_CreateBranch.png)  
3. Create the branch  
	Make sure you are branch starts with feature/  
	Make sure you are branching from JMAQS develop
	Make sure your work item is linked  
[Create branch](JMAQS_CreateFeatureBranch.png)
4. Checkout your branch 
 Two easy methods for checkout 
 a) git checkout -b newbranch origin/yournewbranch 
 b) Visual Studio checkout [Checkout link](CheckoutBranchLink.png)
5. Go team explored home  
 [Go home](GoHome.png)  
6. Go to Visual Studio and sync  
[Sync link](SyncLink.png)  
[Sync](SyncPull.png)
7. Find your branch  
[Branches link](BranchesLink.png)  

8. Note - Visual Studio is now using your new branch  

9. Go team explored home  
[Go home](GoHome.png)
10. Open the solution you want to work on
11. Commit your changes or add files
[Commit link](CommitLink.png)    
[Committing changes](.png)  
 * Commit All only commits to your local version of the repository
 * Commit All and Push commits to your local version of the repository and the centralized repository
 * Commit All and Sync commits to your local version of the repository and the centralized repository.  
  It then pulls changes down from the centralized repository  
12. Create a pull request  
[Create pull request](PullRequest.png)
13. Optional - Share pull request for a reviewer     
[Email reviewers](SharePullRequest.png)    
Approve and Complete review
[Approve](Comment_and_Approve_PullRequest.png)
14. Complete the merge
[merge](CompletePullRequest.png) 

 

## Code Review
1. Find reviews assigned to you  
[Find review](CodeReviewEmail.png)   
2. Open the code review by clicking View Pull Request in Email   
3. Pull down the feature branch code locally  
[Get review code](GetCodeReviewCode.png)   
4. Review code changes  
[Review and Comment](CompletePullRequest.png)   
5. Add review comments and questions  
[Review feedback](CompleteMergeOfPullRequest.png)  
5. Finish the review  
[Finish review](Completed-Green.png)   

#Testing
### CI
Each time we try to  merge into development a build is automatically kicked off.
If the solution fails to build or any of the tests fail we block the merge.
### Release
Before each release we manually run the [MAQS test suite](https://magenic.visualstudio.com/MaqsFramework/_testManagement?planId=2159&suiteId=2160&_a=tests)

#Builds  
///make sure these URL's are active with Git///
1. [JMAQS Pull Request Build](https://magenic.visualstudio.com/MaqsFramework/JavaMAQS/_build/index?context=allDefinitions&path=%5C&definitionId=64&_a=completed) build
 * Run with every pull request - failure will prevent code from being checked in
2. [Nuget And Extension](https://magenic.visualstudio.com/MaqsFramework/_build/index?context=Mine&path=%5C&definitionId=54&_a=completed) build  
 * Run on demand - Creates  a build version specific package and extension for internal testing purposes (Full version)
3. [Open Nuget And Extension](https://magenic.visualstudio.com/MaqsFramework/_build/index?context=Mine&path=%5C&definitionId=55&_a=completed) build  
 * Run on demand - Creates a build version specific package and extension for internal testing purposes (Open source version)
4. [Nuget And Extension - Internal Release](https://magenic.visualstudio.com/MaqsFramework/_build/index?context=Mine&path=%5C&definitionId=56&_a=completed) build  
 * Run on demand - Creates a release build version (Open source and full version)
