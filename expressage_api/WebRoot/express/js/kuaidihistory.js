var flag = true;
function show(){
	
	var Od = document.getElementById("nff");
	if(flag){
		Od.style.display="block";
		
	}
	else{
		Od.style.display="none";
		
	}
			flag = !flag;
		}
window.onload = function(){
	var Obody = document.getElementsByTagName("html");
	var Oheight = window.screen.width;
	var dip = window.devicePixelRatio;
	var reheight = Oheight/dip;
	try{
    if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
          
	var browser = {
	versions: function () {
	var u = navigator.userAgent, app = navigator.appVersion;
	return { //移动终端浏览器版本信息
	trident: u.indexOf('Trident') > -1, //IE内核
	presto: u.indexOf('Presto') > -1, //opera内核
	webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
	gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
	mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
	ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
	android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
	iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
	iPad: u.indexOf('iPad') > -1, //是否iPad
};
}(),
}
	if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
		
		
		Obody[0].style.fontSize = Oheight/10+"px";
		
}
	if (browser.versions.android) {
		
		Obody[0].style.fontSize = reheight/10+"px";
}
       
    }else{
        
       
       Obody[0].style.fontSize = Oheight/40+"px";
    }
	}catch(e){}
	
	var Ohsj = document.getElementById("history-sj");
	var Ohfj1 = document.getElementById("history-fj1");
	var Ohfj1t = document.getElementById("history-fj1-text");
	var Ohfj2 = document.getElementById("history-fj2");
	var Ozsj = document.getElementById("history-zsj");
	var Ozfj = document.getElementById("history-zfj");
	var all =document.getElementsByClassName("history-fj");
	Ozfj.onclick = function(){
		Ozfj.style.color = "white";
		Ozfj.style.backgroundColor = "orange";
		Ozsj.style.color = "gray";
		Ozsj.style.backgroundColor = "whitesmoke";
		Ohfj1.style.display = "block";
		Ohsj.style.display = "none";
	}
	Ozsj.onclick = function(){
		
		Ohsj.style.display = "block";
		
		Ozsj.style.color = "white";
		Ozsj.style.backgroundColor = "orange";
		Ozfj.style.color = "gray";
		Ozfj.style.backgroundColor = "whitesmoke";
		Ohfj1.style.display = "none";
		Ohfj2.style.display = "none";
	}
	all[0].onclick = function(){
		all[0].style.color = "orange";
		all[1].style.color ="gray";
		Ohfj1t.style.display = "block";
		Ohfj2.style.display = "none";
	}
	all[1].onclick = function(){
		
		all[0].style.color = "gray";
		all[1].style.color ="orange";
		Ohfj1t.style.display = "none";
		Ohfj2.style.display = "block";
	}


}
function history_jsjr(obj){
	var a = document.getElementsByClassName("his-jsjr");
	var Oa= document.getElementsByTagName("img");
	var b = document.getElementsByClassName("history-xdan");
	for(var i=0;i<a.length;i++){
		b[i].id = i;
		a[i].id= i+"a";
		Oa[i].id= i+"oa";
	}
	var osrc1 = "arrow-up.png";
	var osrc2 = "arrow-down.png";
		
	for(var z=0;z<a.length;z++){
		var oarr  = Oa[z].src.split("/");
		if(obj.id== z){
			
		$("#"+a[z].id).slideToggle("fast");
		if(oarr[oarr.length-1]==osrc1){
				Oa[z].src = "img/" + osrc2;
			}else{
				Oa[z].src = "img/" + osrc1;
			}
	}
	}
	
}
