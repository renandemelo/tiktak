#!/bin/sh
CURRENT_DIR=`screen -ls`
echo "$CURRENT_DIR" | grep "No Sockets"  1>/dev/null 
if [ `echo $?` -neq 0 ]
then
echo $2 present in $1

fi
