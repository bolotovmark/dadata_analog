1) export JAVA_HOME=`/usr/libexec/java_home -v 21`
2) mvn clean compile jib:dockerBuild
3) docker-compose up