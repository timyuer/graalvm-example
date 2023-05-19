package example;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class GraalDemo {
    private static String VENV_EXECUTABLE = "";

    private static final String PYTHON = "python";
    private static final String PYTHON_PYTHON_PATH = "python.PythonPath";
    private static final String PYTHON_EXECUTABLE = "python.Executable";
    private static final String PYTHON_FORCE_IMPORT_SITE = "python.ForceImportSite";

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            args = new String[]{"1"};
        }


        System.out.println("Hello, World!");
        try (Context ctx = Context.newBuilder("python").
                allowAllAccess(true).
                allowIO(true).

                arguments(PYTHON, args). //python参数
                        option("python.CoreHome", "/usr/local/graalvm/languages/python/lib-graalpython").
                build()) {

            //直接执行python命令
            int i = ctx.eval(PYTHON, "1+2").asInt();
            System.out.println(i);

            //运行python文件
            Value value = ctx.eval(Source.newBuilder(PYTHON, new File("/opt/package/code/graalvm-example/src/main/python/test.py")).build());
            System.out.println(value);

            //执行python文件中的方法
            Value bindings = ctx.getBindings(PYTHON);
            Value getDate = bindings.getMember("get_date");
            Value execute = getDate.execute();

            System.out.println(execute);
        }


        try (Context context = Context.create()) {
            context.eval("js", "print('Hello JavaScript!');");
        }

        try (Context context = Context.create()) {
            context.eval("ruby", "puts 'Hello Ruby!'");
        }

    }


}
