
let photos=["belaus.jpg","hachapiri.jpg","sevas.JPG","sport2Size.jpg","geo2.jpg","geoSize.jpg",
    "vinogradnikiSize.jpg","mezhvodnoeSize.jpg","mamaSize.jpg","papa.jpg","romaSize.jpg","papahaSize.jpg"];
let titles=["Море","Грузинский ресторан","Севастополь","Спортзал","Грузия","Грузия",
    "Грузинские виноградники","Море","Мама","Папа","Парень","Грузинский ресторан"];
let photosBig=["belausSize.jpg","hachapiriSize.jpg","sevasSize.JPG","sport2Size.jpg","geo2.jpg","geoSize.jpg",
    "vinogradnikiSize.jpg","mezhvodnoeSize.jpg","mamaSize.jpg","papaSize.jpg","romaSize.jpg","papahaSize.jpg"];
function viewDiv(i){

    let el=document.getElementById('div'+i);

    if(!el) {return;} //!
    if(el.style.display==="block"){
        el.style.display="none";

    } else {
        el.style.display="block";

    }
}
function load_gallery() {
    let table=document.getElementById('photoTable')
    for (let i = 0; i < 3; i++)
    { let tr=document.createElement('TR');
        for(let j=0; j<4; j++)
        {
            let img = document.createElement('IMG');
            let td=document.createElement('TD');
            let id=i*4+j; //!
            img.src = photos[id]; //!
            img.title = titles[id]; //!
            img.width = '300';
            td.appendChild(img);
            img.addEventListener("click", function(){viewDivt(this.src);}); //!
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
}



load_gallery();
//load_div();

function viewDivt(src) {

    let bigImg= document.getElementById('divClass');
    bigImg.setAttribute('src',src);
    bigImg.setAttribute('width',"600");

    bigImg.addEventListener("click",
        function(){
        bigImg.setAttribute('src',"");
    }
    )
}
function load_div() {
    for (let i = 0; i < 12; i++)
    {
        let div = document.createElement('div');
        div.className = 'divClass';
        div.id='div'+i;
        div.addEventListener("click", function(){viewDivt(this.src);}); //!
        let img = document.createElement('IMG');
        img.src = photos[i];
        img.title = titles[i];
        img.width = '600';
        div.appendChild(img);
        document.body.appendChild(div);//!
    }
}