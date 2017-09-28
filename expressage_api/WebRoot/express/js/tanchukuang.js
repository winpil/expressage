
function allcolor(){
	var Okds = document.getElementsByClassName("kd-leibie");
	for(var i=0;i<3;i++){
		Okds[i].style.color = "lightgrey";
		Okds[i].style.border = "0.06rem solid lightgray"
	}
}
function yhq_fou(obj){
	var Otck = document.getElementById("tck");
	var Oyhq_text = document.getElementById("yhq-text");
	
	if(obj.id=="fou"){
	Otck.style.display = "none";
	
	document.getElementById("heimu").style.display = "none";
	Oyhq_text.innerHTML = "不使用优惠券";
	document.getElementById("arrow4").style.display = "none";
	}
	if(obj.id=="cancel"){
		document.getElementById("qujian-time").style.display = "none";
		document.getElementById("heimu").style.display = "none";
	}
	if(obj.id=="cancel2"){
		document.getElementById("heimu").style.display = "none";
		document.getElementById("tck_jj").style.display = "none";
	}
}
function yhq_yes(){
	sbmWeixinOrder(5);
	//window.location.href="youhuiquan.html";
}

function sure(){
	
	var Otime = document.getElementById("qujian-sj2");
	
	if(Oqujian==null){
		
		Oqujian = "马上";
	}
	Otime.innerHTML = Oqujian;
	
	document.getElementById("qujian-time").style.display = "none";
	document.getElementById("heimu").style.display = "none";
	
}
function sure2(){
	
	var Otime = document.getElementById("qujian-sj");
	
	if(Oqujian==null){
		
		Oqujian = "马上";
	}
	Otime.innerHTML = Oqujian;
	
	document.getElementById("qujian-time").style.display = "none";
	document.getElementById("heimu").style.display = "none";
	
}
function yhq_use(){
	document.documentElement.scrollTop = document.body.scrollTop =0;
	var Oheimu = document.getElementById("heimu");
	var Otck = document.getElementById("tck");
	Oheimu.style.display = "block";
	Otck.style.display = "block";
}
function yhq_use2(){
	document.documentElement.scrollTop = document.body.scrollTop =0;
	var Oheimu = document.getElementById("heimu");
	var Otck = document.getElementById("tck");
	Oheimu.style.display = "block";
	Otck.style.display = "block";
}
function qujian_time(){
	
	document.documentElement.scrollTop = document.body.scrollTop =0;
	var Oheimu = document.getElementById("heimu");
	var Otck = document.getElementById("qujian-time");
	Oheimu.style.display = "block";
	Otck.style.display = "block";
	
}
function yhq(obj){
	
	var Omoney = document.getElementsByClassName("yhq-qian");
	var Oyhq_text = document.getElementById("yhq-text");
	var Oh;
	var Oqian;	
	var mtext;
		for(var i=0;i<Omoney.length;i++){			
			Oh = Omoney[i].innerHTML;
			if((i+1)==obj.id){				
				Oqian = Oh.substr(0,1);
				mtext = "使用"+Oqian+"元优惠券";
				var newtext = window.open("dingdanxq.html",mtext);								
			}		
		}
}
var Oqujian =null;
function change(obj){
	
	var Okd_com2 =document.getElementsByClassName("kd-company2");
	var Otime = document.getElementById("Otime");
		for(var i=0;i<Okd_com2.length;i++){
			
			Okd_com2[i].style.color = "#000000";
			
			if(i==obj.id){
				
				Okd_com2[i].style.color = "orange";		
				Oqujian = Okd_com2[i].innerHTML;
				Otime.innerText = Oqujian;
				
			}			
		}		
	}

function kd_leibie(obj){ 
	
	var Okd_leibie = document.getElementsByClassName("kd-leibie");
	var Ot = document.getElementById("kd-leibie");
	for(var i=0;i<Okd_leibie.length;i++){
		
		Okd_leibie[i].style.color = "lightgrey";
		Okd_leibie[i].style.border = "0.06rem solid lightgray"
		
		if((i+1)==obj.id){
			
			Okd_leibie[i].style.color = "orange";
			Okd_leibie[i].style.border = "0.06rem solid orange";
			Ot.innerText = Okd_leibie[i].innerText;
			
		}
	}
}
var flags = true;
var flag = true;
function changep(){
	
	var Opic = document.getElementById("pic");	
	var Odbtn = document.getElementById("dxxd");
	
	if(flags){		
		
		Opic.src = "/expressage_api/express/img/agreecheckbox.png";
		
		document.getElementById("dxxd").style.backgroundColor = "orange";
		Odbtn.disabled = false;
	}
	else{	
		Opic.src = "/expressage_api/express/img/checkbox.png";		
		Odbtn.disabled = true;
		Odbtn.style.backgroundColor = "darkgray";
		
	}
		flags = !flags;		
}

function changep2(){
	
	var Opic = document.getElementById("pic");	
	var Okbtn = document.getElementById("kkxd");
	
	if(flag){		
		Opic.src = "img/agreecheckbox.png";
		document.getElementById("kkxd").style.backgroundColor = "orange";
		Okbtn.disabled = false;
	}
	else{	
		Opic.src = "img/checkbox.png";		
		Okbtn.disabled = true;
		Okbtn.style.backgroundColor = "darkgray";
		
	}
		flag = !flag;		
}
function choice(){
	
	var Okd_gs = document.getElementById("kd-gs");


	Okd_gs.style.display = "block";
}

function change2(obj){
	
	var Okd_com =document.getElementsByClassName("kd-company");
	var Oky_gs = document.getElementById("ky-gs");
	var a=["debang","ems","huitong","shentong","shunfeng","yuantong","yunda","zhongtong","zjs","aae","axd","aj","xindan","bfdf","bgpyghx","cxwl","chengguang","coe","ctwl","cszx","cs","chuanzhi","dhl","dpex","dsf","dtwl","dywl","ds","fedex","fedexcn","fkd","fbwl","feibao","feihu",
	"gsdwl","guotong","gznd","tdhy","tiantian","henglu","huiqiang","hxlwl","hswl","jldt","jywl","jiaji","jiayunmei","jingguang","jinyue","kuaijie","klwl","longbang","lianhaotong","lejiedi","lijisong","minbang",
	"minhang","meiguo","ocs","pinganda","quanfeng","quanyi","quanchen","quanritong","rufengda","suer","haihong","santai","shenghui","shengfeng","shengan","saiaodi","tnt","thtx","tcwl","ups","yousu",
	"weibang","weitepai","wxwl","xfwl","xinbang","yibang","yuntong","yzjc","yuanfeihang","yafeng","ycwl","yuefeng","zhongtie","zhongyou","zmkm","zzjh","ztwy","jingdong"];
	for(var j=0;j<Okd_com.length;j++){
		Okd_com[j].id = j;
	}
		for(var i=0;i<Okd_com.length;i++){
						
			if(i==obj.id){
												
				Oky_gs.value = obj.innerHTML;
				cancel();
				document.getElementById("kd-gs-id").innerHTML = a[i];
			}			
		}	
	
	}
function cancel(){
	
	var Okd_gs = document.getElementById("kd-gs");
		
	Okd_gs.style.display = "none";

}

function cancel_jj(){
	
	var Ow1 = document.getElementById("w1");
	var Ow3 = document.getElementById("w3");
	var Otck_jj = document.getElementById("tck_jj");
	var Otck_qx = document.getElementById("tck_qx");
	if(Ow1.style.display == "block"){
		
		document.getElementById("heimu").style.display = "block";
		Otck_jj.style.display = "block";
	}
	if(Ow3.style.display == "block"){
		document.getElementById("heimu").style.display = "block";
		Otck_qx.style.display = "block";
		if(Otck_qx.style.display == "block"){
			document.getElementById("heimu").onclick = function(){
				Otck_qx.style.display = "none";
				document.getElementById("heimu").style.display = "none";			
			}
		}
	}
}
function yhq_yes2(){
	
	document.getElementById("heimu").style.display = "none";
	document.getElementById("tck_jj").style.display = "none";
	document.getElementById("w1").style.display = "none";
	document.getElementById("w2").style.display = "block";
}
function editor(){
	
	window.location.href = "kkxd.html";
}
function searchOrder(){
	
	document.getElementById("guodu").style.display = "block";
	
	setTimeout("yanchi()",3000);
	
}
function yanchi(){
	document.getElementById("wuliu").style.display = "block";
	var Oimg = document.getElementsByClassName("wuliu-icon");
	var Oline2 = document.getElementsByClassName("line-wuliu2");
	var Oimgx = document.getElementById("img1x");
	
	var Ohe;
	
	for(var i=1;i<Oimg.length-1;i++){
		
		Ohe = Oimg[i+1].offsetTop-Oimg[i].offsetTop - Oimgx.offsetHeight;
		Oline2[i].style.height = Ohe+"px";
	}
	Ohe = Oimg[1].offsetTop-Oimg[0].offsetTop;
	Oline2[0].style.height = Ohe - Oimgx.offsetHeight +"px";
	document.getElementById("guodu").style.display = "none";
}
function searchOrder2(){
	
	window.location.href = "wuliu.html";
}
function kdy_pj(){
	
	document.getElementById("heimu").style.display = "block";
	document.getElementById("tck-pj").style.display = "block";
}
var Onu ;
function pj(obj){
	
	for(var i=0;i<3;i++){
		document.getElementById(i).src = "img/ccb.png";
		if(obj.id == i){				
		document.getElementById(obj.id).src = "img/ccbx.png";
		Onu = i ;
		}
		
	}
	
}
function qd_pj(){
	var Osp3 = document.getElementsByClassName("sp3");
	var Opjxs = document.getElementById("pj-xs");
	var i = Onu;	
	Opjxs.innerHTML = Osp3[i].innerHTML;	
	document.getElementById("heimu").style.display = "none";
	document.getElementById("tck-pj").style.display = "none";
}
