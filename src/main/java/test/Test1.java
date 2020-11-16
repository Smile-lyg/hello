package test;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/11
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class Test1 {
    public static void main(String[] args) {
        int count = 10;
        int limit = 5;
        int page = count / limit;
        System.out.println("page = " + page);
        page = (count - 1) / limit;
        System.out.println("page = " + page);
        System.out.println("--------------");
        System.out.println((int) Math.ceil((double) count / limit));
        System.out.println((int) Math.floor((double) count / limit));
        System.out.println("--------------");
        System.out.println(count % limit);
        System.out.println("--------------");
        int page_m = (int) Math.ceil((double) count / limit);
        int page_u = 3;
        page = page_u > page_m ? page_m : page_u;
        System.out.println("page = " + page);
    }
}
// 向上取整用Math.ceil(double a)
// 向下取整用Math.floor(double a)
