/**
 * Created by Administrator on 2017/4/21 0021.
 */

/*��ά���л�*/

$(
    function () {

        var erObj=$(".kd-con-container-r-wm img");

        $(".kd-con-container-r-type p:eq(0)").on("click", function () {
                 erObj.attr("src","img/erwx.jpg");
        });
        $(".kd-con-container-r-type p:eq(1)").on("click", function () {
            erObj.attr("src","img/appery.jpg");
        });
        $(".kd-con-container-r-type p:eq(2)").on("click", function () {
            erObj.attr("src","img/iosery.png");
        });

         $(".kd-footer").parent().parent().removeAttr('style');

    }
)

/*����������*/
$(function(){

    $(".kd-con-six-container-list-f").hover(function(e){



                  $(this).find('p').fadeIn();

    },function(){

        $(this).find('p').fadeOut();
        e.stop();

    });
    $(".kd-con-six-container-list-r").hover(function(e){



        $(this).find('p').fadeIn();

    },function(){

        $(this).find('p').fadeOut();
        e.stop();

    })

})