package com.awesome.domain;

/**
 * 페이징 및 검색을 위한 VO 클래스


< 페이징 처리를 함에 있어서 원칙들이 있다.> 

페이징 처리는 반드시 GET 방식만을 이용해야 한다.
게시글 목록 페이지의 하단에 페이지들의 번호를 보여주고 원하는 번호를 선택하면 해당 페이지로 이동해서 목록을 보여줘야 한다.
페이징은 반드시 필요한 페이지 번호만 출력해야 한다. 
만약 페이지당 10개의 게시글을 출력하는데 전체 게시글의 수가 42개라면 페이지의 번호는 5페이지까지여야 한다.
이전과 다음 버튼이 존재해야 한다. 
게시글이 1000개인데 1페이지당 10개의 게시글을 볼 수 있게 했다면 페이징 버튼은 100개가 필요하다. 
그런데 이전과 다음 버튼이 존재하지 않는다면 100개의 버튼을 한번에 보여줘야 한다. 
그렇기때문에 하단 페이지에 보여줄 버튼의 갯수를 정하고 더 많은 데이터가 있다면 이전과 다음 버튼으로 표시해줘야 한다.
게시글을 조회하거나 수정, 삭제를 하고 난 뒤, 다시 원래의 목록 페이지로 이동해야 한다. 
예를 들면 게시판에 5페이지에 있는 어떠한 게시물을 조회하거나 수정, 삭제를 했다고 한다면, '목록으로'버튼을 이용해 목록으로 돌아갈 땐 5페이지로 이동해야 한다.

<SQL 쿼리 예시> 
SELECT *
FROM 테이블명
ORDER BY 게시글 번호 DESC
LIMIT 시작 행, 출력할 갯수

SELECT ...
FROM 테이블명
ORDER BY 게시글 번호 DESC
LIMIT #{pageStart}, #{perPageNum}
* pageStart : 특정 페이지의 첫번째 게시글의 행이다.
* perPageNum : 한 페이지당 보여줄 게시글의 개수이다


< 게시판 페이징을 처리를 위해 PageMaker 클래스 사용 >

 */
public class Criteria {

	private int page;		   // 현재 페이지 번호
    private int perPageNum;	   // 한 페이지당 게시글 갯수
    
    private int rscode;			// 식당코드
    private String search;		// 검색어
    private String sort;		// 정렬조건
    private int rvcode;			// 리뷰코드
    
    public int getPageStart() {	// 특정 페이지의 게시글 시작번호, 게시글 시작 행 번호

//    	현재페이지번호	페이지당 게시글 수		계산식		게시글 시작 행 번호
//    	 5			10					(5-1)*10	40
//    	현재 페이지의 게시글 시작번호 = (현재페이지번호 - 1) * 페이지당 보여줄 게시글 갯수    	
        return (this.page-1) * perPageNum;
    }
    
    //기본생성자
    public Criteria() {
    	//최초로 게시판 목록에 들어 왔을 때를 위해서 기본 셋팅을 해야 한다. 
    	//왜냐하면 페이징을 처리하기 위해선 현재 페이지 번호와 페이지당 게시글 수가 필요한데 처음 게시판에 들어오게 되면 두 개의 정보를 가져올 방법이 없기 때문에 
    	//기본 생성자를 통해 기본 값을 셋팅하도록 하자. 
    	//현재 페이지를 1페이지로, 페이지당 보여줄 게시글의 수를 10개로 기본 셋팅해두었다.
        this.page = 1;
        this.perPageNum = 10;
    }
    
    public int getPage() {
        return page;
    }
    //페이지가 음수값이 되지 않게 설정. 음수가 되면 1페이지를 나타낸다.
    public void setPage(int page) {
        if(page <= 0) {	// 음수시 1페이지로 처리
            this.page = 1;
        } else {	
            this.page = page;
        }
    }
    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
// public void setPerPageNum(int pageCount) {
	    //페이지당 보여줄 게시글 수가 변하지 않게 설정했다.
//    	int cnt = this.perPageNum;
//        if(pageCount != cnt) {
//            this.perPageNum = cnt;
//        } else {
//            this.perPageNum = pageCount;
//        }
    }

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getRscode() {
		return rscode;
	}

	public void setRscode(int rscode) {
		this.rscode = rscode;
	}
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getRvcode() {
		return rvcode;
	}

	public void setRvcode(int rvcode) {
		this.rvcode = rvcode;
	}


	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rscode=" + rscode + ", search=" + search
				+ ", sort=" + sort + ", getPageStart()=" + getPageStart() + ", getPage()=" + getPage()
				+ ", getPerPageNum()=" + getPerPageNum() + ", getSearch()=" + getSearch() + ", getRscode()="
				+ getRscode() + ", getSort()=" + getSort() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
