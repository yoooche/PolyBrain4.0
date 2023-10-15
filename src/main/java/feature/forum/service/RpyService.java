package feature.forum.service;


import feature.forum.dao.RpyDao;
import feature.forum.dao.RpyDaoImpl;
import feature.forum.vo.ArtVo;
import feature.forum.vo.RpyVo;

import java.util.Date;
import java.util.List;

public class RpyService {
    private RpyDaoImpl dao;

    public RpyService(){
        dao = new RpyDao();
    }

    public RpyVo addRpy(Integer memNo, Integer artNo, String rpyCon){

        RpyVo rpyVo =new RpyVo();

        rpyVo.setMemNo(memNo);
        rpyVo.setArtNo(artNo);
        rpyVo.setRpyCon(rpyCon);

        dao.insert(rpyVo);

        return rpyVo;
    }
    public RpyVo updateRpy(Integer rpyNo,Integer memNo, Integer artNo, String rpyCon){

        RpyVo rpyVo =new RpyVo();

        rpyVo.setRpyNo(rpyNo);
        rpyVo.setMemNo(memNo);
        rpyVo.setArtNo(artNo);
        rpyVo.setRpyCon(rpyCon);

        dao.update(rpyVo);

        return dao.findByPrimaryKey(rpyNo);
    }
    public void updateRpy(RpyVo rpyVo) {
        dao.update(rpyVo);
    }

    public void deleteRpy(Integer rpyNo) {

        dao.delete(rpyNo);
    }

    public RpyVo getOneRpy(Integer rpyNo) {
        return dao.findByPrimaryKey(rpyNo);
    }

    public List<RpyVo> getAll() {

        return dao.getAll();
    }

    public List<RpyVo> findByArt(Integer artNo) {
        return dao.findByArt(artNo);
    }
}
