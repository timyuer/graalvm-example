# graalvm示例

## 一、graalvm调用python示例

### 1. 下载

https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.1/graalvm-ce-java17-linux-amd64-22.3.1.tar.gz

graalvm需和
https://github.com/oracle/graalpython/releases
版本对应

### 2. 安装python

```bash
${JAVA_HOME}/bin/gu install python
```

### 3. 执行java调用python代码

## 参考

https://www.graalvm.org/latest/reference-manual/embed-languages/#embed-languages-in-guest-languages

## 二、graalvm制作二进制镜像

### 1. 生成jar包

```bash
export JAVA_HOME=/usr/local/graalvm
mvn clean package
```

### 2. 制作镜像

```bash
/usr/local/graalvm/bin/native-image --language:ruby --language:js --language:python -cp /opt/package/code/graalvm-example/target/graalvm-example-1.0-SNAPSHOT.jar example.GraalDemo GraalDemo
```

### 3. 执行镜像

```bash
./GraalDemo
```

## 参考

https://www.graalvm.org/latest/reference-manual/native-image/