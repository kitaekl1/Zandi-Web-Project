<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mainpage</title>
<link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/Nav.css' />">
</head>
<body>
    <%@ include file="Navigator.jsp"%>
    
    <!-- jumbotron -->
    <%!
    String tagline = "ZANDI에 오신 것을 환영합니다.";%>
    
    <section class="hero is-primary">
        <div class="hero-body">
            <div class="container">
                <h1 class="title">
                </h1>
            </div>
        </div>
    </section>
    <div class="content_area">
        <div class="slide_div_wrap">
            <div class="slide_div">
                
                <div>
                    <a>
                        <img src="<c:url value='/resources/img/1.jfif' />" alt="Description">
                    </a>
                </div>
                <div>
                    <a>
                        <img src="<c:url value='/resources/img/2.jfif' />" alt="Description">
                    </a>
                </div>
                <div>
                    <a>
                        <img src="<c:url value='/resources/img/3.jfif' />" alt="Description">
                    </a>
                </div>                
            </div>  
        </div>
        <div class="ls_wrap">
				<div class="ls_div_subject">
					평점순 상품
				</div>	
				<div class="ls_div">
					<c:forEach items="${ls}" var="ls">
						<a href="/projDetail/${ls.projId}">
							<div class="ls_div_content_wrap">
								<div class="ls_div_content">
									<div class="image_wrap" data-bookid="${ls.imageList[0].bookId}" data-path="${ls.imageList[0].uploadPath}" data-uuid="${ls.imageList[0].uuid}" data-filename="${ls.imageList[0].fileName}">
										<img>
									</div>				
									<div class="ls_category">
										${ls.cateName}
									</div>
									<div class="ls_rating">
										${ls.ratingAvg}
									</div>
									<div class="ls_bookName">
										${ls.bookName}
									</div>							
								</div>
							</div>
						</a>					
					</c:forEach>					
				</div>
			</div>
        
    </div>
    
    <!-- main -->
    <main role="main">
        <div class="container mt-5">
            <div class="content has-text-centered">
                <h3 class="title is-3">
                    <%=tagline%>
                </h3>
                <%
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                    String formattedNow = now.format(formatter);
                %>
                <p>
                    현재 접속 시각
                    <%=formattedNow%></p>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp"%>
        
    <!-- jQuery 및 기타 종속성 라이브러리를 먼저 로드 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

    <script>
        $(document).ready(function(){
            $(".slide_div").slick({
                dots: true,
                infinite: true,
                speed: 500,
                slidesToShow: 1,
                slidesToScroll: 1,
                autoplay: true,
                autoplaySpeed: 4000,
                prevArrow: '<button type="button" class="slick-prev"></button>',
                nextArrow: '<button type="button" class="slick-next"></button>'
            });
            $(".ls_div").slick({
        		
    		});
            //이미지 삽입
    		$(".image_wrap").each(function(i, obj){
    			
    			const bobj = $(obj);
    			
    			if(bobj.data("projid")){
    				const uploadPath = bobj.data("path");
    				const uuid = bobj.data("uuid");
    				const fileName = bobj.data("filename");
    				
    				const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
    				
    				$(this).find("img").attr('src', '/display?fileName=' + fileCallPath);
    			} else {
    				$(this).find("img").attr('src', '/resources/img/goodsNoImage.png');
    			}
    			
    		});
        });
      
    </script>

    <!-- Bulma JS dependencies (optional, for full Bulma functionality) -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
</body>
</html>