@echo off
color 0f
echo ÖØÆôWLANÍø¿¨ ........
echo ½ûÓÃWLANÍø¿¨
netsh interface set interface "WLAN" disabled
echo ÆôÓÃWLANÍø¿¨
netsh interface set interface "WLAN" enabled
choice /t 5 /d y /n >nul