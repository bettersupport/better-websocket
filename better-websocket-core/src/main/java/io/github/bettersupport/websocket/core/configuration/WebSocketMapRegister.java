package io.github.bettersupport.websocket.core.configuration;

import io.github.bettersupport.websocket.core.WebSocketEndpointInterface;
import io.github.bettersupport.websocket.core.annotation.WebSocketMapScan;
import io.github.bettersupport.websocket.core.annotation.WebSocketMapping;
import io.github.bettersupport.websocket.core.exception.WebSocketMapException;
import io.github.bettersupport.websocket.core.model.WebSocketClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author wang.wencheng
 * date 2021-8-3
 * describe
 */
public class WebSocketMapRegister implements ImportBeanDefinitionRegistrar{

    private static final Logger log = LoggerFactory.getLogger(WebSocketMapRegister.class);

    private static final String BASE_PACKAGES_FIELD = "basePackages";

    private static final Map<String, WebSocketClass> webSocketMap = new HashMap<>();

    public static Map<String, WebSocketClass> getWebSocketMap() {
        return webSocketMap;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> defaultAttrs = importingClassMetadata.getAnnotationAttributes(WebSocketMapScan.class.getName(), true);

        if (defaultAttrs != null && defaultAttrs.size() > 0) {

            String[] basePackages = (String[]) defaultAttrs.get(BASE_PACKAGES_FIELD);

            ClassPathScanningCandidateComponentProvider scanner = getScanner();
            AnnotationTypeFilter annotationFilter = new AnnotationTypeFilter(WebSocketMapping.class);
            String InterfaceNameFilter = WebSocketEndpointInterface.class.getName();
            scanner.addIncludeFilter(annotationFilter);

            for (String basePackage : basePackages) {

                Set<BeanDefinition> scanList = scanner.findCandidateComponents(basePackage);
                for (BeanDefinition beanDefinition : scanList) {
                    ScannedGenericBeanDefinition scannedGenericBeanDefinition = (ScannedGenericBeanDefinition) beanDefinition;
                    String[] names = scannedGenericBeanDefinition.getMetadata().getInterfaceNames();
                    List<String> interfaceList = Arrays.asList(names);
                    String classname = scannedGenericBeanDefinition.getBeanClassName();
                    if (interfaceList.contains(InterfaceNameFilter)) {
                        Class<?> clazz;
                        WebSocketMapping webSocketMapping;
                        try {
                            clazz = Class.forName(classname);
                            webSocketMapping = clazz.getAnnotation(WebSocketMapping.class);
                        } catch (ClassNotFoundException e) {
                            throw new WebSocketMapException(e);
                        }
                        if (webSocketMapping == null) {
                            throw new WebSocketMapException("don't find @WebSocketMapping");
                        }
                        String mapPath = webSocketMapping.value();
                        if (StringUtils.isEmpty(mapPath)) {
                            mapPath = webSocketMapping.path();
                        }
                        if (mapPath.indexOf("/") != 0) {
                            mapPath = "/" + mapPath;
                        }
                        WebSocketClass webSocketClass = new WebSocketClass(webSocketMapping, clazz);
                        if (webSocketMap.get(mapPath) != null) {
                            throw new WebSocketMapException("multiple WebSocketMapping value = " + mapPath);
                        }
                        webSocketMap.put(mapPath, webSocketClass);
                        log.info("websocket register map: {}", mapPath);
                    } else {
                        log.warn("class [{}] should implements WebSocketEndpointInterface, otherwise we can't use it", classname);
                    }
                }
            }
        }

    }

    protected ClassPathScanningCandidateComponentProvider getScanner() {

        return new ClassPathScanningCandidateComponentProvider(false);

    }
}
