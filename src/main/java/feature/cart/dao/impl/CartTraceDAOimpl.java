package feature.cart.dao.impl;

import feature.cart.dao.CartTraceDAO;
import feature.cart.vo.CartTraceId;
import feature.cart.vo.CartTraceVO;
import org.hibernate.query.Query;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartTraceDAOimpl implements CartTraceDAO {
    private static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/polybrain");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public List<CartTraceVO> selectCartByMemNo(Integer memNo) {


//        final String hql = "From CartTraceVO where mem_no ="+ memNo ;
//        Session session = getSession();
//
//        //////////////////
//        Query<CartTraceVO> query = session.createQuery(hql,CartTraceVO.class);
//        //////////////////
//        List<CartTraceVO> returnList = query.getResultList();
//        //
//        System.out.println(returnList);
//
//        return returnList;

//        List<CartTraceVO> ctList = new ArrayList<CartTraceVO>();
//        CartTraceVO cartTraceVO = null;
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs =null;
//        final String sql = "Select * From CartTraceVO where mem_no = ?" ;
//        try{
//            con = ds.getConnection();
//            pstmt = con.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            while(rs.next()){
//                cartTraceVO = new CartTraceVO();
//                cartTraceVO.setMemNo(rs.getInt("MEM_NO"));
//                cartTraceVO.setItemNo(rs.getInt("ITEM_NO"));
//                cartTraceVO.setQuantity(rs.getInt("Quantity"));
//                ctList.add(cartTraceVO);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;

    }

    @Override
    public CartTraceVO selectByMemberNumber(Integer memNo) {
        return null;
    }

    @Override
    public List<CartTraceVO> selectAll(Integer memNo) {
        List<CartTraceVO> list = new ArrayList<CartTraceVO>();
        CartTraceVO cartTraceVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final String sql = "Select * From cart where mem_no = ? Order By item_no ";
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,memNo);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                cartTraceVO = new CartTraceVO();
                cartTraceVO.setMemNo(rs.getInt("MEM_NO"));
                cartTraceVO.setItemNo(rs.getInt("ITEM_NO"));
                cartTraceVO.setQuantity(rs.getInt("Quantity"));
                list.add(cartTraceVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public List<CartTraceVO> selectAll() {
        return null;
    }


    @Override
    public Integer updateQuantity(CartTraceVO cartTraceVO){
        try {
            getSession().update(cartTraceVO);
            System.out.println("更新成功");
            return 1;
        }catch(Exception e){
            System.out.println("更新失敗");
            return -1;
        }
    }

    @Override
    public Integer deleteByItemNo(Integer memNo, Integer itemNo ) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement("delete from cart where mem_no = ? AND item_no =?");
            pstmt.setInt(1, memNo);
            pstmt.setInt(2, itemNo);
            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return 1;
    }
    public Integer insertToCart(CartTraceVO cartTraceVO){
        try {
            Integer memNo = cartTraceVO.getMemNo();
            Integer itemNo = cartTraceVO.getItemNo();

            // 先查詢購物車中是否已經存在相同的 memNo 和 itemNo
            CartTraceVO existingCartRecord = getSession()
                    .createQuery("FROM CartTraceVO WHERE memNo = :memNo AND itemNo = :itemNo", CartTraceVO.class)
                    .setParameter("memNo", memNo)
                    .setParameter("itemNo", itemNo)
                    .uniqueResult();

            if (existingCartRecord != null) {
                // 如果存在相同的記錄，則更新數量
                Integer newQuantity = existingCartRecord.getQuantity() + cartTraceVO.getQuantity();
                existingCartRecord.setQuantity(newQuantity);
                getSession().merge(existingCartRecord);
                System.out.println("購物車修改成功");
            } else {
                // 如果不存在相同的記錄，則插入新的購物車記錄
                getSession().merge(cartTraceVO);
                System.out.println("新增購物車成功");
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("購物車新增失敗");
            return -1;
        }

    }

    public static void main(String[] args) {
        List<CartTraceVO> list = new CartTraceDAOimpl().selectCartByMemNo(1001);
        System.out.println(list);
    }


}
