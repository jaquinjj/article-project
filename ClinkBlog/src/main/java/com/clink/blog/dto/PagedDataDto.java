package com.clink.blog.dto;

public class PagedDataDto {
	public PagedDataDto() {}
	
	public PagedDataDto(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	private int page = 0;
	private int pageSize=5;
	private int totalCount;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
