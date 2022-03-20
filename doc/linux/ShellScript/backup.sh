#!/bin/bash

#备份数据库
#压缩备份
#删除之前备份
BACKUP=/Users/garlam/backup
#get current datetime
DATETIME=$(date +%Y-%m-%d_%H_%M_%S)
echo $DATETIME

#database
HOST=localhost
USER=root
PASSWD=garlamau
DATABASE=ace

#create backup dir, if not exist, create it
#[ ! -d "$BACKUP/$DATETIME" ] && mkdir -p "$BACKUP/$DATETIME"
[ ! -d "${BACKUP}/backup_${DATETIME}" ] && mkdir -p "${BACKUP}/backup_${DATETIME}"

#backup mysql
#mysqldump -u${USER} -p${PASSWD} --host=${HOST} -q -R --databases ${DATABASE} | gzip >> ${BACKUP}/backup_${DATETIME}/$DATETIME.sql.gz


#zip to tar gzip
cd ${BACKUP}/backup_${DATETIME}
touch a1.txt
touch a2.txt
touch a3.txt
#单独压缩
gzip -r ${BACKUP}/backup_${DATETIME}/
tar -zcvf $DATETIME.tar.gz ${BACKUP}/backup_${DATETIME}/

#delete backup dir
rm -rf ${BACKUP}/backup_${DATETIME}/*.txt.gz

#delete 10days before backup data
find ${BACKUP} -atime +10 -name "*.tar.gz" -exec rm -rf {} \;
echo "database $DATABASE backup finished"