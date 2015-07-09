@echo off
echo ===========================
for /l %%a in (0,0,0) do (
	call :DO_VISITS
	::call :DO_VISITS2
	call :WAIT_5_MINS
)
pause

:DO_VISITS
	set "TOMCAT_SERVER=10.30.146.109:8988"

	set URL=http://%TOMCAT_SERVER%/APMTestWebApp/action/accessMySQL
	call :VISIT_URL %URL%
	::set URL=http://%TOMCAT_SERVER%/APMTestWebApp/random.jsp
	::call :VISIT_URL %URL%
	::set URL="http://%TOMCAT_SERVER%/APMTestWebApp/action/sayHello?content=Rabbit"
	::call :VISIT_URL %URL%
	::set URL=http://%TOMCAT_SERVER%/examples/jsp/jsp2/simpletag/hello.jsp
	::call :VISIT_URL %URL%
GOTO:EOF

:DO_VISITS2
	set "TOMCAT_SERVER=10.30.146.33:8688"
	set URL=http://%TOMCAT_SERVER%/examples/jsp/jsp2/simpletag/hello.jsp
	call :VISIT_URL %URL%
	set URL=http://%TOMCAT_SERVER%/examples/random.jsp
	call :VISIT_URL %URL%
GOTO:EOF

:VISIT_URL
	echo ------------------Visit URL:%1
	curl %1
GOTO:EOF

:WAIT_5_MINS
	echo ------------------Current time:(%date% %time%), wait 5 minutes...
	::Ping an ip, and make it wait for a fixed time(specified by "-w")
	ping 123.123.123.123 -n 1 -w 300000 > nul
GOTO:EOF
