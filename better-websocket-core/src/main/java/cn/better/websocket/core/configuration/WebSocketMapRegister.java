package cn.better.websocket.core.configuration;

import cn.better.websocket.core.annotation.WebSocketMapScan;
import cn.better.websocket.core.annotation.WebSocketMapping;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
public class WebSocketMapRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(WebSocketMapRegister.class);

    private ClassLoader classLoader;

    private Environment environment;

    private ResourceLoader resourceLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> defaultAttrs = importingClassMetadata.getAnnotationAttributes(WebSocketMapScan.class.getName(), true);

        if (defaultAttrs != null && defaultAttrs.size() > 0) {
            log.info("WebSocketMap package scan: {}",  defaultAttrs.get("basePackages"));


            ClassPathScanningCandidateComponentProvider scanner = getScanner();
//            scanner.setResourceLoader(resourceLoader);
            Set<String> basePackages = new HashSet<String>();

//            AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(WebSocketMapping.class);
//            scanner.addIncludeFilter(annotationTypeFilter);

            for (String pkg : (String[]) defaultAttrs.get("basePackages")) {
                if (pkg != null && !"".equals(pkg)) {
                    basePackages.add(pkg);
                }
            }

            Map<String, WebSocketMapping> webSocketMap = new HashMap<>();

            for (String basePackage : basePackages) {

                Set<BeanDefinition> scanList = scanner.findCandidateComponents(basePackage);
                for (BeanDefinition beanDefinition : scanList) {
                    String classname = beanDefinition.getBeanClassName();
                    WebSocketMapping s = null;
                    try {
                        s = Class.forName(classname).getAnnotation(WebSocketMapping.class);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    webSocketMap.put(classname, s);
                }

//                Set<BeanDefinition> candidates = new LinkedHashSet<>();
//
//                ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//
//                try {
//                    // 这里特别注意一下类路径必须这样写
//                    // 获取指定包下的所有类
//                    basePackage = basePackage.replace(".", "/");
//                    Resource[] resources = resourcePatternResolver.getResources("classpath*:" + basePackage);
//
//                    MetadataReaderFactory metadata1 = new SimpleMetadataReaderFactory();
//                    for (Resource resource : resources) {
//                        MetadataReader metadataReader = metadata1.getMetadataReader(resource);
//                        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
//                        sbd.setResource(resource);
//                        sbd.setSource(resource);
//                        candidates.add(sbd);
//                    }
//                    for (BeanDefinition beanDefinition : candidates) {
//                        String classname = beanDefinition.getBeanClassName();
//                        WebSocketMapping s = Class.forName(classname).getAnnotation(WebSocketMapping.class);
//                        if (s != null) {
//                            WebSocketMap.put(classname, s);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            log.info("WebSocket classes {}", JSONObject.toJSONString(webSocketMap, true));

        }

    }

    protected ClassPathScanningCandidateComponentProvider getScanner() {

        return new ClassPathScanningCandidateComponentProvider(false);
//        {
//            @Override
//            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
//                if (beanDefinition.getMetadata().isIndependent()) {
//
//                    if (beanDefinition.getMetadata().isInterface()
//                            && beanDefinition.getMetadata().getInterfaceNames().length == 1
//                            && Annotation.class.getName().equals(beanDefinition.getMetadata().getInterfaceNames()[0])) {
//                        try {
//                            Class<?> target = ClassUtils.forName(beanDefinition.getMetadata().getClassName(),
//                                    WebSocketMapRegister.this.classLoader);
//                            return !target.isAnnotation();
//                        } catch (Exception ex) {
//                            this.logger.error(
//                                    "Could not load target class: " + beanDefinition.getMetadata().getClassName(), ex);
//
//                        }
//                    }
//                    return true;
//                }
//                return false;
//
//            }
//        };
    }
}
