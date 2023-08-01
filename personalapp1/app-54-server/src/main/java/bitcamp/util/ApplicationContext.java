package bitcamp.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import bitcamp.personalapp.config.AppConfig;

// Ioc 컨테이너 = Bean 컨테이너
// -자바 설정 클래스(예: AppConfig)에서 @Bean 애노테이션이 붙은 메서드를 찾아 호출하고,
// 그 리턴 값을 컨테이너에 보관한다.
// -자바 설정 클래스(예: AppConfig)에서 @ComponentScan 애노테이션을 찾아서 패키지 정보를 알아낸다.
// 패키지에 소속된 모든 클래스에 대해 인스턴스를 생성하여 컨테이너에 보관한다.

public class ApplicationContext {
  // 객체 보관소
  Map<String, Object> beanContainer = new HashMap<>();

  public ApplicationContext(Class<?> configClass) throws Exception {

    processBeanAnnotation(configClass);

    ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);
    if (componentScan != null) {
      processComponentScanAnnotation(componentScan);
    }
  }

  private void processBeanAnnotation(Class<?> configClass) throws Exception {
    System.out.println("@Bean --------------------------------");
    // 클래스의 기본 생성자를 알아낸다.
    Constructor<?> constructor = configClass.getConstructor();

    // 기본 생성자를 이용하여 객체를 생성한다.
    Object obj = constructor.newInstance();

    // 해당 클래스에 정의된 메서드 목록만 가져온다.
    Method[] methods = configClass.getDeclaredMethods();
    for (Method m : methods) {

      // 메서드의 리턴 타입이 없다면 무시한다.
      if (m.getReturnType() == void.class) {
        continue;
      }

      // @Bean 애노테이션이 붙지 않은 메서드라면 무시한다.
      Bean beanAnnotation = m.getAnnotation(Bean.class);
      if (beanAnnotation == null) {
        continue;
      }

      // 메서드 중에서 리턴 값이 있는 메서드를 호출한다.
      // 즉 오직 값을 리턴하는 메서드만 호출한다.
      Object returnValue = m.invoke(obj);

      // 메서드가 리턴한 값을 컨테이너에 저장한다.
      if (beanAnnotation.value().length() > 0) {
        // 애노테이션에 객체 이름이 지정되어 있다면 그 이름으로 객체를 저장한다.
        beanContainer.put(beanAnnotation.value(), returnValue);
      } else {
        // 애노테이션에 설정된 이름이 없다면 메서드 이름을 사용하여 객체를 저장한다.
        beanContainer.put(m.getName(), returnValue);
      }
      System.out.printf("%s() 객체 생성\n", m.getName());
    }
  }

  private void processComponentScanAnnotation(ComponentScan componentScan) throws Exception {
    for (String basePackage : componentScan.basePackages()) {
      System.out.println(basePackage + "------------------------");
      createBeans(basePackage);
    }
  }

  private void createBeans(String basePackage) throws Exception {
    // 패키지 이름에 해당하는 디렉토리 정보를 알아낸다.
    String packagePath = basePackage.replaceAll("[.]", "/");

    ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

    InputStream dirInputStream = systemClassLoader.getResourceAsStream(packagePath);
    if (dirInputStream == null) {
      return;
    }

    BufferedReader dirReader = new BufferedReader(new InputStreamReader(dirInputStream));

    Set<Class<?>> classes =
        dirReader.lines().filter(filename -> filename.endsWith(".class")).map(filename -> {
          try {
            return Class.forName(basePackage + "." + filename.replace(".class", ""));
          } catch (Exception e) {
            return null;
          }
        }).collect(Collectors.toSet());

    for (Class<?> clazz : classes) {
      System.out.println("#####" + clazz.getName());
      if (clazz.isEnum() || clazz.isInterface() || clazz.isLocalClass() || clazz.isMemberClass()) {
        continue;
      }

      Component compAnno = clazz.getAnnotation(Component.class);
      if (compAnno == null) {
        // @Component 애노테이션이 붙지 않았다면 객체 생성 대상에서 제외한다.
        continue;
      }

      Constructor<?> constructor = clazz.getConstructors()[0];
      Parameter[] params = constructor.getParameters();
      Object[] args = prepareArguments(params);
      Object obj = constructor.newInstance(args);

      if (compAnno.value().length() > 0) {
        beanContainer.put(compAnno.value(), obj);
      }
      System.out.printf("%s 객체 생성!\n", clazz.getName());

    }
  }

  private Object[] prepareArguments(Parameter[] params) {
    ArrayList<Object> args = new ArrayList<>();

    for (Parameter param : params) {
      args.add(getBean(param.getType()));
    }

    return args.toArray();
  }

  @SuppressWarnings("unchecked")
  public <T> T getBean(Class<T> type) {
    Collection<?> list = beanContainer.values();
    for (Object obj : list) {
      if (type.isInstance(obj)) {
        return (T) obj;
      }
    }
    return null;
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }

  public String[] getBeanNames() {
    return beanContainer.keySet().toArray(new String[0]);
  }

  public static void main(String[] args) throws Exception {
    ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);

    System.out.println("생성된 객체 목록:");
    for (String name : applicationContext.getBeanNames()) {
      System.out.println(name);
    }
  }
}
