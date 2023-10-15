package feature.booking.dao;

import feature.booking.vo.TableBookingVo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class TableBookingImpl implements TableBookingDao{
    //update!!如果不一定每次都會更新的欄位要寫嗎?
    @Override
    public Integer updateById(@NotNull TableBookingVo newtablebook){
        Session session = getSession();
        TableBookingVo oldtablebook = session.get(TableBookingVo.class, newtablebook.getTablebookno());
        try{
            final Integer tableno = newtablebook.getTablebookno();
            if(tableno != null){
                oldtablebook.setTablebookno(tableno);
            }
            final Integer tablemor = newtablebook.getTablemor();
            if(tablemor != null){
                oldtablebook.setTablemor(tablemor);
            }
            final Integer tableeve = newtablebook.getTableeve();
            if(tableeve != null){
                oldtablebook.setTableeve(tableeve);
            }
            final Integer tablenight = newtablebook.getTablenight();
            if(tablenight != null){
                oldtablebook.setTablenight(tablenight);
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    //選全部
    public List<TableBookingVo> selectAll(){
        final String hql = "FROM TableBookingVO BY tablebookno";
        return getSession().createQuery(hql, TableBookingVo.class).getResultList();
    }
    //用來選擇日期及時段跑出對應開放時間
    public List<TableBookingVo> SelectByChoose(int tableno){

        //entity.TABLE_DATE儲存日期的sql表格
        String hql = "SELECT entity FROM TableBookingVo entity WHERE entity.tableno = :table\n";

        Session session = getSession();
        Query<TableBookingVo> query = session.createQuery(hql, TableBookingVo.class);

        if(tableno != 0){
            query.setParameter("table",tableno);
        }

        List<TableBookingVo> tableBook = query.getResultList();
        return tableBook;
    }
    //改變狀態
    public TableBookingVo updateByState(Integer stateNo,Date tabledate,Integer tableNo){
        try {
            Session session = getSession();
            // 根据 periodTime 的值确定要更新的列名
            String columnName;
            switch (stateNo) {
                case 0:
                    columnName = "TABLE_MOR";
                    break;
                case 1:
                    columnName = "TABLE_EVE";
                    break;
                case 2:
                    columnName = "TABLE_NIGHT";
                    break;
                default:
                    columnName = ""; // 处理无效的 periodTime 值
                    break;
            }
            if(!columnName.isEmpty()){
                // 创建 HQL 更新查询
                String hql = "UPDATE TableBookingVo SET " + columnName + " = :newValue " +
                        "WHERE TABLE_NO = :tableNo AND TABLE_DATE = :tabledate";
                Query<?> query = session.createQuery(hql);
                query.setParameter("newValue", 1); // 更新的新值
                query.setParameter("tableNo", tableNo);
                query.setParameter("tabledate", tabledate);
                //=============================================
                System.out.println("tableNo:" +tableNo);
                System.out.println("bookDate" +tabledate);
                System.out.println(hql);
                // 执行更新操作
                int rowsUpdated = query.executeUpdate();
                System.out.println("更新:" +rowsUpdated);
                if (rowsUpdated > 0) {
                    //session.getTransaction().commit();
                    TableBookingVo updatedBooking = new TableBookingVo();

                    // 根据更新的列名设置对象的属性
                    switch (columnName) {
                        case "TABLE_MOR":
                            updatedBooking.setTablemor(0);
                            break;
                        case "TABLE_EVE":
                            updatedBooking.setTableeve(0);
                            break;
                        case "TABLE_NIGHT":
                            updatedBooking.setTablenight(0);
                            break;
                        // 可以根据需要添加其他列名和相应的设置属性
                    }

                    return updatedBooking;
                } else {
                    //session.getTransaction().rollback();
                }
            } else {
                //session.getTransaction().rollback();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TableBookingVo cancelByState(Integer stateNo,Date bookdate,Integer tableNo){
        try {
            Session session = getSession();
            //session.beginTransaction();
            // 根据 periodTime 的值确定要更新的列名
            String columnName;
            switch (stateNo) {
                case 0:
                    columnName = "TABLE_MOR";
                    break;
                case 1:
                    columnName = "TABLE_EVE";
                    break;
                case 2:
                    columnName = "TABLE_NIGHT";
                    break;
                default:
                    columnName = ""; // 处理无效的 periodTime 值
                    break;
            }
            if(!columnName.isEmpty()){
                // 创建 HQL 更新查询
                String hql = "UPDATE TableBookingVo SET " + columnName + " = :newValue " +
                        "WHERE TABLE_NO = :tableNo AND TABLE_DATE = :tabledate";
                Query<?> query = session.createQuery(hql);
                query.setParameter("newValue", 1); // 更新的新值
                query.setParameter("tableNo", tableNo);
                query.setParameter("tabledate", bookdate);
                //=============================================
                System.out.println("tableNo:" +tableNo);
                System.out.println("bookDate" +bookdate);
                System.out.println(hql);
                // 执行更新操作
                int rowsUpdated = query.executeUpdate();
                System.out.println("更新:" +rowsUpdated);
                if (rowsUpdated > 0) {
                    //session.getTransaction().commit();
                    TableBookingVo updatedBooking = new TableBookingVo();

                    // 根据更新的列名设置对象的属性
                    switch (columnName) {
                        case "TABLE_MOR":
                            updatedBooking.setTablemor(0);
                            break;
                        case "TABLE_EVE":
                            updatedBooking.setTableeve(0);
                            break;
                        case "TABLE_NIGHT":
                            updatedBooking.setTablenight(0);
                            break;
                        // 可以根据需要添加其他列名和相应的设置属性
                    }

                    return updatedBooking;
                } else {
                    //session.getTransaction().rollback();
                }
            } else {
                //session.getTransaction().rollback();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
