@echo off

rem EXECUTAR PELO  x64 Native Tools Command Prompt.

rem Instalar Visual Studio 2019 com opção C++ Apps para Desktop.

rem ver https://github.com/oracle/graal/issues/3114     -> sun.nio.ch.Iocp

rem mvn -Pnative clean package

set "WD=cd"
SET ARTIFACT=demo
SET MAINCLASS=com.jardelnovaes.demo.DemoApplication
SET VERSION=0.0.1-SNAPSHOT

 goto AQUI

rem Remove compilações anteriores
rmdir target /s /q
mkdir target
mkdir target\native-image

rem Empacota a aplicação
echo "Packaging %ARTIFACT% with Maven"
call mvn -ntp package

rem Expande o JAR criado
SET JAR="%ARTIFACT%-%VERSION%.jar"
del %ARTIFACT% /s /q

echo "Unpacking %JAR%"
cd target/native-image
jar -xvf ../%JAR% >NUL
rem cp -R META-INF BOOT-INF/classes
xcopy META-INF BOOT-INF/classes /s /e

:AQUI
 cd target/native-image

rem Registra classpath, incluindo o que foi gerado pelas dependências que adicionamos no pom.xml
setLocal Enabledelayedexpansion
SET LIBPATH=
for %%a in (BOOT-INF\lib\*.jar) do SET LIBPATH=!LIBPATH!;%%a
rem !LIBPATH!;%%~fa
setlocal disabledelayedexpansion
rem echo ABC %LIBPATH%


rem SET LIBPATH=`find BOOT-INF/lib | tr '\n' ':'`
SET CP=BOOT-INF\classes;%LIBPATH%

rem Compilação para uma imagem nativa
for /f "tokens=1-3" %%i in ('native-image --version') do SET "GRAALVM_VERSION=%%i %%j %%k"

echo "Compiling %ARTIFACT% with %GRAALVM_VERSION%"
echo on
rem { time native-image \
native-image^
 --verbose^
 -H:EnableURLProtocols=http^
 -H:+ReportExceptionStackTraces^
 -H:Name=%ARTIFACT%^
 -Dspring.native.remove-yaml-support=true^
 -Dspring.native.remove-xml-support=true^
 -Dspring.native.remove-spel-support=true^
 -Dspring.native.remove-jmx-support=true^
 -cp %CP% %MAINCLASS%

rem -cp $CP $MAINCLASS }

rem  -H:+JNI^
rem --initialize-at-run-time=sun.nio.ch.Iocp^
rem --initialize-at-run-time=org.springframework.boot.validation.MessageInterpolatorFactory
rem java.sql.DriverManager
rem  --initialize-at-build-time^
rem  --enable-all-security-services^
rem  -H:-UseServiceLoaderFeature^

rem  problem adding access for key org.springframework.boot.autoconfigure.jdbc.HikariDriverConfigurationFailureAnalyzer
cd %WD%

rem TESTAR
rem -H:IncludeResourceBundles=org.hsqldb.resources.sql-state-messages^
rem -J-Dsun.nio.ch.maxUpdateArraySize=100 
rem -J-DCoordinatorEnvironmentBean.transactionStatusManagerEnable=false 
rem -J-Duser.language=pt 
rem -J-Dfile.encoding=UTF-8 
rem -H:InitialCollectionPolicy=com.oracle.svm.core.genscavenge.CollectionPolicy\$BySpaceAndTime 
rem -H:+JNI 
rem -H:-UseServiceLoaderFeature 
rem -H:-AddAllCharsets 
rem -H:FallbackThreshold=0 
