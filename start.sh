#! /bin/bash
CWD=$(pwd)

check_result() {
  if [ $1 -eq 0 ]
  then
    echo "$2 OK"
  else
    echo "$2 FAILED"
	killall java &> /dev/null
	exit $1
  fi
}

rm -f *.log
SECONDS=0;
echo "STARTING ALL...";

./mvnw clean install -Dmaven.test.skip=true &> $CWD/build.log
check_result $? "Backend builds"

cd react-client && {
  yarn install &> $CWD/react.log
  yarn build &>> $CWD/react.log
  cd ..
}
check_result $? "React builds"

cd angular-client && {
  npm install &> $CWD/angular.log
  node_modules/@angular/cli/bin/ng build --prod &>> $CWD/angular.log
  cd ..
}
check_result $? "Angular builds"

echo "Starting docker...";
docker-compose up -d --build --pull always &> $CWD/docker.log &

echo "FINISHED in ${SECONDS}sec";
