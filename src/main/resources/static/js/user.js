let index = {
	init: function(){    //init 함수를 만들자 
		$("#btn-save").on("click",()=>{  //id가 btn-save인것이 click이 되면 save함수를 호출하자
			this.save();
		});
	},
	
	save: function(){
		alert("user의 save함수 호출됨");
	}
}

index.init(); //index의 init함수를 호출하겠다는 뜻이다
