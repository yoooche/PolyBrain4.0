package feature.bid.service;

import feature.bid.dao.*;
import feature.bid.dto.BidItemDto;
import feature.bid.dto.BidItemListDto;
import feature.bid.vo.BidEventVo;
import feature.bid.vo.BidItemPicVo;
import feature.bid.vo.BidItemVo;
import feature.bid.vo.BidOrderVo;
import feature.mail.service.MailService;
import feature.mem.dao.MemDao;
import feature.mem.dao.MemDaoImpl;
import feature.mem.vo.MemVo;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class BiddingServiceImpl implements BiddingService{
    private final BidItemDao bidItemDao;
    private final BidEventDao bidEventDao;
    private final MemDao memDao;
    private final BidOrderDao bidOrderDao;
    private final BidOrderVo bidOrderVo;
    private final BidItemPicDao bidItemPicDao;
    private final BidOrderMail bidOrderMail;
    Jedis jedis = new Jedis("localhost", 6379);
    public BiddingServiceImpl(){
        bidItemDao = new BidItemDaoImpl();
        bidEventDao = new BidEventDaoImpl();
        memDao = new MemDaoImpl();
        bidOrderDao = new BidOrderDaoImpl();
        bidOrderVo = new BidOrderVo();
        bidItemPicDao = new BidItemPicDaoImpl();
        bidOrderMail = new BidOrderMail();
    }
    @Override
    public List<BidItemVo> viewAll() {
        System.out.println("viewAll service");
        return bidItemDao.selectAll();
    }
    @Override
    public BidItemVo addAnItem(BidItemVo bidItemVo) {
        final int resultCount = bidItemDao.insert(bidItemVo);;
        if(resultCount < 1){
            bidItemVo.setMessage("新增失敗，請聯絡管理員!");
            bidItemVo.setSuccess(false);
        }else {
            bidItemVo.setMessage("新增成功");
            bidItemVo.setSuccess(true);
        }
        return bidItemVo;
    }

    @Override
    public BidItemVo getOneItem(Integer bidItemNo) {
        return bidItemDao.selectById(bidItemNo);
    }

    @Override
    public boolean removeOneItem(Integer bidItemNo) {
        System.out.println("刪除成功 (商品)");
        return bidItemDao.deleteById(bidItemNo) > 0;
    }

    @Override
    public List<String> viewAllName() {
        List<BidItemVo> all = bidItemDao.selectAll();
        List<String> allName = new ArrayList<>();
        for(BidItemVo bidItemVo : all){
            allName.add(bidItemVo.getBidItemName());
        }
        return allName;
    }

    @Override
    public void addPics(BidItemPicVo bidItemPicVo) {
        bidItemPicDao.insert(bidItemPicVo);
    }

    @Override
    public List<BidItemListDto> getHomePageList() {
        List<BidItemListDto> bidItemListDtoList = new ArrayList<>();
        List<BidEventVo> bidEventVoList = bidEventDao.selectAll();
        List<BidItemVo> bidItemVoList = bidItemDao.selectAll();
        List<BidItemPicVo> bidItemPicVoList = bidItemPicDao.selectAllPics();

        for (BidEventVo bidEventVo : bidEventVoList) {
            BidItemListDto dto = new BidItemListDto();
            dto.setBidEventNo(bidEventVo.getBidEventNo());
            dto.setFloorPrice(bidEventVo.getFloorPrice());
            dto.setDirectivePrice(bidEventVo.getDirectivePrice());
            dto.setStartTime(bidEventVo.getStartTime());
            dto.setCloseTime(bidEventVo.getCloseTime());

            for (BidItemVo bidItemVo : bidItemVoList) {
                if (bidItemVo.getBidItemNo().intValue() == bidEventVo.getBidItemNo().intValue()) {
                    dto.setBidItemVo(bidItemVo);

                    for (BidItemPicVo bidItemPicVo : bidItemPicVoList) {
                        if (bidItemVo.getBidItemNo().intValue() == bidItemPicVo.getBidItemNo().intValue()) {
                            List<byte[]> str =  bidItemPicDao.selectPicsById(bidItemVo.getBidItemNo());
                            for(byte[] str1 : str){
                                List<String> base64 = new ArrayList<>();
                                String base64Img = Base64.getEncoder().encodeToString(str1);
                                base64.add(base64Img);
                                dto.setBidItemPic(base64);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            bidItemListDtoList.add(dto);
        }

        return bidItemListDtoList;
    }

    @Override
    public List<BidItemDto> getTableData() {
        List<BidItemDto> itemDto = new ArrayList<>();
        List<BidItemVo> bidItemVoList = bidItemDao.selectAll();
        List<BidItemPicVo> bidItemPicVoList = bidItemPicDao.selectAllPics();
        for(BidItemVo bidItemVo : bidItemVoList) {
            BidItemDto dto = new BidItemDto();
            dto.setBidItemVo(bidItemVo);
            for (BidItemPicVo bidItemPicVo : bidItemPicVoList) {
                if (bidItemVo.getBidItemNo().intValue() == bidItemPicVo.getBidItemNo().intValue()) {
                    List<byte[]> str = bidItemPicDao.selectPicsById(bidItemVo.getBidItemNo());
                    List<String> base64 = new ArrayList<>();

                    for (byte[] str1 : str) {
                        String base64Img = Base64.getEncoder().encodeToString(str1);
                        base64.add(base64Img);
                    }
                    dto.setBidItemPic(base64);
                    break;
                }
            }
            itemDto.add(dto);
        }
        return itemDto;
    }
    @Override
    public List<String> selectAllPicsB64() {
       List<BidItemPicVo> bidItemPics =  bidItemPicDao.selectAllPics();
        List<String> imgB64 = new ArrayList<>();
        bidItemPics.forEach(bidItemPicVo -> {
          byte[] imgs =  bidItemPicVo.getBidItemPic();
          String img64 = Base64.getEncoder().encodeToString(imgs);
          imgB64.add(img64);
       });
       return imgB64;
    }

    @Override
    public BidItemVo edit(BidItemVo bidItemVo) {
        final int resultCount = bidItemDao.update(bidItemVo);
        bidItemVo.setSuccess(resultCount > 0);
        bidItemVo.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
        return bidItemVo;
    }

    @Override
    public List<BidEventVo> viewAllEvent() {
        return bidEventDao.selectAll();
    }
    @Override
    public void addAnEvent(BidEventVo bidEventVo) {
        System.out.println("有到addAnEvent");
        bidEventDao.insert(bidEventVo);
    }

    @Override
    public BidEventVo getEventByNo(Integer bidEventNo) {
        return bidEventDao.selectById(bidEventNo);
    }

    @Override
    public Map<String, String> getStartTimeByNo(Integer bidEventNo) {
        BidEventVo bidEventVo = bidEventDao.selectById(bidEventNo);
        Timestamp startTimeOrigin = bidEventVo.getStartTime();
        Timestamp closeTimeOrigin = bidEventVo.getCloseTime();

        SimpleDateFormat timerTrans = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = timerTrans.format(startTimeOrigin);
        String closeTime = timerTrans.format(closeTimeOrigin);

        Map<String, String> timer = new HashMap<>();
        timer.put("startTime", startTime);
        timer.put("closeTime", closeTime);
        return timer;
    }

    @Override
    public void removeEventById(Integer bidEventNo) {
        bidEventDao.deleteById(bidEventNo);
    }

    @Override
    public void createOneOrder(Integer bidEventNo) {
        Set<Tuple> highestRecord =  jedis.zrevrangeWithScores(String.valueOf(bidEventNo), 0, 0);
        Stream<Tuple> tupleStream = highestRecord.stream();
        tupleStream.forEach(tuple -> {
            String member = tuple.getElement();
            int score = (int)tuple.getScore();

            MemVo memVo = memDao.selectByMemName(member);
            bidOrderVo.setBidEventNo(bidEventNo); // 競標活動編號
            bidOrderVo.setMemNo(memVo.getMemNo()); // 競標參與者會員編號
            bidOrderVo.setFinalPrice(score); // 結標價
            bidOrderVo.setBidItemNo(bidEventDao.selectItemNoByEveNo(bidEventNo)); // 競標商品編號

            BidOrderVo b = bidOrderDao.insert(bidOrderVo);
            System.out.println(b);
            System.out.println(b.getBidOrderNo());
            bidOrderMail.sendBidOrderMail(b.getBidOrderNo());

        });
    }

    @Override
    public BidOrderVo orderWithoutBid(Integer bidEventNo, Integer memNo) {
        BidEventVo bidEventVo = bidEventDao.selectById(bidEventNo);

        bidOrderVo.setBidEventNo(bidEventNo);
        bidOrderVo.setBidItemNo(bidEventVo.getBidItemNo());
        bidOrderVo.setFinalPrice(bidEventVo.getDirectivePrice());
        bidOrderVo.setMemNo(memNo);

        BidOrderVo b = bidOrderDao.insert(bidOrderVo);
        System.out.println("訂單成立");

        return bidOrderVo;
    }


    @Override
    public List<byte[]> getItemPicsByEveNo(Integer bidEventNo) {
        Integer bidItemNo = bidEventDao.selectItemNoByEveNo(bidEventNo);
        return bidItemPicDao.selectPicsById(bidItemNo);
    }

}
