$(document).ready(function(){
	var Okds = document.getElementsByClassName("kd-leibie");
	var Oqu = document.getElementById("qujian-sj");
	var Otime = document.getElementById("Otime").innerHTML;
	var Okdt = document.getElementById("kd-leibie").innerHTML;
	
	if(Otime!=null&&Otime.length!=0){
		
		if(Otime==0){Oqu.innerHTML = "上午";}
		if(Otime==1){Oqu.innerHTML = "马上";}
		if(Otime==2){Oqu.innerHTML = "下午";}
	}
	if(Okdt!=null&&Okdt.length!=0){
		if(Okdt==1){
			allcolor();
			Okds[0].style.border = "0.06rem solid orange";
			Okds[0].style.color = "orange";
		}
		if(Okdt==2){
			allcolor();
			Okds[1].style.border = "0.06rem solid orange";
			Okds[1].style.color = "orange";
		}
		if(Okdt==3){
			allcolor();
			Okds[2].style.border = "0.06rem solid orange";
			Okds[2].style.color = "orange";
		}
	}
});