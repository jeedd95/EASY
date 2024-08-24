package model.dao;

import java.util.List;
import model.vo.MemberVO;

public interface ManagerDAO {

	/**
	 * 회원정보 전체보기
	 */
	List<MemberVO> searchAllMember();
	
	/**
	 * 회원정보 검색하기
	 */
	MemberVO searchMemeberById(String memberId);
}
