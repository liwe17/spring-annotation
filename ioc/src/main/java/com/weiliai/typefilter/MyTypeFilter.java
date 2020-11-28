package com.weiliai.typefilter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author: Doug Li
 * @Date: 2019/7/22
 * @Describe: 自定义类型过滤器
 */
public class MyTypeFilter implements TypeFilter {

    /**
     * @param metadataReader 读取到当前正在扫描的类信息
     * @param metadataReaderFactory 获取其他任何类信息
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类资源(类的路径)
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        //System.out.println("-------->className:"+className);
        if(!className.contains("er")){
            return true;
        }
        return false;
    }
}
