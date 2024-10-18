@echo off

REM Create directory for reqy
mkdir "C:\Program Files\reqy"

REM Download the JAR file
powershell -Command "Invoke-WebRequest -Uri https://github.com/yourusername/reqy/releases/download/v1.0/reqy.jar -OutFile 'C:\Program Files\reqy\reqy.jar'"

REM Create the batch file wrapper
echo @echo off > "C:\Program Files\reqy\reqy.bat"
echo java -jar "C:\Program Files\reqy\reqy.jar" %%* >> "C:\Program Files\reqy\reqy.bat"

REM Add the wrapper directory to PATH
setx PATH "%PATH%;C:\Program Files\reqy"

echo Installation complete. Please restart your command prompt or computer to start using `reqy`.

