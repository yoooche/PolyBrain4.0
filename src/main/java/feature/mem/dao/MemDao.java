package feature.mem.dao;


import feature.mem.vo.MemVo;


import java.util.List;

public interface MemDao {
    public List<MemVo> selectAll();
    MemVo selectByMemName(String memName);
    void createMember(MemVo member);
    void createMemberJDBC(MemVo member);
    MemVo getMemberById(Integer memNo);
    void updateMember(MemVo member);
    void deleteMember(Integer memNo);
    List<MemVo> getAllMembers();
    MemVo selectByMemEmailAndPwd(String memEmail, String memPwd);
    MemVo selectById(Integer memNo);



    public List<MemVo> getAll();

    public MemVo findByPrimaryKey(Integer memNo);

    public void delete(Integer memNo);

    public void updateJDBC(MemVo memNo);

    public List<MemVo> GETEmailPwd();

    // 检查是否存在具有相同电子邮件的会员
    boolean isEmailExists(String email);

    // 检查是否存在具有相同电子邮件的会员
    boolean isPidExists(String pid);

    // 检查是否存在具有相同电子邮件的会员
    boolean isPhoneExists(String phone);


    public void updateInformation(MemVo memNo);

    public MemVo getMemberByMemNo(Integer memNo);

}
