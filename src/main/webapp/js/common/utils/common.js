$(document).ready(function() {
    if (window.location.host != 'xn--2z2b48uhnl.xn--h32bi4v.xn--3e0b707e') {
        location.href = 'http://한망울.메인.한국'
    }
});

var consoleLog = false; // console 출력 여부

/**
 * header.jsp 에 hidden 속성으로 contextPath를 먼저 지정한다. <input type="hidden"
 * id="contextPath" value="<%=request.getContextPath()%>">
 * 
 * @param url
 * @returns {contextPath}/url
 */
function makeApiUrl(url) {
    return $("#contextPath").val() + url;
}

function goPage(url) {
    // loadingWindow();
    location.href = makeApiUrl(url);
}

function requestList(pageNo, url) {
    document.frm.pageIndex.value = pageNo;
    document.frm.action = makeApiUrl(url);
    document.frm.submit();
}

/**
 * enterKey 입력 시 페이지 호출
 * 
 * @param event
 * @param url
 */
function pressSearchKey(event, url) {
    if (event.keyCode === 13) {
        requestList('1', url);
    }
}

/**
 * GET 방식 ajax 호출
 * 
 * @param url
 * @param callbackFunc
 * @param data
 * @param callbackFailFunc
 * @param ajaxAsync
 *            (default = true)
 */
function callAjaxGet(url, callbackFunc, data, callbackFailFunc, ajaxAsync, loading) {
    if (ajaxAsync === undefined) {
        ajaxAsync = true;
    }

    if (loading === undefined) {
        loading = true;
    }

    let transferData = getTransferData(data);
    
    url = makeApiUrl(url);
    if (consoleLog) {
        console.log(url);
    }
    
    $.ajax({
        type : "get",
        async : ajaxAsync,
        url : url,
        data : transferData,
        dataType : "json",
        cache : false,
        xhrFields : {
            withCredentials : true
        },
        crossDomain : true,
        beforeSend : function(jqXHR) {
            if (loading) {
                loadingWindow();
            }
        },
        complete : function() {
            if (loading) {
                loadingWindowClose();
            }
        },
        success : function(data, textStatus, jqXHR) {
            if(data.success) {
                if (consoleLog) {
                    console.log("Success=====S");
                    logView(data);
                    console.log("Success=====E");
                }
                if (isFunction(callbackFunc)) {
                    window[callbackFunc](data);
                    SetDg();
                }
            } else {
                if (consoleLog) {
                    console.log("error=====S");
                    logView(jqXHR);
                    console.log("error=====E");
                }
                if (isFunction(callbackFailFunc)) {
                    window[callbackFailFunc](jqXHR);
                } else {
                    handleErrorStatus(jqXHR);
                }
            }
        },
        error : function(jqXHR, textStatus, errorThrown) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });
}

/**
 * POST 방식 ajax 호출
 * 
 * @param url
 * @param callbackFunc
 * @param data
 * @param callbackFailFunc
 * @param ajaxAsync (
 *            default = true )
 */
function callAjaxPost(url, callbackFunc, data, callbackFailFunc, ajaxAsync, loading) {
    if (ajaxAsync === undefined) {
        ajaxAsync = true;
    }

    if (loading === undefined) {
        loading = true;
    }

    let transferData = getTransferData(data);
    url = makeApiUrl(url);
    if (consoleLog) {
        console.log("loading :" + loading);
        console.log(transferData);
        console.log(url);
    }
    
    $.ajax({
        type : "post",
        async : ajaxAsync,
        url : url,
        data : transferData,
        dataType : "json",
        cache : false,
        xhrFields : {
            withCredentials : true
        },
        crossDomain : true,
        beforeSend : function(jqXHR) {
            if (loading) {
                loadingWindow();
            }
            jqXHR.setRequestHeader("AJAX", "true");
        },
        complete : function() {
            if (loading) {
                loadingWindowClose();
            }
        },
        success : function(data, textStatus, jqXHR) {
            if(data.success) {
                if (consoleLog) {
                    console.log("Success=====S");
                    logView(data);
                    console.log("Success=====E");
                }
                if (isFunction(callbackFunc)) {
                    window[callbackFunc](data);
                    SetDg();
                }
            } else {
                if (consoleLog) {
                    console.log("error=====S");
                    logView(jqXHR);
                    console.log("error=====E");
                }
                if (isFunction(callbackFailFunc)) {
                    window[callbackFailFunc](jqXHR);
                } else {
                    handleErrorStatus(jqXHR);
                }
            }
        },
        error : function(jqXHR, textStatus, errorThrown) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR.responseJSON);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });
}

function callAjaxhtml(url, callbackFunc, data, callbackFailFunc, ajaxAsync, loading) {
    if (ajaxAsync === undefined) {
        ajaxAsync = true;
    }

    if (loading === undefined) {
        loading = true;
    }

    let transferData = getTransferData(data);
    
    url = makeApiUrl(url);
    if (consoleLog) {
        console.log(url);
        console.log("loading :" + loading);
        console.log(transferData);
    }
    
    $.ajax({
        type : "post",
        async : ajaxAsync,
        url : url,
        data : transferData,
        dataType : "html",
        cache : false,
        xhrFields : {
            withCredentials : true
        },
        crossDomain : true,
        beforeSend : function(jqXHR) {
            if (loading) {
                loadingWindow();
            }
            jqXHR.setRequestHeader("AJAX", "true");
        },
        complete : function() {
            if (loading) {
                loadingWindowClose();
            }
        },
        success : function(data, textStatus, jqXHR) {
            if (consoleLog) {
                console.log("Success=====S");
                logView(data);
                console.log("Success=====E");
            }
            if (isFunction(callbackFunc)) {
                window[callbackFunc](data);
                SetDg();
            }
        },
        error : function(jqXHR, textStatus, errorThrown) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR.responseJSON);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });
}

/**
 * multipart ajax call
 * 
 * @param url
 * @param callbackFunc
 * @param formId
 * @param callbackFailFunc
 */
function callByMultipart(url, callbackFunc, formId, callbackFailFunc, loading) {

    if (loading === undefined) {
        loading = true;
    }

    if (consoleLog) {
        console.log(url);
    }
    url = makeApiUrl(url);

    $("#" + formId).ajaxForm({
        xhrFields : {
            withCredentials : true
        },
        crossDomain : true,
        processData : false,
        dataType : 'json',
        beforeSend : function(jqXHR) {
            if (loading) {
                loadingWindow();
            }
            jqXHR.setRequestHeader("AJAX", "true");
        },
        xhr : function() {
            var xhr = $.ajaxSettings.xhr();
            if ($(".progressBar:visible").length > 0) {
                xhr.upload.onprogress = function(e) {
                    var percent = e.loaded * 100 / e.total;
                    $(".progressBar:visible").val(percent); // 개별 파일의 프로그레스바 진행
                };
            }
            return xhr;
        },
        complete : function(jqXHR, textStatus) {
            if (loading) {
                loadingWindowClose();
            }
        },
        success : function(data, textStatus, jqXHR) {
            if(data.success) {
                if (consoleLog) {
                    console.log("Success=====S");
                    logView(data);
                    console.log("Success=====E");
                }
                if (isFunction(callbackFunc)) {
                    window[callbackFunc](data);
                    SetDg();
                }
            } else {
                if (consoleLog) {
                    console.log("error=====S");
                    logView(jqXHR);
                    console.log("error=====E");
                }
                if (isFunction(callbackFailFunc)) {
                    window[callbackFailFunc](jqXHR);
                } else {
                    handleErrorStatus(jqXHR);
                }
            }
        },
        error : function(jqXHR, textStatus) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR.responseJSON);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });

    $("#" + formId).attr("action", url);
    $("#" + formId).submit();
}

function callByMultipartFormData(url, callbackFunc, data, callbackFailFunc, ajaxAsync, loading) {
    if (ajaxAsync === undefined) {
        ajaxAsync = true;
    }
    
    if (loading === undefined) {
        loading = true;
    }

    url = makeApiUrl(url);
    if (consoleLog) {
        console.log(url);
    }
    
    $.ajax({
        type : "post",
        async : ajaxAsync,
        url : url,
        data : data,
        dataType : "json",
        cache : false,
        xhrFields : {
            withCredentials : true
        },
        beforeSend : function(jqXHR) {
            if (loading) {
                loadingWindow();
            }
        },
        crossDomain : true,
        processData : false,
        contentType : false,
        complete : function() {
        	loadingWindowClose();
        },
        success : function(data, textStatus, jqXHR) {
            if(data.success) {
                if (consoleLog) {
                    console.log("Success=====S");
                    logView(data);
                    console.log("Success=====E");
                }
                if (isFunction(callbackFunc)) {
                    window[callbackFunc](data);
                    SetDg();
                }
            } else {
                if (consoleLog) {
                    console.log("error=====S");
                    logView(jqXHR);
                    console.log("error=====E");
                }
                if (isFunction(callbackFailFunc)) {
                    window[callbackFailFunc](jqXHR);
                } else {
                    handleErrorStatus(jqXHR);
                }
            }
        },
        error : function(jqXHR, textStatus, errorThrown) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR.responseJSON);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });
}

// 파일 다운로드
function callFileAjaxPost(url, data, loading, encode) {
    
    if(loading === undefined) {
        loading = true;
    }
    
    if (loading) {
        loadingWindow();
    }
    
    var frm = document.getElementById(data);
    var formData = new FormData(frm);

    let transferData = formData;
    url = makeApiUrl(url);
    
    if(consoleLog){
        console.log(url);
        console.log(transferData);
    }
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (this.readyState == 4) {
            if (this.status == 200){
                var fileName = xhr.getResponseHeader('content-disposition').split('filename=')[1].split(';')[0];

                if (encode == 'atob') {
                    fileName = window.atob(fileName);
                } else {
                    fileName = decodeURI(fileName);
                }

                if(consoleLog){
                    console.log(this.response, typeof this.response);
                }
                var a = document.createElement("a");
                var url = URL.createObjectURL(this.response)
                a.href = url;
                a.download = fileName;
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            }

            if (loading) {
                loadingWindowClose();
            }
        }
    }
    xhr.open('POST', url);
    xhr.responseType = 'blob';
    xhr.send(formData);
}

function getTransferData(data) {
    var transferData = '';
    if (typeof (data) === 'object') {
        transferData = data;
    } else if (typeof (data) === 'string') {
        transferData = data !== "" ? $("#" + data).serialize() : "";
    }
    return transferData;
}

function logView(data) {
    console.log(JSON.stringify(data, null, 4));
}

function isFunction(functionName) {
    return (typeof window[functionName] === "function");
}

function handleErrorStatus(jqXHR, forbidden) {
    if (jqXHR.status === 401) {
        alert("세션이 만료되었습니다. 다시 로그인을 해주세요.");
        // document.location.href = makeApiUrl("/");
    } else if (jqXHR.status === 403) {
        // console.log(JSON.stringify(jqXHR.responseText,null,4));

    } else if (jqXHR.status === 404) {
        // console.log(JSON.stringify(jqXHR.responseText,null,4));
    } else if (jqXHR.status === 400) {
        // console.log(JSON.stringify(jqXHR.responseText,null,4));
    } else if (jqXHR.status === 405) {

    } else if (jqXHR.status === 500) {
        // console.log(JSON.stringify(jqXHR.responseText,null,4));
    } else {
        // console.log(JSON.stringify(jqXHR.responseText,null,4));
    }
}

/**
 * 전체 replace
 * 
 * @param str
 * @param searchStr
 * @param replaceStr
 * @returns {*}
 */
function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

/**
 * input 필드에 오직 숫자만 입력 사용방법 : oninput="this.value = onlyNumber(this.value);"
 * 
 * @param value
 * @returns {*}
 */
function onlyNumber(value) {
    var tempValue = value.replace(/,/gi, "");
    return String(tempValue).replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
}
// 영문만
function onlyEnglish(value) {
    return value.replace(/[^a-zA-Z]/g, '').replace(/(\..*)\./g, '$1');
}
// 한글만
function onlyKorea(value) {
    return value.replace(/([^가-힣ㄱ-ㅎㅏ-ㅣ\x20])/g, '').replace(/(\..*)\./g, '$1');
}

// 영문만 제외
function exceptEnglish(value) {
    return value.replace(/[a-zA-Z]/g, '').replace(/(\..*)\./g, '$1');
}
// 한글만 제외
function exceptKorea(value) {
    return value.replace(/([가-힣ㄱ-ㅎㅏ-ㅣ\x20])/g, '').replace(/(\..*)\./g, '$1');
}

// 천단위 콤마
function moneyComma(value) {
    return String(value).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 이메일 정규식 확인
function emailCheck(value) {
    let result = true;

    var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    if (regEmail.test(value)) {
        result = false;
    }

    return result;
}

// 전화번호 정규식 확인
function phoneCheck(value) {
    let result = true;

    var regPhone = /^(01[0|1|6|7|8|9]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
    if (regPhone.test(value)) {
        result = false;
    }

    return result;
}

function insertComma(value) {
    return onlyNumber(value).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    // return onlyNumber(value).toLocaleString('ko-KR');
}
/*
 * function insertComma(value) { return onlyNumber(value).replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,
 * ","); // return onlyNumber(value).toLocaleString('ko-KR'); }
 */

// 검색 초기화
function searchInit() {
    $("form").find('input[type=text]').each(function() {
        $(this).val('');
    });
    $('form select').each(function() {
        $(this).find('option:eq(0)').prop("selected", true);
    });
}

/**
 * escape
 * 
 * @param str
 * @returns {*}
 */
function strConv(str) {
    str = str.replace(/&lt;/gi, "<");
    str = str.replace(/&gt;/gi, ">");
    str = str.replace(/&quot;/gi, "\"");
    // str = str.replace(/&nbsp;/gi," ");
    str = str.replace(/&amp;/gi, "&");
    str = str.replace(/&amp;#034;/gi, "\"");
    str = str.replace(/&#034;/gi, "\"");
    return str;
}

/**
 * checkbox 전체 선택
 * 
 * @param targetName
 *            target checkbox name
 */
function checkAllCheckbox(targetName) {
    const checkboxes = document.getElementsByName(targetName);
    let isChecked = false;
    
    for (let i = 0, n = checkboxes.length; i < n; i++) {
        if (checkboxes[i].disabled) {
            continue;
        }
        if (!checkboxes[i].checked) {
            isChecked = true;
            break;
        }
    }
    
    for (let i = 0, n = checkboxes.length; i < n; i++) {
        if (checkboxes[i].disabled) {
            continue;
        }
        checkboxes[i].checked = isChecked;
    }
}

/**
 * target checkbox의 상태를 확인하여 main checkbox 값을 변경
 * 
 * @param targetName
 *            target checkbox name
 * @param mainCheckerID
 *            전체 선택 기능을 가진 checkbox id
 */
function modifyAllCheckerStatus(targetName, mainCheckerID) {
    const checkboxes = document.getElementsByName(targetName);
    let isAllSelected = true;

    for (let i = 0; i < checkboxes.length; i++) {
        if (!checkboxes[i].checked) {
            isAllSelected = false;
        }
    }
    document.getElementById(mainCheckerID).checked = isAllSelected;
}

/**
 * 형제 태그
 * 
 * @param t
 * @returns {*[]}
 */
function siblings(t) {
    let children = t.parentElement.children;
    let tempArr = [];

    for (let i = 0; i < children.length; i++) {
        tempArr.push(children[i]);
    }

    return tempArr.filter(function(e) {
        return e != t;
    });
}

/**
 * 형제 태그 중 특정 태그 찾기
 * 
 * @param obj
 * @returns {any}
 * @param info
 *            입력정보 : tagName(대문자), type, id, name
 * @example info {"tagName":"INPUT", "type":"text"}
 */
function findTag(obj, info) {
    let keys = Object.keys(info);
    return obj.find(function(element) {
        let count = 0;
        for (let i = 0; i < keys.length; i++) {
            if (element[keys[i]] === info[keys[i]]) {
                count++;
            }
        }
        return count === keys.length;
    });
}

/**
 * tag 삭제
 * 
 * @param obj
 */
function deleteTag(obj) {
    obj.parentElement.removeChild(obj);
}

/**
 * 파일 확장자
 * 
 * @returns {string}
 * @param fileObj
 */
function getFileExt(fileObj) {
    // return fileObj.split('.').pop().toLowerCase();
    return getFileInfo(fileObj, 'ext');
}

/**
 * 파일 이름
 * 
 * @param fileObj
 * @returns {*|string|string}
 */
function getFileName(fileObj) {
    return getFileInfo(fileObj, 'name');
}

/**
 * 파일 이름.확장자
 * 
 * @param fileObj
 * @returns {*|string|string}
 */
function getFileNameAndExt(fileObj) {
    return getFileInfo(fileObj, 'all');
}

function getFileInfo(fileObj, flag) {
    let pathHeader = fileObj.lastIndexOf('\\');
    let pathMiddle = fileObj.lastIndexOf('.');
    let pathEnd = fileObj.length;
    let fileName = fileObj.substring(pathHeader + 1, pathMiddle);
    let extName = fileObj.substring(pathMiddle + 1, pathEnd);
    if (flag === 'ext') {
        return extName;
    } else if (flag === 'name') {
        return fileName;
    } else if (flag === 'all') {
        return fileName + '.' + extName;
    }
    return '';
}

function fileDownload(atchFileId, fileSn) {
    window.location.href = makeApiUrl('/api/files/download?atchFileId=' + atchFileId + '&fileSn=' + fileSn);
}

/**
 * API 호출 시 데이터 전달 함수
 * 
 * @param {Object}
 *            id Form 아이디
 * @param {Object}
 *            name 파라메타 명
 * @param {Object}
 *            value 파라메타 값
 */
function formData(id, name, value) {
    var objMethod = document.createElement("input");
    objMethod.type = "hidden";
    objMethod.name = name;
    objMethod.value = value;
    document.getElementById(id).insertBefore(objMethod, null);
}

/**
 * API 호출 시 데이터 전달 Form 삭제 함수
 * 
 * @param {Object}
 *            id Form 아이디
 * @param {Object}
 *            name 파라메타 명
 */
function formDataDelete(id, name) {
    $("#" + id + " input[name=\"" + name + "\"]").remove();
}

/**
 * API 호출 시 데이터 전달 Form 전체 삭제 함수
 * 
 * @param {Object}
 *            id Form 아이디
 */
function formDataDeleteAll(id) {
    $("#" + id).children().remove();
}

/**
 * form 데이터 를 json object 로 변환하는 함수
 * 
 * @param formId
 * @returns {{}}
 */
function convertFormDataToJson(formId) {
    let data = {};
    let array = $('#' + formId).serializeArray()
    for (let i = 0; i < array.length; i++) {
        data[array[i].name] = array[i].value;
    }
    return data;
}

// setTimeout 저장될 변수
var ldtimeout = [];

function loadingWindow() {
    $(".loading_area").show();
    ldtimeout.push(setTimeout(loadingTimeout, 60000));
}

function loadingWindowClose() {
    $(".loading_area").fadeOut();

    for (let i = 0; i < ldtimeout.length; i++) {
        clearTimeout(ldtimeout[i]);
    }
}

function loadingTimeout() {
    loadingWindowClose();
}

// 선택된 div 영역 내에서 로딩 표시
function loadingWindowInner(selector, flg) {
    if(flg) {
        var h = $(selector).height();
        var w = $(selector).width();
        var pos = $(selector).position();

        var marginTop = parseInt($(selector).css("margin-top"));
        var marginLeft = parseInt($(selector).css("margin-left"));

        var css = {
            position:   "absolute",
            left:       pos.left + marginLeft,
            top:        pos.top + marginTop,
            height:     h + "px", 
            width:      w + "px",  
            lineHeight: h + "px", 
            textAlign:  "center",
            fontSize:   "60px",
            fontWeight: "bold",
            color:      "#aaa",
            //background: "#fff", 
            opacity:    0.8,
        };

        $(selector).after($('<div class="loading_area"><div class="loading_box"><img src="'+$("#contextPath").val()+'/images/loadingImg.svg"></div></div>').css(css));
    } else {
        $(selector + ' ~ .loading_area').remove();
    }
}

function runSubmit(url, formId, method) {
    if (method === undefined) {
        method = 'post';
    }

    loadingWindow();

    var frm = document.getElementById(formId);

    frm.method = method;
    frm.action = makeApiUrl(url);
    frm.submit();
}

// date 타입을 문자열로 변환 (date -> string)
// seperator : 날짜 사이 구분자 문자 지정(default : "-")
// timeFlag : time 형식도 표시
function convertDateToString(date, seperator, timeFlag) {
    var yyyy = date.getFullYear();
    var mm = (date.getMonth() < 9)? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
    var dd = (date.getDate() < 10)? "0" + date.getDate() : date.getDate();
    var hour = (date.getHours() < 10)? "0" + date.getHours() : date.getHours();
    var minute = (date.getMinutes() < 10)? "0" + date.getMinutes() : date.getMinutes();
    var seconds = (date.getSeconds() < 10)? "0" + date.getSeconds() : date.getSeconds();

    var seperator = (typeof seperator === "undefined")? "-" : seperator;
    if(timeFlag) {
        return yyyy + seperator + mm + seperator + dd + " " + hour + ":" + minute + ":" + seconds;
    } else {
        return yyyy + seperator + mm + seperator + dd;
    }
}

function validateTelNumber(str) {
    if (!str) {
        return false;
    }

    str = str.trim();
    var temp = str.split('-').join('');
    if (temp.length <= 0 || temp.length < 9 || temp.length > 11) {
        return false;
    }
    var regStr = /^(02)-?([1-9]{1}[0-9]{2,3})-?[0-9]{4}$|(0505|0[3-8]{1}[0-5]{1})-?([1-9]{1}[0-9]{2,3})-?[0-9]{4}$/;
    var result = regStr.test(str);

    if (!result) {
        regStr = /^(01[16789]{1})-?([1-9]{1}[0-9]{2,3})-?[0-9]{4}$|(010)-?([1-9]{1}[0-9]{3})-?[0-9]{4}$/;
        result = regStr.test(str);
    }

    return result;
}

// 전화번호 자동 '-' 입력
// <input type="text" oninput="autoHyphen(this);" maxlength="13">
function autoHypenPhone(str) {
    str = str
         .replace(/[^0-9]/g, '')
         //.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
        .replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/, `$1-$2-$3`);
    return str;
}

// 폴더에 있는 파일 그대로 다운
function fileDown(fileName) {
    goPage('/download?fileName=' + fileName);
    loadingWindowClose();
}

//object to url param (create by 이현기)
/*
{ pageIndex: 1, searchCnd1: 10}

result -> ?pageIndex=1&searchCnd1=10
*/
function convertParam(paramObj) {
	var param = [];
	
	for (var key in paramObj) {
		param.push(key + "=" + paramObj[key]);
	}
	
	return "?" + param.join("&")
}

//object to url param (create by 이현기) ?(물음표) 빼먹지 말고 넣어야 합니다.
/*
?pageIndex=1&searchCnd1=10

result -> { pageIndex: 1, searchCnd1: 10 }
*/

function convertParamReverse(paramStr) {
    var temp = paramStr.substr(paramStr.indexOf("?")+1);
    return JSON.parse('{"' + decodeURI(temp.replace(/&/g, "\",\"").replace(/=/g,"\":\"")) + '"}')
}

function convertParamReverseDecode(paramStr) {
    paramStr = decodeURIComponent(paramStr);
	var temp = paramStr.substr(paramStr.indexOf("?")+1);
	return JSON.parse('{"' + decodeURI(temp.replace(/&/g, "\",\"").replace(/=/g,"\":\"")) + '"}')
}

function removeReadonly(attribute) {
    $(attribute).removeClass('readonly');
    $(attribute).removeAttr('readonly');
}

function addReadonly(attribute) {
    $(attribute).addClass('readonly');
    $(attribute).attr('readonly', true);
}

function autoHypenBrno(str) {
    str = str
        .replace(/[^0-9]/g, '')
        .replace(/([0-9]{3})([0-9]{2})([0-9]{5})/, `$1-$2-$3`);
    if (str.length > 12) {
        return str.substring(0,12);
    }
    return str;
}

/**
 * fetch RestApi
 */

const Fetch = {
    send: function(fetchObj, cb) {
        return fetchObj
            .then(res => res.json())
            .then(cb)
            .catch(e => { console.log(e) });
    },
    get: function(url, cb, param) {
        if(param) {
            const queryString = '?' + Object.keys(param).map(key => `${key}=${param[key]}`).join('&');
            url += queryString;
        }
        return this.send(fetch(url), cb);
    },
    post: function(url, cb, param) {
        return this.send(fetch(url, {
            'method': 'post',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb);
    },
    put: function(url, cb, param) {
        return this.send(fetch(url, {
            'method': 'put',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb)
    },
    delete: function(url, cb) {
        return this.send(fetch(url, {
            'method': 'delete',
            'headers': { 'Content-Type': 'application/json' },
        }), cb);
    }
}

function SetDg() {
    $('.gnb > li').on('mouseover', function(){
        $(this).find('.lnb').stop().slideDown(300);
    });
    $('.gnb > li').on('mouseout', function(){
        $(this).find('.lnb').stop().slideUp(300);
    });
}