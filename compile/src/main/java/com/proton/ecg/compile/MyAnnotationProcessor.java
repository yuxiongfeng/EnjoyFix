package com.proton.ecg.compile;

import com.google.auto.service.AutoService;
import com.proton.ecg.annotation.MyAnnotation;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes({"com.proton.ecg.annotation.MyAnnotation"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(MyAnnotationProcessor.class)
public class MyAnnotationProcessor extends AbstractProcessor {
    /**
     * 提供类型相关的操作，如：获取父类，判断两个类之间的关系
     */
    private Types mTypeUtils;
    /**
     * 提供元素相关的操作，如：获取包名等
     */
    private Elements mElementUtils;
    /**
     * 提供文件操作，可以为我们生成代码
     */
    private Filer mFiler;
    /**
     * 用来打印日志
     */
    private Messager mMessager;

    private static final String INJECTOR_NAME = "ViewInjector";
    private static final String GEN_CLASS_SUFFIX = "Injector";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "========>开始处理注解");
        boolean empty = annotations.isEmpty();
        if (!empty) {
            mMessager.printMessage(Diagnostic.Kind.NOTE, "dealing annotations.");
            Iterator<? extends TypeElement> iterator = annotations.iterator();
            while (iterator.hasNext()) {
                TypeElement element = iterator.next();
                PackageElement packageName = mElementUtils.getPackageOf(element);
                mMessager.printMessage(Diagnostic.Kind.NOTE, "packageName==" + packageName);
                Name simpleName = element.getSimpleName();
                mMessager.printMessage(Diagnostic.Kind.NOTE, "simpleName==" + simpleName);

                String test = "package com.proton.ecg.enjoyfix;\n" +
                        "\n" +
                        "public class Test {\n" +
                        "    public static void print() {\n" +
                        "        System.out.println(\"我是Test打印的内容...\");\n" +
                        "        ;\n" +
                        "    }\n" +
                        "}";
                try {
                    JavaFileObject fileObject = mFiler.createSourceFile("Test");
                    Writer writer = fileObject.openWriter();
                    writer.write(test);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mMessager.printMessage(Diagnostic.Kind.NOTE, "========>注解处理器初始化");
    }

}