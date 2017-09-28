window.onload=function(){	
	var Obody = document.getElementsByTagName("html");
	var Oheight = window.screen.width;
	var dip = window.devicePixelRatio;
	var reheight = Oheight/dip;
	Obody[0].style.fontSize = reheight/10+"px";
		
}