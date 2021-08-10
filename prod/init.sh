service sshd start
cd /opt
java -javaagent:/pinpoint-agent/pinpoint-bootstrap-1.8.5.jar -Dpinpoint.agentId="$SERVER_HOST" -Dpinpoint.applicationName=testgroup -Xmx3072m -Xms3072m -Xmn2048m -jar /opt/testgroup-1.0.0.RELEASE.jar &
tail -f /etc/hosts
