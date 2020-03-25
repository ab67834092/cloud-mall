package com.cjb.mall.common.utils;

import com.cjb.mall.common.user.UserInfo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static void main(String[] args) {
        UserInfo user = new UserInfo();
        generateBeanSetCode(user);
        System.out.println("=====================");
        generateBeanSetCodeNoGet(user);
        System.out.println("=====================");
        UserInfo userInfo = new UserInfo(1,"zhangsan",new Date());
        UserInfo userInfo1 = new UserInfo(2,"lisi",new Date());
        List<UserInfo> db = new ArrayList();
        db.add(userInfo);
        db.add(userInfo1);

        List<UserInfo> list = new ArrayList();
        list = db;
        System.out.println(list.toString());

        List<UserInfo> list2;
        try{
            list2 = db;
            System.out.println(list2.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
