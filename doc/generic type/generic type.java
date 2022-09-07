/**
 * 泛型返回值<T> T 与 T 的区别
 *
 * <T> T: T 受入参泛型的影响
 * T: 不受入参T的影响
 **/

/**
 * @Description
 * 方法返回值为 <T> T, 有的方法返回值为 T 区别
 **/
public class Request<T> {


    public <T> T getObject(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T t = tClass.newInstance();
        return t;
    }

    /**
     * 方法返回前的 <T> 是告诉编译器, 当前方法的值传入类型可以和类初始化的泛型类型不同, 
     * 也是就是该方法的泛型类可以自定义, 不需要跟类初始化的泛型类相同
     *
     * 参数 T 
     *  第一个 表示是泛型
     *  第二个 表示是返回是T类型的数据
     *  第三个 表示限制参数类型为T
     * @param data
     * @param <T>
     * @return
     */
    private <T> T getListFirst(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    /**
     * 这个只能传T类型的数据
     * @param data
     * @return
     */
    private T getListFirst2(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        // 入参由List<T>的T 决定, 因为返回值为<T> T ,所以入参不受 Request<T> 影响
        Integer a = new Request<String>().getListFirst(data);

        // 编译出错, 入参由Request<T> T的决定, 受Request<T>影响
        //new Request<String>().getListFirst2(data);

        // 没什么区别
        String aa = new Request<String>().getListFirst(data2);
        String bb = new Request<String>().getListFirst2(data2);
    }

    public static void main(String[] args) {
        System.out.println("<T> 和 <?> 的 区别, 首先要区分开两种不同的场景");
        System.out.println("<T> 主要用于声明一个泛型类或泛型方法");
        System.out.println("<?> 主要用于使用泛型类或泛型方法");
    }
}
