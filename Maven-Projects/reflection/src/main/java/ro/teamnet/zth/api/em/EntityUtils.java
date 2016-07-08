package ro.teamnet.zth.api.em;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import javafx.scene.control.Tab;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtils {
    private EntityUtils() throws UnsupportedOperationException{

    }

    public static String getTableName(Class entity) {
        Table annotation =(Table)entity.getAnnotation(Table.class);
        if(annotation!=null){
            return annotation.name();
        }
        return entity.getSimpleName();
    }

    public static List getColumns(Class entity){ // posibily broken, mostly broken... sure broken
        Field[] fields = entity.getDeclaredFields();
        List<ColumnInfo> columnInfos=new ArrayList<ColumnInfo>();

        for(Field field:fields){
            ColumnInfo info=new ColumnInfo();

            Id id=(Id)field.getAnnotation(Id.class);
            if(id!=null){
                info.setId(true);
                info.setColumnName(id.name());
                info.setColumnType(id.annotationType());
            }

            Column column=(Column) field.getAnnotation(Column.class);
            if(column!=null){
                info.setId(false);
                info.setColumnName(column.name());
                info.setColumnType(column.annotationType());
            }
            info.setDbName(field.getName());

            columnInfos.add(info);
        }

        return columnInfos;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        if (value instanceof BigDecimal){
            BigDecimal bd=(BigDecimal) value;

            if(wantedType.equals(Integer.class)){
                return bd.intValue();
            }
            else if(wantedType.equals(Long.class)){
                return bd.longValue();
            }
            else if(wantedType.equals(float.class)){
                return bd.floatValue();
            }
            else {
                return bd.doubleValue();
            }
        }else{
            return value;
        }
    }

    public static List getFieldsByAnnotations(Class clazz, Class annotation){
        Field[] fields=clazz.getDeclaredFields();

        List<Field> list=new ArrayList<Field>();
        for(Field field:fields){
            Annotation ann=field.getAnnotation(annotation);
            if(ann!=null) list.add(field);
        }
        return list;
    }

    public static Object getSqlValue(Object object){  //Iisus a fost aici
        Table ann=(Table) ((Class) object).getAnnotation(Table.class);
        if(ann!=null){
            List<Field> fields=getFieldsByAnnotations((Class)object,Id.class);
            fields.get(0).setAccessible(true);

            try {
                return fields.get(0).get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
