package model.vo;

import java.util.Date;

public class MemberVO {
    private int mNo;          // 회원 번호
    private String mId;       // 회원 ID
    private String mPw;       // 비밀번호
    private String mName;     // 이름
    private String mNickname; // 닉네임
    private String mGender;   // 성별
    private Date joinDate;    // 가입일

    
    public MemberVO() {}


    public MemberVO(String mId, String mPw) {
        this.mId = mId;
        this.mPw = mPw;
 
    }
    
    public MemberVO(int mNo, String mId, String mPw, String mName, String mNickname, String mGender, Date joinDate) {
        this(mId,mPw);
    	this.mNo = mNo;
        this.mName = mName;
        this.mNickname = mNickname;
        this.mGender = mGender;
        this.joinDate = joinDate;
    }

    
    public int getMNo() {
        return mNo;
    }

    public void setMNo(int mNo) {
        this.mNo = mNo;
    }

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getMPw() {
        return mPw;
    }

    public void setMPw(String mPw) {
        this.mPw = mPw;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMNickname() {
        return mNickname;
    }

    public void setMNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public String getMGender() {
        return mGender;
    }

    public void setMGender(String mGender) {
        this.mGender = mGender;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    
    
}