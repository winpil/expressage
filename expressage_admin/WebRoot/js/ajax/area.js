
	function getXmlHttpObject(){
			
			var xmlHttpRequest;
			if(window.ActiveXObject){
				xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
			}else{
				xmlHttpRequest=new XMLHttpRequest();
			}
			return xmlHttpRequest;
	}

	var myXmlHttpRequest="";

	function changeProvincial(provincialId){

		myXmlHttpRequest=getXmlHttpObject();

		if(myXmlHttpRequest){
			
			var url="areaQueryAction!cityInfo.action";
			var data="provincialId="+provincialId.value;

			myXmlHttpRequest.open("post",url,true);

			myXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

			myXmlHttpRequest.onreadystatechange=deal;

			myXmlHttpRequest.send(data);
		}
	}

	function deal(){
		if(myXmlHttpRequest.readyState==4){
			
			if(myXmlHttpRequest.status==200){
				
				var cityId=myXmlHttpRequest.responseXML.getElementsByTagName("cityId");
				var cityName=myXmlHttpRequest.responseXML.getElementsByTagName("cityName");
				
				$('city').length=0;
				var myOption=document.createElement("option");
					
				myOption.innerText="--城市--";
				$('city').appendChild(myOption);

				for(var i=0;i<cityId.length;i++){
					var city_id=cityId[i].childNodes[0].nodeValue;
					var city_name=cityName[i].childNodes[0].nodeValue;
					var myOption=document.createElement("option");
					myOption.value=city_id;
					myOption.innerText=city_name;
					$('city').appendChild(myOption);
				}
				
				$('district').length=0;
				var myOption2=document.createElement("option");
					
				myOption2.innerText="--区域--";
				$('district').appendChild(myOption2);
				
			}
		}
	}
	
	function changeCity(cityId){

		myXmlHttpRequest=getXmlHttpObject();

		if(myXmlHttpRequest){
			
			var url="areaQueryAction!districtInfo.action";
			var data="cityId="+cityId.value;

			myXmlHttpRequest.open("post",url,true);

			myXmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

			myXmlHttpRequest.onreadystatechange=deal2;

			myXmlHttpRequest.send(data);
		}
		
	}
	
	function deal2(){
		if(myXmlHttpRequest.readyState==4){
			
			if(myXmlHttpRequest.status==200){
				
				var districtId=myXmlHttpRequest.responseXML.getElementsByTagName("districtId");
				var districtName=myXmlHttpRequest.responseXML.getElementsByTagName("districtName");
				
				$('district').length=0;
				var myOption=document.createElement("option");
					
				myOption.innerText="--区域--";
				$('district').appendChild(myOption);

				for(var i=0;i<districtId.length;i++){
					var district_id=districtId[i].childNodes[0].nodeValue;
					var district_name=districtName[i].childNodes[0].nodeValue;
					var myOption=document.createElement("option");
					myOption.value=district_id;
					myOption.innerText=district_name;
					$('district').appendChild(myOption);
				}
			}
		}
	}

	function $(id){
		return document.getElementById(id);
	}
