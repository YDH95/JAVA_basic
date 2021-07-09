package com.awesome.domain;

/*
 * 게시판 페이징을 처리하는 PageMaker 클래스
 * 
 * PageMaker 객체를 사용하려면 setCri() 와 setTotalCount()를 먼저 호출해서 값을 셋팅해야 한다. 
 * 페이징 버튼들의 값을 구하려면 제일 먼저 총 게시글 수가 있어야 위의 계산식대로 차례로 구할 수 있다. 
 * 그렇기에 총 게시글을 셋팅할 때 계산식 메소드를 호출하게 한 것이다. 
 * 또한 Criteria 객체에서 필요한 page 와 perPageNum을 사용하기 위해서 setCri()를 먼저 셋팅해야 한다. 
 * 컨트롤러에서 객체로 값을 셋팅할 때 유의하자.
 * 
 */

public class PageMaker {
	
	
	private Criteria cri;
	
    private int totalCount;				// 총 게시글 수
    private int startPage;				// 시작 페이지번호
    private int endPage;				// 끝 페이지번호
    private boolean prev;				// 이전버튼 생성여부
    private boolean next;				// 다음버튼 생성여부
    private int displayPageNum = 5;		// 화면 하단에 보여지는 페이지 버튼의 수


    public Criteria getCri() {
        return cri;
    }
    public void setCri(Criteria cri) {
        this.cri = cri;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        
        // 총 게시글 수를 셋팅할때 calcData() 메서드를 호출하여 페이징 관련 버튼 계산을 한다.
        calcData();
    }
    
    //페이징의 버튼들을 생성하는 계산식. 끝 페이지 번호, 시작 페이지 번호, 이전, 다음 버튼들을 구한다.
    private void calcData() {
        
    	// cri.getPage() : 현재 페이지번호
    	//끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 갯수) * 화면에 보여질 페이지 번호의 갯수
    	// 현재 페이지 번호		페이지 번호의 갯수	계산식					끝 페이지 번호
    	//	1				10				Math.ceil(1/10)*10		10
    	//	3				10				Math.ceil(3/10)*10		10
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        
        //시작 페이지 번호 = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 갯수) + 1
        // 끝페이지번호		페이지 번호의 갯수		계산식		시작페이지 번호
        //	10			10					(10-10)+1	1
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;

        //cri.getPerPageNum() : 한 페이지당 보여줄 게시글의 갯수
        //마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 갯수
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
 
        //이전 버튼 생성 여부 = 시작 페이지 번호 == 1 ? false : true	
        // 시작페이지 번호가 1이 아니면 생기면 된다
        prev = startPage == 1 ? false : true;
        //다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 갯수 < 총 게시글의 수 ? true: false
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
        
    }
    
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", getCri()="
				+ getCri() + ", getTotalCount()=" + getTotalCount() + ", getStartPage()=" + getStartPage()
				+ ", getEndPage()=" + getEndPage() + ", isPrev()=" + isPrev() + ", isNext()=" + isNext()
				+ ", getDisplayPageNum()=" + getDisplayPageNum() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
