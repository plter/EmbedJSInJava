import com.eclipsesource.v8.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {

        V8 runtime = V8.createV8Runtime();
        runtime.registerJavaMethod((receiver, parameters) -> {
            System.out.println(parameters.get(0));
        }, "print");


        File f = new File("app.js");
        FileInputStream in = new FileInputStream(f);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        String jsContent = new String(bytes, StandardCharsets.UTF_8);
        in.close();

        runtime.executeScript(jsContent);
        runtime.release();
    }
}
