function clock() {
    let d = new Date();
    let month_num = d.getMonth()
    let day = d.getDate();
    let hours = d.getHours();
    let minutes = d.getMinutes();
    let seconds = d.getSeconds();
    let weekDay =d.getDay();

    let month=new Array("января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря");
    let week=new Array( "Воскресенье","Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота");
    if (day <= 9) day = "0" + day;
    if (hours <= 9) hours = "0" + hours;
    if (minutes <= 9) minutes = "0" + minutes;
    if (seconds <= 9) seconds = "0" + seconds;

    let date_time = "Сегодня - " + day + " " + month[month_num] + " " + d.getFullYear() +
        " г.&nbsp;&nbsp;&nbsp; Текущее время - "+ hours + ":" + minutes + ":" + seconds+" "+week[weekDay];
    if (document.layers) {
        document.layers.doc_time.document.write(date_time);
        document.layers.doc_time.document.close();
    }
    else document.getElementById("doc_time").innerHTML = date_time;
    setTimeout("clock()", 1000);

}
clock();