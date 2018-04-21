# Arkavquarium in Java

## Dependencies
This project use Maven, [here](https://www.vultr.com/docs/how-to-install-apache-maven-on-ubuntu-16-04) is detailed instruction

```bash
# asuming java has been already installed
cd /opt/
sudo wget http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
sudo tar -xvzf apache-maven-3.3.9-bin.tar.gz
sudo mv apache-maven-3.3.9 maven
sudo nano /etc/profile.d/mavenenv.sh
```
Add these two lines:
```
export M2_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
```

```
sudo chmod +x /etc/profile.d/mavenenv.sh
sudo source /etc/profile.d/mavenenv.sh

# verify your installation
mvn --version
```

## Usage

```bash
# compile
mvn compile
# run test
mvn test
# run class
java target/classes/com/arkavquarium/Arkavquarium
```

## Credits

- 13516030 Yonas Adiel Wiguna
- 13516084 Maulana Akmal
- 13516057 Dafi Ihsandiya Faraz
- 13516105 Ricky Kennedy