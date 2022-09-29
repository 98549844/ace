
public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    //以前使用try catch-finally都是捕获异常,
    // 然后流关闭等等,代码总是这样的:好比往FileOutputStream写东西:
    public void try1() throws IOException {
        File file = new File("E://test");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            System.out.println("do something...");
            fileOutputStream.write("aaa".getBytes());
            fileOutputStream.flush();
        } catch (Exception e) {
            System.out.println("do ...");
        } finally {
            fileOutputStream.close();
        }
    }


    //这样写很难受,可以进行优化,
    //将FileOutputStream fileOutputStream = new FileOutputStream(file)放到try()里面,也可以放多个
    public void try12() throws IOException {
        File file = new File("E://test");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            System.out.println("do something...");
            fileOutputStream.write("aaa".getBytes());
            fileOutputStream.flush();
        } catch (Exception e) {
            System.out.println("do ...");
        }
    }
}



