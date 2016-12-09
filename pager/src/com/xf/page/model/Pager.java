package com.xf.page.model;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1380898667849848066L;
	
	private int pageSize;
	
	private int currentPage;
	
	private int totalRecord;//һ����������¼
	
	private int totalPage;
	
	private List<T> dataList;
	//���ݴ������ʵ����Pager���󣬼�����Ӧ����Ϣ�Լ����dataList
	public Pager(int pageNum, int pageSize, List<T> sourceList){
		if(sourceList == null) return;
		this.totalRecord = sourceList.size();
		this.pageSize = pageSize;
		this.totalPage = this.totalRecord / this.pageSize;
		//������+1
		if(this.totalRecord % this.pageSize != 0) this.totalPage +=1;
		//��ǰ�ڼ�ҳ,������ҳ����ֵΪ��ҳ����С��1��ֵΪ1
		if(this.totalPage < pageNum){
			this.currentPage = this.totalPage;
		}else{
			this.currentPage = pageNum;
		}
		if(this.currentPage < 1) this.currentPage =1;
		int fromIndex = this.pageSize * (this.currentPage -1);
		int toIndex = this.pageSize * this.currentPage > this.totalRecord? this.totalRecord: this.pageSize * this.currentPage;
		this.dataList = sourceList.subList(fromIndex, toIndex);
	}

	public Pager() {
		super();
	}

	public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
