package util;

import model.vo.MemberVO;

public class Session {
	private static MemberVO currentMember;

	public static MemberVO getCurrentMember() {
		return currentMember;
	}

	public static void setCurrentMember(MemberVO currentMember) {
		Session.currentMember = currentMember;
	}
	
	
}
