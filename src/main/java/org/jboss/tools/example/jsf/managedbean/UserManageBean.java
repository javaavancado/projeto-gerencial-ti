package org.jboss.tools.example.jsf.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.jboss.tools.example.springmvc.data.MemberDao;
import org.jboss.tools.example.springmvc.model.Member;

@RequestScoped
@ManagedBean(name = "userManageBean")
public class UserManageBean {

	private Member member = new Member();

	@ManagedProperty(name = "memberDao", value = "#{memberDao}")
	private MemberDao memberDao;

	public String salvar() {
		memberDao.register(member);
		member = new Member();
		return "/cadastro/member.jsf";
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
}
