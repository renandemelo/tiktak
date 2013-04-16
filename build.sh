cd tiktak-api
mvn clean install -DskipTests eclipse:clean eclipse:eclipse

cd ..
cd simulacaoCliente
mvn clean install -DskipTests eclipse:clean eclipse:eclipse

cd ..
cd tiktak-dashboard
mvn clean install -DskipTests eclipse:clean eclipse:eclipse
