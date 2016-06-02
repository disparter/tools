:: Name:     Bulky Renamer
:: Purpose:  Rename files that match the desirable pattern
:: Author:   felipe.faria
:: Revision: 0.1

@ECHO OFF
SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION

	chcp 1252

	SET "match=%~1"
	SET "replace=%~2" 
	SET fileType=%3	
	
	FOR %%f IN (*.%fileType%) do (
	
	   
		SET fileName=%%f
		SET newFileName=!fileName:%match%=%replace%!
		REN "!filename!" "!newFileName!" 
	)
	
:END
ENDLOCAL
ECHO ON
@EXIT /B 0