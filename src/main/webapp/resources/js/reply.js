console.log("Reply Module...");

var replyService= (function(){
	
	//댓글 추가
	function add(reply, callback, error){
		console.log("add reply...");
		
		$.ajax({
			type : 'POST',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error :  function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}//add
	
	//댓글 목록
	function getList(param, callback, error){
		console.log("list reply...");
	
		var bno=param.bno;
		var page = param.page ||1;
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
				function(data){
			if(callback){
				//callback(data);
				callback(data.replyCnt, data.list); //댓글 숫자와 목록을 가져오는 경우
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}//getList
	
	
	//댓글 삭제, 갱신
	function remove(rno, callback, error){
		
		console.log("여기 와야 해...");
		$.ajax({

			
			type : 'Delete',
			url : '/replies/' + rno,
			success :  function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}//remove
	
	//댓글 수정
	function update(reply, callback, error){
		console.log("RNO: " + reply.rno);
		$.ajax({
			type : 'PUT',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er);
				}
			}
			
		});
		
	}//update
	
	//댓글 조회
	function get(rno, callback, error){
		$.get("/replies/" + rno + ".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}//get
	
	//날짜
	function displayTime(timeValue){
		var today = new Date();
		
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		var str="";
		
		if(gap < (1000 * 60 * 60 *24)){
			
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [(hh>9 ?'' : '0')+hh, ':', (mi > 9 ?'': '0')+mi, ':', (ss>9?'':'0') + ss ].join('');
		}
	}; //display
	
	return {
		add:add,
		getList:getList,
		remove:remove,
		update:update,
		get:get,
		displayTime:displayTime
	};
})();