package com.xf.page.service;

import com.xf.page.model.Pager;
import com.xf.page.model.Student;

public interface StudentService {

/**
 * @author XF
 *	���ݲ�ѯ��������ѯѧ����ҳ��Ϣ
 */	
	public Pager<Student> findStudent(Student searchMode, int pageNum, int pageSize);
}
