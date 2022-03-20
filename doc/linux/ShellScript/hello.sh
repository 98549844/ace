#!/bin/bash
echo "hello world~"

#1.定义变量
A=100

echo 1 A=$A
echo "2 A=$A"

#2.撤消变量
unset A
echo 3 A=$A

#3.静态变量
readonly B=200
echo 4 B=$B

#4.将命令的结果返回给变量
C=$(date)
D=$(date)
echo 5 C=$C
echo 6 D=$D

echo "7 home=$HOME"

#5.位置参数变量
echo "8 $0 $1 $2 $3"
echo "9 $*"
echo "10 $@"
echo "11 所有参数 $#"

#6 预定义变量
echo "12 当前进程的pid=$$"
echo "13 最后一个进程的pid=$!"
echo "14 最后一个进程执行的返回状态=$?"

result1=$[(3 + 4) * 5]
echo "15 result1=$result1"
result2=`expr 3 + 5`
echo "16 result2=$result2"


#7 判断语句
OK=not_ok
if [ "ok" = $OK ]
then
  echo "17 equal"
fi

N86=86
if [ 99 -ge $N86 ]
then
  echo "18 大于等于$N86"
fi

if [ -f /Users/garlam/ppEnv.json ]
then
  echo "19 存在"
fi


case $1 in
"1")
  echo "一"
  ;;
"2")
  echo "二"
  ;;
"3")
  echo "三"
  ;;
"4")
  echo "四"
  ;;
*)
  echo "other"
  ;;
esac


#8 for循环
#case1
for i in "$*"
do
    echo "20 number is $i"
done
#case2
for i in "$@"
do
    echo " 21 number is $i"
done
#case3 1加到100
SUM=0
for (( i = 1; i <= 100; i++ )); do
    SUM=$[$SUM+$i]
done
echo "22 sum=$SUM"

#case4 while循环
SUM1=0
i=0
while [ $i -le 200 ]; do
        SUM1=$[SUM1+$i]
        #i自增
        i=$[$i+1]
done
echo "23 SUM1=$SUM1"














