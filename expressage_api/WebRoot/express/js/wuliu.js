$(document).ready(function(){
	var Oimg = document.getElementsByClassName("wuliu-icon");
	var Oline2 = document.getElementsByClassName("line-wuliu2");
	var Oimgx = document.getElementById("img1x");
	
	var Ohe;
	
	for(var i=1;i<Oimg.length-1;i++){
		
		Ohe = Oimg[i+1].offsetTop-Oimg[i].offsetTop;
		Oline2[i].style.height = Ohe+"px";
	}
	Ohe = Oimg[1].offsetTop-Oimg[0].offsetTop;
	Oline2[0].style.height = Ohe + Oimgx.offsetHeight*2 +"px";
});
	

