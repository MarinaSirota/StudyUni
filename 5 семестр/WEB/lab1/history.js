let namesStl=["Главная","Обо мне","Интересы","Контакты","Тест","История","Фото","Учеба"];
function historyTable() {
    let table=document.getElementById('historyTable');
    table.style.border='1px solid black';
    for (let i = 0; i < 8; i++)
    { let tr=document.createElement('TR');
        tr.style.border='1px solid black';
        let td1=document.createElement('TD');
        td1.style.border='1px solid black';
        tr.appendChild(td1);
        td1.appendChild(document.createTextNode(namesStl[i]));
        let td2=document.createElement('TD');
        td2.style.border='1px solid black';
        tr.appendChild(td2);
        td2.appendChild(document.createTextNode(localStorage.startCount));
        table.appendChild(tr);
    }
}
//historyTable();
function visitS() {
    if (getCookie("counter")) {
        let count=getCookie("counter")+1;
        setCookie("counter",count,365);
        console.log("dfghjk");
    } else {
        setCookie("counter",1,365);
        console.log("11dfghjk");
    }

    console.log("Вы зашли на эту страницу "+getCookie("counter"));
}

function setCookie(name,value,days) {
    if (days) {
        let date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        let expires = "; expires="+date.toGMTString();
    }
    else let expires = "";

    document.cookie = name+"="+value+expires+"; path=/";
}

function getCookie(name) {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for(let i=0;i < ca.length;i++) {
        let c = ca[i];
        while (c.charAt(0)===' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function delCookie(name) {
    setCookie(name,"",-1);
}
visitS();