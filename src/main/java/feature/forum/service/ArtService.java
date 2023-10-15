package feature.forum.service;

import feature.forum.dao.ArtDao;
import feature.forum.dao.ArtDaoImpl;
import feature.forum.vo.ArtVo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ArtService {
    private ArtDaoImpl dao;

    public ArtService(){
        dao = new ArtDao();
    }

    public ArtVo addArt(Integer memNo, String artTitle, String artCon, Integer itemNo,byte[] upFiles){

        ArtVo artVo =new ArtVo();

        artVo.setMemNo(memNo);
        artVo.setArtTitle(artTitle);
        artVo.setArtCon(artCon);
        artVo.setItemNo(itemNo);
        artVo.setUpFiles(upFiles);
        dao.insert(artVo);

        return artVo;
    }
    public ArtVo updateArt(Integer artNo, Integer memNo, String artTitle, String artCon, Timestamp artTime, Byte artState, Integer itemNo, byte[] upFiles){

        ArtVo artVo =new ArtVo();

        artVo.setArtNo(artNo);
        artVo.setMemNo(memNo);
        artVo.setArtTitle(artTitle);
        artVo.setArtCon(artCon);
        artVo.setArtTime(artTime);
        artVo.setArtState(artState);
        artVo.setItemNo(itemNo);
        artVo.setUpFiles(upFiles);
        dao.update(artVo);

        return dao.findByPrimaryKey(artNo);
    }
    public void updateArt(ArtVo artVo) {
        dao.update(artVo);
    }

    public void deleteArt(Integer artNo) {
        dao.delete(artNo);
    }

    public ArtVo getOneArt(Integer artNo) {
        return dao.findByPrimaryKey(artNo);
    }

    public List<ArtVo> getAll() {

        return dao.getAll();
    }

    public List<ArtVo> findByItemNo(Integer itemNo) {
        return dao.findByItemNo(itemNo);
    }





}
