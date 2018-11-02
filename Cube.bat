echo off
@echo=off
prompt $S
:s
cls
set /p sz="Cube Size(n) for nxnxn"
set /p sp="Ease"
java Cube %sz% %sp%
goto s