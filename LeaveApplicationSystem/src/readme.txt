[How to run the program]
Run the main method in RunApplicationSystem.java(org.system) to lanch the Porject System Program.

[How to read the source code]

[Main package(org.system)]**************************************************
The System Main Frame(MainFrameMainSystem.java) provides two buttons.
One is "Leave Application System (Session)", which launch Leave Application System running in Session Mode. (It means data will lose if system exit)
Another is "Leave Application System (File System)", which launch Leave Application System running in Save Mode. (Data will be save to file)

[MVC Pattern]**************************************************
Model (org.system.core.model) - Model represents the Object carrying data in the System  (e.g. Staff, HRStaff, Leave)
View (org.system.core.view)  - View (JFrame and JInternalFrame) visualize the data of model contains.
Controller (org.system.core.handler) - Controller handles the data flow into model object and updates the view.

[View package]**************************************************
The Application Frame(MainFrameLeaveApplicationSystem.java)
, which contains all the Internal Functional Frames(InternalFrameXXX extends AbstractFunctionalFrame).

And each Internal Functional Frame represents its functional interface.

AbstractMainFrame.java is Template Method of MainFrameLeaveApplicationSystem.java and MainFrameMainSystem.java
AbstractFunctionalFrame.java is Template Method of all Internal Frames(InternalFrameXXX.java)
Especially, AbstractLeaveFunctionalFrame is Template Method of the Internal Frame which need to Show Leaves.


[Controller package]**************************************************
AbstractMainHandler.java is Template Method of LeaveApplicationSystemHandler.java and MainSystemHandler.java
AbstractFunctionalFrame.java is Template Method of all Function Handlers(FunXXXHandler.java)

The Main System Handler(MainSystemHandler.java) control the MainFrame(MainFrameMainSystem.java)
, which can fire the LeaveApplicationSystemHandler to show the Leave Application System Frame(MainFrameLeaveApplicationSystem.java)
, where LeaveApplicationSystemHandler contains all the Function Handlers(FunXXXHandler.java)
, and each Function Handler has the control of the corresponding Frame
, like FunXXXHandler.java is correspond to InternalFrameXXX.java

[Other Package (org.system.base.dao)]**************************************************
The System base package - which contains some supporting class to save or load from session or file
, or some Util abstract class like ViewValidator for the Template Method.



