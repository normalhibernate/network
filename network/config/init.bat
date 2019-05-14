@echo off
set "exe=NetworkCheck.exe"
set "lnk=NetworkCheck.exe"
mshta VBScript:Execute("Set a=CreateObject(""WScript.Shell""):Set b=a.CreateShortcut(""%lnk%.lnk""):b.TargetPath=""%~dp0%exe%"":b.WorkingDirectory=""%~dp0"":b.Save:close")
copy ""%lnk%.lnk"" "C:\Users\Administrator\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup\"
del ""%lnk%.lnk""
echo finish&pause