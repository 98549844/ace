// 读取当前用户资料(留意loginController Set入Seesion是用哪个Bean)
POPOPO popopo=(POPOPO)request.getSession().getAttribute(GlobalConstant.USER_LOGIN_SESSION);

//前台 <a href="${basePath}introduction/${po.id}.html">xxx</a>
//后台
@RequestMapping("/introduction/{pid}.html") public ModelAndView introduction(@PathVariable("pid") Integer pid){
        //方法体
        }

        数据转换 object转换成string,再转换成Integer Integer(obj[0].toString());
        Integer.parseInt(String.valueOf(value));

        string转换成Integer Integer.parseInt(string)