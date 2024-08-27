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
            SuccessView.printmessage("회원 가입 되었습니다");        
        } catch (Exception e) {  // 중복 ID 또는 닉네임 예외 처리
        	FailView.printMessage(e.toString());
        
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
    	boolean idDuplicate = true;
    	try { 
        	idDuplicate = memberService.checkIdDuplicate(id);  // 비즈니스 로직은 서비스에서 처리
        	return idDuplicate;
        }catch(Exception e){
        	FailView.printMessage(e.getMessage());
        }
    	return idDuplicate;
    }
    public static boolean checkNickNameDuplicate(String nickName) {
        boolean idDuplicate = true;
    	try { 
        	idDuplicate = memberService.checkNickNameDuplicate(nickName);  // 비즈니스 로직은 서비스에서 처리
        	return idDuplicate;
        }catch(Exception e){
        	FailView.printMessage(e.getMessage());
        }
    	return idDuplicate;
    }
    
    public static boolean checkPw(String pw) {
        boolean idDuplicate = true;
    	try { 
        	idDuplicate = memberService.checkPw(pw);  // 비즈니스 로직은 서비스에서 처리
        	return idDuplicate;
        }catch(Exception e){
        	FailView.printMessage(e.getMessage());
        }
    	return idDuplicate;
    }
    

}
    