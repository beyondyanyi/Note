<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>基于高德地图HeatMap热力图</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://webapi.amap.com/maps?v=1.4.0&key=1580b3399d5c277290237c00d2e029bf"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <script type="text/javascript" src="http://a.amap.com/jsapi_demos/static/resource/heatmapData.js"></script>
</head>
<body>
<div id="container"></div>
<div class="button-group">
    <input type="button" class="button" value="显示热力图" onclick="heatmap.show()"/>
    <input type="button" class="button" value="关闭热力图" onclick="heatmap.hide()"/>
</div>
<script>
    var map = new AMap.Map("container", {
        resizeEnable: true,
        center: [114.311487,30.598327],
        zoom: 10,
        mapStyle: 'amap://styles/cce047d60091b6ce8d58b7b3251e22bd'//样式URL
    });
    /* map.setMapStyle("blue_night"); */
    if (!isSupportCanvas()) {
        alert('热力图仅对支持canvas的浏览器适用,您所使用的浏览器不能使用热力图功能,请换个浏览器试试~')
    }
    //详细的参数,可以查看heatmap.js的文档 http://www.patrick-wied.at/static/heatmapjs/docs.html
    //参数说明如下:
    /* visible 热力图是否显示,默认为true
     * opacity 热力图的透明度,分别对应heatmap.js的minOpacity和maxOpacity
     * radius 势力图的每个点的半径大小   
     * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
     *	{
     .2:'rgb(0, 255, 255)',
     .5:'rgb(0, 110, 255)',
     .8:'rgb(100, 0, 255)'
     }
     其中 key 表示插值的位置, 0-1 
     value 为颜色值 
     */
    var heatmap;
     //热力数据
   /*   var points =[
         {"lng":114.168428,"lat":30.61463,"count":200},
         {"lng":114.226495,"lat":30.561406,"count":300},
         {"lng":114.349526,"lat":30.507158,"count":400},
         {"lng":114.32538,"lat":30.384613,"count":500},
         {"lng":114.397819,"lat":30.64645,"count":600},
         {"lng":114.140257,"lat":30.628055,"count":700},
         {"lng":114.029873,"lat":30.590757,"count":900}
     ]; */
    map.plugin(["AMap.Heatmap"], function() {
        //初始化heatmap对象
        heatmap = new AMap.Heatmap(map, {
            radius: 30, //给定半径
            opacity: [0, 0.9]
            /*,gradient:{
             0.5: 'blue',
             0.65: 'rgb(117,211,248)',
             0.7: 'rgb(0, 255, 0)',
             0.9: '#ffea00',
             1.0: 'red'
             }*/
        });
        //设置数据集：
        heatmap.setDataSet({
            data: getDatas(),
            max: 100
        });
    });
    //判断浏览区是否支持canvas
    function isSupportCanvas() {
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }
    
    
    
    //生成测试数据
    var lngs=getRandoms(113.788984,114.990557,500);
    var lats=getRandoms(30.153549,31.081422,500);
    	//封装热力坐标  格式：[{"lng":114.168428,"lat":30.61463,"count":200},{"lng":114.226495,"lat":30.561406,"count":300},....]
    function getDatas(){
    	var datas=new Array();
    	for(var i=0;i<lngs.length;i++){
    		datas.push({"lng":lngs[i],"lat":lats[i],"count":(Math.random()*99+1)});
    	}
    	console.log(datas);
    	return datas;
    }
    	//随机生成范围内数字，size:数组长度，min、max起始范围最大范围
    function getRandoms(min,max,size){
    	var arrays=new Array();
    	for(var i=0;i<size;i++){
    		var num=Math.random()*(max-min)+min;	
    		arrays.push(num);
    	}
    	return arrays;
    }
</script>
</body>
</html>