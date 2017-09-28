window.onload = function(){
	
	var Obody = document.getElementsByTagName("html");
	var Oheight = window.screen.width;
	var dip = window.devicePixelRatio;
	var O1 = document.getElementsByClassName("1");
	var Oarr = document.getElementById("arrow1");
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
		
		
		Obody[0].style.fontSize = Oheight/9+"px";
		
}
	if (browser.versions.android) {
		
		Obody[0].style.fontSize = reheight/10+"px";
		O1[0].style.paddingTop = 1.5+"%";
		O1[1].style.paddingTop = 1.5+"%";
		Oarr.style.marginLeft = 5 + "%";
}
       
    }else{
      
       
       Obody[0].style.fontSize = Oheight/40+"px";
    }
	}catch(e){}	
}
function pro_xiala(obj){
	var a = document.getElementsByClassName("answer");
	var ab = document.getElementsByClassName("pro_pic");
	var b = document.getElementsByClassName("problem-kuai");
	var osrc1 = "arrow-up.png";
	var osrc2 = "arrow-down.png";
	
	for(var i=0;i<b.length;i++){b[i].id = i;}
	for(var j=0;j<a.length;j++){a[j].id = j+"a";}
	for(var z=0;z<a.length;z++){
		var abb = ab[z].src.split("/");
		if(obj.id== z){
		$("#"+a[z].id).slideToggle("slow");
		if(abb[abb.length-1] == osrc1){
			ab[z].src = "img/" + osrc2;
		}else{
			ab[z].src = "img/" + osrc1;
		}
	}
	}	
}

var flag = true;
function show(){
	
	var Oimg = document.getElementById("img1");
	if(flag){
		Oimg.src = "img/agreecheckbox.png";	
		document.getElementById("isXz").innerHTML = 1;
	}
	else{
		Oimg.src = "img/checkbox.png";
		document.getElementById("isXz").innerHTML = 2;
	}
			flag = !flag;
}

Array.prototype.indexOf = function(val) {
			for (var i = 0; i < this.length; i++) {
			if (this[i] == val) return i;
			}
			return -1;
			};
Array.prototype.remove = function(val) {
			var index = this.indexOf(val);
			if (index > -1) {
			this.splice(index, 1);
			}
	};

function changp3(obj){
	var Odiv = document.getElementsByClassName("person-radio");	
	
	var Odel = document.getElementById("delete");
	
	var Oimg;
	var arr;
	var array;
	var Arr = new Array();	
	var Osrc1 = "ccb.png";
	var Osrc2 = "ccbx.png";	
	for(var i=0;i<Odiv.length;i++){
		Oimg = Odiv[i].getElementsByTagName("img")[0];
		Oimg.id = i + "a" ;			
		arr = Oimg.src.split("/");	
		
		if(obj.id == Oimg.id ){
			
			if(arr[arr.length-1] == Osrc1){
				Oimg.src = "/expressage_api/express/img/" + Osrc2;
				
			

				
				
			}else{
				Oimg.src = "/expressage_api/express/img/" + Osrc1; 
				
				
				
				
			}
		}
		Arr.push(Oimg.src.split("/")[Oimg.src.split("/").length-1]);
	}		
	
	for(var j=0;j<Arr.length;j++){
		
			if(Arr[j] == Osrc2){				
				Odel.disabled = false;				
				Odel.style.backgroundColor = "orange";
				return ;
			}else{
				Odel.disabled = true;
				Odel.style.backgroundColor = "lightgray";
			}
				
			}				
}
function deletedatas(){	
	var Oimg = document.getElementsByClassName("p1");
	var Opid = document.getElementsByClassName("person-id");
	var Pid = document.getElementById("person-id");
	var Arr = new Array();
	var Arr2 = new Array();
	
	for(var i=0;i<Oimg.length;i++){
		Arr.push(Oimg[i].src.split("/")[Oimg[i].src.split("/").length-1]);
	}
	for(var j=0;j<Arr.length;j++){
		if(Arr[j] == "ccbx.png"){
			Pid.innerHTML = Opid[j].innerHTML;
			Arr2.push(Pid.innerHTML);
		}		
		
	}
	Pid.innerHTML = Arr2;
	
}




function sure(){
	var Odiqu = document.getElementById("diqu");
	Odiqu.value = document.getElementById("shengyu").innerHTML+" "+document.getElementById("shiyu").innerHTML+" "+document.getElementById("quyu").innerHTML;
	document.getElementsByClassName("quyu")[0].style.display = "none";
	document.getElementById("heimu").style.display = "none";
	
}
