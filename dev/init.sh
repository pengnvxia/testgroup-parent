service sshd start
cd /opt
java -Xmx1344m -Xms1344m -Xmn448m -Xss256k -jar ./testgroup-0.0.1-SNAPSHOT.jar &
tail -f /etc/hosts
