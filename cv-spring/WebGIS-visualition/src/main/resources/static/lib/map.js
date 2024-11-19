var ServerUrl="http://183.161.30.119:6080/arcgis/rest/services/";

/**
 * 智图地图内容
 */
var normalm1 = L.tileLayer.chinaProvider('Geoq.Normal.Map', {
    maxZoom: 18,
    minZoom: 5
});
var normalm2 = L.tileLayer.chinaProvider('Geoq.Normal.Color', {
    maxZoom: 18,
    minZoom: 5
});
var normalm3 = L.tileLayer.chinaProvider('Geoq.Normal.PurplishBlue', {
    maxZoom: 18,
    minZoom: 5
});
var normalm4 = L.tileLayer.chinaProvider('Geoq.Normal.Gray', {
    maxZoom: 18,
    minZoom: 5
});
var normalm5 = L.tileLayer.chinaProvider('Geoq.Normal.Warm', {
    maxZoom: 18,
    minZoom: 5
});
var normalm6 = L.tileLayer.chinaProvider('Geoq.Normal.Cold', {
    maxZoom: 18,
    minZoom: 5
});
/**
 * 天地图内容
 */
var normalm = L.tileLayer.chinaProvider('TianDiTu.Normal.Map', {
        maxZoom: 18,
        minZoom: 5
    }),
    normala = L.tileLayer.chinaProvider('TianDiTu.Normal.Annotion', {
        maxZoom: 18,
        minZoom: 5
    }),
    imgm = L.tileLayer.chinaProvider('TianDiTu.Satellite.Map', {
        maxZoom: 18,
        minZoom: 5
    }),
    imga = L.tileLayer.chinaProvider('TianDiTu.Satellite.Annotion', {
        maxZoom: 18,
        minZoom: 5
    });
// 地形地图
var terrainMap = L.tileLayer.chinaProvider("TianDiTu.Terrain.Map", {});
var terrainAnn = L.tileLayer.chinaProvider(
    "TianDiTu.Terrain.Annotion",
    {}
);
var terrainGroup = L.layerGroup([terrainMap, terrainAnn]);
var normal = L.layerGroup([normalm, normala]),
    image = L.layerGroup([imgm, imga]);
/**
 * 谷歌
 */
var normalMap = L.tileLayer.chinaProvider('Google.Normal.Map', {
        maxZoom: 18,
        minZoom: 5
    }),
    satelliteMap = L.tileLayer.chinaProvider('Google.Satellite.Map', {
        maxZoom: 18,
        minZoom: 5
    });
/**
 * 高德地图
 */
var Gaode = L.tileLayer.chinaProvider('GaoDe.Normal.Map', {
    maxZoom: 18,
    minZoom: 5
});
var Gaodimgem = L.tileLayer.chinaProvider('GaoDe.Satellite.Map', {
    maxZoom: 18,
    minZoom: 5
});
var Gaodimga = L.tileLayer.chinaProvider('GaoDe.Satellite.Annotion', {
    maxZoom: 18,
    minZoom: 5
});
var Gaodimage = L.layerGroup([Gaodimgem, Gaodimga]);

var baseLayers = {
    "智图地图": normalm1,
    // "智图多彩": normalm2,
    "智图午夜蓝": normalm3,
    "智图灰色": normalm4,
    "智图暖色": normalm5,
    // "智图冷色": normalm6,
    "天地图": normal,
    "天地图影像": image,
    "天地图地形图": terrainGroup,
    "谷歌地图": normalMap,
    "谷歌影像": satelliteMap,
    //"高德地图": Gaode,
    "高德影像": Gaodimage
};

var map = L.map("map", {
    renderer: L.canvas(),
    center: [31.520, 118.587],
    zoom: 11,
    layers: [image],
    //zoomControl: false,//放大缩小控件
    attributionControl: false,//关闭右下角标识
    editable: true//允许编辑
    , fullscreenControl: {//地图全屏
        pseudoFullscreen: false
    }
});

//将图层组控件添加到地图中
L.control.layers(baseLayers, null).addTo(map);
//比例尺
L.control.scale({maxWidth: 100, metric: true, imperial: false}).addTo(map);
//鹰眼图
var miniMap = new L.Control.MiniMap(Gaode, {
    toggleDisplay: true,
    width: 150,
    height: 150,
    position: 'bottomleft'
}).addTo(map);
//鼠标经纬度
L.control.mousePosition({position: 'bottomright'}).addTo(map);

L.easyButton({
    states:[
        {
            icon: '<img src="/static/lib/leaflets/img/location.png">',
            title: '复原显示框',
            onClick: function(){
                map.flyTo([31.520, 118.587], 11);
            }
        }
    ]
}).addTo(map);

getBorder();


function getBorder() {
    $.ajax({
        type: "get",
        url: "/static/lib/border.json",
        dataType: "json",
        async: true,
        success: function (res) {
            for (var i in res.features) {
                L.geoJSON(res.features[i], {
                    style: function (feature) {
                        return {
                            color: '#FF0000',
                            fillOpacity: 0,
                            weight: 2
                        }
                    }
                }).addTo(map);
            }
        }
    })
}

function Legend(url, name, x, y) {
    url = url + "/legend?f=pjson";
    name = name || "图例";
    x = x || 0;
    y = y || 0;
    if ($(".legend_div") != null) {
        $(".legend_div").remove();
    }
    $.ajax({
        type: "post",
        cache: false,
        url: url,
        success: function (res) {
            res = eval("(" + res + ")");
            var htms = "<div class='Lenged_css layui-card-body'>";
            var Name = name;
            htms += "<h5>" + Name + "</h5>";
            var length = res.layers.length;
            for (var i = 0; i < length+x; i++) {
                for (var j = 0; j < res.layers[i].legend.length+y; j++) {
                    var image = "data:image/png;base64," + res.layers[i].legend[j].imageData;
                    var layerName;
                    if(res.layers[i].legend[j].label!=""){
                        layerName=res.layers[i].legend[j].label;
                    }else {
                        layerName=res.layers[i].layerName
                    }

                    htms += "<div><img src=" + image + " />" + layerName + "</div>"
                }
            }
            htms += "</div>";
            var legend = L.control({position: 'bottomright'});
            legend.onAdd = function () {
                var div = L.DomUtil.create('div', 'legend_div');
                div.innerHTML += htms;
                return div;
            };
            legend.addTo(map);
        }
    })
}

/*鼠标悬停高亮*/
function highlight(e) {
    var layer = e.target;
    layer.setStyle({
        weight: 6,
        color: '#fff',
        fillOpacity: 0.9,
        dashArray: '0'
    })
}

/*鼠标移除后复原地图样式*/
function resetHighlight(e) {
    var layer = e.target;
    layer.setStyle({
        color: '#FF0000',
        weight: 2,
        fillOpacity: 0.25
    })
}

/*地图点击后最大化*/
function zoomTo(e){
    map.fitBounds(e.target.getBounds());
}

// //获取单图层的图例
// function GetLegend(LengendUrl,name,x){
//     //console.log(x);
//     LengendUrl=LengendUrl+"/legend?f=pjson";
//     x=x||0;
//     name=name||'图例';
//     if ($(".legend div") != null)
//     {
//         $(".legend div").remove()
//     }
//     $.ajax({
//         type: "POST",
//         cache: false,
//         url: LengendUrl,
//         async: true,
//         success: function (data) {
//             //字符串转换为数组
//             data = eval("(" + data + ")");
//             var hmts = '<div class="Lenged Lenged_css">';
//             var Name = name;
//             hmts += "<h5>" +Name+"<h5>";
//             //添加图片注释
//             // console.log(x);
//             for (var i = 0; i <data.layers[x].legend.length; i++) {
//                 //图片
//                 var image = "data:image/png;base64," + data.layers[x].legend[i].imageData;
//                 //标注
//                 var label = data.layers[x].legend[i].label;
//
//                 hmts += "<div><img src=" + image + " height='20' width='20' /> " + label + "</div>"
//             }
//             hmts += '</div>';
//             // //定义控件
//             var legend = L.control({ position: 'bottomright' });
//             legend.onAdd = function (map) {
//                 var div = L.DomUtil.create('div', 'legend div');
//                 div.innerHTML += hmts;
//                 return div;
//             };
//             legend.addTo(map);
//         }
//     })
// }
//
// //获取多图层的图例
// function coverage_GetLegend(LengendUrl,name){
//     LengendUrl=LengendUrl+"/legend?f=pjson";
//     name=name||'施肥含量';
//     if ($(".legend div") != null)
//     {
//         $(".legend div").remove()
//     }
//     $.ajax({
//         type: "POST",
//         cache: false,
//         url: LengendUrl,
//         async: true,
//         success: function (data) {
//             //字符串转换为数组
//             data = eval("(" + data + ")");
//             console.data;
//             var hmts = '<div class="layui-card-body Lenged_css">';
//             var Name = name;
//             hmts += "<h5>" +Name+"<h5>";
//             //添加图片注释
//             var length=data.layers.length;
//             for(var j=0;j<length;j++) {
//                 for (var i = 0; i < data.layers[j].legend.length-1; i++) {
//                     //图片
//                     var image = "data:image/png;base64," + data.layers[j].legend[i].imageData;
//                     //标注
//                     var label = data.layers[j].legend[i].label;
//
//                     hmts += "<div><img src=" + image + " height='20' width='20' /> " + label + "</div>"
//                 }
//             }
//             hmts += '</div>';
//
//             //定义控件
//             var legend = L.control({ position: 'bottomright' });
//             legend.onAdd = function (map) {
//                 var div = L.DomUtil.create('div', 'legend div');
//                 div.innerHTML += hmts;
//                 return div;
//             };
//             legend.addTo(map);
//         }
//     })
// }