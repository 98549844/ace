`<scope>test</scope>`\
实际项目打包的时候没有这个jar包

`<scope>compile</scope>`\
(默认)实际项目打包的时候有这个jar包

`<scope>provided</scope>`\
已提供,告诉maven这个jar包未来在我的服务器中有,
不需要它打包放在项目中,
实际项目打包的时候,lib文件夹里面maven导入的provided包没有

`<scope>runtime</scope>`\
表示被依赖项目无需参与项目的编译,
不过参与后期的测试,打包,运行.
与compile相比,跳过了编译.例如JDBC驱动,适用运行和测试阶段

`<scope>system</scope>`\
结合<systemPath>指定jar路径
类似provided (不会打包到项目中)

import:\
它只使用在<dependencyManagement>中,
表示从其它的pom文件导入dependency的配置


![001.png](img%2F001.png)


optional是maven依赖jar时的一个选项,表示该依赖是可选的,不会被依赖传递
为什么要使用optional
减少不必要的依赖传递
减少jar包冲突

使用场景
A项目的pom中依赖了system-local-api\
`<dependency`>\
&emsp;&emsp;`<groupId>com.sinosoft</groupId`>\
&emsp;&emsp;`<artifactId>system-local-api</artifactId`>\
&emsp;&emsp;`<version>1.0.0</version`>\
`</dependency`>\

B项目依赖了A项目
因为maven有依赖传递机制,那么B项目就会有system-local-api的jar包,实际上在B项目中不一定要使用system-local-api,那么我们项目中就会有多余的依赖,
当这种情况时越来越多时,最后整个项目的jar包就有很多的多余依赖,导致项目很臃肿.此时可以设置optional为true,就不会传递给B项目.\



`<dependency`>\
&emsp;&emsp;`<groupId>com.sinosoft</groupId`>\
&emsp;&emsp;`<artifactId>system-local-api</artifactId`>\
&emsp;&emsp;`<version>1.0.0</version`>\
&emsp;&emsp;`<optional>true</optional`>\
`</dependency`>\

总结
当你开发的组件时,有一个功能,有多种实现方式的jar可以提供,
但是实际只会使用一种的情况下.可以把实现的jar包设置成：true.
即依赖时可以自行选择需要什么功能,只提供项目需要的jar,而不是默认全部都给你.