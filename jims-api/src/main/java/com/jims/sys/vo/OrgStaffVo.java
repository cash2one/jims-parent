package com.jims.sys.vo;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.OrgDeptPropertyDict;

/**
 * Created by yangruidong on 2016/5/6 0006.
 */
public class OrgStaffVo extends DataEntity<OrgStaffVo> {
    private static final long serialVersionUID = 1L;
    //private String id ;    //persionInfo的id
    private String name; // 姓名
    private String sex;// 性别
    private String nation; // 民族
    private String cardNo; // 身份证号
    private String phoneNum; // 联系电话
    private String email; // 邮箱
    private String nickName; // 昵称
    private String title;    //职称
    private String password;  //密码
    private String deptId;      //所属科室ID
    private String orgId;      //组织机构Id
    private String selectCardNo;   //查询身份证号的字段
    private String role[];      //角色id
    private String roleName;    //角色名称
    private String staffId;    //人员id
    private String inputCode;

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public OrgStaffVo() {
    }

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    public String getSelectCardNo() {
        return selectCardNo;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/
    public void setSelectCardNo(String selectCardNo) {
        this.selectCardNo = selectCardNo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
