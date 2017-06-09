/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.tools.example.springmvc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.jboss.tools.example.springmvc.data.MemberDao;
import org.jboss.tools.example.springmvc.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MemberDaoTest {
	@Autowired
	private MemberDao memberDao;

	@Test
	public void testFindById() {

		Member member = new Member();
		member.setEmail("jane.doe@mailinator.com");
		member.setName("Jane Doe");
		member.setPhoneNumber("2125552121");

		member = memberDao.merge(member);

		member = memberDao.findById(member.getId());

		assertEquals("Jane Doe", member.getName());
		assertEquals("jane.doe@mailinator.com", member.getEmail());
		assertEquals("2125552121", member.getPhoneNumber());
	}

	@Test
	public void testFindByEmail() {
		Member member = new Member();
		member.setEmail("john.smith@mailinator.com");
		member.setName("John Smith");
		member.setPhoneNumber("2125551212");

		member = memberDao.merge(member);
		
		 member = memberDao.findByEmail("john.smith@mailinator.com");

		assertEquals("John Smith", member.getName());
		assertEquals("john.smith@mailinator.com", member.getEmail());
		assertEquals("2125551212", member.getPhoneNumber());
		return;
	}

	@Test
	public void testRegister() {
		Member member = new Member();
		member.setEmail("jane.doe@mailinator.com");
		member.setName("Jane Doe");
		member.setPhoneNumber("2125552121");

		memberDao.persistir(member);
		Long id = member.getId();
		assertNotNull(id);

		Member newMember = memberDao.findById(id);

		assertEquals("Jane Doe", newMember.getName());
		assertEquals("jane.doe@mailinator.com", newMember.getEmail());
		assertEquals("2125552121", newMember.getPhoneNumber());
	}

	@Test
	public void testFindAllOrderedByName() {
		Member member = new Member();
		member.setEmail("jane.doe@mailinator.com");
		member.setName("Jane Doe");
		member.setPhoneNumber("2125552121");
		memberDao.persistir(member);

		List<Member> members = memberDao.findAllOrderedByName();

		if (members.size() <= 0) {
			fail("O método findAllOrderedByName não carregou resultdos");
		}

	}
}
