package model.service;

import exception.DuplicationIdOrNickNameException;
import model.dao.MemberDAO;
import model.dao.MemberDAOImpl;
import model.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private static MemberService service;
	private static MemberDAO memberDAO = MemberDAOImpl.getInstance();
	
	public static MemberService getInstance() {
		if(service==null)
			return service = new MemberServiceImpl();
		return service;
	}
	
    public MemberServiceImpl() {
    	
    }

    @Override
    public void joinMember(MemberVO member) throws Exception {
        int result =memberDAO.joinMember(member);
        if(result==0)
        	throw new Exception("회원가입이 되지 않았습니다");
    }
    public boolean checkPw(String pw) {
        
        if (pw.length() < 5 || pw.length() > 20) {
            throw new IllegalArgumentException("비밀번호는 5자 이상 20자 이하로 설정해야 합니다.");
        }
        
        return false;
    }


    @Override
    public boolean checkNickNameDuplicate(String nickName) {
        boolean isDuplicate = memberDAO.checkNickNameDuplicate(nickName);
        if(isDuplicate)
        	throw new DuplicationIdOrNickNameException("이미 존재하는 NickName입니다.");
        return isDuplicate;
    }

    @Override
    public boolean checkIdDuplicate(String id) {
    	 boolean isDuplicate = memberDAO.checkIdDuplicate(id);
        if(isDuplicate)
        	throw new DuplicationIdOrNickNameException("이미 존재하는 ID입니다.");
        return isDuplicate;
    }
    
    @Override
    public MemberVO login(MemberVO member) {
        // 입력값 검증
        if (member == null || member.getMId() == null || member.getMPw() == null) {
            throw new IllegalArgumentException("id와 password는 null일 수 없습니다.");
        }

        // DAO를 통해 사용자 정보 조회
        MemberVO storedMember = memberDAO.login(member);

        // 조회된 사용자 정보가 없으면 로그인 실패
        if (storedMember == null) {
            throw new IllegalArgumentException("아이디와 비밀번호가 일치하지 않습니다.");
        }

        return storedMember;
    }

    /**
     * 회원 탈퇴 메소드
     * @param mId 탈퇴할 회원의 ID
     * @param pw 탈퇴할 회원의 비밀번호
     * @return 탈퇴 성공 여부를 나타내는 정수 값 (1: 성공, 0: 실패)
     */
    public int removeMember(String mId, String pw) {
        // 입력값 검증
        if (mId == null || mId.isEmpty() || pw == null || pw.isEmpty()) {
            throw new IllegalArgumentException("ID와 비밀번호는 null이거나 빈 문자열일 수 없습니다.");
        }

        // 아이디와 비밀번호로 사용자 정보 조회
        MemberVO member = memberDAO.login(new MemberVO(mId, pw));

        // 조회된 사용자가 없거나 비밀번호가 일치하지 않는 경우
        if (member == null || !member.getMPw().equals(pw)) {
            return 0; // 로그인 실패로 탈퇴 실패
        }

        // 비밀번호로 회원 정보 삭제
        int result = memberDAO.removeMember(pw);

        // 삭제 결과 반환
        return result;
    }

	@Override
	public int removeMember(String pw) {
		// TODO Auto-generated method stub
		return 0;
	}


}
        
   
