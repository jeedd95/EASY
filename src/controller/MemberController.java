package controller;

import model.service.MemberService;
import model.service.MemberServiceImpl;
import model.vo.MemberVO;

public class MemberController {
 private  MemberService memberService;
 
 public MemberController() {
	 this.memberService = new MemberServiceImpl();
 }
 
 
 public void joinMember(MemberVO member) {
	 boolean isSuccess	=memberService.joinMember(member);
	 if(isSuccess) {
		 return 1;
	 }else {
		 return 0;
	 }
 }
}
