let index = {
	init: function(){    //init 함수를 만들자 
		$("#btn-save").on("click",()=>{  //id가 btn-save인것이 click이 되면 save함수를 호출하자
			this.save();
		});
	},
	
	save: function(){
		//alert("user의 save함수 호출됨");
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		//console.log(data);
		
		$.ajax({
			type:"POST",
			url:"/blog/api/user",
			data:JSON.stringify(data), //data는 자바스크립트로 적혀있어서 ajax가 이해를 못한다 그러므로 json으로 변형시켜서 ajax에 보내준다 보내는방법은 JSON.stringify()함수를 사용해준다
		 	contentType:"application/json; charset=utf-8",  //내가보낸타입을 알려주는 코드이다 json형태로 utf-8로 보내겠다는 뜻이다
			dataType:"json" //요청을 서버로해서 응답이왔을때 json으로 달라는 뜻이다 만약json형태로오면 javascript오브젝트로변경해준다 버전이 바뀌면서 알아서해준다 안적어도됨
		}).done(function(resp){  //ajax가 호출에 성공하면 function()을 실행한다는뜻이다 resp에는 data가 들어간다
			alert("회원가입이 완료되었습니다");
			//console.log(resp)   //resp는 /blog/api/user에서 실행하는함수가 return하는 값이다
			location.href = "/blog";
		}).fail(function(error){  //ajax가 호출에 실패하면 function()을 실행한다는뜻이다
			alert(JSON.stringify(error));
		});  //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
	}
};

index.init(); //index의 init함수를 호출하겠다는 뜻이다
