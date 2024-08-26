package model.service;

import model.dao.MemberDAO;
import model.dao.MemberDAOImpl;
import model.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;
	
	public MemberServiceImpl() {
		this.memberDAO = new MemberDAOImpl();
	}

	@Override
	public void joinMember(MemberVO member) {
		

	}

	@Override
	public boolean checkIdDuplicate(String mId) {
		// TODO Auto-generated method stub
		return memberDAO.checkIdDuplicate(mId);
	}

	@Override
	public MemberVO login(MemberVO member) {
		// TODO Auto-generated method stub
		return memberDAO.login(member);
	}

	@Override
	public int removeMember(String mPw) {
		// TODO Auto-generated method stub
		return memberDAO.removeMember(mPw);
	}

}
