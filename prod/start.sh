rm -rf /opt/docker/application*
rm -rf /opt/docker/{{ name }}-1.0.0-RELEASE.jar
cd /{{ name }}
mv application* /opt/docker/
mv {{ name }}-1.0.0-RELEASE.jar /opt/docker
sleep 10
time=`date +%F-%H-%M-%S`
cd /opt/docker/
docker build -t {{ name }}-prod:$time ./
docker rm -f `docker ps -a -q`
docker run -d -p 8080:8080 {{ name }}:$time