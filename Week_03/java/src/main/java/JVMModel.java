import java.util.HashMap;
import java.util.Map;

public class JVMModel {

    public static final int initData = 888; // 常量
    public static Map map = new HashMap();
    public int i = 11;
    public Integer I = 12;
    public double d = 2d;
    public long l = 31L;
    public Long L = 32L;

    public static void main(String[] args) {
        JVMModel model = new JVMModel();
        model.test();
    }

    public int test(){
        int a=1;
        int b=2;
        int c=(a+b)*10;
        return c;
    }
}
