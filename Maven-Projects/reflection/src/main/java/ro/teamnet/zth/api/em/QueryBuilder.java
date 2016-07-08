package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ro.teamnet.zth.api.em.QueryType.*;

/**
 * Created by user on 7/7/2016.
 */
public class QueryBuilder {
    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;
    public String getValueForQuery(Object value){
        if(value instanceof Date){
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";

        }
        return ""; // should return only for string
    }

    public QueryBuilder addCondition(Condition condition){
        if(conditions==null)conditions=new ArrayList<Condition>();

        conditions.add(condition);

        return this;
    }

    public QueryBuilder setTableName(Object tableName){
        this.tableName=tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        this.queryColumns=queryColumns;
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType=queryType;
        return this;
    }

    private String createSelectQuery(){
        StringBuilder sb=new StringBuilder();

        sb.append("Select ");
        if(queryColumns.size()==0){
            sb.append("*");
        }else
            for(int i=0;i<queryColumns.size();i++){
                ColumnInfo ci=queryColumns.get(i);
                sb.append(ci.getColumnName());

                if(i<queryColumns.size()-1){
                    sb.append(',');
                }
            }

        sb.append(" from "+tableName+" ");

        if(conditions.size()!=0){
            sb.append("Where ");
            for(int i=0;i<conditions.size();i++){
                sb.append(conditions.get(i).getColumnName()+"="+conditions.get(i).getValue());

                if(i<conditions.size()-1){
                    sb.append(",");
                }
            }
        }
        sb.append(";");
        return sb.toString();
    }
    private String createUpdateQuery(){
        StringBuilder sb=new StringBuilder();

        sb.append("Update "+tableName+" set ");
        if(queryColumns.size()==0){
            return "";
        }else
            for(int i=0;i<queryColumns.size();i++){
                ColumnInfo ci=queryColumns.get(i);
                sb.append(ci.getColumnName()+"="+ci.getValue());

                if(i<queryColumns.size()-1){
                    sb.append(',');
                }
            }

        sb.append(" ");
        if(conditions.size()!=0){
            sb.append("where ");
            for(int i=0;i<conditions.size();i++){
                sb.append(conditions.get(i).getColumnName()+"="+conditions.get(i).getValue());

                if(i<conditions.size()-1){
                    sb.append(",");
                }
            }
        }
        sb.append(";");
        return sb.toString();
    }
    private String createDeleteQuery(){

            StringBuilder sb=new StringBuilder();

            sb.append("Delete from "+tableName+" ");

            if(conditions.size()!=0){
                sb.append("where ");
                for(int i=0;i<conditions.size();i++){
                    sb.append(conditions.get(i).getColumnName()+"="+conditions.get(i).getValue());

                    if(i<conditions.size()-1){
                        sb.append(",");
                    }
                }
            }
            sb.append(";");
        return sb.toString();
    }
    private String createInsertQuery(){

            StringBuilder sb=new StringBuilder();

            sb.append("Insert into "+tableName+" values (");
            if(queryColumns.size()==0){
                return "";
            }else
                for(int i=0;i<queryColumns.size();i++){
                    ColumnInfo ci=queryColumns.get(i);
                    sb.append(ci.getValue());

                    if(i<queryColumns.size()-1){
                        sb.append(',');
                    }
                }

            sb.append(");");
        return sb.toString();
    }
    public String createQuery(){
        switch (queryType){
            case SELECT: return createDeleteQuery();
            case UPDATE: return createUpdateQuery();
            case DELETE: return createSelectQuery();
            case INSERT: return createInsertQuery();
            default: return "";
        }
    }
}
