package controller;

import exception.DuplicationIdOrNickNameException;
import model.service.MemberService;
import model.service.MemberServiceImpl;
import model.vo.MemberVO;
import view.FailView;
import view.SuccessView;

public class MemberController {
    private static MemberService memberService = MemberServiceImpl.getInstance();

    public static void joinMember(MemberVO member) {
        try {
            memberService.joinMember(member);  // 비즈니스 로직은 서비스에서 처리
            System.out.println("회원 가입이 완료되었습니다.");
        } catch (DuplicationIdOrNickNameException e) {  // 중복 ID 또는 닉네임 예외 처리
            System.out.println("이미 존재하는 ID 또는 닉네임입니다. 다른 ID를 선택해 주세요.");
        } catch (IllegalArgumentException e) {  // 기타 예외 처리
            System.out.println("회원 가입 실패: " + e.getMessage());
           
        }
        
    }


    public static int login(MemberVO member) {
    	try {
    		MemberVO storedMember = memberService.login(member);  // 비즈니스 로직은 서비스에서 처리
    		SuccessView.printMember(storedMember);
    		return 1;
    	}catch(Exception e){
    		FailView.printMessage(e.getMessage());
    	}
		return 0;
      
    }
    
    public static void removeMember(String pw) {
        int result = memberService.removeMember(pw);  // 비즈니스 로직은 서비스에서 처리
        if (result > 0) {
            System.out.println("회원 탈퇴가 완료되었습니다.");
        } else {
            System.out.println("회원 탈퇴 실패. 비밀번호를 확인하세요.");
        }
    }

    public static boolean checkIdDuplicate(String id) {
        return memberService.checkIdDuplicate(id);  // 비즈니스 로직은 서비스에서 처리
    }
}
    