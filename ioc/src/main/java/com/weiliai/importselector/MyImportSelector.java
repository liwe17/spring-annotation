package com.weiliai.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe:
 */
public class MyImportSelector implements ImportSelector {

    //AnnotationMetadata,当前标注@Import注解的类的所有注解信息
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"com.weiliai.pojo.Blue"};
    }
}
