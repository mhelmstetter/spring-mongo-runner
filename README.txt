Building
===============================================================================
Run:
mvn clean package


Running
===============================================================================
cd bin
java -jar SpringMongoRunner.jar -c mongodb://localhost:27017


Help
===============================================================================
java -jar SpringMongoRunner.jar --help

**** NOTE **** not all options implemented, currently insert only