#!/bin/bash

PROFILE=dev
PROJECT_NAME=wooper_stat_demon

CMD=$1;
ARCHIVE_NAME=${PROJECT_NAME}.jar

echo -e "# CMD = $CMD"
echo -e "# PROJECT_NAME = ${PROJECT_NAME}"
echo -e "# ARCHIVE_NAME = ${ARCHIVE_NAME}"

GET_PID()
{
    tmp=`ps -ef | grep ${ARCHIVE_NAME} | grep -v grep | awk '{print $2}'`
    PID=`echo $tmp | awk '{print $1}'`
}

if [ $CMD = "start" ]; then
      echo -e "\n[ start ] ============================================================== "
      GET_PID

      if [ "$PID" = "" ]; then

            # starting
            nohup java -Xmx1024m -jar ${ARCHIVE_NAME} --spring.profiles.active=${PROFILE} > /dev/null 2>&1 &
            GET_PID

            # 실행 오류 발생
            if [ "$PID" = "" ]; then
                echo `date` " [FAIL] $0 ${BUILD_NUMBER} : Fail Start!!!!! " >> run_history.log
                echo "${PROJECT_NAME} Fail Start!!!!!"
                exit
            fi

            # start 로그 저장
            echo `date` " [SUCCESS]" >> run_history.log
            echo "${PROJECT_NAME}($PID) Startup!"
            echo ""

      else
            echo `date` " [FAIL] " >> deploy_hist.log
            echo "$PROJECT_NAME($PID) Already Startup!"
            echo ""
      fi

elif [ $CMD = "stop" ]; then
      echo -e "\n[ stop ] =============================================================="
      GET_PID

      if [ "$PID" = "" ]; then
            echo `date` " [FAIL] " >> run_history.log
            echo "${PROJECT_NAME} is not running"; echo
      else
            kill -15 $PID

            echo `date` " [SUCCESS]  " >> run_history.log
            echo "${PROJECT_NAME}($PID) Shutdown!!"
            echo ""
      fi
fi

echo ""
echo `tail -1 run_history.log`

