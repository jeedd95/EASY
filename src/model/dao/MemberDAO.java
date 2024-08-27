package model.dao;

import model.vo.MemberVO;

public interface MemberDAO {
	/**
	 * 회원가입
	 * @return 
	 */
	int joinMember(MemberVO member);
	
	/**
	 * 아이디 중복체크
	 */
	boolean checkIdDuplicate(String id);
	
	/**
	 * 로그인
	 */
	MemberVO login(MemberVO member);
	
	/**
	 * 회원탈퇴
	 */
	int removeMember(String pw);

	boolean checkNickNameDuplicate(String nickName);
	
}
