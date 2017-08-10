package org.jboss.tools.example.springmvc.services;

import org.jboss.tools.example.springmvc.data.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MermberService{
	
	@SuppressWarnings("unused")
	@Autowired
	private MemberDao memberDao;

}
