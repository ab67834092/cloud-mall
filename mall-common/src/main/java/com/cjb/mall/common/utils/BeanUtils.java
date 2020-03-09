package com.cjb.mall.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

public class BeanUtils {

    public static<T> void initBean(T object) {
        Class<?> classType = object.getClass();
        Field[] fields = classType.getDeclaredFields();//得到对象中的字段
        for(Field field : fields) {
            String fieldName = field.getName();
            try{
                PropertyDescriptor pd = new PropertyDescriptor(fieldName, classType);
                Method method = pd.getWriteMethod();//获得写方法
                //根据字段类型决定结果集中使用哪种get方法从数据中取到数据
                if (field.getType().equals(String.class)) {
                    method.invoke(object, "");
                }
                if (field.getType().equals(Integer.class)) {
                    method.invoke(object, -1);
                }
                if (field.getType().equals(java.util.Date.class)) {
                    method.invoke(object, new Date());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static<T> void generateBeanSetCode(T object) {
        Class<?> classType = object.getClass();
        Field[] fields = classType.getDeclaredFields();//得到对象中的字段
        for(Field field : fields) {
            StringBuilder sb = new StringBuilder();
            String fieldName = field.getName();
            String simpleName = object.getClass().getSimpleName();
            sb.append(String.valueOf(simpleName.charAt(0)).toLowerCase());
            sb.append(simpleName.substring(1));
            sb.append(".set");
            sb.append(String.valueOf(fieldName.charAt(0)).toUpperCase());
            sb.append(fieldName.substring(1));
            sb.append("(");
            sb.append("vo.get");
            sb.append(String.valueOf(fieldName.charAt(0)).toUpperCase());
            sb.append(fieldName.substring(1));
            sb.append("(");
            sb.append(")");
            sb.append(");");
            System.out.println(sb.toString());
        }
    }

    public static<T> void generateBeanSetCodeNoGet(T object) {
        Class<?> classType = object.getClass();
        Field[] fields = classType.getDeclaredFields();//得到对象中的字段
        for(Field field : fields) {
            StringBuilder sb = new StringBuilder();
            String fieldName = field.getName();
            String simpleName = object.getClass().getSimpleName();
            sb.append(String.valueOf(simpleName.charAt(0)).toLowerCase());
            sb.append(simpleName.substring(1));
            sb.append(".set");
            sb.append(String.valueOf(fieldName.charAt(0)).toUpperCase());
            sb.append(fieldName.substring(1));
            sb.append("(");
            sb.append(");");
            System.out.println(sb.toString());
        }
    }

}
