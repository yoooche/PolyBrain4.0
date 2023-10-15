package feature.forum.dao;

import feature.forum.vo.ArtVo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class ArtDao implements ArtDaoImpl {

    private  static DataSource ds =null;
    static {
        try {
            Context ctx =new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/polybrain");

        }catch (NamingException e){
            e.printStackTrace();
        }
    }

    private static final String INSERT_STMT =
            "INSERT INTO article (MEM_NO,ARTICLE_TITLE,ARTICLE_CONTENT,ARTICLE_TIME,ARTICLE_STATE,ITEM_CLASS_NO,upFiles) VALUES (?, ?, ?, NOW(), 1, ?,?)";
    private static final String GET_ALL_STMT =
            "SELECT ARTICLE_NO,MEM_NO,ARTICLE_TITLE,ARTICLE_CONTENT,ARTICLE_TIME,ARTICLE_STATE,ITEM_CLASS_NO,upFiles FROM article order by ARTICLE_NO";
    private static final String GET_ONE_STMT =
            "SELECT ARTICLE_NO,MEM_NO,ARTICLE_TITLE,ARTICLE_CONTENT,ARTICLE_TIME,ARTICLE_STATE,ITEM_CLASS_NO,upFiles FROM article where ARTICLE_NO = ?";
    private static final String GET_ONE_NO =
            "SELECT ARTICLE_NO,MEM_NO,ARTICLE_TITLE,ARTICLE_CONTENT,ARTICLE_TIME,ARTICLE_STATE,ITEM_CLASS_NO,upFiles FROM article where ITEM_CLASS_NO = ?";
    private static final String DELETE =
            "DELETE FROM article where ARTICLE_NO = ?";
    private static final String UPDATE =
            "UPDATE article set MEM_NO=?, ARTICLE_TITLE=?, ARTICLE_CONTENT=?, ARTICLE_STATE=?, ITEM_CLASS_NO=? ,upFiles=? where ARTICLE_NO = ?";

    @Override
    public void insert(ArtVo artVo) {
        Connection con =null;
        PreparedStatement pstmt =null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1,artVo.getMemNo());
            pstmt.setString(2, artVo.getArtTitle());
            pstmt.setString(3, artVo.getArtCon());
            pstmt.setInt(4,artVo.getItemNo());
            pstmt.setBytes(5,artVo.getUpFiles());

            pstmt.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException("A database error occurred. "
                    + e.getMessage());
        }finally {
            if(pstmt !=null){
                try {
                    pstmt.close();
                }catch (SQLException e){
                    e.printStackTrace(System.err);
                }
            }
            if(con !=null){
                try {
                    con.close();
                }catch (Exception e){
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public void update(ArtVo artvo) {
        Connection con =null;
        PreparedStatement pstmt =null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1,artvo.getMemNo());
            pstmt.setString(2,artvo.getArtTitle());
            pstmt.setString(3,artvo.getArtCon());
            pstmt.setByte(4,artvo.getArtState());
            pstmt.setInt(5,artvo.getItemNo());
            pstmt.setBytes(6,artvo.getUpFiles());
            pstmt.setInt(7,artvo.getArtNo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred. "
                    + e.getMessage());
        }finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con !=null){
                try {
                    con.close();
                }catch (Exception e){
                    e.printStackTrace(System.err);
                }

            }
        }
    }

    @Override
    public void delete(Integer artNo) {
        Connection con =null;
        PreparedStatement pstmt =null;

        try {

            con = ds.getConnection();
            pstmt =con.prepareStatement(DELETE);

            pstmt.setInt(1,artNo);

            pstmt.executeUpdate();

        }  catch (SQLException e) {
            throw new RuntimeException("A database error occurred. "
                    + e.getMessage());
        }finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con !=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }


    }

    @Override
    public ArtVo findByPrimaryKey(Integer artNo) {
        ArtVo artVo =null;
        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try {
            con =ds.getConnection();
            pstmt =con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1,artNo);

            rs =pstmt.executeQuery();

            while (rs.next()) {
                artVo = new ArtVo();
                artVo.setArtNo(rs.getInt("ARTICLE_NO"));
                artVo.setMemNo(rs.getInt("MEM_NO"));
                artVo.setArtTitle(rs.getString("ARTICLE_TITLE"));
                artVo.setArtCon(rs.getString("ARTICLE_CONTENT"));
                artVo.setArtTime(rs.getTimestamp("ARTICLE_TIME"));
                artVo.setArtState(rs.getByte("ARTICLE_STATE"));
                artVo.setItemNo(rs.getInt("ITEM_CLASS_NO"));
                artVo.setUpFiles(rs.getBytes("upFiles"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred. "
                    + e.getMessage());
        }finally {
            if(rs !=null){
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace(System.err);
                }
            }
            if (pstmt !=null){
                try {
                    pstmt.close();
                }catch (SQLException e){
                    e.printStackTrace(System.err);
                }
            }
            if (con !=null){
                try {
                    con.close();
                }catch (Exception e){
                    e.printStackTrace(System.err);
                }
            }
        }
        return artVo;
    }

    @Override
    public List<ArtVo> findByItemNo(Integer itemNo) {
        List<ArtVo> list =new ArrayList<ArtVo>();
        ArtVo artVo = null ;

        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try {
            con =ds.getConnection();
            pstmt =con.prepareStatement(GET_ONE_NO);
            pstmt.setInt(1,itemNo );
            rs =pstmt.executeQuery();

            while (rs.next()) {
                artVo = new ArtVo();
                artVo.setArtNo(rs.getInt("ARTICLE_NO"));
                artVo.setMemNo(rs.getInt("MEM_NO"));
                artVo.setArtTitle(rs.getString("ARTICLE_TITLE"));
                artVo.setArtCon(rs.getString("ARTICLE_CONTENT"));
                artVo.setArtTime(rs.getTimestamp("ARTICLE_TIME"));
                artVo.setArtState(rs.getByte("ARTICLE_STATE"));
                artVo.setItemNo(rs.getInt("ITEM_CLASS_NO"));
                list.add(artVo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred. "
                    + e.getMessage());
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
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

        return list;
    }

    @Override
    public List<ArtVo> getAll() {
        List<ArtVo> list =new ArrayList<ArtVo>();
        ArtVo artVo = null ;

        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try {
            con =ds.getConnection();
            pstmt =con.prepareStatement(GET_ALL_STMT);
            rs =pstmt.executeQuery();

            while (rs.next()) {
                artVo = new ArtVo();
                artVo.setArtNo(rs.getInt("ARTICLE_NO"));
                artVo.setMemNo(rs.getInt("MEM_NO"));
                artVo.setArtTitle(rs.getString("ARTICLE_TITLE"));
                artVo.setArtCon(rs.getString("ARTICLE_CONTENT"));
                artVo.setArtTime(rs.getTimestamp("ARTICLE_TIME"));
                artVo.setArtState(rs.getByte("ARTICLE_STATE"));
                artVo.setItemNo(rs.getInt("ITEM_CLASS_NO"));
                list.add(artVo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred. "
                    + e.getMessage());
        }finally {
            if(rs !=null){
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace(System.err);
                }
            }
            if (pstmt !=null){
                try {
                    pstmt.close();
                }catch (SQLException e){
                    e.printStackTrace(System.err);
                }
            }
            if (con !=null){
                try {
                    con.close();
                }catch (Exception e){
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }
}
