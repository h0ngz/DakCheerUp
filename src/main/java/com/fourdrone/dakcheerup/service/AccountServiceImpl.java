package com.fourdrone.dakcheerup.service;

import com.fourdrone.dakcheerup.domain.member.Member;
import com.fourdrone.dakcheerup.mapper.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by h0ngz on 2015. 8. 17..
 */
@Repository
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Member getMember(String memberId) {
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        return accountMapper.getMember(memberId);
    }

    @Override
    public void addMember(Member member) {
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.addMember(member);

    }

    @Override
    public void modMember(Member member) {
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.modMember(member);

    }

    @Override
    public void delMember(String memberId) {
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.delMember(memberId);

    }

    @Override
    public ArrayList<Member> getMemberList() {
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        ArrayList<Member> result = accountMapper.getMemberList();
        return result;
    }

    @Override
    public Member getMemberForLogin(Member member) {
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        return accountMapper.getMemberForLogin(member);
    }

	@Override
	public void modMemberSignOut(Member member) {
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.modMemberSignOut(member);
		
	}

	@Override
	public void modPhoto(Member member) {
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.modPhoto(member);
		
	}
}
