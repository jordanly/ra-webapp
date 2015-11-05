echo "Setting up local server for RA testing..."

echo "##### Installing Java 8 JDK #####"
sudo apt-get update
sudo apt-get install -y software-properties-common
sudo apt-get install -y python-software-properties

sudo add-apt-repository ppa:webupd8team/java -y
sudo apt-get update
sudo apt-get install -y oracle-java8-installer
sudo apt-get install -y oracle-java8-set-default

echo "##### Setting up PostgreSQL database #####"
sudo apt-get -y install postgresql postgresql-contrib
sudo -u postgres psql -c "CREATE USER raservice WITH PASSWORD 'test'";
sudo -u postgres psql -c "CREATE DATABASE beers WITH OWNER = raservice;"
sudo -u postgres psql -f setup-queries.sql beers
sudo -u postgres psql beers -c "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO raservice;"
echo "##### Finish PostgreSQL setup #####"

echo "##### Compiling source code #####"
sudo apt-get -y install maven
mvn compile
mvn package

echo "########################################################################"
echo "#                                                                      #"
echo "#             Done! To start the server, use the command:              #"
echo "#                                                                      #"
echo "# java -jar /vagrant/target/ra3-1.0-SNAPSHOT-jar-with-dependencies.jar #"
echo "#                                                                      #"
echo "########################################################################"