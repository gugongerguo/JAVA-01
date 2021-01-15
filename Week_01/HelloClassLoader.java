import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Object o = new HelloClassLoader().findClass("Hello.xlass").newInstance();
            Method[] methods = o.getClass().getMethods();
            Method[] declaredMethods = o.getClass().getDeclaredMethods();
//            for (Method m : methods) {
//                System.out.println(m.toString());
//            }
            for (Method m : declaredMethods) {
                System.out.println("------------------------------------------");
                System.out.println(m.toString());
                m.invoke(o);
            }
//            o.getClass().getMethod("hello").invoke(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            File file = new File(name);
            int l = (int) file.length();
            byte[] bytes = new byte[l];
            int read = new FileInputStream(file).read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass("Hello", bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }
}
