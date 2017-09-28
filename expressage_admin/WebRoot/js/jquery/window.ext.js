/**
 *@param position表示窗口的最终位置,包含两个属性，一个是left，一个是top
 *@param hidefunc表示执行窗口隐藏的方法
 *@param initPos表示窗口初始位置，包含两个属性，一个是left，一个是top
 */
$.fn.mywin = function(position, hidefunc, initPos) {
	if (position && position instanceof Object) {
		var positionleft = position.left;
		var positiontop = position.top;
		
		var left;
		var top;
		var windowobj = $(window);
		var currentwin = this;
		var cwinwidth;
		var cwinheight;

		var browserwidth;
		var browserheight;
		var scrollLeft;
		var scrollTop;
		//计算浏览器当前可视区域的宽和高，以及滚动条左边界，上边界的值
		function getBrowserDim() {
			browserwidth = windowobj.width();
			browserheight = windowobj.height();
			scrollLeft = windowobj.scrollLeft();
			scrollTop = windowobj.scrollTop();	
		}		
		//计算窗口真实的左边界值
		function calLeft(positionleft, scrollLeft, browserwidth, cwinwidth) {
			if (positionleft && typeof positionleft == "string") {
				if (positionleft == "center") {
					left = scrollLeft + (browserwidth - cwinwidth) / 2;	
				} else if (positionleft == "left") {
					left = scrollLeft;	
				} else if (positionleft == "right") {
					left = scrollLeft + browserwidth - cwinwidth;
					if ($.browser.safari) {
						left = left - 15;
					}
					if ($.browser.opera) {
						left = left + 15;
					}
					if ($.browser.msie && $.browser.version.indexOf("8") >= 0) {
						left = left - 20;
					}
				} else  {
					left = scrollLeft + (browserwidth - cwinwidth) / 2;	
				}
			} else if (positionleft && typeof positionleft == "number") {
				left = positionleft;
			} else {
				left = 0;
			}
		}
		//计算窗口真实的上边界值		
		function calTop(positiontop, scrollTop, browserheight, cwinheight) {
			if (positiontop && typeof positiontop == "string") {
				if (positiontop == "center") {
					top = scrollTop + (browserheight - cwinheight) / 2;
				} else if (positiontop == "top") {
					top = scrollTop;
				} else if (positiontop == "bottom") {
					top = scrollTop + browserheight - cwinheight;
					if ($.browser.opera) {
						top = top - 25;
					}
				} else {
					top = scrollTop + (browserheight - cwinheight) / 2;
				}
			} else if (positiontop && typeof positiontop == "number") {
				top = positiontop;
			} else {
				top = 0;
			}
		}
		//移动窗口的位置
		function moveWin() {
			calLeft(currentwin.data("positionleft"), scrollLeft, browserwidth, cwinwidth);
			calTop(currentwin.data("positiontop"), scrollTop, browserheight, cwinheight);
			currentwin.animate({
				left: left,
				top: top
			},600);	
		}
		
		//定义关闭按钮的动作
		currentwin.children(".title").children("img").click(function() {
			if (!hidefunc) {
				currentwin.hide("slow")	;
			} else {
				hidefunc();
			}
		});

		if (initPos && initPos instanceof Object) {
			var initLeft = initPos.left;
			var initTop = initPos.top;
			if (initLeft && typeof initLeft == "number") {
				currentwin.css("left", initLeft);	
			} else {
				currentwin.css("left", 0);
			}
			if (initTop && typeof initTop == "number") {
				currentwin.css("top", initTop);	
			} else {
				currentwin.css("top", 0);
			}
			currentwin.show();
		}
		cwinwidth = currentwin.outerWidth(true);
		cwinheight = currentwin.outerHeight(true);
		currentwin.data("positionleft", positionleft);
		currentwin.data("positiontop", positiontop);
		getBrowserDim();
		moveWin();

		var scrollTimeout;
		//浏览器滚动条滚动时，移动窗口的位置
		$(window).scroll(function(){
			//判断一下当前窗口是否可见
			if (!currentwin.is(":visible")) {
				return;	
			}
			clearTimeout(scrollTimeout);
			scrollTimeout = setTimeout(function(){
				getBrowserDim();		
				moveWin();
			},300);
		});
		//浏览器大小改变时，移动窗口的位置
		$(window).resize(function(){
			//判断一下当前窗口是否可见
			if (!currentwin.is(":visible")) {
				return;	
			}
			getBrowserDim();	
			moveWin();	
		});
		//返回当前对象，以便可以级联的执行其他方法
		return currentwin;
	}
}
