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


    public static MemberVO login(MemberVO member) {
    	try {
    		MemberVO storedMember = memberService.login(member);  // 비즈니스 로직은 서비스에서 처리
    		SuccessView.printMember(storedMember);
    		return storedMember;
    	}catch(Exception e){
    		FailView.printMessage(e.getMessage());
    	}
		return null;
      
    }
    
    public static void removeMember(MemberVO member) {
        try {
        	memberService.removeMember(member);  // 비즈니스 로직은 서비스에서 처리
        	SuccessView.printmessage("삭제되었습니다");
        }catch(Exception e){
        	FailView.printMessage(e.toString());
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
    