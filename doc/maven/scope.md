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