package model.service;

import model.vo.MemberVO;

public interface MemberService {
	/**
	 * 회원 가입 기능
	 * @throws Exception 
	 */
    void joinMember(MemberVO member) throws Exception;
    /**
     * 아이디 중복 체크 기능
     */
    
    boolean checkIdDuplicate(String id);
    
    /**
     * 로그인 기능
     */
    
    MemberVO login(MemberVO member);
    /**
     * 회원탈퇴 기능
     */
    
    int removeMember(String pw);
    
	boolean checkNickNameDuplicate(String nickName);
	
	boolean checkPw(String pw); 
}