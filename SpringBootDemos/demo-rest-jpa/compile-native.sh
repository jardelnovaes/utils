#!/usr/bin/env bash

ARTIFACT=demo-spring-boot
MAINCLASS=com.jardelnovaes.demo.DemoApplication
VERSION=0.0.1-SNAPSHOT

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

# Remove compilações anteriores
rm -rf target
mkdir -p target/native-image

# Empacota a aplicação
echo "Packaging $ARTIFACT with Maven"
mvn -ntp package > target/native-image/output.txt

# Expande o JAR criado
JAR="$ARTIFACT-$VERSION.jar"
rm -f $ARTIFACT
echo "Unpacking $JAR"
cd target/native-image
jar -xvf ../$JAR >/dev/null 2>&1
cp -R META-INF BOOT-INF/classes

# Registra classpath, incluindo o que foi gerado pelas dependências que adicionamos no pom.xml
LIBPATH=`find BOOT-INF/lib | tr '\n' ':'`
CP=BOOT-INF/classes:$LIBPATH

# Compilação para uma imagem nativa
GRAALVM_VERSION=`native-image --version`
echo "Compiling $ARTIFACT with $GRAALVM_VERSION"

{ time native-image \
  --verbose \
  -H:EnableURLProtocols=http,https \
  --enable-all-security-services \
  -H:-UseServiceLoaderFeature \
  -H:+ReportExceptionStackTraces \
  -H:Name=$ARTIFACT \
  -Dspring.native.remove-yaml-support=true \
  -Dspring.native.remove-xml-support=true \
  -Dspring.native.remove-spel-support=true \
  -Dspring.native.remove-jmx-support=true \
  -cp $CP $MAINCLASS >> output.txt ; } 2>> output.txt

# Verifica se foi sucesso ou falha e nos mostra a mensagem. ;-)
if [[ -f $ARTIFACT ]]
then
  printf "${GREEN}SUCCESS${NC}\n"
  mv ./$ARTIFACT ..
  exit 0
else
  cat output.txt
  printf "${RED}FAILURE${NC}: an error occurred when compiling the native-image.\n"
  exit 1
fi