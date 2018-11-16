<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Upload with Ajax</h1>

<div class='uploadDiv'>
<input type='file' name='uploadFile' multiple>
<button id='uploadBtn'>Upload</button>
</div>

<div class='uploadResult'>
<ul>
</ul>
</div>

<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script> <!-- jquery cdn minified…. 클론용 -->

  
  
<script>
$(document).ready(function(){
	
	//파일 이름 출력
	var uploadResult = $(".uploadResult ul");
	function showUploadedFile(uploadResultArr){
		 var str ="";
		
		$(uploadResultArr).each(function(i,obj){
			str += "<li>" + obj.fileName + "</li>";
		});
		uploadResult.append(str); 
		
		
	}
	
	//파일 형식
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	
	
	
	//초기화
	var cloneObj = $(".uploadDiv").clone();
	
	//등록 버튼
	$("#uploadBtn").on("click",function(e){
		
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']"); //input[name='uploadFile']
		var files = inputFile[0].files;
		
		console.log(files);
		
		
		 //add filedate to form
		for(var i=0; i<files.length; i++){
			
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadFile",files[i]);
			

		  }
		  
		  $.ajax({ //ajax를 통해 foamdata 자체를 전송
			  url:"/uploadAjaxAction",
			  processData: false,
			  contentType: false,
			  data: formData,
			  type:'POST',
			  dataType:'json',
			  success:function(result){
				  console.log("ajax success");
				  console.log(result);
				  showUploadedFile(result);
				  $(".uploadDiv").html(cloneObj.html()); //초기화
			  }
		  });

		
	
		
	});
});
</script>





</body>
</html>