#!/bin/bash
#两数之和

function getSum() {
    SUM=$[$n1+$n2]
    echo "result = $SUM"
}

#输入数值
read -p "请输入第一个数值: " n1
read -p "请输入第二个数值: " n2

#调用function
getSum $n1 $n2