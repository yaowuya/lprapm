/*******************  选重行高亮显示  ***************************/

var c;
function fOver(obj) {
    c = obj.style.backgroundColor;
    obj.style.backgroundColor = "red";
}


function fOut(obj) {
    obj.style.backgroundColor = c;
}




/*********************** 弹出框   *****************************************/

function ShowModalDialog(url, width, height, scroll) {
    if (url == null)
        return;
    url = url + "&radom=" + Math.random();

    if (width == null) {
        width = window.screen.width - 12;
    }


    if (height == null) {
        height = window.screen.height - 20;
    }

    return window.showModalDialog(url, 'window', "dialogWidth:" + width + "px;dialogHeight:" + height + "px;scroll:" + scroll + ";status:no;resizable:no;address:no;");
}


/**********************  去空格 ******************************/

function trim(str) {
    return str.replace("/(^\s*)|{\s*$}/g", "");
}



/*************************校验函数***************/
var reg =
{
    naturalnum: /^\+?[0-9]*[1-9][0-9]*$/,   //正整数
    naturalnumf: /^[-]\d+$/,                 //负整数
    isint: /^-?\d+$/,                  //整数
    isfloat: /^(-?\d+)(\.\d+)?$/,        //浮点数
    isData: /^(-?\d+)(\.\d+)?$/,        //数字
    isfloatz: /^(\+?\d+)(\.\d+)?$/,       //正数
    isfloatf: /^([-]\d+)(\.\d+)?$/,       //负数
    //日期时间
    isdate: /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/,
    isQQ: /^[1-9]*[1-9][0-9]*$/, //qq号，数字
    //身份证
    isidcard: /((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}[X0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))/,
    isemail: /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/, //Email
    isphone: /^(\d{3,4}[-])?(\d{3,8})$/, //电话号码
    ismphone: /^[0]?(86)?1\d{10}$/, //手机号
    isTelephone: /(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|((([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{1,4}))?$)|(^0{0,1}13[0-9]{9}$)|(^0{0,1}15[0-9]{9}$)|(^0{0,1}18[0-9]{9}$)/, //(不带区号/带3,4位区号/带3,4位区号与1-4位分机号)电话/(号前带0/号前不带0)手机号码
    ispostcode: /^[1-9]\d{5}$/,         //邮政编码
    zmsz_: /^[_a-zA-Z0-9]+$/,      //只能输入字母数字和_
    zw: /^[\u4e00-\u9fa5]*$/,   //只能输入中文
    nozw: /^[^\u4e00-\u9fa5]*$/,   //不能输入中文
    zm: /^[_a-zA-Z]+$/,     //只能输入字母
    score: /^([1-9]{1}[0-9]{0,4})$|^0$/,  //少于五位的正数(包括0)
    point: /^([1-9]{1}[0-9]{0,8})$|^0$/,  //int型
    ipReg: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,  //IP
    isMoney: /^(\d+)(\.\d{0,2})?$/,  //验证金额
    isOKTest: /^([1-9]\d{0,}|0)$/  //验证大于等于零

    //添加结束

}
var msg =
{
    naturalnum: "请输入正整数",
    naturalnumf: "请输入负整数",
    isint: "请输入整数",
    isfloat: "请输入数字类型",
    isfloatz: "请输入正数",
    isfloatf: "请输入负数",
    isdate: "请输入格式如：1999-9-9的日期",
    isQQ: "请输入正确的QQ号",
    isidcard: "请输入正确的身份证号",
    isemail: "请输入正确的Email地址",
    isphone: "请输入格式如：028-865533443的电话号码",
    ismphone: "请输入正确的手机号码",
    isTelephone: "请输入格式为:区号（3-4位)-电话号(7-8位)-分机号(1-4位)的电话号码或手机号码",
    ispostcode: "请输入正确的邮政编码",
    zmsz_: "只能输入字母，数字或者_",
    zw: "只能输入中文",
    nozw: "不能输入中文",
    zm: "只能输入字母",
    score: "请输入正整数且不能超过5位",
    point: "请输入少于8位的正整数",
    isData: "请输入数字",
    ipReg: "请输入IP地址 如：192.168.2.2",
    isMoney: "请输入正确的金额",
    isOKTest: "请输入大于或等于零的数"
    //添加结束

}




//表单验证函数
//obj：对象名称。需要验证的输入对象
//type：要求输入的类型
//isback：是否允许为空.ture:允许 / false：不允许
//title：提示信息标题。如：传“姓名”提示信息变成"姓名:不能为空"。
function $valid(obj, type, isback, title) {
    if (isback) {
        if (isnull(obj)) {
            return true;
        }
    }
    else {
        if (isnull(obj) || !isNaN(obj)) {
            alert(title + "不能为空");
            obj.focus();
            return false;
        }
    }

    if (type == "") {
        return true;
    }

    var regs = null;
    var msgs = null;
    switch (type) {
        case "正整数":
            {
                regs = reg.naturalnum;
                msgs = msg.naturalnum;
                break;
            }
        case "负整数":
            {
                regs = reg.naturalnumf;
                msgs = msg.naturalnumf;
                break;
            }
        case "整数":
            {
                regs = reg.isint;
                msgs = msg.isint;
                break;
            }
        case "浮点数":
            {
                regs = reg.isfloat;
                msgs = msg.isfloat;
                break;
            }
        case "数字":
            {
                regs = reg.isData;
                msgs = msg.isData;
                break;
            }

        case "正数":
            {
                regs = reg.isfloatz;
                msgs = msg.isfloatz;
                break;
            }
        case "负数":
            {
                regs = reg.isfloatf;
                msgs = msg.isfloatf;
                break;
            }
        case "日期":
            {
                regs = reg.isdate;
                msgs = msg.isdate;
                break;
            }
        case "QQ":
            {
                regs = reg.isQQ;
                msgs = msg.isQQ;
                break;
            }
        case "身份证":
            {
                regs = reg.isidcard;
                msgs = msg.isidcard;
                break;
            }
        case "Email":
            {
                regs = reg.isemail;
                msgs = msg.isemail;
                break;
            }
        case "电话":
            {
                regs = reg.isphone;
                msgs = msg.isphone;
                break;
            }
        case "手机":
            {
                regs = reg.ismphone;
                msgs = msg.ismphone;
                break;
            }
        case "电话或手机号码":
            {
                regs = reg.isTelephone;
                msgs = msg.isTelephone;
                break;
            }
        case "邮编":
            {
                regs = reg.ispostcode;
                msgs = msg.ispostcode;
                break;
            }
        case "字母数字或_":
            {
                regs = reg.zmsz_;
                msgs = msg.zmsz_;
                break;
            }
        case "字母":
            {
                regs = reg.zmsz_;
                msgs = msg.zmsz_;
                break;
            }
        case "中文":
            {
                regs = reg.zw;
                msgs = msg.zw;
                break;
            }
        case "非中文":
            {
                regs = reg.nozw;
                msgs = msg.nozw;
                break;
            }
        case "分数":
            {
                regs = reg.score;
                msgs = msg.score;
                break;
            }
        case "积分":
            {
                regs = reg.point;
                msgs = msg.point;
                break;
            }

        case "IP地址":
            {
                regs = reg.ipReg;
                msgs = msg.ipReg;
                break;
            }
        case "金额":
            {
                regs = reg.isMoney;
                msgs = msg.isMoney;
                break;
            }
        case "大于或等于零":
            {
                regs = reg.isOKTest;
                msgs = msg.isOKTest;
                break;
            }

    }

    var s = obj.val();
    if (regs.test(s)) {
        return true;
    }
    else {

        if (title != null && title != undefined) {
            msgs = title + msgs
        }
        alert(msgs);
        obj.focus();
        return false;

    }
}

//是否为空
//为空返回true，否则返回false
function isnull(obj) {
    if (obj.val() == "") {
        return true;
    }
    else {
        return false;
    }
}



//树型结构的checkbox的点击事件 start
//checkbox的点击事件
function OnCheckEvent() {
    var objNode = event.srcElement;
    if (objNode.tagName != "INPUT" || objNode.type != "checkbox")
        return;

    //获取当前树结点
    var ch_ID = objNode.getAttribute("ID");
    var node_ID = ch_ID.substring(0, ch_ID.indexOf("CheckBox")) + "Nodes";
    var curTreeBode = document.getElementById(node_ID);

    //级联选择
    SetChildCheckBox(curTreeBode, objNode.checked);   //向下
    SetParentCheckBox(objNode);   //向上
}

//设置子结点的checkbox
function SetChildCheckBox(parentNode, checked) {
    if (parentNode == null)
        return;
    var childNodes = parentNode.children;   //获取子节点
    var count = childNodes.length;
    for (var i = 0; i < count; i++) {
        var tmpNode = childNodes[i];
        if (tmpNode.tagName == "INPUT" && tmpNode.type == "checkbox") {
            tmpNode.checked = checked;
        }
        SetChildCheckBox(tmpNode, checked);   //递归
    }
}

var childIDs = "";

//设置父节点的checkbox
function SetParentCheckBox(childNode) {
    if (childNode == null)
        return;
    var parent = childNode.parentNode;  //找到父节点
    if (parent == null || parent == "underfined")
        return;
    do {
        parent = parent.parentNode;
    }
    while (parent && parent.tagName != "DIV");
    if (parent == "underfined" || parent == null)
        return;

    var parentID = parent.getAttribute("ID");
    var objParent = document.getElementById(parentID);
    childIDs = "";

    GetChildIDArray(objParent);  //查找父节点的所有子节点集合

    //判断子结点状态
    childIDs = childIDs.substring(0, childIDs.length - 1);
    var arryChild = childIDs.split(":");
    var result = false;

    //当子结点的Checkbox状态有一个为true(选重),其父结点checkbox状态即为true,否则为false
    for (var i in arryChild) {
        var childCk = document.getElementById(arryChild[i]);
        if (childCk.checked) {
            result = true;
        }
    }

    parentID = parentID.replace("Nodes", "CheckBox");
    var parentCk = document.getElementById(parentID);
    if (parentCk == null)
        return;
    if (result)
        parentCk.checked = true;
    else
        parentCk.checked = false;

    SetParentCheckBox(parentCk);   //递归 
}

//获取子节点ID的数组
function GetChildIDArray(parentNode) {
    if (parentNode == null)
        return;
    var childNodes = parentNode.children;
    var count = childNodes.length;
    for (var i = 0; i < count; i++) {
        var tmpNode = childNodes[i];
        if (tmpNode.tagName == "INPUT" && tmpNode.type == "checkbox") {
            childIDs = tmpNode.id + ":" + childIDs;
        }
        GetChildIDArray(tmpNode);   //递归
    }
}
