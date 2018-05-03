package com;

import com.docker.yyf.entity.BaseEntity;
import com.docker.yyf.entity.Customer;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/2/26 10:00
 * @Modified By
 * \
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(GetImageStr());
    }

    public static String GetImageStr()
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "E:/image/file/web/security/0d6007f5325d416f933a00427a7ab5ed/1524192451895.png";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    private static void setQuery(Query query, Customer record) throws IllegalAccessException {
        if (record==null){
            return;
        }
        if (StringUtils.isNotBlank(record.getId())){
            query.addCriteria(Criteria.where("id").is(record.getId()));
        }
        Class<? extends BaseEntity> recordClass = record.getClass();
        Field[] fs = recordClass.getDeclaredFields();
        for (Field field : fs) {
            field.setAccessible(true);
            if (field.get(record) !=null){
                if(field.getType().isAssignableFrom(List.class)){
                    List list = (List) field.get(record);
                    for (int i=0;i<list.size();i++){
                        Class listClass= list.get(i).getClass();
                        Field[] fss = listClass.getDeclaredFields();
                        for (Field field1:fss){
                            field1.setAccessible(true);
                            if (field1.get(list.get(i))!=null){
                                query.addCriteria(Criteria.where(field.getName()).elemMatch(Criteria.where(field1.getName()).is(field1.get(list.get(i)))));
                            }
                        }
                    }
                }else {
                    query.addCriteria(Criteria.where(field.getName()).is(field.get(record)));
                }
            }
        }
    }
}
