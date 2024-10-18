#!/bin/bash

# Create directory for JAR file
sudo mkdir -p /usr/local/share/reqy

# Download the JAR file
wget https://github.com/xerctia/reqy/releases/download/v1.0/reqy.jar -O /usr/local/share/reqy/reqy.jar

# Create and download the `reqy` wrapper script
echo '#!/bin/bash' | sudo tee /usr/local/bin/reqy > /dev/null
echo 'java -jar /usr/local/share/reqy/reqy.jar "$@"' | sudo tee -a /usr/local/bin/reqy > /dev/null

# Make the wrapper executable
sudo chmod +x /usr/local/bin/reqy
