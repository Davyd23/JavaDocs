package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be Departments!", "Departments", tableName);

        tableName = EntityUtils.getTableName(Location.class);
        assertEquals("Table name should be LOCATIONS!", "LOCATIONS", tableName);
    }

    @Test
    public void testGetColumnsMethod(){
        ArrayList<ColumnInfo> list= (ArrayList<ColumnInfo>) EntityUtils.getColumns(Department.class);
        assertEquals("Size not right!",3,list.size());

        list=(ArrayList<ColumnInfo>) EntityUtils.getColumns(Location.class);
        assertEquals("Size not right!",5,list.size());
    }

    @Test
    public void testCastFromSqlTypeMethod(){
        Long[] values={2147483648L,-2147483648l};
        Long value=(Long)EntityUtils.castFromSqlType(new BigDecimal("2147483648"),Long.class);
        assertEquals("Should return 2147483648!",value,values[0]);

        Integer value2=(Integer)EntityUtils.castFromSqlType(new BigDecimal("2147483648"),Integer.class);
        assertEquals("Should return -2147483648!",value2.longValue(),(long)values[1]);
    }

    @Test
    public void TestGetFieldsByAnnotationsMethod(){
        ArrayList<Field> fields= (ArrayList<Field>) EntityUtils.getFieldsByAnnotations(Location.class, Id.class);
        assertEquals(1,fields.size());

        fields= (ArrayList<Field>) EntityUtils.getFieldsByAnnotations(Location.class, Column.class);
        assertEquals(4,fields.size());
    }

}
