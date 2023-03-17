package space.novium.nebula.core.annotations;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import space.novium.nebula.core.event.EventListener;
import space.novium.nebula.core.event.enums.EventType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandler {
    private AnnotationHandler(){}

    private static boolean loaded = false;
    private static Map<String, Set<Method>> annotationsByMethod;
    private static final String EVENT_LISTENER = "EventListener";

    public static void loadAnnotations(){
        if(!loaded){
            annotationsByMethod = new HashMap<>();
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forJavaClassPath()).setScanners(
                            Scanners.MethodsAnnotated
                    )
            );
            Set<Method> annotated = reflections.getMethodsAnnotatedWith(EventListener.class);
            annotationsByMethod.put(EVENT_LISTENER, annotated);
            loaded = true;
        }
    }

    public static void doEvent(EventType type){
        doEvent(type, null);
    }

    public static void doEvent(EventType type, Object... args){
        for(Method method :annotationsByMethod.get(EVENT_LISTENER)){
            try {
                if(method.getAnnotation(EventListener.class).event() == type){
                    method.invoke(method.getDeclaringClass(), args);
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static boolean isLoaded(){
        return loaded;
    }
}
