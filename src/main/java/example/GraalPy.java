package example;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Value;

import java.nio.file.Paths;

public class GraalPy {
    private static String VENV_EXECUTABLE = "";

    private static final String PYTHON = "python";
    private static final String PYTHON_PYTHON_PATH = "python.PythonPath";
    private static final String PYTHON_EXECUTABLE = "python.Executable";
    private static final String PYTHON_FORCE_IMPORT_SITE = "python.ForceImportSite";

    public static void main(String[] args) {
        System.out.println("Hello, World!");
//        String venvExePath = GraalPy.class.
//                getClassLoader().
//                getResource(Paths.get("venv", "bin", "graalpy").toString()).
//                getPath();

        System.out.println(Paths.get("venv", "bin", "graalpy").toString());

        Context ctx = Context.newBuilder("python").
                allowIO(true).
//                option("python.ForceImportSite", "true").
//                option("python.PythonPath", "/opt/package/code/java-example/java-common/python").
                option("python.Executable", "/opt/package/code/java-example/java-common/graalpy-22.3.1-linux-amd64/bin/graalpy").
        build();

        int i = ctx.eval(PYTHON, "a+b").asInt();
        System.out.println(i);
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
