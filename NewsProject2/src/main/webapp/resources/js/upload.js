function checkImageType(fileName){
    var pattern = /jpg|gif|png|jpeg/i;
    return fileName.match(pattern);
}

function getFileInfo(fullName){
    var fileName, imgsrc, getLink; 
    //fileName:경로나 UUID가 제외된 파일의 이름
    //imgsrc : 썸네일 혹은 파일 이미지의 경로
    //getLink : 화면에서 원본 파일을 볼 수 있는 링크
    //csrf 설정
    var csrfToken = $("meta[name='_csrf']").attr("content"); 
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content"); 
    //alert(fullName);
    var fileLink;
    if(checkImageType(fullName)){
        imgsrc="/displayFile?location=board&fileName="+fullName;
        fileLink = fullName.substr(14);
        
        var front = fullName.substr(0,12);// /2018/11/18
        var end = fullName.substr(14);// 3ebb5ffc-22f1-4c6b-bdf0-5bfea6e853ee_0003140383_001_20180109151316505.jpg
        
        getLink = "/displayFile?location=board&fileName="+ front + "s_" +end;
        //console.log("imgsrc : "+imgsrc + "\n" + "fileLink : " + fileLink + "\n" + "fullName :" + fullName + "\n" )
    }else{
        imgsrc = "/resources/dist/img/file.png";
        fileLink = fullName.substr(12);
        getLink = "/displayFile?location=board&fileName="+fullName;
    }
    fileName = fileLink.substr(fileLink.indexOf("_")+1);
    //alert(getLink);
    return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName, csrf:{"paramterName":csrfParameter, "headerName": csrfHeader, "token" : csrfToken}};
    //정보들을 javascript객체로 생성해서 반환 -> insert.jsp에서 템플릿을 이용해 표시
}