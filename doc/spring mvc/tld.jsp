图片:<s:imgUrl alias=""></s:imgUrl>

前台jsp

<s:imgUrl alias=""></s:imgUrl>
<img src="<s:imgUrl alias="${popopo[0].alias}" w="80" h="80"/>" class="">

将alias,w,h提交到tld指向的class:ImageUrlTag

tld
<tag>
    <name>imgUrl</name>
    <tag-class>common.tag.ImageUrlTag</tag-class>
    <body-content>JSP</body-content>
    <description>生成图片读取URL，alias：KEY; w:宽(Integer); w:高(Integer);var 将数据保存至request中</description>
    <attribute>
        <name>alias</name>
        <required>true</required>
        <rtexprvalue>true</rtexprvalue>
    </attribute>
    <attribute>
        <name>w</name>
        <required>false</required>
        <rtexprvalue>true</rtexprvalue>
    </attribute>
    <attribute>
        <name>h</name>
        <required>false</required>
        <rtexprvalue>true</rtexprvalue>
    </attribute>
    <attribute>
        <name>var</name>
        <required>false</required>
        <rtexprvalue>true</rtexprvalue>
    </attribute>
</tag>

设定文字长度显示:<s:display value=""></s:display>
前台jsp
<s:display length="19" value="${BEANBEANBEAN.name}"/>

tld
<tag>
    <name>display</name>
    <tag-class>hk.com.synergis.common.tag.StringDisplayTag</tag-class>
    <body-content>JSP</body-content>
    <description>数据库查找 type 参数类型, value参数值</description>
    <attribute>
        <name>length</name>
        <required>false</required>
        <rtexprvalue>true</rtexprvalue>
    </attribute>
    <attribute>
        <name>value</name>
        <required>true</required>
        <rtexprvalue>true</rtexprvalue>
    </attribute>
</tag>