package controller;

import model.vo.MemberVO;
import exception.DuplicationIdOrNickNameException;
import model.service.MemberService;

public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void joinMember(MemberVO member) {
        try {
            memberService.joinMember(member);  // 비즈니스 로직은 서비스에서 처리
            System.out.println("회원 가입이 완료되었습니다.");
        } catch (DuplicationIdOrNickNameException e) {  // 중복 ID 또는 닉네임 예외 처리
            System.out.println("이미 존재하는 ID 또는 닉네임입니다. 다른 ID를 선택해 주세요.");
        } catch (IllegalArgumentException e) {  // 기타 예외 처리
            System.out.println("회원 가입 실패: " + e.getMessage());
        }
    }


    public void login(String id, String pw) {
        MemberVO member = new MemberVO();
        member.setMId(id);
        member.setMPw(pw);

        MemberVO result = memberService.login(member);  // 비즈니스 로직은 서비스에서 처리
        if (result != null) {
            System.out.println(result.getMName() + "님, 환영합니다!");
        } else {
            System.out.println("로그인 실패. ID 또는 비밀번호를 확인하세요.");
        }
    }
    
    public void removeMember(String pw) {
        int result = memberService.removeMember(pw);  // 비즈니스 로직은 서비스에서 처리
        if (result > 0) {
            System.out.println("회원 탈퇴가 완료되었습니다.");
        } else {
            System.out.println("회원 탈퇴 실패. 비밀번호를 확인하세요.");
        }
    }

    public boolean checkIdDuplicate(String id) {
        return memberService.checkIdDuplicate(id);  // 비즈니스 로직은 서비스에서 처리
    }
}
    