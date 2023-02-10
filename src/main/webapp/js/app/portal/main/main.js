var MapLang, map, markers = [], imageSrc, imageSize, imageOption, markerImage, mapflag = false, infowindows =[], markerHome, infowindowHome;

$(document).ready(function(){
    callAjaxGet("/main/getMenu", "sucgetMenu", null, "fail", true);

    var mapContainer = document.getElementById('map'),
    mapOption = { 
        center: new kakao.maps.LatLng(35.86550033398335, 128.59340284624898),
        level: 3
    };

    map = new kakao.maps.Map(mapContainer, mapOption); 
    var mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    markerHome = new kakao.maps.Marker()
    infowindowHome = new kakao.maps.InfoWindow({zindex:1});

    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
        searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                if(mapflag) {
                    reloadMap();
                    mapflag = false;
                }
                
                if(!!result[0].road_address) {
                    var detailAddr = '<div>'
                    if (!!result[0].road_address.building_name) {
                        detailAddr += '[' + result[0].road_address.building_name + ']'
                    }
                    detailAddr += result[0].road_address.address_name + '</div>';
                } else {
                    var detailAddr = '<div>주소가 없는 위치 입니다.</div>';
                }
                
                var content = '<div class="bAddr" style="display: inline-flex;">' +
                                detailAddr + 
                                '<button type="button" class="btn-3d green" onclick="MapEatHome()" style="height: auto;">등록</button>'
                                '</div>';

                MapLang = mouseEvent.latLng;
                markerHome.setPosition(MapLang);
                markerHome.setMap(map);

                infowindowHome.setContent(content);
                infowindowHome.open(map, markerHome);

                $(".bAddr").css("background-color", "white");
                $(".bAddr").css("border", "1px solid");
                $(".bAddr").css("font-size", "12px");
                $("#map > div:first-child > div > div:last-child > div:last-child").css("border", "none");
                $("#map > div:first-child > div > div:last-child > div:last-child").css("background", "none");
            }   
        });
    });

    imageSrc = '/images/mapHome.png';
    imageSize = new kakao.maps.Size(75, 50);
    imageOption = {offset: new kakao.maps.Point(30 + ((map.getLevel() - 1) * 3.5), 20 + ((map.getLevel() - 1) * 7.75))};
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
    reloadMap();
});

function reloadMap() {
    callAjaxGet("/main/getMapHome", "SetMapMarker", null, "fail", true);
}

function MapEatHome() {
    SWQalert("가게 이름을 입력하여주세요.", 'PutEatHome')
}

function PutEatHome(res) {
    mapflag = true;
    var data = {'mapLng':MapLang.La,'mapLat':MapLang.Ma, "mapName":res.value}
    callAjaxPost("/main/putMapHome", "suc", data, "fail", true);
}

function SetMapMarker(res) {
    var data = res.data.MapHomeList;

    resetMarkers();
    for(var Info in data) {
        var markerPosition  = new kakao.maps.LatLng(data[Info].mapLat, data[Info].mapLng); 
        var marker = new kakao.maps.Marker({
            position: markerPosition,
            title: data[Info].mapName,
            image: markerImage
        });
    
        marker.setMap(map);
        markers.push(marker);

        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="padding:5px;">' + data[Info].mapName + '</div>'
        });

        infowindows.push(infowindow);
        kakao.maps.event.addListener(marker, 'mouseover', makeListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'click', makeListener(map, marker, infowindow));
    }
}

function resetMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];     
}

function resetInfowindows() {
    for (var i = 0; i < infowindows.length; i++) {
        infowindows[i].close();
    }  
}

function makeListener(map, marker, infowindow) {
    return function() {
        resetInfowindows();
        infowindow.open(map, marker);
    };
}

function SrchMap(value) {
    if (value != '') {
        var ps = new kakao.maps.services.Places();
        ps.keywordSearch(value, placesSearchCB);
    }
}

function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        if(mapflag) {
            reloadMap();
            mapflag = false;
        }
        
        if(!!data[0].road_address_name) {
            var detailAddr = '<div> [' + data[0].place_name + ']' + data[0].road_address_name  + '</div>';
        } else {
            var detailAddr = '<div>길가</div>';
        }
        
        var content = '<div class="bAddr" style="display: inline-flex;">' +
                        detailAddr + 
                        '<button class="btn-3d green" type="button" onclick="MapEatHome()">등록</button>'
                        '</div>';

        MapLang = new kakao.maps.LatLng(data[0].y, data[0].x);
        markerHome.setPosition(MapLang);
        markerHome.setMap(map);

        infowindowHome.setContent(content);
        infowindowHome.open(map, markerHome);

        map.panTo(new kakao.maps.LatLng(data[0].y, data[0].x))

        $(".bAddr").css("background-color", "white");
        $(".bAddr").css("border", "1px solid");
        $(".bAddr").css("font-size", "12px");
        $("#map > div:first-child > div > div:last-child > div:last-child").css("border", "none");
        $("#map > div:first-child > div > div:last-child > div:last-child").css("background", "none");
    }
}

function searchDetailAddrFromCoords(coords, callback) {
    var geocoder = new kakao.maps.services.Geocoder();
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

function fail(res) {
    if (res.responseJSON.code == 1) {
        SWalert('error', false , res.responseJSON.message);
    } else {
        SWalert('error', false , "오류가 발생하였습니다.");
    }
}

function suc(res) {
    if (res.message != "success") {
        SWalert('error', false , res.message);
    } else {
        SWalert('success', false , "정상적으로 처리되었습니다.");
    }
}

function sucgetMenu(res) {
    let Data = res.data;

    $("#menu-top-menu").empty();
    $("#menuListTmpl").tmpl(Data).appendTo("#menu-top-menu");
}

function setPage(name, path) {
    let Data = {
        'pageName' : name,
        'pagePath' : path
    }
    callAjaxhtml("/getMenuInPath", "sucgetMenuInPath", Data, "fail", true);
}

function sucgetMenuInPath(res) {
    $("#borad").html(res);
    ListOnReady();
}