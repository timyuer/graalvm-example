package example;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class GraalPy {
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
//        String venvExePath = GraalPy.class.
//                getClassLoader().
//                getResource(Paths.get("venv", "bin", "graalpy").toString()).
//                getPath();

        System.out.println(Paths.get("venv", "bin", "graalpy").toString());

        Context ctx = Context.newBuilder("python").
                allowAllAccess(true).
                allowIO(true).
                allowExperimentalOptions(true).
                option("python.ForceImportSite", "true").
                arguments(PYTHON, args). //python参数
//                option("python.PythonPath", "/opt/package/code/java-example/java-common/python").
//                option("python.Executable", "/usr/local/graalpy-22.3.1-linux-amd64/bin/graalpy").
        build();

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


    private static Context createContext(String modulePath) {
        Engine engine = Engine.create();
        Context context = Context.newBuilder(PYTHON).allowAllAccess(true).engine(engine)
//                .option(PYTHON_FORCE_IMPORT_SITE, "true")
//                .option(PYTHON_PYTHON_PATH, modulePath)
//                .option(PYTHON_EXECUTABLE, customizeConfig.getPythonExecutable())
                .build();
        return context;
    }
}
