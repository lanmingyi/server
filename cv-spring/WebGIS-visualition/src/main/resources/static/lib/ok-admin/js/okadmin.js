// /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
layui.use(['element', 'layer', 'okUtils', 'okTab'], function () {
  var element = layui.element,
    okUtils = layui.okUtils,
    $ = layui.jquery,
    layer = layui.layer,
    okTab = layui.okTab({
      url: "/static/lib/ok-admin/data/navs.json",
      openTabNum: 30, //允许同时选项卡的个数
      parseData:function (data) {//如果返回的结果和navs.json中的数据结构一致可省略这个方法
        return data;
      }
    });
  
  okTab.render(function () {
    //左侧导航渲染完成之后的操作

  });//渲染左侧导航
  
  // 添加新窗口
  $("body").on("click", "#navBar .layui-nav-item a,#userInfo a", function () {
    //如果不存在子级
    if ($(this).siblings().length == 0) {
      okTab.tabAdd($(this));
    }
    $(this).parent("li").siblings().removeClass("layui-nav-itemed");//关闭其他的二级标签
  });
  
  /**
   * 左边菜单显隐功能
   * @type {boolean}
   */
  $(".ok-menu").click(function () {
    $(".layui-layout-admin").toggleClass("ok-left-hide");
    $(this).find('i').toggleClass("ok-menu-hide");
  });
  
  //移动端的处理事件Start
  $("body").on("click", ".layui-layout-admin .ok-left a[data-url],.ok-make", function () {
    if ($(".layui-layout-admin").hasClass("ok-left-hide")) {
      $(".layui-layout-admin").removeClass("ok-left-hide");
      $(".ok-menu").find('i').removeClass("ok-menu-hide");
    }
  });
  //移动端的处理事件End
  
  //tab左右移动
  $("body").on("click", ".okNavMove", function () {
    var moveId = $(this).attr("data-id");
    var that = this;
    okTab.navMove(moveId, that);
    // console.log(width);
  });
  
  //刷新当前tab页
  $("body").on("click", ".ok-refresh", function () {
    okTab.refresh(this);
  });
  
  //关闭tab页
  $("body").on("click", "#tabAction a", function () {
    var num = $(this).attr('data-num');
    okTab.tabClose(num);
  });
  
  //全屏/退出全屏
  $("body").on("keydown", function (event) {
    event = event || window.event || arguments.callee.caller.arguments[0];
    if (event && event.keyCode == 27) { // 按 Esc
      console.log("Esc");
      $("#fullScreen").children("i").eq(0).removeClass("okicon-screen-restore");
    }
    if (event && event.keyCode == 122) { // 按 F11
      $("#fullScreen").children("i").eq(0).addClass("okicon-screen-restore");
    }
  });
  
  $("body").on("click", "#fullScreen", function () {
    if ($(this).children("i").hasClass("okicon-screen-restore")) {
      screenFun(2).then(function(){
        $(this).children("i").eq(0).removeClass("okicon-screen-restore");
      });
    } else {
      screenFun(1).then(function(){
        $(this).children("i").eq(0).addClass("okicon-screen-restore");
      });
    }
  });
  
  /**
   * 全屏和退出全屏的方法
   * @param num
   * num为1代表全屏
   * num为2代表退出全屏
   */
  function screenFun(num) {
    num = num || 1;
    num = num * 1;
    var docElm = document.documentElement;
    
    switch (num) {
      case 1:
        if (docElm.requestFullscreen) {
          docElm.requestFullscreen();
        } else if (docElm.mozRequestFullScreen) {
          docElm.mozRequestFullScreen();
        } else if (docElm.webkitRequestFullScreen) {
          docElm.webkitRequestFullScreen();
        } else if (docElm.msRequestFullscreen) {
          docElm.msRequestFullscreen();
        }
        break;
      case 2:
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
        break;
    }
    
    return new Promise(function(res, rej){
      res("返回值");
    });
  }
  
  /**
   * 系统公告
   */
  $(document).on("click", "#notice", noticeFun);
  !function () {
    let notice = sessionStorage.getItem("notice");
    if (notice != "true") {
      //noticeFun();
    }
  }();
  
  function noticeFun() {
    var srcWidth = okUtils.getBodyWidth();
    layer.open({
      type: 0,
      title: "系统公告",
      btn: "我知道啦",
      btnAlign: 'c',
      content: "此处为系统通知类容",
      yes: function(index){
        if (srcWidth > 800) {
          layer.tips('系统公告', '#notice', {
            tips: [1, '#000'],
            time: 2000
          });
        }
        sessionStorage.setItem("notice", "true");
        layer.close(index);
      },
      cancel: function(index){
        if (srcWidth > 800) {
          layer.tips('系统公告', '#notice', {
            tips: [1, '#000'],
            time: 2000
          });
        }
      }
    });
  }
  
  /**
   * 捐赠作者
   */
  $(".layui-footer button.donate").click(function () {
    layer.tab({
      area: ["330px", "350px"],
      tab: [{
        title: "支付宝",
        content: "<img src='/static/img/支付宝.jpg' width='200' height='300' style='margin-left: 60px'>"
      }, {
        title: "微信",
        content: "<img src='/static/img/微信pay.jpg' width='200' height='300' style='margin-left: 60px'>"
      }]
    });
  });
  
  /**
   * QQ群交流
   */
  $("body").on("click", ".layui-footer button.communication,#noticeQQ", function () {
    layer.tab({
      area: ["330px", "350px"],
      tab: [{
        title: "QQ",
        content: "<img src='/static/img/QQ.jpg' width='200' height='300' style='margin-left: 60px'>"
      }]
    });
  });
  
  /**
   * 退出操作
   */
  $("#logout").click(function () {
    layer.confirm("确定要退出吗？", {skin: 'layui-layer-lan', icon: 3, title: '提示', anim: 6}, function () {
      window.location = "/system/exit";
    });
  });
  
  /**
   * 锁定账户
   */
  $("#lock").click(function () {
    layer.confirm("确定要锁定账户吗？", {skin: 'layui-layer-lan', icon: 4, title: '提示', anim: 1}, function (index) {
      layer.close(index);
      $(".yy").show();
      layer.prompt({
        btn: ['确定'],
        title: '输入密码解锁(123456)',
        closeBtn: 0,
        formType: 1
      }, function (value, index, elem) {
        if (value == "123456") {
          layer.close(index);
          $(".yy").hide();
        } else {
          layer.msg('密码错误', {anim: 6});
        }
      });
    });
  });

  // console.log("       _                     _       _       \n" +
  //     "      | |                   | |     (_)      \n" +
  //     "  ___ | |  _ _____ _____  __| |____  _ ____  \n" +
  //     " / _ \\| |_/ |_____|____ |/ _  |    \\| |  _ \\ \n" +
  //     "| |_| |  _ (      / ___ ( (_| | | | | | | | |\n" +
  //     " \\___/|_| \\_)     \\_____|\\____|_|_|_|_|_| |_|\n" +
  //     "                                             \n" +
  //     "版本：v2.0\n" +
  //     "作者：bobi\n" +
  //     "邮箱：bobi1234@foxmail.com\n" +
  //     "描述：一个很赞的，扁平化风格的，响应式布局的后台管理模版，旨为后端程序员减压！");
});
