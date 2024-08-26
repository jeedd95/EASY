package model.service;

import model.vo.MemberVO;

public interface MemberService {
	void joinMember(MemberVO member);
	boolean checkIdDuplicate(String mId);
	MemberVO login(MemberVO member);
	int removeMember(String mPw);
	
}
