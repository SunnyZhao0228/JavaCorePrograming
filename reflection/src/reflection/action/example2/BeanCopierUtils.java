
package reflection.action.example2;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class BeanCopierUtils {
  private static final Logger LOGGER = null;

  private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap();

  static{
    //LOGGER = LoggerFactory.getLogger(BeanCopierUtils.class);
  }
  public BeanCopierUtils() {

  }

  public static void copyProperties(Object source, Object target) {
    Objects.requireNonNull(source, "source must not be null");
    Objects.requireNonNull(target, "target must not be null");
    BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
    beanCopier.copy(source, target, (Converter)null);
  }

  public static <T> T convert(Object source, Class<T> targetClass) {
    Object result = null;
    try {
      result = targetClass.getDeclaredConstructor().newInstance();
    } catch (ReflectiveOperationException var4) {
//      throw new GeneralRuntimeException("fail to create instance of type" + targetClass.getCanonicalName(), var4);
    }

    copyProperties(source, result);
    return (T) result;
  }

  public static <T> List<T> convertOfList(Collection<?> sourceCollection, Class<T> targetClass) {
    List<T> sourceList = new ArrayList();
    sourceCollection.forEach((source) -> {
      T target = convert(source, targetClass);
      sourceList.add(target);
    });
    return sourceList;
  }

  @Deprecated
  public static <T> Set<T> convertOfSet(Collection<?> sourceCollection, Class<T> targetClass, boolean isOrderly) {
    Set sourceSet;
    if (isOrderly) {
      sourceSet = new LinkedHashSet();
    } else {
      sourceSet = new HashSet();
    }

    sourceCollection.forEach((source) -> {
      T target = convert(source, targetClass);
      sourceSet.add(target);
    });
    return sourceSet;
  }

  private static <T> void injectionCollection(Collection<?> sourceCollection, Collection<T> targetCollection, Class<T> targetClass) {
    sourceCollection.forEach((source) -> {
      T target = convert(source, targetClass);
      targetCollection.add(target);
    });
  }

  private static <T> Set<T> injectionSet(Collection<?> sourceCollection, Set<T> targetSet, Class<T> targetClass) {
    injectionCollection(sourceCollection, targetSet, targetClass);
    return targetSet;
  }

  public static <T> Set<T> convertOfHashSet(Collection<?> sourceCollection, Class<T> targetClass) {
    Set<T> targetSet = new HashSet();
    return injectionSet(sourceCollection, targetSet, targetClass);
  }

  public static <T> Set<T> convertOfLinkedHashSet(Collection<?> sourceCollection, Class<T> targetClass) {
    Set<T> targetSet = new LinkedHashSet();
    return injectionSet(sourceCollection, targetSet, targetClass);
  }

  private static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
    String key = generateKey(source, target);
    return (BeanCopier)BEAN_COPIER_MAP.computeIfAbsent(key, (x) -> {
      return BeanCopier.create(source, target, false);
    });
  }

  private static String generateKey(Class<?> source, Class<?> target) {
    return source.getCanonicalName().concat(target.getCanonicalName());
  }

  public static void applyIf(Object target, Object source) {
    Objects.requireNonNull(source, "source must not be null");
    Objects.requireNonNull(target, "target must not be null");
    Class<?> targetClass = target.getClass();
    List<Field> targetClassDeclaredFields = getAllField(target);
    BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), true);
    beanCopier.copy(source, target, (sourceValue, sourceValueClass, methodName) -> {
      if (Objects.nonNull(sourceValue)) {
        return sourceValue;
      } else {
        Iterator var6 = targetClassDeclaredFields.iterator();

        while(var6.hasNext()) {
          Field targetClassDeclaredField = (Field)var6.next();

          try {
            String name = targetClassDeclaredField.getName();
            PropertyDescriptor pd = new PropertyDescriptor(name, targetClass);
            targetClassDeclaredField.setAccessible(true);
            if (pd.getWriteMethod().getName().equals(methodName)) {
              return targetClassDeclaredField.get(target);
            }
          } catch (Exception var10) {
            LOGGER.error("源对象属性:[{}]，拷贝失败", (Throwable) sourceValue);
          }
        }

        return null;
      }
    });
  }

  public static List<Field> getAllField(Object object) {
    Class clazz = object.getClass();

    ArrayList fields;
    for(fields = new ArrayList(); clazz != null; clazz = clazz.getSuperclass()) {
      fields.addAll(new ArrayList(Arrays.asList(clazz.getDeclaredFields())));
    }

    return fields;
  }
}
