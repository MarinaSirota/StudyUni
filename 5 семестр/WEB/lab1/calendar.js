function getObj(objID)
{
    if (document.getElementById) {return document.getElementById(objID);}
    else if (document.all) {return document.all[objID];}
    else if (document.layers) {return document.layers[objID];}
}

function checkClick(e) {
    e?event=e:event=event;
    let CSE = event.target ? event.target : event.srcElement;
    if (CSE.tagName!=='SPAN')
        if (getObj('fc'))
            if (!isChild(CSE,getObj('fc')))
                getObj('fc').style.display='none';
}

function isChild(s,d) {
    while(s) {
        if (s===d)
            return true;
        s=s.parentNode;
    }
    return false;
}

function Left(obj)
{
    let curleft = 0;
    if (obj.offsetParent)
    {
        while (obj.offsetParent)
        {
            curleft += obj.offsetLeft
            obj = obj.offsetParent;
        }
    }
    else if (obj.x)
        curleft += obj.x;
    return curleft;
}

function Top(obj)
{
    let curtop = 0;
    if (obj.offsetParent)
    {
        while (obj.offsetParent)
        {
            curtop += obj.offsetTop
            obj = obj.offsetParent;
        }
    }
    else if (obj.y)
        curtop += obj.y;
    return curtop;
}

// Calendar script
let now = new Date;
let sccd=now.getDate();
let sccm=now.getMonth();
let sccy=now.getFullYear();
let ccm=now.getMonth();
let ccy=now.getFullYear();

// For current selected date
let selectedd, selectedm, selectedy;

document.write('<table id="fc" style="position:absolute;border-collapse:collapse;background:#FFFFFF;border:1px solid #91648b;display:none;-moz-user-select:none;-khtml-user-select:none;user-select:none;" cellpadding="2">');
document.write('<tr style="font:bold 13px Arial" onselectstart="return false"><td style="cursor:pointer;font-size:15px" onclick="upmonth(-1)">&lt;</td><td colspan="5" id="mns" align="center"></td><td align="right" style="cursor:pointer;font-size:15px" onclick="upmonth(1)">&gt;</td></tr>');
document.write('<tr style="background:#ffe4e1;font:12px Arial;color:rgba(0,0,0,0.99)"><td align=center>П</td><td align=center>В</td><td align=center>С</td><td align=center>Ч</td><td align=center>П</td><td align=center>С</td><td align=center>В</td></tr>');
for(let kk=1;kk<=6;kk++) {
    document.write('<tr>');
    for(let tt=1;tt<=7;tt++) {
       let num=7 * (kk-1) - (-tt);
        document.write('<td id="cv' + num + '" style="width:18px;height:18px">&nbsp;</td>');
    }
    document.write('</tr>');
}
document.write('<tr><td colspan="7" align="center" style="cursor:pointer;font:13px Arial;background:#ffe4e1" onclick="today()">Сегодня: '+firstZero(sccd,sccm+1,sccy)+'</td></tr>');
document.write('</table>');

document.all?document.attachEvent('onclick',checkClick):document.addEventListener('click',checkClick,false);


let updobj;

function lcs(ielem) {
    updobj=ielem;
    getObj('fc').style.left=Left(ielem)+'px';
    getObj('fc').style.top=Top(ielem)+ielem.offsetHeight+'px';
    getObj('fc').style.display='';

    // First check date is valid
    let curdt = ielem.value;
    let curdtarr = curdt.split('-');
    let isdt = true;
    for(let k=0;k<curdtarr.length;k++) {
        if (isNaN(curdtarr[k]))
            isdt=false;
    }
    if (isdt&&(curdtarr.length===3)) {
        ccm=curdtarr[1]-1;
        ccy=curdtarr[2];

        selectedd=parseInt ( curdtarr[0], 10 );
        selectedm=parseInt ( curdtarr[1]-1, 10 );
        selectedy=parseInt ( curdtarr[2], 10 );

        prepcalendar(curdtarr[0],curdtarr[1]-1,curdtarr[2]);
    }

}

function eventTgt(e){
    let el;
    if(e.target)el=e.target;
    else if(e.srcElement)el=e.srcElement;
    if(el.nodeType===3)el=el.parentNode; // defeat Safari bug
    return el;
}
function eventObj(e){if(!e)e=window.event;return e;}
function cs_over(e) {
    eventTgt(eventObj(e)).style.background='#ffe4e1';
}
function cs_out(e) {
    eventTgt(eventObj(e)).style.background='#FFFFFF';
}
function cs_click(e) {
    updobj.value=calvalarr[eventTgt(eventObj(e)).id.substring(2,eventTgt(eventObj(e)).id.length)];
    getObj('fc').style.display='none';
}

let mn = new Array('Январь','Февраль','Март','Апрель','Май','Июнь','Июль','Август','Сентрябрь','Октябрь','Ноябрь','Декабрь');
let mnn = new Array('31','28','31','30','31','30','31','31','30','31','30','31');
let mnl = new Array('31','29','31','30','31','30','31','31','30','31','30','31');
let calvalarr=new Array(42);

function bcGroundColor(obj) {
    obj.style.background='#FFFFFF';
    obj.style.font='10px Arial';
    obj.style.color='#333333';
    obj.style.textAlign='center';
    obj.style.textDecoration='none';
    obj.style.border='1px solid #91648b';//'1px solid #606060';
    obj.style.cursor='pointer';
}


// day selected
function prepcalendar(hd,cm,cy) {
    now=new Date();
    let sd = now.getDate();
    let td = new Date();
    td.setDate(1);
    td.setFullYear(cy);
    td.setMonth(cm);
    let cd = td.getDay();
    if (cd===0)cd=6; else cd--;
    getObj('mns').innerHTML=mn[cm]+'&nbsp;<span style="cursor:pointer" onclick="upmonth(-12)">&lt;</span>'+cy+'<span style="cursor:pointer" onclick="upmonth(12)">&gt;</span>';
    let marr = ((cy % 4) === 0) ? mnl : mnn;
    let cv;
    let htd;
    let dip;
    for (let d = 1; d <= 42; d++) {
        cv = getObj('cv' + parseInt(d));
        bcGroundColor(cv);
        if ((d >= (cd - (-1))) && (d <= cd - (-marr[cm]))) {
            dip = ((d - cd < sd) && (cm === sccm) && (cy === sccy));
            htd = ((hd !== '') && (d - cd === hd));

            cv.onmouseover = cs_over;
            cv.onmouseout = cs_out;
            cv.onclick = cs_click;

            // if today
            if (sccm === cm && sccd === (d - cd) && sccy === cy)
                cv.style.color = '#91648b';

            // if selected date
            if (cm === selectedm && cy === selectedy && selectedd === (d - cd)) {
                cv.style.background = '#ffe4e1';
                // when use style.background
                cv.onmouseout = null;
            }

            cv.innerHTML = d - cd;

            calvalarr[d] = firstZero(d - cd, cm - (-1), cy);
        }
        else {
            cv.innerHTML = '&nbsp;';
            cv.onmouseover = null;
            cv.onmouseout = null;
            cv.onclick = null;
            cv.style.cursor = 'default';
        }
    }
}

prepcalendar('',ccm,ccy);

function upmonth(s)
{
    marr=((ccy%4)===0)?mnl:mnn;

    ccm+=s;
    if (ccm>=12)
    {
        ccm-=12;
        ccy++;
    }
    else if(ccm<0)
    {
        ccm+=12;
        ccy--;
    }
    prepcalendar('',ccm,ccy);
}

function today() {
    updobj.value=firstZero(now.getDate(),now.getMonth()+1,now.getFullYear());
    getObj('fc').style.display='none';
    prepcalendar('',sccm,sccy);
}

function firstZero(d,m,y)
{
    var d0='',m0='';
    if (d<10)d0='0';
    if (m<10)m0='0';

    return ''+d0+d+'-'+m0+m+'-'+y;
}