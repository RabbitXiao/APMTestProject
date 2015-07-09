@echo off
echo ===========================
for /l %%a in (0,0,0) do (
	call :DO_VISITS
	call :WAIT_5_MINS
)
pause

:DO_VISITS
	set "TOMCAT_SERVER=10.30.146.109:8988"
	::set URL="http://%TOMCAT_SERVER%/APMTestWebApp/action/sayHello?content=Rabbit"
	::call :VISIT_URL %URL%
	::set URL=http://%TOMCAT_SERVER%/APMTestWebApp/action/accessMySQL
	::call :VISIT_URL %URL%
	::Beijing, visit the fast page
	set IP="60.195.223.212"
	set URL=http://%TOMCAT_SERVER%/examples/jsp/jsp2/simpletag/hello.jsp
	call :VISIT_URL %IP% %URL%
	::Guangzhou, visit the slow page
	set IP="58.61.200.17"
	set URL=http://%TOMCAT_SERVER%/APMTestWebApp/random.jsp
	call :VISIT_URL %IP% %URL%
GOTO:EOF

:VISIT_URL
	echo ------------------Visit URL: "X-Forwarded-For: %1" %2
	curl --header "X-Forwarded-For: %1" %2
GOTO:EOF

:WAIT_5_MINS
	echo ------------------Current time:(%date% %time%), wait 5 minutes...
	::Ping an ip, and make it wait for a fixed time(specified by "-w")
	ping 123.123.123.123 -n 1 -w 300000 > nul
GOTO:EOF
